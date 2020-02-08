<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모듈화</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
<div id="header">
	<!-- 서버사이드 페이지 모듈화 작업 : 실행시 요청이 딱 1개가 발생(jsp:include) 서버에서 부하가 덜 부담-->
	<!-- 클라이언트사이드 페이지 모듈화 작업 : 실행시 요청이 각각 1개씩 발생 (iframe) 서버에서 부하가 발생-->
	
	<!-- 동적include -->
	<jsp:include page="/includee/header.jsp"/> 
	<%--
		pageContext.include(""); //가독성 차이, 디자이너가 알아보기 어렵다. 
	--%>
	
	<!-- ★주의 : 소스보기로 보면 div태그안에 html태그가 또 열림. dom개체가 정상적으로 구성이 되지 않는다.
	html문서에 의해 document객체가 만들어짐. dom tree구조 생성. root는 하나여야 하는데 두개이상이 되어버림 
	랜더링 방식이 엔진마다 다 다르기 때문에 에러가 뜨지 않음.
	★include되는 대상에 root 태그를 다 지운다.--> 
	
<%-- <iframe src="<%=request.getContextPath()%>/includee/header.jsp"></iframe> --%>
</div>
<div id="left">
	<jsp:include page="/includee/left.jsp"/>
 <%-- <iframe src="<%=request.getContextPath()%>/includee/left.jsp"></iframe> --%> 
</div>
<div id="content">
<%
	
	String path = (String)request.getAttribute("goPage");

	if(path!=null){//경로가있을때
		pageContext.include(path);
	}else{
		%>
		기본 텍스트
		<%
	}

%>
</div>
<div id="footer">
	<jsp:include page="/includee/footer.jsp"></jsp:include>
</div>
</body>
</html>