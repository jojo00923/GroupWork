package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class MemberReadController {
		IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value="/member/memberList.do", method= HttpMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//의존객체를 사용할 수 있도록 생성하거나 받아오기
		String accept = request.getHeader("accept");
		String pageParam = request.getParameter("page"); //currentPage뽑아오기
		String searchType = request.getParameter("searchType"); 
		String searchWord = request.getParameter("searchWord"); 
		SearchVO searchVO = new SearchVO(searchType, searchWord);

		int currentPage = 1; //기본값 1페이지로 설정
		if(StringUtils.isNumeric(pageParam)) {//숫자로 구성되어있을 때 currentPage에 할당
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5,3);
		pagingVO.setSearchVO(searchVO); //pagingVO가 has하게끔. 얘먼저 세팅하고 아래로 내려가야한다.★
		
	
		pagingVO.setTotalRecord(service.readMemberCount(pagingVO));
	
		pagingVO.setCurrentPage(currentPage);
		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		//111111
		//응답데이터 내보내기
		if(StringUtils.containsIgnoreCase(accept, "json")) {//비동기
			//1.응답데이터의 마임설정
			response.setContentType("application/json;charset=UTF-8");
			//2.json데이터 만들어내기(마샬링)
			ObjectMapper mapper = new ObjectMapper();
			//마샬링과 직렬화를 한번에
			mapper.writeValue(response.getWriter(), pagingVO);
			return null; //별도의 view가 필요없음
		}else {//동기
			request.setAttribute("pagingVO", pagingVO);
			String view = "member/memberList";
			return view; //호출자에게 넘김
		}
		
	}
	
	//다른요청을 받는 메서드라는 것을 구분하기 위해 어노테이션 사용
	@URIMapping("/member/memberView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("who");
		if(StringUtils.isBlank(mem_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null; //view를 사용하지 않음
		}
		MemberVO member = service.readMember(mem_id);
		
		req.setAttribute("member", member);
		String view = "member/memberView";//논리적 VIEWNAME : 일부분
		return view;
	}

	

}
