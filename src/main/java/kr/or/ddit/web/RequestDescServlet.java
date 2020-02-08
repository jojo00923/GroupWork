package kr.or.ddit.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;
/**
 * 	HttpServletRequest
 *	: http프로토콜에 따라 패키징된 웹 요청(request)에 대한 정보를 캡슐화한 객체
 *
 *	http request 구조
 *	1.Request Line : Protocol/ver. URL(수신자의 정보). http Method
 *		http Method - 요청의 목적이면서 포장 방식
 *		GET(R) : 조회
 *		POST(C) : 생성
 *		PUT/PATCH(U) : 갱신
 * 		DELETE(D) : 삭제
 * 		OPTIONS : pre-flight 요청에서 사용
 * 		HEAD : response body가 없는 응답을 받을때 사용
 * 		TRACE :	서버 디버깅용
 *	2.Request Header : client와 request message에 대한 부가정보(metadata)
 *		header name : header value (String)
 *		enumeration = getHeaderNames()
 *		value = getHeader(name)
 *	3.Request Body(Message Body, Content Body) : only POST
 *
 *	요청 파라미터 전송 방법(Body 사용 여부)
 *	요청 파라미터?? 클라이언트가 서버로 전송하는 명시적인 입력 데이터
 *	parameter name = parameter value
 *	1. GET(Body X) : request line의 URL에 QueryString의 형태로 전송
 *		ex) gugudan.do?queryString
 *			queryString형태 : 섹터1&섹터2...&섹터n
 *			sector : 한 쌍의 파라미터 : param_name=param_value
 *			ex) gugudan.do?minDan=3&maxDan=7
 *		단점 : 보안 취약, 전송 데이터 형태와 길이의 제한 --> 보완하기 위해 post방식 사용
 *	2. POST(Body O) : 전송 데이터 다양화. 마임텍스트 세팅으로 전송 데이터 종류 식별(헤더에 세팅)
 *	
 ***한글을 비롯한 특수문자가 전송되는 경우.
 *HTTP(1.1), IETF, %Ef%EC -> URLEncoding 방식으로 네트웤을 통해 전송됨.
 ***특수문자를 꺼내기 전에 characterset설정
 *1. GET : line에 QueryString 형태로 전송.
 *		서버의 설정 필요
 *		server.xml -> URIEncoding = UTF-8(fix)
 *					  useBodyEncodingForURI=true(setCharacterEncoding 사용 가능)
 *2. POST : body 영역을 통해 전송.
 *		parameter getter를 사용하기 전에 setCharacterEncoding(only body) 사용
 */
@WebServlet(urlPatterns = "/requestDesc.do", 
			loadOnStartup = 2, 
			initParams = {@WebInitParam(name = "param", value="value")})
public class RequestDescServlet extends HttpServlet{
	
	//DB를 대신할 맵 생성
	private Map<String,String[]> browsers = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String value= config.getInitParameter("param");
		browsers = new LinkedHashMap<String, String[]>(); //맵은 순서가 없으므로 링크드 해시맵으로 순서를 형성해줌
		browsers.put("firefox",new String[]{"파이어폭스"});//db구조형상화위해 배열로 했음~
		browsers.put("chrome",new String[]{"크롬"});
		browsers.put("trident",new String[]{"IE"});
		browsers.put("others",new String[]{"기타등등"});
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//body가없음

		StringBuffer html = TemplateUtils.readTemplate("/kr/or/ddit/web/request.tmpl");
		
		//request line
		StringBuffer line = new StringBuffer();
		String Ptrn1 = "<tr><td>%s</td><td>%s</td></tr>\n";
		
		String protocol = req.getProtocol();
		String uri = req.getRequestURI();
		String method = req.getMethod(); 

		line.append(String.format(Ptrn1,"protocol",protocol));
		line.append(String.format(Ptrn1,"uri",uri));
		line.append(String.format(Ptrn1,"method",method));

		//header
		StringBuffer header = new StringBuffer();//StringBuffer 자료형은 append 라는 메소드를 이용하여 계속해서 문자열을 추가해 나갈 수 있다.
		String Ptrn2 = "<tr><td>%s</td><td>%s</td></tr>\n";
		
		String encoding = req.getCharacterEncoding();//body파트가있으면 유효값이 돌아온다. return null
		int length = req.getContentLength();//body의길이   return -1
		String contentType = req.getContentType();//body의 MIME return null
		String contextPath = req.getContextPath();//context- web apps아래 폴더. context들의 경로를 uri로 가져옴

		line.append(String.format(Ptrn1,"encoding",encoding));
		line.append(String.format(Ptrn1,"length",length));
		line.append(String.format(Ptrn1,"contentType",contentType));
		line.append(String.format(Ptrn1,"contextPath",contextPath));
		
		Enumeration<String> en = req.getHeaderNames();
		while (en.hasMoreElements()) {
			String headerName = (String) en.nextElement();
			String headerValue = req.getHeader(headerName); //header는 이름과 값의 쌍 형태 , 값은 string형태
			header.append(String.format(Ptrn2,headerName,headerValue));
		}

		String agent = req.getHeader("user-agent").toLowerCase(); //헤더이름은 대소문자 구별 X , keyword가 소문자라서 소문자로 바꿔준다.
		//포함되는지 확인
		String browserName=null;
		
		//Iterator<String> it = browsers.keySet().iterator(); //반복문으로 접근하기 위해 키만 셋의 형태로가져와서 다시 데이터에 따로접근해야함
		//while (it.hasNext()) {
		//	String key = (String) it.next();
		//	String[] datas = browsers.get(key);
		//	
		//}
		
		for(Entry<String, String[]> entry:browsers.entrySet()) { //set은 iterator로 접근할 수 있다.
		  //entryset은 key value 모두 필요할 때 사용. 
		  //요소의타입<>요소 하나에 접근하기 위한 임시블럭변수 : 대상
		String keyWord = entry.getKey(); 
		String[]  datas = entry.getValue(); 
		  if(agent.contains(keyWord)) { 
			  browserName = datas[0]; 
			  break; 
		  } 
		  
	   }
		 
		if(browserName==null) {
			browserName = browsers.get("others")[0];
		}
		
		
		InputStream is = req.getInputStream(); //only post
		String serverIP = req.getLocalAddr(); //server의 ip addr
		int serverPort = req.getLocalPort(); //80포트
		String clientIP = req.getRemoteAddr(); //client의 ip addr
		int clientPort = req.getRemotePort(); //random이라 모름

		line.append(String.format(Ptrn1,"serverIP",serverIP));
		line.append(String.format(Ptrn1,"serverPort",serverPort));
		line.append(String.format(Ptrn1,"clientIP",clientIP));
		line.append(String.format(Ptrn1,"clientPort",clientPort));
		
		Locale locale = req.getLocale();//국가 정보 가져옴
		String country = locale.getDisplayCountry();
		String language = locale.getDisplayLanguage();
		line.append(String.format(Ptrn1,"국가",country));
		line.append(String.format(Ptrn1,"언어",language));
		
		
		String contents = html.toString().replace("%line", line.toString())
				.replace("%header", header.toString())
				.replace("%browser",browserName);
	
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


}













