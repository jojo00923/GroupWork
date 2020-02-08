<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionDesc.jsp</title>
</head>
<body>
<h4>HttpSession session</h4>
<pre>
	:하나의 세션에 대한 모든 정보를 가진 객체.
	세션 - 통로(database) : 한 세션 내에서 두 피어 사이의 유일한 연결 통로.
		  시간(web) : 한 사용자(브라우저)가 의미있는 단위로 어플리케이션을 이용하는 기간.
	세션의 생성 시점 : <%=new Date(session.getCreationTime()) %>
	세션의 만료 시간 : <%=session.getMaxInactiveInterval() %>s 비활동기최대간격 
	<%
		session.setMaxInactiveInterval(120);
	%>
	**한 페이지가 아닌 어플리케이션 자체의 만료시간 설정 - web.xml
	
	세션의 만료 시간 : <%=session.getMaxInactiveInterval() %>s 비활동기최대간격 
	세션 종료 이벤트 종류
	1. 만료 시간 이내에 새로운 요청이 없는 경우.
	2. 브라우저의 종료(버전에 따라 세션이 살아있는 브라우저도 있다.)
	3. 명시적인 로그아웃
	4. 쿠키 삭제
		: 세션의 기본 트래킹 모드가 쿠키로 되어있으므로
	세션 유지 과정에서 각 세션을 구분하기 위한 식별자 : <%=session.getId() %>
	<a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">url트래킹모드로 세션 유지 방법</a>

	세션 유지 방법(tracking-mode)
	1.쿠키 : JSESSINID라는 특수한 쿠키를 이용해 세션 유지
	2.url : jsessionid라는 세션파라미터를 이용해 유지(보안 취약)
	3.ssl : 공인된 기관의 인증서를 보유한 서버에서 Secure Socket Layer 프로토콜을 이용해 유지
</pre>
</body>
</html>