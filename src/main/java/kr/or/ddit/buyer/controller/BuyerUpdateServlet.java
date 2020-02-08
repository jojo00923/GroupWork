package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class BuyerUpdateServlet {
	IBuyerService service = new BuyerServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl(); 
	
	private void addAttribute(HttpServletRequest req){
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	
	@URIMapping("/buyer/buyerUpdate.do")
	public String updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		String buyer_id = req.getParameter("what");
		if(StringUtils.isBlank(buyer_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		BuyerVO buyer = new BuyerVO();
		buyer = service.readBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		
		return "buyer/buyerForm";
		
	}
	
	
	@URIMapping(value="/buyer/buyerUpdate.do", method = HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, String> errors = new HashMap<String, String>();
		
		req.setAttribute("errors", errors); 
		boolean valid = validate(buyer, errors);
		String goPage =null;
		String message = null;
		ServiceResult result = null;
		if (valid) {
//			2. 로직 선택 service.createprod(prod)
					result = service.createBuyer(buyer);
//			3. 로직의 실행 결과에 따른 분기 페이지 선택
				switch (result) {
				case OK:
					goPage = "redirect:/buyer/buyerView.do?what="+buyer.getBuyer_id(); //id가 채워져있다.
					break;
				default: // FAIL
					message = "서버 오류, 잠시뒤 다시 .";
					goPage = "buyer/buyerForm";
					break;
				}
			} else {
				goPage = "buyer/buyerForm";
			}
		
		req.setAttribute("message", message);//request에 저장
		
		return goPage;
		
		
	}
	
	private boolean validate(BuyerVO buyer, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(buyer.getBuyer_name())){valid=false; errors.put("buyer_name","거래처이름 누락");}
		if(StringUtils.isBlank(buyer.getBuyer_lgu())){valid=false; errors.put("buyer_lgu","거래처 상품분류 누락");}
		if(StringUtils.isBlank(buyer.getBuyer_comtel())){valid=false; errors.put("buyer_comtel","회사번호 누락");}
		if(StringUtils.isBlank(buyer.getBuyer_fax())){valid=false; errors.put("buyer_fax","팩스번호 누락");}
		if(StringUtils.isBlank(buyer.getBuyer_mail())){valid=false; errors.put("buyer_mail","메일 누락");}
		
		return valid;
	}
		
		
	}
		
