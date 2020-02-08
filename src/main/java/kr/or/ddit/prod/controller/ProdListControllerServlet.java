package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

//@WebServlet("/prod/prodList.do")
@CommandHandler
public class ProdListControllerServlet extends HttpServlet{
	IProdService service = new ProdServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl(); 
	
	private void addAttribute(HttpServletRequest req){
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	
	@URIMapping("/prod/prodList.do")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//헤더 꺼내기
		String accept = req.getHeader("Accept"); //json을 포함하거나 아니거나

		addAttribute(req);
		String pageParam = req.getParameter("page"); //currentPage뽑아오기
		ProdVO searchDetail = new ProdVO();
		try {
			BeanUtils.populate(searchDetail, req.getParameterMap());//prod_lgu와 prod_buyer,name을 pagingVO에 담아서 보낸다.
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		int currentPage = 1; //1페이지로 설정
		if(StringUtils.isNumeric(pageParam)) {//숫자로만 구성되어있을 때
			currentPage = Integer.parseInt(pageParam);
		}
		
		//element타입들이 prodVO로 제한이됨
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5,3); //기본값 변경시에 기본생성자말고 다른 생성자 사용
		//screenSize, blockSize, totalRecord, currentPage결정
		pagingVO.setSearchDetail(searchDetail);
		pagingVO.setTotalRecord(service.readProdCount(pagingVO));
	
		pagingVO.setCurrentPage(currentPage);
		
		List<ProdVO> list = service.readProdList(pagingVO);//검색과 페이징이 끝난 list
		pagingVO.setDataList(list); 
		
		String view = null;
		//여기까지 오게된 동기 비동기를 구분한다.(url을 직접입력한경우,form이 서밋되어 여기로 온경우) -> html(동기), json(비동기) 둘중에 결정
		//accept라는 헤더에 의해 결정.
		if(StringUtils.containsIgnoreCase(accept, "json")) {//json이라는 단어가 포함되어있는지 확인
			//비동기 : 데이터만 필요!!
			//1.응답데이터의 마임설정
			resp.setContentType("application/json;charset=UTF-8");
			//2.json데이터만들어내기 (마샬링) 
			ObjectMapper mapper= new ObjectMapper();//mapper 마샬링,언마샬링 기능
			//여기는 마샬링+직렬화해야함 . 현재 모델링 데이터가 pagingVO
			mapper.writeValue(resp.getWriter(), pagingVO); //스트림, 자바객체
			
		}else { //동기 : 데이터+ ui가 필요
			req.setAttribute("pagingVO", pagingVO); //list와 vo 따로 set할 필요없이 vo로 전달한다.
			view ="prod/prodList";
		}
		return view;
	}
}
