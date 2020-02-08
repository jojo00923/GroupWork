<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>scopeDesc.jsp</title>
</head>
<body>
<h4>Scope(영역)을 통한 데이터 공유</h4>
<pre>
	: 생존 범위가 다른 4개의 기본객체가 가진 메모리(Map&lt;String,Object%gt;).
	: 해당 공간을 통해 공유되는 데이터를 attribute라 함.
	** 모든 map은 생존 범위가 다른 객체와 동일한 생존성(공유의 범위)을 가짐.
	page Scope : pageContext의 map (한 jsp를 벗어나면 쓸 수 없음. 제일 협소)
	request Scope : request의 map (request가 살아있는 동안 사용가능)
	session Scope : session의 map(한명의 유저만 사용)
	application Scope : ServletContext의 map(서버가 살아있는 동안 사용가능, 모든 유저가 공유하는 공간)
	
	**scope 선택은 최소한의 공간을 기준으로 한다.
	
	setAttribute, getAttribute, 
	removeAttribute(flash attribute에 사용됨.)
	flash - 넣은 다음에 꺼내면 지우는 것	ex)에러
	
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "서블릿컨텍스트 속성");
		
		//pageContext.forward("/06/attrView.jsp");
		//pageContext.include("/06/attrView.jsp");
		response.sendRedirect(request.getContextPath()+"/06/attrView.jsp");
	%>
	<%-- <a href="<%=request.getContextPath() %>/06/attrView.jsp">어트리뷰트 뷰</a> --%>	
</pre>
</body>
</html>