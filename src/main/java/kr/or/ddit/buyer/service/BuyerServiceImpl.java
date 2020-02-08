package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class BuyerServiceImpl implements IBuyerService {
	IBuyerDAO dao = new BuyerDAOImpl();
	
	@Override
	public List<BuyerVO> readBuyerList(PagingVO pagingVO) {
		return dao.selectBuyerList(pagingVO);
	}

	@Override
	public int readBuyerCount(PagingVO pagingVO) {
		return dao.selectBuyerCount(pagingVO);
	}

	@Override
	public BuyerVO readBuyer(String buyer_id) {
		return dao.selectBuyer(buyer_id);
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyerVO) {
		ServiceResult result = null;
		int cnt = dao.insertBuyer(buyerVO);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyerVO) {
		ServiceResult result = null;
		int cnt = dao.updateBuyer(buyerVO);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
	

}
