package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public interface IAttatchDAO {
	/**
	 * insertAll사용
	 * @param board
	 * @return
	 */
	public int insertAttatch(BoardVO board); //boardVO안에 list<attatchVO>가 있음.
	public int insertAttatch(BoardVO board, SqlSession sqlSession);
	public AttatchVO selectAttatch(int att_no); //다운로드목적. 어느위치에 어떤이름인지 찾아야함
	/**
	 * 한번에 여러건의 첨부파일 메타 삭제
	 * @param board
	 * @return
	 */
	public int deleteAttatch(BoardVO board); 
	public int deleteAttatch(BoardVO board, SqlSession sqlSession); 
	
}
