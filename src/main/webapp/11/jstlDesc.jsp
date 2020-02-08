<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Library)</h4>
<input type="image" id="ko" src="<c:url value='/images/korea.jpg'/>" class="flag"/>
<input type="image" id="en" src="<c:url value='/images/america.jpg'/>" class="flag"/>
<script>
	$(".flag").css({
		height:"20px",
		width:"20px"
	}).on("click",function(){
		//눌렀을 때 요청발생
		let id = $(this).prop("id");
		location.href="?language="+id; //현재 페이지의 출처변경가능
	});
</script>
<pre>
	: 라이브러리 형태의 커스텀 태그 집합
	사용형태 : &lt;prefix:tagname&gt;
	** taglib 지시자를 통해 태그 로딩 필수(uri, prefix).
	
	1. core 
		1) 속성(EL변수) 지원 : 
			set - 속성 생성 및 할당, var(속성명), value(값), scope(영역)
			remove - 속성 제거(scope 명시)
			<c:set var="test" value="테스트" scope="request" />
			${requestScope.test }
			<%-- <c:remove var="test" scope="request"/> --%>
			${test }
		2) 제어문
		    조건문 : if(조건식){조건문}
		    	<c:if test="${empty test }">
		    		test 속성이 비어있다.
		    	</c:if>
		    	<c:if test="${!empty test }">
		    		test 속성이 비어있지 않다.
		    	</c:if>
		    다중조건문 : choose, when, otherwise
		    	<c:choose>
		    		<c:when test="${empty test }">
		    			test 속성이 비어있다.
		    		</c:when>
		    		<c:otherwise>
		    			test 속성이 비어있지 않다.
		    		</c:otherwise>
		    	</c:choose>
		    반복문 : forEach, forTokens
		    	for(선언절; 조건절; 증감절){반복문}
		    	<c:forEach var="i" begin="0" end="3" step="1">
		    		${i}번째 반복	i는 속성. pageScope사용
		    	</c:forEach>
		    	
		    	for(선언절:반복대상 집합객체){반복문}
		    	<%
		    		List<String> list = new ArrayList<>();
		    		list.add("value1");
		    		list.add("value2");
		    		list.add("value3");
		    		pageContext.setAttribute("list", list);
		    	%>
		    	<c:forEach items="${list}" var="element" varStatus="vs">
		    		${element} ${ not vs.last? "," : "" }
		    	</c:forEach>
		    	
		    	
		    	forTokens 
		    	<c:forTokens items="1,2,3,4" delims="," var="token" varStatus="vs">
		    		${token * 1000}
		    	</c:forTokens>
		    	
		3) URL 재처리 : client side 절대경로 , 쿼리스트링,  url rewriting
				
			${pageContext.session.id} 세션유지가 안될 때 계속 바뀜
			
			<c:url value="/11/jstlDesc.jsp" var="tmp"/>
			<a href="${tmp}">세션유지</a> 
			<a href="jstlDesc.jsp;jsessionid=${pageContext.session.id}">세션유지</a> url rewriting기법
		
			<c:url value="/member/memberView.do">
				<c:param name="who" value="a001"/>
			</c:url>
		4) 기타 : redirect, import, out
			<%-- <c:redirect url="/04/requestDesc.jsp"/> --%>
			알아서 contextpath를 붙여줌
			<%-- <c:import url="https://www.naver.com" var="naver"/> 컨텍스트 제한이 없음
			<c:out value="${naver}" escapeXml="false"/> 소스가 아닌 네이버가 출력됨 (escape시킬건지 말건지 결정할 때 사용) --%>
	2. fmt
		1) 국제화 메시지 처리(i18n)
			- 언어 종류 결정
			- 언어별 메시지 번들 작성
				(한글, 영어)
				properties 파일 (kr.or.ddit.msg.message)
				kr/or/ddit/msg/message_ko.properties
				kr/or/ddit/msg/message_en.properties
			- 어플리케이션에서 사용할 수 있게 메모리에 번들 로딩(locale 결정)
				ResourceBundle 사용
				<%
					//ResourceBundle.getBundle("kr.or.ddit.msg.message", Locale.KOREAN);
					//basename : classpath기준으로 한 qualified name , /대신 . , 확장자가 빠진형태
					//basename에서 locale정보는 포함되지 않는다.
				%>
				<%----------------------------------------------------------------- --%>
				<c:choose>
					<c:when test="${param.language eq 'ko'}">
						<fmt:setLocale value="ko"/>						
					</c:when>
					<c:when test="${param.language eq 'en'}">
						<fmt:setLocale value="en"/>						
					</c:when>
					<c:otherwise>
						<fmt:setLocale value="${pageContext.request.locale}"/>
					</c:otherwise>		
				</c:choose>
				
				<fmt:bundle basename="kr.or.ddit.msg.message" >
					- 메시지 사용
					<fmt:message key="bow"/>
					<fmt:message key="test"/>
				</fmt:bundle>
				
		2) 지역화(l10n)
			<fmt:parseNumber value="2,000" pattern="#,###" var="afterParse" />
			${afterParse * 3}
			<fmt:parseDate value="2020년01월30일" pattern="yyyy년MM월dd일" var="afterDate"/>
			${afterDate}
			<fmt:setLocale value="<%=Locale.JAPAN %>"/>
			<fmt:formatNumber value="${afterParse * 3}" type="currency" />
			<fmt:formatDate value="${afterDate}" type="both"/>
	3. functions
		${fn:length("test")}
		${fn:substringAfter("test","e")}
		${fn:substringBefore("test","e")}
		<%
			String[] array = new String[]{"1", "2", "3"};
			pageContext.setAttribute("array", array);
 		%>
		${fn:length(array)}
		${fn:join(array,"|")} <!-- forTokens의 반대. 하나의 문장으로 만듦 -->
</pre>
</body>
</html>