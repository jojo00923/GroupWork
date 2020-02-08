package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberDeleteController{//id,password가져오기 
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value="/member/memberDelete.do",method=HttpMethod.POST)
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_pass = req.getParameter("mem_pass");
		
		//session의 authmember로 id 뽑기
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();//로그인이 되어있는지 확인이 필요 **
		
		if(StringUtils.isBlank(mem_id)||StringUtils.isBlank(mem_pass)) {//중복, 추후에 프레임워크가 해결
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		ServiceResult result = service.removeMember(new MemberVO(mem_id, mem_pass)); //authMember xxx
		String goPage = null;
		String message = null;
		//id를 세션에서 꺼냈기 때문에 exception발생xx
		switch (result) {
		case OK:
			//로그아웃 후 index로
			//1)세션만료
			goPage = "redirect:/login/logout.do"; 
			break;
		case INVALIDPASSWORD:
			goPage = "redirect:/mypage.do";
			message = "비번 오류";
			break;
		default : //fail
			goPage = "redirect:/mypage.do";
			message = "서버 오류";
			break;
		}
		session.setAttribute("message", message);
		return goPage;
	
		
		}
		
	}

