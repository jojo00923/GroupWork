package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	public ServiceResult createBoard(BoardVO board);
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVO);
	public int readBoardCount(PagingVO<BoardVO> pagingVO); //detail
	public BoardVO readBoard(int bo_no); //글 삭제됐을 경우 common exception 활용 
	public void incrementHit(int bo_no); //ServiceResult로 할 수 있지만 큰 영향이 없어서 void로 함
	public ServiceResult modifyBoard(BoardVO board);//비번이 틀렸을 경우 , 성공, 실패, 글 삭제됐을 경우(common exception 활용) --> enum 
	public ServiceResult removeBoard(BoardVO board);//비번이 틀렸을 경우 , 성공, 실패, 글 삭제됐을 경우(common exception 활용) --> enum
	public ServiceResult incrementLike(int bo_no);
}
