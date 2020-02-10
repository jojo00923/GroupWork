package kr.or.ddit.alba.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicAlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaDAOImpl implements IAlbaDAO {

	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	
	@Override
	public int selectAlbaCount(PagingVO<AlbaVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaCount(pagingVO);
		}
	}

	@Override
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlbaList(pagingVO);
		}
	}

	@Override
	public AlbaVO selectAlba(String al_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectAlba(al_id);
		}
	}

	@Override
	public int insertAlba(AlbaVO alba) {
		return 0;
	}

	@Override
	public int insertAlba(AlbaVO alba, SqlSession sqlSession) {
		IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.insertAlba(alba);
	}

	@Override
	public int updateAlba(AlbaVO alba) {
		return 0;
	}
	
	@Override
	public int updateAlba(AlbaVO alba, SqlSession sqlSession) {
		IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.updateAlba(alba);
	}

	@Override
	public int deleteAlba(AlbaVO alba) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.deleteAlba(alba);
		}
	}

	@Override
	public List<GradeVO> selectGrade() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectGrade();
		}
	}

	@Override
	public List<LicAlbaVO> selectLIC() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectLIC();
		}
	}

	@Override
	public HashMap<String, Object> selectView(LicAlbaVO licAlba) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.selectView(licAlba);
		}
	}

	@Override
	public LicAlbaVO selectLicense(LicAlbaVO licAlba) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
