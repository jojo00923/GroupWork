<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>implicitObject.jsp</title>
</head>
<body>
<h4>기본객체(내장 객체)</h4>
<pre>
	: 필요에 의해 명시적으로 선언하지 않아도 자동 지원되는 객체.
	0. pageContext(PageContext) : 모든 기본 객체 중 제일 먼저 생성되며, "하나의" JSP와 관련된 모든 걸 가지고 있다.
		jsp생성시?											--> a를 떠나는 순간 죽는다.
	1. request(HttpServletRequest) : 
		클라이언트와 요청에 관한 모든 정보를 가진 객체.(캡슐화 되어있다.) - 두번째로 생성
	
	2. response(HttpServletResponse) : 
		서버에서 클라이언트로 전송될 응답에 대한 모든 정보를 가진 객체.
	3. out(JspWriter) :
		응답으로 전송할 데이터를 가진 버퍼에 대한 컨트롤 객체
		표현식을 대신해서 출력할 때 사용.
		<%="데이터"%> <%out.println("데이터"); %> 
	4. session(HttpSession) : 세션에 대한 모든 정보를 가진 객체. -요청이 들어오면 생성
	5. application(ServletContext) : 현재 컨텍스트(webstudy01)에 대한 정보를 가진 객체.
	- 서버시작시 제일먼저 생성, 서버종료시 마지막으로 소멸 
	- 제일 광범위하게 사용
	- realpath를가져옴
	6. config(ServletConfig) : web.xml의 객체들을 묶은 것 - config
								Servlet init의 파라미터로 넘어오는 것 
	7. page(Object) : 현재 JSP의 객체를 참조.(==this)
	 				page와 this--> 타입의 차이
	8. exception(Throwable) : 발생한 예외에 대한 정보를 가진 객체. 예외를 처리할 목적의 jsp에서만 사용됨.
							  page 지시자의 isErrorPage="true" 일 때 사용.
		- checked : i/o ,sql exception
		- unchecked : runtime exception
</pre>
</body>
</html>