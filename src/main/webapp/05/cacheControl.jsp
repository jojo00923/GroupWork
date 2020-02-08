<%@page import="java.util.Calendar"%>
<%@page import="com.sun.corba.se.spi.activation.Repository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>캐시 제어 방법</h4>
<pre>
	Web Cache data : C/S 사이 데이터 속도차이를 보완하기 위해 클라이언트의 브라우저에 저장된 임시 데이터.
	-언제 어떻게 임시데이터를 지울것인가.
	-어떻게하면 캐시를 절대 안남길 수 있을까.
	
	캐시 제어 헤더 : Pragma(HTTP/1.0), Cache-Control(HTTP/1.1), Expires(공통)  
	--불특정 다수 클라이언트 브라우저 버전와 밀접한 관련되어 있기 때문에 하나의 종류로 특화시켜서는 안된다. 웹 접근성 보장.
	<%
		response.setHeader("Pragma", "no-cache"); //1.0
		response.setHeader("Cache-Control", "no-cache"); //1.1
		response.addHeader("Cache-Control", "no-store"); //firefox는 예외로 addheader : 기존의 값 유지한 채로 하나의 값을 새로 추가

		Calendar cal = Calendar.getInstance(); //현재시간세팅
		cal.add(Calendar.DATE, 7); //7일뒤로 연산
		//long expires = cal.getTimeInMillis();
		long expires = 0; //캐시를 남기지 않을때
		
		response.setDateHeader("Expires", expires);//캐시만료시간설정. 타입안정성을 위해 setDateHeader메서드 이용
	%>
	
</pre>
</body>
</html>
