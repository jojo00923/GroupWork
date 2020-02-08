package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MyPageController{ 
	IMemberService service = MemberServiceImpl.getInstance();
	 
	@URIMapping("/mypage.do")
	public String myPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.요청 접수, 분석, 검증
		HttpSession session = req.getSession(false);//존재하지 않으면 새로 생성하지 않고 그냥 null을 반환합니다. 
		// true - 최초의 요청이라 세션이 없다면 강제로 만든다.
		String goPage = null;
		boolean redirect = false;
		if(session==null) {//최초의 요청이라면(400에러)
			redirect = true;//잘못된 요청을 없애기 위해 redirect로
			goPage = "login/login.do"; 
			
		}else {
			MemberVO authMember = (MemberVO) session.getAttribute("authMember");
			//로그인 되어있는지 확인이 필요 **
			String mem_id = authMember.getMem_id();
			//2.로직 실행하여 Model 확보
			MemberVO member = service.readMember(mem_id); //jsp로 보내서 ui구성
			
			try {
				BeanUtils.copyProperties(authMember, member);//session - authMember에도 반영(이름변경 대비) , reflectiondesc.java참고
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			
			//3.scope를 이용해 model을 공유
			req.setAttribute("member", member);
			//4.view layer선택
			goPage = "/member/mypage";
		}
		
		return goPage;
	}
}
