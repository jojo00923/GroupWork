package kr.or.ddit.alba.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicAlbaVO;

public class LicAlbaDAOImpl implements ILicAlbaDAO {

	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertLicAlba(AlbaVO alba) {
		return 0;
	}

	@Override
	public int insertLicAlba(AlbaVO alba, SqlSession sqlSession) {
		ILicAlbaDAO mapper = sqlSession.getMapper(ILicAlbaDAO.class);
		return mapper.insertLicAlba(alba);
	}

	@Override
	public int deleteLicAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteLicAlba(AlbaVO alba, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		return 0;
	}

}
