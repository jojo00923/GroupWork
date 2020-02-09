package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class AlbaRetrieveController {
	IAlbaService service = new AlbaServiceImpl();
	IAlbaDAO dao = new AlbaDAOImpl();
	
	private void addAttribute(HttpServletRequest req){
		
		req.setAttribute("gradeList", dao.selectGrade());
		req.setAttribute("licenseList", dao.selectLIC());
	}

	@URIMapping("/alba/albaList.do")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accept = req.getHeader("Accept"); //헤더로 json여부 판단
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType"); 
		String searchWord = req.getParameter("searchWord"); 
		SearchVO searchVO = new SearchVO(searchType, searchWord);
		
		PagingVO<AlbaVO> pagingVO = new PagingVO<>(10,5);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {//숫자로만 구성되어있을 때
			currentPage = Integer.parseInt(pageParam);
		}
		pagingVO.setCurrentPage(currentPage);
		
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.readAlbaCount(pagingVO));
	
		
		List<AlbaVO> list = service.readAlbaList(pagingVO);
		pagingVO.setDataList(list);  //해당페이지의 목록들
		System.out.println(list);
		
		String view = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {//json이라는 단어가 포함되어있는지 확인
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper= new ObjectMapper();//mapper 마샬링,언마샬링 기능
			mapper.writeValue(resp.getWriter(), pagingVO); //스트림, 자바객체
			return null;
		}else { //동기 : 데이터+ ui가 필요
			req.setAttribute("pagingVO", pagingVO); //list와 vo 따로 set할 필요없이 vo로 전달한다.
			view ="alba/albaList";
			return view;
		}
	}
	
	
	@URIMapping("/alba/albaView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		String al_id = req.getParameter("who");
		if(StringUtils.isBlank(al_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null; //view를 사용하지 않음
		}
		AlbaVO alba = service.readAlba(al_id);
		
		req.setAttribute("alba", alba);
		String view = "alba/albaView";//논리적 VIEWNAME : 일부분
		return view;
	
	}
}

















