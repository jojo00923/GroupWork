<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
	
		
		<c:if test="${not empty message}">
			<div class='error'>${message}</div>
			<c:remove var="message" scope="session"/>
		</c:if>
		
	${errors}
<form id="updateForm" action="${pageContext.request.contextPath}/member/memberUpdate.do"
	method="post" enctype="multipart/form-data">		


	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>
			${member.mem_id}
			<input class="form-control"  type="hidden" name="mem_id"
				 required value="${member.mem_id}" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input class="form-control" type="text" name="mem_pass" /> <!--  required는 client입장에서 보는 오류 -->
				<span class="error">${errors.mem_pass}</span>
			</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input class="form-control" type="text" name="mem_name"
				 value="${member.mem_name}" />
				<span class="error">${errors.mem_name}</span>
			</td>
		</tr>
		<tr>
			<th>기존 이미지</th>
			<td>
				<img src="data:image/*;base64,${member.imgBase64}"/>	<!-- 메소드호출 -->
			</td>
		</tr>
		<tr>
			<th>신규이미지 선택</th>
			<td>
				<input type="file" name="mem_image"/>
			</td>
		</tr>	
		
		<tr>
			<th>주민번호1</th>
			<td>${member.mem_regno1}</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.mem_regno2}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.mem_bir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input class="form-control" type="text" name="mem_zip"
				 value="${member.mem_zip}" required/>
				<span class="error">${errors.mem_zip}</span>
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input class="form-control" type="text" name="mem_add1"
				 value="${member['mem_add1']}" />
			<span class="error">${errors.mem_add1}</span>
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input class="form-control" type="text" name="mem_add2"
				 value="${member.mem_add2 }" required/>
				<span class="error">${errors.mem_add2}</span>
			</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><input class="form-control" type="text" name="mem_hometel"
				 value="${member.mem_hometel }" /></td>
		</tr>
		<tr>
			<th>사무실전번</th>
			<td><input class="form-control" type="text" name="mem_comtel"
				 value="${member.mem_comtel }" /></td>
		</tr>
		<tr>
			<th>휴대폰번호</th>
			<td><input class="form-control" type="text" name="mem_hp"
				 value="${member.mem_hp }" /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input class="form-control" type="text" name="mem_mail"
				 value="${member.mem_mail }" /></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input class="form-control" type="text" name="mem_job"
				 value="${member.mem_job }" /></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input class="form-control" type="text" name="mem_like"
				 value="${member.mem_like }" /></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input class="form-control" type="text" name="mem_memorial"
				 value="${member.mem_memorial }" /></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input class="form-control" type="text" name="mem_memorialday"
				 value="${member.mem_memorialday }" 
				placeholder="1999-09-09"
				pattern = "[0-9]{4}-\d{2}-\d{2}"/>
				<span class="error">${errors.mem_add1}</span>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.mem_mileage }</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.mem_delete }</td>
		</tr>
		<tr>
			<td colspan="2">
				<input class="btn btn-success" type="submit" value="저장"> <!-- 클릭이후에 타겟이 form이 됨 -->
				<input class="btn btn-warning" type="reset" value="취소">
				<input class="btn btn-info" id="delBtn" type="button" value="탈퇴"> <!-- 데이터전송목적 폼이 필요~! -->
			</td>
		</tr>
	</table>
</form>	
<form id="deleteForm" action="${pageContext.request.contextPath }/member/memberDelete.do" method="post">
	<input class="form-control" type="hidden" name="mem_pass" />
</form>
<script type="text/javascript">
	/* $('input[type=button]').on('click',function(){
		$('#oForm').attr('action','${pageContext.request.contextPath }/member/memberDelete.do').submit();
	}) */
	
	var deleteForm = $("#deleteForm");
	var inputPassTag = $("#updateForm [name='mem_pass']");
	var delPassTag = deleteForm.find("[name='mem_pass']");
	$("#delBtn").on("click", function(){
		var inputPass = inputPassTag.val();
		if(inputPass){
			delPassTag.val(inputPass);
			var result = confirm("탈퇴하시겠습니까?"); //true or false
			if(result){
				deleteForm.submit();		
			}
			inputPassTag.val("");
			delPassTag.val(""); //서밋후 비번 리셋
		}else{
			alert("비번 입력!!!");
			inputPassTag.focus();
		}
	});
	

</script>
</body>
</html>