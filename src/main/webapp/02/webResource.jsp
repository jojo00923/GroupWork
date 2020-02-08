<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/webResource.jsp</title>
</head>
<body>
<pre>
	웹 리소스 접근 경로 표기방식
	1. 절대경로  : 고정된 루트로부터 전체 경로를 표기.
		ex)http://localhost/webStudy01/images/Koala.jpg
		클라이언트 사이드 절대경로 : /webStudy01/images/Koala.jpg
			http://localhost (브라우저가 원래 알고 있는 경로. 생략가능)
		서버 사이드 절대경로 : /images/Koala.jpg
			컨텍스트네임까지 생략가능. web.xml - webstudy01안에 있기때문에 이미 경로를 알고있음 
		
		/로 시작했을 때를 절대경로라고 한다.
	2. 상대경로 : 현재 기준위치에 따라 표기방식이 달라진다.
		기준위치 : 현재 브라우저의 주소창의 주소.
		ex)../images/Koala.jpg
		
	URI(Identifier) 식별성중점 vs URL(Locator) 위치중점
	1.URN(Naming)
	2.URC(Contents)
	3.URL(Locator)
</pre>
<img src="../images/Koala.jpg"/>
<img src="/webStudy01/images/Koala.jpg"/>
</body>
</html>