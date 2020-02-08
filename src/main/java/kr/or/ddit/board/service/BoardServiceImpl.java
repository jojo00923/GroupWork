package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	// ★대부분 service 대상으로 트랜잭션 관리. SESSION OPEN을 DAO가 아닌 여기서 하고 세션을 DAO로 넘기는 구조★

	IBoardDAO boardDAO = new BoardDAOImpl();
	IAttatchDAO attatchDAO = new AttatchDAOImpl();
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	File saveFolder = new File("d:/saveFiles");
	
	private void processAttatchList(BoardVO board,SqlSession sqlSession) {
		// 첨부파일에 대한 처리
		List<AttatchVO> attatchList = board.getAttatchList();
		if (attatchList != null && attatchList.size() > 0) {
			// if(1==1) throw new RuntimeException("강제 발생 예외");
			// 원자성- 트랜잭션이 데이터베이스에 모두 반영되던가, 아니면 전혀 반영되지 않아야 한다는 것
			attatchDAO.insertAttatch(board, sqlSession);
			try {
				for(AttatchVO attatch : attatchList) {
					attatch.saveFile(saveFolder);
				}
			}catch(IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		try (
			SqlSession sqlSession = sqlSessionFactory.openSession(false);// 절대 truexxx
		) {
			int rowcnt = boardDAO.insertBoard(board, sqlSession);

			ServiceResult result = ServiceResult.FAIL;
			if (rowcnt > 0) {
				processAttatchList(board, sqlSession);
				result = ServiceResult.OK;
				sqlSession.commit(); // ★★★ open~commit까지 한 트랜잭션으로 묶임
			}
			return result;

		}

	}

	@Override
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public int readBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardCount(pagingVO);
	}

	@Override
	public BoardVO readBoard(int bo_no) {
		BoardVO board = boardDAO.selectBoard(bo_no);
		if (board == null) {
			throw new CommonException(bo_no + "에 해당하는 글이 없음");
		}
		return board;
	}

	@Override
	public void incrementHit(int bo_no) {
		boardDAO.incrementHit(bo_no);
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {//하나의 세션을 가지고 논다.
		BoardVO savedBoard = authenticate(board);
		if(savedBoard==null) return ServiceResult.INVALIDPASSWORD;
		ServiceResult result = null;
		//인증에성공함
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession(false);// 절대 truexxx
		) {
				result = ServiceResult.FAIL;
				int rowcnt = boardDAO.updateBoard(board, sqlSession);
				if (rowcnt > 0) {
					// 첨부파일에 대한 처리
					
					processAttatchList(board, sqlSession);
					deleteAttatchList(board, sqlSession, savedBoard);
					
					result = ServiceResult.OK;
					sqlSession.commit();
			}
			return result;
		}
	}

	/**
	 * 첨부파일 삭제
	 * @param board 삭제할 파일의 번호를 가진 VO
	 * @param sqlSession
	 * @param savedBoard 2진데이터 삭제를 위해 저장 파일명을 가진 BoardVO
	 */
	private void deleteAttatchList(BoardVO board, SqlSession sqlSession, BoardVO savedBoard) {
		
		int[] delAttNos = board.getDelAttNos();
		if(delAttNos!=null && delAttNos.length>0) {
			Arrays.sort(delAttNos); //이진탐색을 위해 정렬
			String[] filePaths = new String[delAttNos.length];
			List<AttatchVO> attList = savedBoard.getAttatchList();
			int idx = 0;
			for(AttatchVO delAttatch:attList) {
				if(Arrays.binarySearch(delAttNos,delAttatch.getAtt_no())>=0) { 
					//이진탐색. 인덱스로 돌아옴
					filePaths[idx++] = delAttatch.getAtt_savename(); //저장명 넣어줌
				}
			}
			
			//메타삭제(db)
			attatchDAO.deleteAttatch(board,sqlSession); 
			//2진삭제(savefiles)
			for(String filePath :filePaths) {
				File delFile = new File(saveFolder,filePath);
				delFile.delete(); 
			}
		}
	}

	
	
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		BoardVO savedBoard = authenticate(board);
		if(savedBoard==null) return ServiceResult.INVALIDPASSWORD;
		ServiceResult result = ServiceResult.FAIL;
		
		//자식 지우고 난 다음 부모 지우기
		boardDAO.deleteBoard(board);
		
		
		int rowcnt = board.getRowcnt();
		
		if (rowcnt > 0) {//삭제성공
			//이진데이터 지우기
			List<AttatchVO> attList = savedBoard.getAttatchList();
			if(attList!=null) {
				for(AttatchVO delAttatch:attList) {
					File delFile = new File(saveFolder, delAttatch.getAtt_savename());
					delFile.delete();
				}
			}
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult incrementLike(int bo_no) {
		int rowcnt = boardDAO.incrementLike(bo_no);
		ServiceResult result = ServiceResult.FAIL;
		if (rowcnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	private BoardVO authenticate(BoardVO board) {// 수정,삭제에서 공통으로 사용하기 때문에 따로 뺌
		BoardVO savedBoard = readBoard(board.getBo_no());
		if(savedBoard.getBo_pass().equals(board.getBo_pass())) {
			return savedBoard;
		}else {
			return null;
		}
	}

}
