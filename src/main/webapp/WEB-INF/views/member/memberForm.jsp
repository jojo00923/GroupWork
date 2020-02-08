<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		</c:if>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.HashMap" scope="request" /> <!-- 있으면 꺼내오고 없으면 새로생성 -->
<form action="<c:url value='/member/memberInsert.do'/>"
	method="post" id="insertForm" enctype="multipart/form-data">		


	<table class="table table-bordered">
	<tr>
			<th>회원아이디</th>
			<td><input class="form-control" type="text" 
				name="mem_id" value="${member.mem_id }" />
				<span class="error">${errors.mem_id }</span>
				<button id="idCheckBtn" type="button">아이디중복검사</button>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input class="form-control" type="text" 
				name="mem_pass" />
				<span class="error">${errors.mem_pass }</span></td>
		</tr>
		<tr>
			<th>회원이미지</th>
			<td>
				<input type="file" name="mem_image" />
			</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input class="form-control" type="text" 
				name="mem_name" value="${member.mem_name }" />
				<span class="error">${errors.mem_name }</span></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><input class="form-control" type="text" name="mem_regno1"
				value="${member.mem_regno1 }" />
				<span class="error">${errors.mem_regno1 }</span></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><input class="form-control" type="text" name="mem_regno2"
				value="${member.mem_regno2 }" />
				<span class="error">${errors.mem_regno2 }</span></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input class="form-control" type="date" name="mem_bir"
				value="${member.mem_bir }" />
				<span class="error">${errors.mem_bir }</span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input class="form-control" type="text" 
				name="mem_zip" value="${member.mem_zip }" />
				<span class="error">${errors.mem_zip }</span></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input class="form-control" type="text" 
				name="mem_add1" value="${member.mem_add1 }" />
				<span class="error">${errors.mem_add1 }</span></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input class="form-control" type="text" 
				name="mem_add2" value="${member.mem_add2 }" />
				<span class="error">${errors.mem_add2 }</span></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><input class="form-control" type="text" name="mem_hometel"
				value="${member.mem_hometel }" />
				<span class="error">${errors.mem_hometel }</span></td>
		</tr>
		<tr>
			<th>사무실전번</th>
			<td><input class="form-control" type="text" name="mem_comtel"
				value="${member.mem_comtel }" />
				<span class="error">${errors.mem_comtel }</span></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input class="form-control" type="text" name="mem_hp"
				value="${member.mem_hp }" />
				<span class="error">${errors.mem_hp }</span></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input class="form-control" type="text" 
				name="mem_mail" value="${member.mem_mail }" />
				<span class="error">${errors.mem_mail }</span></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input class="form-control" type="text" name="mem_job"
				value="${member.mem_job }" />
				<span class="error">${errors.mem_job }</span></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input class="form-control" type="text" name="mem_like"
				value="${member.mem_like }" />
				<span class="error">${errors.mem_like }</span></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input class="form-control" type="text" name="mem_memorial"
				value="${member.mem_memorial }" />
				<span class="error">${errors.mem_memorial }</span></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input class="form-control" type="date"
				name="mem_memorialday" value="${member.mem_memorialday }" />
				<span class="error">${errors.mem_memorialday }</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>3000</td>
		</tr>
	
		<tr>
			<td colspan="2">
				<input class="btn btn-success" type="submit" value="가입"> <!-- 클릭이후에 타겟이 form이 됨 -->
				<input class="btn btn-warning" type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>	
<script type="text/javascript">
	$("[type='date']").datepicker({
		dateFormat: "yy-mm-dd"
	});

	var insertForm = $("#insertForm").on("submit",function(){
		let idChecked = $(this).data("idchecked");
		if(!idChecked){
			alert("중복 검사하세요.");
			return false;
		}else{
			return true;
		}
	})
	var idInputTag = $("[name='mem_id']").on("change",function(){//타이핑 이벤트 발생시마다 idchecked를 false로 해줌
		insertForm.data("idchecked",false);//외부에서 수정할 수 없다.
	});
	
	
	$("#idCheckBtn").on("click", function(){
		//다른 항목들을 유지한 채로 중복여부 확인 ==> 비동기
		
		let inputId = idInputTag.val();
		if(!inputId){//null일 때
			alert("중복 검사할 게 있어야지!!");
			idInputTag.focus();
			return;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkId.do",
			data : {
				id : inputId //파라미터명 : 파라미터값
			},
			method : "post",
			dataType : "json", //Accept헤더,Content-Type 두가지 헤더 결정
			success : function(resp) { //resp = json데이터가 javascript객체로 언마샬링
				if(resp.status=="PKDUPLICATED"){
					idInputTag.val("");
					idInputTag.focus();
				}else{//중복확인까지 끝내고 submit버튼 기능이 되어야함
					insertForm.data("idchecked",true);	
				}
				idInputTag.after(
						$("<span>").addClass("message")
							//동일 $("<span>").addClass("message")
								.text(resp.message)
					);
			},
			error : function(xhr) {
				console.log(xhr.status)
			}
		});
	
	})
</script>
</body>
</html>