package kr.or.ddit.web04_model2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/outer.do")
public class OuterServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청 접수
		//2. 요청 분석
		//3. 로직 실행(서비스임플)
		req.getRequestDispatcher("/WEB-INF/views/innerView.jsp")
			.forward(req, resp); //원본req,res를 넘겨줌
	}
	
	
	
}
