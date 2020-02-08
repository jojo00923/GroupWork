package kr.or.ddit.buyer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImpl implements IOthersDAO {

	SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IOthersDAO mapper = sqlSession.getMapper(IOthersDAO.class);
			return mapper.selectLprodList();			
		}
		
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IOthersDAO mapper = sqlSession.getMapper(IOthersDAO.class);
			return mapper.selectBuyerList();			
		}
	}

}
