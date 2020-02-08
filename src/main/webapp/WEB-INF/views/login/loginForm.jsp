     <%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.utils.CookieUtils.TextType"%>
<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm.jsp</title>
<!-- web-inf아래에 있어서 응답만 보낼 수 있다. 
Objects.toString - 처음 로그인 화면에서 id칸에 null이 나올 때 처리-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h4>로그인폼</h4>
	<%
	String mem_id = new CookieUtils(request).getCookieValue("idCookie");
	String message = (String)session.getAttribute("message");
	if(message!=null && message.trim().length()>0){
%>
<script>
	alert("<%=message %>");
</script>
<%
		session.removeAttribute("message");
	}
%>
<form method="post">
	<ul>
		<li>
			아이디 : <input type="text" name="mem_id" 
				value="<%=Objects.toString(mem_id,"") %>"/>
		</li>
		<li>
			비  번 : <input type="text" name="mem_pass" />
			<label>
			<input type="checkbox" name="idSave" value="save"
				<%=StringUtils.isNotBlank(mem_id)?"checked":""%>
			/>
			아이디 기억하기
			</label>
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>
</body>
</html>