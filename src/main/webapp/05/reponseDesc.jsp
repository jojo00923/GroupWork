<%@ page language="java"
    pageEncoding="UTF-8"%>
<%
   	response.setHeader("Content-Type", "text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reponseDesc.jsp</title>
</head>
<body>
<h4>response (HttpServletResponse) 활용</h4>
<pre>
	Http에 따라 응답이 패키징 되는 구조
	1. Response Line : protocol, Status code
		Status Code : 요청 처리 결과를 의미하는 세자리 상태코드
		100 : 연결유지중 ing..(Http의 하위 프로토콜인 websocket에서 사용)
			Http 기본 특성 : Stateless (양방향소통X)
			Websocket: statefull(connectfull) - 양방향소통 (채팅,쪽지)
		200 : 성공(OK)
			주로 200(완전한 성공,body까지포함), 
			202(Accepted,서버에 의해 요청이 정상접수됨,body가 없는 경우 사용, 향후 처리됨을 의미) 사용
		300 : 클라이언트의 추가 액션이 필요한 상황
			304(Not Modified, 변경사항이 없으니 cache 사용)
			302/307(Moved, 자원의 위치가 변경되었으니 새로운 요청을 보내라.)
			
		400 : client side 문제로 fail
			400(BadRequest, 필수파라미터 누락, 파라미터의 타입 오류, 데이터 길이 오류),
			401(Unauthorized,허가받지 않은),403(Forbidden, 금지된) 404(Not found) - 보안처리
		500 : server side 문제로 fail (Internel server eroor)
		<%
			//response.sendError(HttpServletResponse.SC_NOT_FOUND, "원하는 서비스가 제공되지 않음");
			//response.setStatus(HttpServletResponse.SC_OK);
			
			//response.sendRedirect(request.getContextPath()+ "/test.jsp");
			// 슬러시가붙으면 절대경로. context path가 없으면 서버사이드표기방식
			//요청이 두개 날아감. 1. 주소를 치고 엔터친 요청 -> 302로 돌아옴, 2. 응답데이터에 바뀐새로운위치 /test.jsp로 요청
			//새로운 위치(/test.jsp)는 클라이언트사이드 절대경로 표기방식으로 해야함.
			//webStudy01 - context path
			
			//response.sendRedirect(request.getContextPath()+ "/04/requestDesc.jsp"); //moved(302/307)를 통해 requestDesc.jsp로 이동함.
			
		%>
	2. Response Header : 이름과 값의 쌍의 구조로 전송, 문자열로 전송.
		1) (서버의)MIME 설정 : Content-Type
			a) page 지시자의 contentType속성
			b) response.setContentType(MIME)
			c) response.setHeader(name,value) 맨위참고
		2) 캐시 제어 : Pragma, Cache-Control, Expires
			<a href="<%=request.getContextPath()%>/05/cacheControl.jsp">cacheControl.jsp참고</a>
		3) Auto request : Refresh
			<a href="${pageContext.request.contextPath}/05/autoRequest.jsp">autoRequest.jsp참고</a>
		4) 페이지 흐름 구조(페이지 이동) : Redirect(Location)
			<a href="./flowControl.jsp">flowControl.jsp</a>
	3. Response Body
</pre>
</body>
</html>