package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BuyerVO;

/**
 * 상품 검색에 사용할 persistence layer
 * 상품분류에 따른 검색, 거래처에 따른 검색
 */
public interface IOthersDAO {
	public List<Map<String, Object>> selectLprodList(); //컬럼이 두개뿐이라 vo대신 map사용
	public List<BuyerVO> selectBuyerList();
}
