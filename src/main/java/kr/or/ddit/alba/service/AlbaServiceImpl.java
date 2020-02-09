package kr.or.ddit.alba.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.dao.ILicAlbaDAO;
import kr.or.ddit.alba.dao.LicAlbaDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicAlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {
	
	IAlbaDAO albaDAO = new AlbaDAOImpl();
	ILicAlbaDAO licAlbaDAO = new LicAlbaDAOImpl();
	
	File saveFolder = new File("d:/saveFiles");
	
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaCount(pagingVO);
	}

	@Override
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaList(pagingVO);
	}

	@Override
	public AlbaVO readAlba(String al_id) {
		return albaDAO.selectAlba(al_id);
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO alba) {
		ServiceResult result = null;
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(false);	
		){
			result = ServiceResult.FAIL;
			int rowcnt = albaDAO.updateAlba(alba, sqlSession);
			if(rowcnt>0) {
				licAlbaDAO.insertLicAlba(alba, sqlSession);
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}

	@Override
	public ServiceResult createAlba(AlbaVO alba) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(false);	
		){
			int cnt =  albaDAO.insertAlba(alba, sqlSession);
			ServiceResult result = ServiceResult.FAIL;
			if(cnt>0) {
				licAlbaDAO.insertLicAlba(alba, sqlSession);
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}

	@Override
	public ServiceResult removeAlba(String al_id) {
		ServiceResult result = null;
		int cnt =  albaDAO.deleteAlba(al_id);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	

	@Override
	public HashMap<String, Object> readImage(LicAlbaVO licAlba) {
		return albaDAO.selectView(licAlba);
	}

	
}
