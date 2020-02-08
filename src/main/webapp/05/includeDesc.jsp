<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>includeDesc.jsp</title>
</head>
<body>
<h4>include(내포)의 종류</h4>
<pre>

	: 시점, 대상
	1. 동적 include : 실행 시점에(객체만있음) 결과값을 내포. 
		
		<%RequestDispatcher rd = 
					request.getRequestDispatcher("/04/requestDesc.jsp");
			rd.include(request, response); //--실행 시점(소스가 존재x, b의 실행 결과값을 가져옴)
			 %>	
 <%-- 	<jsp:include page="/04/requestDesc.jsp"/>
 --%> 	
	2. 정적 include : 실행 이전에 소스가 파싱될 때, 소스 자체를 내포. 
				단점 : 매 번 지시자를 직접 써야한다.
		<%--  <%@ include file="/04/requestDesc.jsp"%>
		 <%=names %> => requestDesc.jsp에 선언된 변수 (소스인지 아닌지 판단할 수 있다.)   --%>
		<%--   <%=commonData %> --%> <!-- web.xml을 통해 commonData가 있는 파일 경로를 찾아갈 수 있다. -->
		 
</pre>
</body>
</html>