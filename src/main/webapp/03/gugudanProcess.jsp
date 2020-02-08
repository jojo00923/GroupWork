<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style type="text/css">
	td{
		border:1px solid black;
	}
	table{
		border-collapse:collapse;
	}
	.red{
		background-color: red;
	}
</style>
</head>
<body>	
${param.minDan}, ${param.maxDan}
1)파라미터가 있는지
2)mindan이 maxdan보다 작은지 확인
forEach에 step은 무조건 양수만!

<c:set var="minDan" value="2"/>
<c:if test="${not empty param.minDan }">
	<c:set var="minDan" value="${param.minDan}"></c:set>
</c:if>
<c:set var="maxDan" value="9"/>
<c:if test="${not empty param.minDan }">
	<c:set var="maxDan" value="${param.maxDan}"></c:set>
</c:if>

	<table>
		<tbody>	
			<!-- LoopTagStatus 객체 참조 : varStatus
			int : begin, end, step, index, count
			boolean : first, last -->
			<c:forEach var="dan" begin="${minDan}" end="${maxDan}" step="1"
				varStatus="vs"
			>
				<c:choose>
					<c:when test="${vs.count eq 3}">
						<tr style="background:yellow">
					</c:when>
					<c:otherwise>
		    			<tr>
		    		</c:otherwise>
		    	</c:choose>
				<c:forEach var="mul" begin="1" end="9" varStatus="vsInner">
					<c:if test="${vsInner.first or vsInner.last}">
						<td class="red">
					</c:if>
					<c:if test="${not vsInner.first and not vsInner.last}">
						<td>
					</c:if>
						${dan} * ${mul} = ${dan*mul} 
					</td>
				</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>	
</html>