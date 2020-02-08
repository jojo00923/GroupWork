package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer
 * 
 */
public interface IProdDAO {
	public int insertProd(ProdVO prod); //리턴값 int ,  ibatis는 insert 메서드의 리턴값 : pk
	public ProdVO selectProd(String prod_id);//존재하지않으면 null
	public int selectProdCount(PagingVO pagingVO); //total record 조회
	public List<ProdVO> selectProdList(PagingVO pagingVO); //페이징 처리한다면 전체 레코드 수 조회하는 메서드 필요**
	public int updateProd(ProdVO prod);
	
}
