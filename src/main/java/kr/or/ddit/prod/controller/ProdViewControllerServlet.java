package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdViewControllerServlet extends HttpServlet{
	IProdService service = new ProdServiceImpl();
	
	@URIMapping("/prod/prodView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//a태그니까 doget
		String prod_id = req.getParameter("what");
		
		//넘어왔는지 검증
		if(StringUtils.isBlank(prod_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			//badrequest
			return null;
		}
		
		//비즈니스로직 사용 (readProd)
		ProdVO prod = new ProdVO();
		prod = service.readProd(prod_id);
		
		//scope로 공유
		req.setAttribute("prod", prod);
		//뷰설정
		return "prod/prodView";
	}
}
