package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDaoImpl implements IProdDAO {
	
	//1.의존하는 객체 가져오기(mybatis api)
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); //싱글톤
	
	@Override
	public int insertProd(ProdVO prod) {
		//2.세션오픈
		//3.mapper 프록시 
		//4.메서드 호출
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);//commit
				){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.insertProd(prod);			
		}
		
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);			
		}
	}
	
	@Override
	public int selectProdCount(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
				IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
				return mapper.selectProdCount(pagingVO);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
				IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
				return mapper.selectProdList(pagingVO);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
				IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
				return mapper.updateProd(prod);
		}
	}

	
}
