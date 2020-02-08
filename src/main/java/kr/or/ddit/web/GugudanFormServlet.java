package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gugudanForm.do")
public class GugudanFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//http://localhost/webStudy01/gugudan.do?minDan=3&maxDan=7 
	//ㄴ쿼리스트링방식! 문자열밖에 못보낸다. 길이가 제한된다. (get방식에서 쓰임)
	//body가 있으면 문제가 해결됨(post방식) F12-network참고
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//템플릿읽기. 
		URL targetURL = getClass().getResource("gugudanForm.tmpl");
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		String tmp = null;
		
		while((tmp = bufReader.readLine()) != null) {
			html.append(tmp+"\n");
		}
		//구멍채울 옵션만들기.
		StringBuffer option = new StringBuffer();
		String optPtrn = "<option value='%1$d'>%1$d단</option>\n"; //%1$-- 인덱스 하나를 받아서 두번넣는다.
		for(int dan=2;dan<=9;dan++) {
			option.append(String.format(optPtrn,dan));
		}
		
		//치환하기.
		String contents = html.toString().replace("%option", option.toString());
		
		//마임설정
		response.setContentType("text/html;charset=UTF-8");
		
		//서비스제공하기. 문자니까 writer
		PrintWriter out = null;
		
		try {
		out = response.getWriter();
		out.println(contents);
		}finally {
			if(out!=null)
			out.close();
		}
		
	}

}
