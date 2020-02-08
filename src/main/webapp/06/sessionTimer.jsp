<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionTimer.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<h4> 세션 타이머</h4>
<h4 id="timerArea"></h4>
<div id = "msgArea">
	세션 타임을 연장할래?
	<input class="ctrlBtn" id="yesBtn" type="button" value="응"/>
	<input class="ctrlBtn" id="noBtn" type="button" value="아니"/>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sessionTimer.js"></script>  <!-- 엘리먼트 만들고 로딩해야해서 밑에~! -->
<script type="text/javascript">
	
	$("#timerArea").uiInit({//객체의 형태로 전달. 엘리먼트 선택 후 사용가능. 현재 주인 timerArea
		msgArea : $("#msgArea"),
		ctrlBtn : $(".ctrlBtn"),
		yesBtnId : "yesBtn"
	});
	init(<%=session.getMaxInactiveInterval()%>);
</script>
</body>
</html>