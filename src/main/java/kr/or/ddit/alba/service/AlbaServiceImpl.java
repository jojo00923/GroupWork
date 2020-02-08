package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {
	
	IAlbaDAO albaDAO = new AlbaDAOImpl();

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
		int cnt =  albaDAO.updateAlba(alba);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult createAlba(AlbaVO alba) {
		ServiceResult result = null;
		int cnt =  albaDAO.insertAlba(alba);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
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

	
}
