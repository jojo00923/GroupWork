package kr.or.ddit.web;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.net.URL;

@WebServlet(urlPatterns = "/gugudan.do") //서블릿등록&매핑시 사용
public class GugudanServlet extends HttpServlet{
	
	public void service(HttpServletRequest req, //doget -> dopost-> service로 바꿈 
			//리퀘스트 콜백 : service, do계열메서드.
			//메서드와 무관하게 만드는 콜백메서드 : service
						HttpServletResponse resp)
	throws IOException, ServletException{
		String minStr = req.getParameter("minDan"); //request . name 파라미터 뽑아오기. 파라미터는 보통 문자열로 넘어온다.
		String maxStr = req.getParameter("maxDan");
		int minDan = 2;
		int maxDan = 9;
		if(minStr!=null && minStr.matches("[2-9]")) {
			minDan =  Integer.parseInt(minStr);
		}
		if(maxStr!=null && minStr.matches("[2-9]")) {
			maxDan =  Integer.parseInt(maxStr);
		}
			
		
		URL targetURL = getClass().getResource("gugudan.tmpl");
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		String tmp = null;
		
		while((tmp = bufReader.readLine()) != null) {
			html.append(tmp+"\n");
		}
		
		String headPtrn = "<th>%d단</th>";
		StringBuffer header = new StringBuffer();
		for(int dan = minDan; dan<=maxDan; dan++) {
			header.append(String.format(headPtrn, dan));
		}
		
		String bodyPtrn = "<td>%d * %d = %d</td>";
		StringBuffer body = new StringBuffer();
		for(int mul=1; mul<=9; mul++) {
			body.append("<tr>");
			for(int dan=minDan; dan<=maxDan; dan++) {
				body.append(String.format(bodyPtrn, dan, mul, (dan*mul)));
			}
			body.append("</tr>");
		}
		
		String contents =
				html.toString().replace("%header", header.toString())
								.replace("%body", body.toString());
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(contents);
		out.close();
	}
}