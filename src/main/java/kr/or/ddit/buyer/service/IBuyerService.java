package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerService {
	
	public int readBuyerCount(PagingVO pagingVO);
	/**
	 * 거래처목록조회
	 * @param pagingVO TODO
	 * @return
	 */
	public List<BuyerVO> readBuyerList(PagingVO pagingVO);
	public BuyerVO readBuyer(String buyer_id);
	public ServiceResult createBuyer(BuyerVO buyerVO);
	public ServiceResult modifyBuyer(BuyerVO buyerVO);
}
