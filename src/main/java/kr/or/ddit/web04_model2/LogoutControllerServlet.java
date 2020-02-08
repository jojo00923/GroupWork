package kr.or.ddit.web04_model2;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class LogoutControllerServlet {

	@URIMapping("/login/logout.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();//세션이없으면 항상 세션을 만드는 기능
		String viewName= null;
		
		if(session.isNew()) {//로그인이 되지 않은것
			resp.sendError(400);
		}else {
			//authmember외에도 모든 상태정보를 다 지움. 명시적으로 세션 완료
			session.invalidate();
			viewName = "redirect:/"; //웰컴페이지라서 생략
		}
		return viewName;
	}
}
