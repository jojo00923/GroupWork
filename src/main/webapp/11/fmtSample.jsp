<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/locales.tld" prefix="loc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<form>
<select name="country">
	<option value >국가</option>
	<c:forEach items="${loc:availableLocales()}" var="tmp">
		<c:if test="${not empty tmp.displayCountry }">
		<option value="${loc:toLanguageTag(tmp) }">${tmp.displayCountry }</option>
		</c:if>
	</c:forEach>
</select>
금액 : <input type="number" name="amount" />
</form>
<c:if test="${not empty param.amount}">
<div>
	<fmt:parseNumber value="${param.amount}" var="parsAmount"/>
	<c:if test="${not empty param.country}">
		<fmt:setLocale value="${param.country}"/>
	</c:if>
	<fmt:formatNumber value="${parsAmount}" type="currency" var="formatAmount"/>
	금액 : ${formatAmount}
</div>
</c:if>
<script type="text/javascript">
	$("[name='country']").val("${param.country}");
	$("[name='amount']").val("${param.amount}");
	$("[name='country'],[name='amount']").on("change", function(){
		$(this).closest("form").submit();
	});
</script>
</html>