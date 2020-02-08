<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>attrView.jsp</title>
</head>
<body>
<h4>속성 확인</h4>
<pre>
	page Scope : <%= pageContext.getAttribute("pageAttr")%>
	request Scope : <%= request.getAttribute("requestAttr")%>
	session Scope : <%= session.getAttribute("sessionAttr")%>
	application Scope : <%= application.getAttribute("applicationAttr")%>
</pre>
</body>
</html>