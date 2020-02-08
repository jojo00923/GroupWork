package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO {
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<BuyerVO> selectBuyerList(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
				IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				return mapper.selectBuyerList(pagingVO);
		}
	}

	@Override
	public int selectBuyerCount(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
				IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				return mapper.selectBuyerCount(pagingVO);
		}
	}

	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
				IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				return mapper.selectBuyer(buyer_id);
		}
	}

	@Override
	public int insertBuyer(BuyerVO buyerVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
				IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				return mapper.insertBuyer(buyerVO);
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyerVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
				IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				return mapper.updateBuyer(buyerVO);
		}
	}

	@Override
	public int deleteBuyer(String buyer_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
				IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
				return mapper.deleteBuyer(buyer_id);
		}
	}

	
	
}
