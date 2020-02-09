package kr.or.ddit.alba.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AlbaVO;

public interface ILicAlbaDAO {

	public int insertLicAlba (AlbaVO alba);
	public int insertLicAlba (AlbaVO alba, SqlSession sqlSession);
	public int deleteLicAlba (AlbaVO alba);
	public int deleteLicAlba (AlbaVO alba, SqlSession sqlSession);
	
}
