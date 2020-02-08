package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class BoardRetrieveController { // 조회(목록,상세)
	// paging, serarch(전체,작성자,제목,내용검색)
	IBoardService service = new BoardServiceImpl();
	
	@URIMapping("/board/boardList.do")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String accept = req.getHeader("Accept");
		String searchType = req.getParameter("searchType"); 
		String searchWord = req.getParameter("searchWord"); 
		SearchVO searchVO = new SearchVO(searchType, searchWord);
		
		String pageParam = req.getParameter("page");
		
		int currentPage = 1;
		if(StringUtils.isNotBlank(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10,5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setTotalRecord(service.readBoardCount(pagingVO));
	
		pagingVO.setCurrentPage(currentPage);
		
		List<BoardVO> list = service.readBoardList(pagingVO);//검색과 페이징이 끝난 list
		pagingVO.setDataList(list); 		
		
		String view = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			//비동기 : 데이터만 필요!!
			//1.응답데이터의 마임설정
			resp.setContentType("application/json;charset=UTF-8");
			//2.json데이터만들어내기 (마샬링) 
			ObjectMapper mapper= new ObjectMapper();//mapper 마샬링,언마샬링 기능
			//여기는 마샬링+직렬화해야함 .
			mapper.writeValue(resp.getWriter(), pagingVO); 
			return null;
		}else { //동기 : 데이터+ ui가 필요
			req.setAttribute("pagingVO", pagingVO); //list와 vo 따로 set할 필요없이 vo로 전달한다.
			view ="board/boardList";
			return view;
		}
	}
	
	@URIMapping("/board/boardView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String bo_noStr = req.getParameter("what");
		
		
		//넘어왔는지 검증
		if(!StringUtils.isNumeric(bo_noStr)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"글 번호 이상함");
			return null;
		}
		
		int bo_no = Integer.parseInt(bo_noStr);
		BoardVO board = service.readBoard(bo_no);
		service.incrementHit(bo_no); //삭제 ,수정시 조회수 증가 방지
		
		//쿠키꺼내오기
		String cookieValue = new CookieUtils(req).getCookieValue("likeCookie");//json
		boolean recommended = false; //추천여부
		if(StringUtils.isNoneBlank(cookieValue)) {
			//int의 배열복원 , 언마샬링
			ObjectMapper mapper = new ObjectMapper();
			int[] boNOArray = mapper.readValue(cookieValue,int[].class);
			Arrays.sort(boNOArray);//정렬
			
			//글번호가 포함되어있는지 확인
			if(Arrays.binarySearch(boNOArray, bo_no)>=0) {//이진검색. 배열,찾을대상 
				recommended = true; //view.jsp까지 가져가야한다.
			}; 
			
		}
		
		board.setRecommended(recommended);
		//scope로 공유
		req.setAttribute("board", board);
		return "board/boardView"; // logical view name
		
	}
	
		@URIMapping("/board/boardLike.do")
		public String recommend(HttpServletRequest req, HttpServletResponse resp) throws IOException {//호출자 : handler invoker가 예외 가져감
			String bo_noStr = req.getParameter("what");
			if(!StringUtils.isNumeric(bo_noStr)){
				resp.sendError(400,"글번호가 이상함");
				return null;
			}
			
			int bo_no = Integer.parseInt(bo_noStr);
			ServiceResult result = service.incrementLike(bo_no);
			ObjectMapper mapper = new ObjectMapper(); //마샬링or언마샬링
			Map<String, Object> resultMap = new HashMap<>(); //키=마샬링 후에 json property
			if(ServiceResult.OK.equals(result)) {//추천 성공 
				resultMap.put("success",true);
				
				String cookieValue = new CookieUtils(req).getCookieValue("likeCookie");
				int[] newArray = null;
				if(StringUtils.isBlank(cookieValue)) {//처음으로 추천한 경우
					newArray = new int[1];
				}else {//이전에 추천한 글이 하나이상인 경우
					//글번호를 json 배열의 형태로 저장해서 언마샬링 수행
					int[] boNOArray = mapper.readValue(cookieValue, int[].class); // java객체의 타입
					newArray = new int[boNOArray.length+1];
					System.arraycopy(boNOArray, 0, newArray, 0, boNOArray.length); //newArray에 복사
					
				}
				newArray[newArray.length-1] = bo_no;
				//마샬링
				String newCookieValue = mapper.writeValueAsString(newArray);
				Cookie likeCookie = CookieUtils.createCookie("likeCookie", newCookieValue, 
											req.getContextPath(), TextType.PATH, 60*60*24*3);
				resp.addCookie(likeCookie);
				//VALUE에서 5B, 5D = []
				
			}else {
				resultMap.put("success",false);
				resultMap.put("message","실패.");
			}
			
			//마샬링+직렬화
			mapper.writeValue(resp.getWriter(), resultMap);//응답스트림,마샬링의 타겟
								
			return null;
			
		}
		
	

}
