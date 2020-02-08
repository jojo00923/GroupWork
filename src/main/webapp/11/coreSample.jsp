<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<div id="realtime">

</div>
<c:set var="cPath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
setInterval(() => {
	$.ajax({
		url : "${cPath}/11/crawler.jsp", 
		//SOP 동일출처의원칙으로 인해  localhost로 요청발생 후 daum페이지를 대신 접근  **프록시서버방식 사용
		dataType : "html", //index 페이지가져오기
		success : function(resp) {
			let part = $(resp).find(".realtime_part");
			$("#realtime").html(part);
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	});
}, 5000);
//5초마다 갱신 검색순위 가져오기
	
</script>
<form>
	<input type="url" name="siteURL" placeholder="https://www.naver.com"
		value="${param.siteURL}"/>
	<input type="checkbox" name="toSource" value="true"
		${param.toSource eq 'true'?"checked":"" }/> 소스로 가져오기
	<input type="submit" value="가져오기" />
</form>
<c:if test="${not empty param.siteURL }">
	<div>
		<c:import url="${param.siteURL }" var="contents"/>
		<c:out value="${contents }" escapeXml="${param.toSource eq 'true' }"></c:out>
	</div>
</c:if>
</body>
</html>