package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;
@WebServlet(urlPatterns ="/calForm.do")
public class CalculatorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String num1 = req.getParameter("first");
		String op = req.getParameter("op");
		String num2 = req.getParameter("second");

		StringBuffer html = 
				TemplateUtils.readTemplate("/kr/or/ddit/web/calculator.tmpl");
		
		int nb1 = 0;
		int nb2 = 0;
		int result = 0;
		String ptrn = "<p>%d</p>";
		if(num1 != null && num1.matches("^[0-9]*$")) {
			nb1 = Integer.parseInt(num1);
		}
		if(num2 != null && num2.matches("^[0-9]*$")) {
			nb2 = Integer.parseInt(num2);
		}
		if(op != null && op.matches("[+\\-X/]")) {
			if(op.equals("+")) {
				result = nb1 + nb2;
				
				System.out.println(nb1);
				System.out.println(nb2);
				System.out.println(result);
			}else if(op.equals("-")) {
				result = nb1 - nb2;
				
			}else if(op.equals("*")) {
				result = nb1 * nb2;
				
			}else if(op.equals("/")) {
				result = nb1 / nb2;
			}
		}
		
		StringBuffer rs = new StringBuffer();
		rs.append(String.format(ptrn, result));
		
		String contents =
				html.toString().replace("%result", rs.toString());
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(contents);
		out.close();
	}
}
