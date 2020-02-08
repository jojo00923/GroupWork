package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;

@WebServlet("/param.do")
public class RequestParameterDescServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//모든요청의 기본메서드가 get이다. 다른 메서드는 form ui를 만들어야함.
		//do계열은 마지막 콜백
		System.out.println("2. 구체적인 요청 처리(get)");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("2. 구체적인 요청 처리(post)");

		StringBuffer html = TemplateUtils.readTemplate("/kr/or/ddit/web/request2.tmpl");
		req.setCharacterEncoding("UTF-8"); //body안에 데이터의 인코딩설정(꺼내기전에) 
		//get메서드로 해서 body가 없어도 톰캣8버전은 line을 꺼낼때 기본적으로 utf-8로 변환해준다.
		
		
		StringBuffer line1 = new StringBuffer();
		StringBuffer line2 = new StringBuffer();
		String Ptrn1 = "<tr><td>%s</td><td>%s</td></tr>\n";
		
		//방법1
		Map<String, String[]> parameterMap = req.getParameterMap(); //multiple을 고려하여 배열[]
		for(Entry<String, String[]> entry : parameterMap.entrySet()) { //set은 iterator로 접근할 수 있다.
			String keyWord = entry.getKey(); 
			String[]  datas = entry.getValue(); 
			line1.append(String.format(Ptrn1,keyWord,Arrays.toString(datas))); 
		}
		
		
		//방법2
		Enumeration<String> names = req.getParameterNames(); //이름만 컬렉션으로 꺼내옴
		while (names.hasMoreElements()) {
			String paramName = (String) names.nextElement();
			String[] paramValue = req.getParameterValues(paramName);
			line2.append(String.format(Ptrn1,paramName,Arrays.toString(paramValue)));

		}
		
		String contents = html.toString().replace("%paramPair1", line1.toString())
				.replace("%paramPair2",line2.toString());
		
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = null;
		
		try {
		out = resp.getWriter();
		out.println(contents);
		}finally {
			if(out!=null)
			out.close();
		}
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청이 들어오면 무조건 호출(메소드와 무관하게 호출)
		System.out.println("1. 요청 접수");
		super.service(req, resp);//현재요청의 메서드 판단. get방식이라면 doget. post방식이면 dopost호출
		System.out.println("3. 응답 전송 완료");
	}
	
}
