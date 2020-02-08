<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>actionTagDesc.jsp</title>
</head>
<body>
<h4>jsp Action 태그</h4>
<pre>
	: JSP 스펙에 따라 기본 제공되는 일종의 커스텀 태그.(개발자가 만든 태그)
	커스텀 태그 사용 형태(serverside의 자바 코드)
	jsp보다 jstl이 더 많이 쓰인다.
	* 모듈화 - 원래 하나의 기능을 여러가지로 쪼개는것
	&lt;접두어:태그명 속성들.. /&gt;
	<%-- <jsp:forward page="/04/requestDesc.jsp"></jsp:forward> --%>
	- 자바코드를 태그형태로 쓸 수 있다. 
	<jsp:include page="/04/requestDesc.jsp"></jsp:include> 
	- 동적 include
	
	- 정적/동적 - 시점, 대상의 차이
	정적 - 실행이전에 소스가 들어온다. include 지시자를 쓰거나 web.xml에 include태그를 사용하거나
	동적 - runtime에 결과
	
	이 텍스트의 위치 확인.
</pre>
</body>
</html>