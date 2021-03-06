package kr.or.ddit.alba.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicAlbaVO;
import kr.or.ddit.vo.PagingVO;

public interface IAlbaDAO {
	
	public int selectAlbaCount(PagingVO<AlbaVO> pagingVO);
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO);
	public AlbaVO selectAlba(String al_id);
	public int insertAlba(AlbaVO alba);
	public int insertAlba(AlbaVO alba, SqlSession sqlSession);
	public int updateAlba(AlbaVO alba);
	public int updateAlba(AlbaVO alba, SqlSession sqlSession);
	public int deleteAlba(AlbaVO alba);
	public int deleteAlba(AlbaVO alba, SqlSession sqlSession);
	public List<GradeVO> selectGrade(); //학력
	public List<LicAlbaVO> selectLIC(); //자격증
	public HashMap<String, Object> selectView(LicAlbaVO licAlba);
	public int deleteLicenses(AlbaVO albaVO, SqlSession sqlSession);	
}
