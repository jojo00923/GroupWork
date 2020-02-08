package kr.or.ddit.web05_model2;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


@WebServlet("/07/modulization.do")
//요청에 관한 모든 정보 - servlet이 책임
public class PageModulizationServlet extends HttpServlet{
	Properties commandMap;
	@Override
	public void init() throws ServletException {//클라이언트가 요청하기 전에 properties 한번 읽는다. --> init
		super.init();
		commandMap = new Properties();
		try(
		InputStream in = getClass().getResourceAsStream("serviceKind.xml");//입력스트림생성. 상대경로?
		){
			commandMap.loadFromXML(in);//데이터 로딩
		}catch (IOException e) {//inputstream에서 잘못 됐을 때 
			//init자체를 톰캣이 호출함. 예외를 톰캣에게 보내서 500코드 응답이 나옴. 
			//예외처리 
			//1.개발자가 처리 (try - catch)
			//2.메소드를 호출자에게 떠넘기는 방법(throws)
			//throw new ServletException(e); //ioe -> servletException으로 바뀜 예외의 종류를 바꿔서 톰캣에게 넘김. 위에 servletException이 있기 때문에 명시적으로 보낼 수 있었음
			//3. unchecked exception 예외를 처리하지 않아도 알아서 호출자에게 넘어감
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//맵 - 휘발성 
		//properties - 외부에 설정파일로 뺄 수 있다. 비휘발성
		//여기서 맵은 계속 수정될 수 있기 때문에 properties로 바꾼다.
		/*
		 * Map<String,String> commandMap = new LinkedHashMap<>();
		 * commandMap.put("STANDARD","/03/standard.jsp");
		 * commandMap.put("GUGUDAN","/03/gugudan.jsp");
		 * commandMap.put("GUGUDANPRO","/03/gugudanProcess.jsp");
		 * commandMap.put("IMAGESTREAMING","/02/imageForm.do");
		 * commandMap.put("VIDEOSTREAMING","/02/videoForm.do");
		 * commandMap.put("CALENDAR","/04/calendar.jsp");
		 * commandMap.put("SESSIONTIMER","/06/sessionTimer.jsp");
		 */
		request.setCharacterEncoding("UTF-8");
	   	String command = request.getParameter("command");
	   	String path = null;
	   	if(StringUtils.isNotBlank(command)) {
	   		//6개의 상수중에 포함되는지 확인
	   		if(!commandMap.containsKey(command)){//키가 없다면
	   			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
	   		}
	   		path = commandMap.getProperty(command);
		}
	   	if(path!=null) {
	   	request.setAttribute("goPage", path.trim());
	   	//최소한의 scope
	   	}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/background.jsp");
		rd.forward(request,response);
	}
	
}
