package kr.or.ddit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//("/") 톰캣 web.xml과 똑같은 주소로 매핑 되어있을 때 톰캣보다 작은 웹어플리케이션이 먼저 동작 
//톰캣에서 정적요청을 처리하는 default 서블릿이 동작하지 않아서 정적요청을 처리하지 못한다.
//({"*.jsp"}) , ({"/*"}) 위험~!
public class FakeDefaultServlet extends HttpServlet{	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=UTF-8");
		resp.getWriter().println("메롱메롱");
	}
}
