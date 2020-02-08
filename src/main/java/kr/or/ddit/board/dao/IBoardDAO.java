package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardDAO {
	public int insertBoard(BoardVO board);//mybatis연동시 사용
	public int insertBoard(BoardVO board, SqlSession sqlSession); //트랜잭션 관리목적
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	public int selectBoardCount(PagingVO<BoardVO> pagingVO); //페이징처리위해
	public BoardVO selectBoard(int bo_no);
	public int incrementHit(int bo_no); //조회수 증가
	public int updateBoard(BoardVO board);
	public int updateBoard(BoardVO board, SqlSession sqlSession);
	public int deleteBoard(BoardVO board); //글을 절차적으로 지우면서 rowcnt까지 받아야함 
	public int incrementLike(int bo_no); //추천수 증가
}
