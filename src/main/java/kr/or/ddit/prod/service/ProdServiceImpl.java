package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	IProdDAO prodDAO = new ProdDaoImpl(); //여기는 싱글톤 xx 추후 스프링에서 자체적으로 싱글톤으로 관리해준다.
			

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		int cnt = prodDAO.insertProd(prod);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
		
	}

	@Override
	public ProdVO readProd(String prod_id) {
		ProdVO prod = prodDAO.selectProd(prod_id);
		if(prod==null) {
			throw new CommonException(prod_id+"에 해당 상품이 없음."); //runtimeexception의 하위이기 때문에 저절로 throws가 된다.
		}
		return prod;
	}

	@Override
	public int readProdCount(PagingVO pagingVO) {
		return prodDAO.selectProdCount(pagingVO);
	}
	
	@Override
	public List<ProdVO> readProdList(PagingVO pagingVO) {
		return prodDAO.selectProdList(pagingVO);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = null;
		int cnt =  prodDAO.updateProd(prod);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	


}
