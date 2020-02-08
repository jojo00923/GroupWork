<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
웰컴 페이지 **강제만료 여부 확인 : <%=session.getId() %>
<br>
<% 
	//	request.setCharacterEncoding("UTF-8");
	//	String mem_id = request.getParameter("mem_id");
	MemberVO authMember = (MemberVO)session.getAttribute("authMember");
	if(authMember != null){//로그인되었을 때
		%>
		<a href="<%=request.getContextPath()%>/mypage.do"><%=authMember.getMem_name() %>님</a> 
		<a href="<%=request.getContextPath() %>/login/logout.do">로그아웃</a>
		<%
	}else{
		%>
		<a href="<%=request.getContextPath() %>/login/login.do">로그인</a>
		<a href="<%=request.getContextPath() %>/member/memberInsert.do">회원가입</a>
		<%
	}
%>

</body>
</html>