<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pageContextDesc.jsp</title>
</head>
<body>
<h4>pageContextDesc.jsp</h4>
<pre>
	: 하나의 JSP 페이지에 대한 모든 정보를 가지며, 가장 먼저 생성되는 객체.
	1. 나머지 기본객체를 확보
	<%=request == pageContext.getRequest() %> 
	<%=out == pageContext.getOut() %>
	<%=pageContext.getServletContext() == application %>
	<%=application == getServletContext() %>
	<%=application.hashCode() %>
	2. 페이지 이동(Request Dispatch)
	<%
		//pageContext.forward("/04/requestDesc.jsp"); //1번이론에 의해 가능함.
		pageContext.include("/04/requestDesc.jsp"); //include코드 위치에서 실행됨. page 모듈화 때 사용
		//request.getRequestDispatcher("/04/requestDesc.jsp").include(request, response);//이동과정에 중간에 버퍼 flush가능하다.
	%>
	3. 에러 데이터 확보 - exception기본 객체에 의해
	4. Scope 핸들링 - 4개의 scope를 실행할 수 있다.
	<%
		pageContext.setAttribute("now", new Date(), PageContext.SESSION_SCOPE); //session scope에 속성 넣기
		//session.setAttribute("now", new Date());
		
		//session.getAttribute("now");
		pageContext.getAttribute("now",pageContext.SESSION_SCOPE);//session scope에서 꺼내오기
	%>
	
</pre>
</body>
</html>