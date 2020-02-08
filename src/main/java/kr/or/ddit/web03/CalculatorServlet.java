package kr.or.ddit.web03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;
@WebServlet(urlPatterns = "/calculator.do")
public class CalculatorServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		String accept = req.getHeader("Accept");
		
		int statusCode = 0;
		if(leftParam==null || !leftParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(rightParam==null || !rightParam.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		OperatorType operator = null;
		try {
			//연산자
			operator = OperatorType.valueOf(opParam);
		}catch(IllegalArgumentException e) {//연산수행할수없음
			statusCode = HttpServletResponse.SC_BAD_REQUEST; 
		}
		
		if(accept.toLowerCase().contains("xml")) {
			statusCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
		}
		
		
		if(statusCode != 0) { //문제가 발생했을때
			resp.sendError(statusCode);
			return;
		}
		
		int leftOp = Integer.parseInt(leftParam);
		int rightOp = Integer.parseInt(rightParam);
		long result = operator.operate(leftOp, rightOp);
		String contents = operator.opExpr(leftOp, rightOp);
		
		String mime = "text/plain";
		if(accept.toLowerCase().contains("json")) {
			mime = "applicaiton/json";
			Date now = new Date();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result",contents);
			resultMap.put("now",now);
			contents = marshalling(resultMap);
			
			
			//contents = "{\"result\":\""+contents+"\", \"now\":\""+now+"\"}"; //응답데이터 json으로 표현 (마샬링-native한 형태로 표현된 데이터를 공통된 형식의 형태로 바꾸는것)
		
		}
		
		resp.setContentType(mime);
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(contents);//0,1로 나타내기 위해 직렬화
		}
	}

	private String marshalling(Map<String, Object> resultMap) {//마샬링
		StringBuffer json = new StringBuffer();
		json.append("{");
		String ptrn = "\"%s\":\"%s\",";
		for(Entry<String, Object> entry :resultMap.entrySet()) {
			String name= entry.getKey();
			Object value = entry.getValue();
			json.append(String.format(ptrn, name, value.toString()));
		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("}");
		
		return json.toString();
	}
	
	
}
