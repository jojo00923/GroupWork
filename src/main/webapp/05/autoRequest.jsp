<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <!-- <meta http-equiv ="Refresh" content="5;url=https://www.naver.com"> --> 

<title>autoRequest</title>
</head>
<body onload="init()">
<h4>자동 요청 발생 방법</h4>
<pre>
	1.서버사이드 방식 : Refresh 응답 헤더
		서버의 현재 시각 : <%=String.format("%tc", Calendar.getInstance()) %>
		<%--
			response.setIntHeader("Refresh", 5); //1초마다 갱신. int이면 단위가 초(second)
			
		--%>
	2.클라이언트 사이드 방식
		클라이언트의 현재 시각 : <span id="timer"></span>
		1) HTML : meta
		 
			 http-equiv ="Refresh" --> 응답데이터에 포함되어있지 않은 헤더를 헤더가 있는 것처럼 동작
			 content="5;url=https://www.naver.com" --> 지연시간설정(5초)뒤에 이동할 url
		 
		2) Javascript
			a) interval : 주기적인 반복작업 설정
			b) timeout : 시간 지연 후 한번의 작업
			
			
			
			
			이벤트처리단계
			1. 타겟 결정(버튼)
			2. 타겟 이벤트 종류 결정(누르면)
			3. 이벤트 핸들러 구현(작업종료)
			4. 핸들러 함수를 타겟에 갖다붙임
			
			<input type="button" value="timer stop" onclick="clickHandler();"/>
			<input type="button" value="timer start" onclick="init();"/> 
			
</pre>
<script type="text/javascript">
	var timerJob=null; 
	function clickHandler(){
			//작업쓰레드 종료
			if(timerJob!=null){//start가 실행중일 때
				clearInterval(timerJob);
				timerJob = null;
			}
	}
	 function init(){
		 timerTag = document.querySelector("#timer"); //var를떼면 전역변수가됨
		 if(timerJob == null){//start를 또 눌렀을 때를 대비해서 처음 돌릴때만.
			 timerJob = setInterval(function(){ //콜백함수, 초를 밀리세컨드로 설정
				var now = new Date();
				timerTag.innerHTML = now.toLocaleString(); //제이쿼리가없을때.innerHTML
			},1000);
	 	}
		 setTimeout(function(){
				location.href="https://www.naver.com";
				//href : 전체 URL을 설정하거나 반환
		 },5000);
		console.log(location.orgin)
		//origin : URL의 프로토콜, 호스트네임, 포트번호를 반환
	} 
</script>
</body>
</html>
