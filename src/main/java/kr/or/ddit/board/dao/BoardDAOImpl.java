package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO {
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public int insertBoard(BoardVO board, SqlSession sqlSession) {
		IBoardDAO mapper= sqlSession.getMapper(IBoardDAO.class);
		return mapper.insertBoard(board);
	}
	
	@Override
	public int insertBoard(BoardVO board) {//쿼리문 바인딩목적, 얘는 아무것도안함
		return 0;
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IBoardDAO mapper= sqlSession.getMapper(IBoardDAO.class);
			return mapper.selectBoardList(pagingVO);
		}
	}

	@Override
	public int selectBoardCount(PagingVO<BoardVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IBoardDAO mapper= sqlSession.getMapper(IBoardDAO.class);
				return mapper.selectBoardCount(pagingVO);
			}
	}

	@Override
	public BoardVO selectBoard(int bo_no) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IBoardDAO mapper= sqlSession.getMapper(IBoardDAO.class);
				return mapper.selectBoard(bo_no);
			}
	}

	@Override
	public int incrementHit(int bo_no) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
			){
				IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
				return mapper.incrementHit(bo_no);			
			}
	}

	@Override
	public int updateBoard(BoardVO board, SqlSession sqlSession) {
			IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
			return mapper.updateBoard(board);			
	}
	
	@Override
	public int updateBoard(BoardVO board) {
		return 0;
	}

	@Override
	public int deleteBoard(BoardVO board) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
			){
				IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
				return mapper.deleteBoard(board);			
			}
	}

	@Override
	public int incrementLike(int bo_no) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
			){
				IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
				return mapper.incrementLike(bo_no);			
			}
	}


	

}
