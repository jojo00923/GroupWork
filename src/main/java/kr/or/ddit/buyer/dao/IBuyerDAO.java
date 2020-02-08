package kr.or.ddit.buyer.dao;

import java.util.List;

import javax.naming.directory.SearchResult;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerDAO {
	public List<BuyerVO> selectBuyerList(PagingVO pagingVO);
	public int selectBuyerCount(PagingVO pagingVO);
	public BuyerVO selectBuyer(String buyer_id);
	public int insertBuyer(BuyerVO buyerVO);
	public int updateBuyer(BuyerVO buyerVO);
	public int deleteBuyer(String buyer_id);
}
