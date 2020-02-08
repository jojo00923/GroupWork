<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<h4> JSP(Java Server Page) </h4>
<pre>
	: 템플릿 기반의 스크립트 방식의 자바 웹 페이지 스펙. 우리가 작성하는 소스는 진짜가 아님.
	
	JSP 소스의 표준 구성 요소
	1. 정적 텍스트 : HTML, CSS, JAVASCRIPT, 일반 텍스트
	2. 스크립트 요소
		1) 지시자(directive) : <%--@ --%>, jsp 페이지에 대한 부가정보 설정에 사용
			구체적인 설정은 지시자의 속성(attribute)으로 남김.
			- page(required) : JSP페이지에 대한 전처리등의 환경설정에 사용
			- include(optional)
			- taglib(optional)
			
		2) 스크립틀릿(scriptlet) : 지역변수로 제한 
		<% 
			//java code
			String temp = "random";
		
		%>
		3) 표현식(expression) : 보여줄 목적의 구성요소 <%= "변수나 값" %>, <%=new Date() %>
		4) 선언부(declaration) : 전역변수로 제한
		<%! 
		//전역변수나 메소드 선언
			public void test(){
			
			}
		%>
		5) 주석(comment) : 
			- 서버사이드 주석 : java, jsp
			- 클라이언트 사이드 주석 : html, javascript, 응답데이터의 크기, 정보 노출의 단점
		<%-- 주석--%>
		<!-- 주석-->
	3. 기본 객체(내장 객체) : Scope를 통한 데이터 공유
	4. 표현 언어(Expression Language)
	5. JSTL(Java Standard Tag Library)
</pre>
