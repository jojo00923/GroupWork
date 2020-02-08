<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>

	<%
	Cookie[] cookies = request.getCookies();
	String search = null;
	if(cookies!=null){
		for(Cookie tmp :cookies){//모든쿠키 확인용
			/* if("sampleCookie".equals(tmp.getName())){
				search = URLDecoder.decode(tmp.getValue(),"UTF-8");
				break;
			}*/
			String value = URLDecoder.decode(tmp.getValue(),"UTF-8");
			out.println(String.format("%s : %s", tmp.getName(), value));
			}
		}
	
	%>
	
</pre>
</body>
</html>