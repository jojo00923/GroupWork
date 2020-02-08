<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>flowControl</title>
</head>
<body>
<h4>웹 어플리케이션의 흐름 제어 (페이지 이동)</h4>
<pre>
	**Stateless : request가 서버로 전송이 되고, 그에 대한 1:1구조의 응답이 다시 클라이언트로 전송되면, 
	connection이 끊기고 , 양쪽 peer에서 request와 response에 대한 모든 정보가 사라진다.
		
	: A -> B로 가는 방법
	1. Request Dispatch 방식 : 원본 요청이 그대로 도착지로 전달되는 유지된다.
							서버내에서만 이동하는 구조(서버 사이드 위임 처리 방식)
							책임이 쪼개질 수 있다.
		1) forward
		<%
			//RequestDispatcher rd = request.getRequestDispatcher("/04/requestDesc.jsp"); 
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/innerView.jsp");
			//b의 경로 적어줌. 서버에서 경로를 알아야함~! 서버사이드는 무조건 절대경로
			//rd.forward(request, response); 
		%>
		2) include
		
	2. Redirect방식 : Stateless
			B로 이동하기 전 body가 없고, status code(302), header(Location)을 가지고 reponse 하나가 commit
			- 원본 요청이 삭제됨 - 브라우저가 응답 데이터를 확인 - 클라이언트가 Location(B)의 방향으로 새로운 request 발생시킴.
			**** B에서는 원본 요청의 데이터를 알 수 없음.
	
		<%
			//response.sendRedirect(request.getContextPath()+"/04/requestDesc.jsp");//클라이언트가 새로운 요청할 때 사용. 클라이언트사이드의 절대경로로 표시!
			//response.sendRedirect(request.getContextPath()+"/WEB-INF/views/innerView.jsp");//오류 web-inf는 클라이언트가 direct로 접근할 수 없다.
		%>
</pre>
</body>
</html>