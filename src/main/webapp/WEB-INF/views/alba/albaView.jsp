<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>

<body>
	<table class="table table_bordered">
		<tr>
			<th>아이디</th>
			<td>${alba["al_id"]}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${alba["al_name"]}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${alba["al_age"]}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${alba["al_address"]}</td>
		</tr>
		<tr>
			<th>핸드폰번호</th>
			<td>${alba["al_hp"]}</td>
		</tr>
		<tr>
			<th>특기사항</th>
			<td>${alba["al_spec"]}</td>
		</tr>
		<tr>
			<th>학력</th>
			<td>${grade["gr_name"]}</td>
		</tr>
		<tr>
			<th>경력사항</th>
			<td>${alba["al_career"]}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${alba["al_gen"]}</td>
		</tr>
		<tr>
			<th>혈액형</th>
			<td>${alba["al_btype"]}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${alba["al_mail"]}</td>
		</tr>
			<tr>
			<td colspan="2">
				<c:url value="/alba/albaUpdate.do" var="albaUpdateURL">
					<c:param name="what" value="${alba.al_id}"/>
				</c:url>
				<input type="button" value="게시물 수정" class="btn btn-info"
					onclick="location.href='${albaUpdateURL}';"/>
				
				<c:url value="/alba/albaDelete.do" var="albaDeleteURL">
					<c:param name="what" value="${alba.al_id}"/>
				</c:url>
				<input type="button" id="deleteBtn" value="삭제" class="btn btn-success" 
						onclick="location.href='${albaDeleteURL}';"/>
				<input type="button" value="목록으로" class="btn btn-info" 
					onclick="location.href='<c:url value="/alba/albaList.do"/>';"
				/>
				
			</td>
		</tr>	
	</table>
	
				
</body>
</html>