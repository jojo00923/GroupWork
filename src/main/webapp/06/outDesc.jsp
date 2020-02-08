<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
    							<!--오버플로우 발생시 버퍼내용 자동방출여부 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>outDesc.jsp</title>
</head>
<body>
<h4>JspWriter out</h4>
<pre>
	: 응답 데이터를 기록하기 위한 출력스트림, 출력 버퍼 제어 관리자.
		버퍼에 대한 핸들러.
		
	출력버퍼 :	
	-데이터 전송 성능의 향상
	-웹 브라우저에 바로 전송되지 않기 때문에, JSP 실행 도중에 버퍼를 비우고 새로운 내용을 보여 줄 수 있다.
	-버퍼가 다 차기 전까지는 response 헤더를 변경할 수 있다.
	-★한 번 플러쉬 된 이후에는 회수 할 수 없다.
	-dispatch는 버퍼없이 실행될 수 없다.
	
	버퍼의 크기 : <%=out.getBufferSize() %>
	현재 버퍼의 잔여 크기 : <%=out.getRemaining() %>
	자동 방출 여부 : <%=out.isAutoFlush() %>
	<%--
		//buffer="1kb" autoFlush="true" 일 때
		
		for(int i=1; i<=100; i++){
			out.println(i+"번째 데이터 기록"); // 오류: JSP 버퍼 오버플로우
			if(i==99){
				throw new RuntimeException("강제 발생 예외");
				//에러코드500이지만  버퍼가 이미 다 찬적이 있어서 헤더를 변경할 수 없기 때문에 에러페이지가 뜨지 않음.
				//buffer사이즈, autoflush 등 다시 고려해야함.★
			}
		}
	--%>
	<%
		for(int i=1; i<=100; i++){
			if(out.getRemaining()<20){//한글자의 크기?
				//out.flush();//명시적으로 flush하는 순간 response가 commit되어 데이터를 기록할 수 없다. 이럴 때는 buffer가 넉넉하게 잡혀있어야함
				out.clear();//버퍼의 내용을 다 지우고 response commit. 그 이후 버퍼를 바꿀땐 i/o exception처리된다.

				//버퍼가 차기전에 헤더를 바꾼 경우
				out.clearBuffer();//응답을 다 지움. 정상적 예외처리됨.
			}
			out.println(i+"번째 데이터 기록"); 
			if(i==99){
				throw new RuntimeException("강제 발생 예외");
			}
		}
	%>
		
</pre>
</body>
</html>