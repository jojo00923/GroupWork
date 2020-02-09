package kr.or.ddit.alba.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.LicAlbaVO;

public class LicAlbaDAOImpl implements ILicAlbaDAO {

	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertLicAlba(LicAlbaVO licAlbaVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	
			){
				ILicAlbaDAO mapper = sqlSession.getMapper(ILicAlbaDAO.class);
				return mapper.insertLicAlba(licAlbaVO);
			}
	}

}
