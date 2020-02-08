package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.buyer.dao.IOthersDAO;
import kr.or.ddit.buyer.dao.OthersDAOImpl;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class BuyerListServlet extends HttpServlet{
	IBuyerService service = new BuyerServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl();
	PagingVO<BuyerVO> pagingVO = new PagingVO<>();
	
	private void addAttribute(HttpServletRequest req){
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
		req.setAttribute("lprodList", othersDAO.selectLprodList());
	}
	
	@URIMapping("/buyer/buyerList.do")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		String pageParam = req.getParameter("page");
		BuyerVO searchDetail = new BuyerVO();
		String view = null;
		try {
			BeanUtils.populate(searchDetail, req.getParameterMap());//prod_lgu와 prod_buyer를 pagingVO에 담아서 보낸다.
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		
		int currentPage = 1; //1페이지로 설정
		if(StringUtils.isNumeric(pageParam)) {//숫자로만 구성되어있을 때
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5,3);
		pagingVO.setSearchDetail(searchDetail);
		
		pagingVO.setTotalRecord(service.readBuyerCount(pagingVO)); //검색키워드가 있을수도있으니깐 pagingVO를 파라미터로
		pagingVO.setCurrentPage(currentPage);
		
		List<BuyerVO> list = service.readBuyerList(pagingVO);
		pagingVO.setDataList(list);
		
		String accept = req.getHeader("accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getWriter(), pagingVO);
			
		}else {
			req.setAttribute("pagingVO", pagingVO);
			view = "buyer/buyerList";
		}
		
		return view;
	
	}
}
