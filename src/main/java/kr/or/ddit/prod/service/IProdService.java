package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * Business Login Layer
 *
 */
public interface IProdService {
	public ServiceResult createProd(ProdVO prod); //성공,실패
	public ProdVO readProd(String prod_id); //존재하지 않을 경우 commonException발생
	public int readProdCount(PagingVO pagingVO);
	public List<ProdVO> readProdList(PagingVO pagingVO); //존재하지 않을 경우 size = 0 nullxxxxx
	public ServiceResult modifyProd(ProdVO prod); //성공,실패 - serviceresult 수정할 파일이 없을 경우 commonException발생
	
}
