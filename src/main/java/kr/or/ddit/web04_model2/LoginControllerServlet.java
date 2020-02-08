package kr.or.ddit.web04_model2;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class LoginControllerServlet{
	IAuthenticateService service = AuthenticateServiceImpl.getInstance();
	
	@URIMapping(value="/login/login.do", method = HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//jsp로 연결만 해주면 됨.
		return "login/loginForm";
	}
	
	@URIMapping(value="/login/login.do", method = HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터 잡기. (id,password)
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String idSave = req.getParameter("idSave");

		//검증(둘 다 필수 파라미터로 들어왔는지 누락시 400번대 코드)
		if(mem_id ==null || mem_id.trim().length()==0 || mem_pass == null || mem_pass.trim().length()==0) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;//메서드를 빠져나간다.
		}
		
		String goPage = null;
		try {
		Object result = service.authenticate(new MemberVO(mem_id, mem_pass));
		
		if(result instanceof MemberVO) {//result가 memberVO 생성자 함수를 사용하였는지 확인
			Cookie idCookie = CookieUtils.createCookie("idCookie", mem_id, req.getContextPath()+"/", TextType.PATH);
			int maxAge = 0; //아이디저장 안했을때
			
			if("save".equals(idSave)) { //idsave는 체크하지않으면 value가null
				maxAge = 60*60*24*4;
			}
			idCookie.setMaxAge(maxAge);
			resp.addCookie(idCookie);
			
			//인증 성공 : POST - redirect- get(새로운요청) PRG패턴
			
			//아이디에 한글 포함될 수 있어서 url encoding방식을 쓴다.
			//String encoded = URLEncoder.encode(mem_id, "UTF-8");
			//goPage = "/?mem_id="+encoded;//index.jsp에서 아이디를 뽑아와서 사용 하는데 
			//쿼리스트링설계 - 누구나 볼 수 있다. -> scope개념사용
			req.getSession().setAttribute("authMember", result);
			goPage = "redirect:/"; //웰컴페이지라서 생략함.
			
		}else {//실패, 서버에서 사용하기 때문에 서블릿을 거칠필요가 없음. jsp사용
			String msg  = null;
			if(result.equals(ServiceResult.INVALIDPASSWORD)) {
				msg = "비번 오류";
			}else if(ServiceResult.REMOVED.equals(result)) {
				msg = "탈퇴한 회원입니다.";
			}
			
			req.getSession().setAttribute("message", msg);
			goPage = "redirect:/login/login.do"; //redirection방식 (잘못된요청이 다시날아가지 않는다.)

			//실패시 로그인 페이지로 돌아갈 때 디스패치 사용해보기
			//goPage = "/WEB-INF/views/login/loginForm.jsp"; 디스패치방식(잘못된요청이 다시날아가서 새로고침을 하면 계속 에러메세지가 뜬다.)
			
		}//if end
		
		}catch (UserNotFoundException e) {//예외 직접처리
			String msg  = "존재하지 않는 유저";
			req.getSession().setAttribute("message", msg);
			goPage = "redirect:/login/login.do";
		}//try ~ catch end
		
		return goPage;
	}
	

}

