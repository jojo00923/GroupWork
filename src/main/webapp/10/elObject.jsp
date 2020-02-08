<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> EL 기본 객체(11) </h4>
<pre>
	1. 영역(scope) 객체 : Map&lt;String,Object&gt;
		pageScope, requestScope, sessionScope, applicationScope 
		${applicationScope.attrName }, ${applicationScope["attrName"]}
	2. 요청 파라미터 객체 : param (Map&lt;String,String&gt;), paramValues(Map&lt;String,String[]&gt;)
		<%=request.getParameter("paramName") %>, ${param.paramName}, ${param["paramName"]}
		
		**같은이름으로 여러 파라미터를 넘겼을 때 (?paramName=asdf&paramName=def)
		<%--request.getParameterValues("paramName")[1] --%>, ${paramValues.paramName[1]}, ${paramValues["paramName"][1] }
	3. 요청 헤더 객체 : header(Map&lt;String,String&gt;), headerValues(Map&lt;String,String[]&gt;)
		<%=request.getHeader("Accept") %>, 
		${header.accept},
		${header["accept"]}
		<%=request.getHeader("User-Agent") %>,
		${header.user-agent}, **단점 : 연산자로 인식 
		${header["user-agent"]}
		${headerValues["user-agent"][0] }
	4. 쿠키 객체 : cookie(Map&lt;String,Cookie&gt;)
		<%=request.getCookies() %>, <%=new CookieUtils(request).getCookieValue("JSESSIONID") %>
		${cookie.JSESSIONID.value},
		${cookie["JSESSIONID"]["value"]}
	5. 컨텍스트 파라미터 객체 : initParam(Map&lt;String,String&gt;)
		<%=application.getInitParameter("companyName") %>
		${initParam.companyName }, ${initParam["companyName"]}
	6. pageContext
		<%=request.getContextPath() %>
		${pageContext.request.contextPath}
		
</pre>
</body>
</html>