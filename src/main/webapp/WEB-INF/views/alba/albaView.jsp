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
			<th>학력</th>
			<td>${alba["gr_name"]}</td>
		</tr>
		<tr>
			<th>자격증</th>
			<c:set var="licAlbaList" value="${alba.licAlbaList }" />
			<td>
				<c:if test="${not empty licAlbaList }">
					<c:forEach items="${licAlbaList }" var="licAlba">
						<a href="#"
							data-toggle="modal" data-target="#exampleModalLong"
							data-who="${licAlba.al_id }" data-code="${licAlba.lic_code }" >
						${licAlba.lic_name }</a><br>
					</c:forEach>
				</c:if>
			</td>
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
	
	<div class="modal fade bd-example-modal-xl" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	
<script>
	$("#exampleModalLong").on("show.bs.modal", function(event){
		let aTag = $(event.relatedTarget);
		let modalBody = $(this).find(".modal-body")
		$.ajax({
			url : "<c:url value='/alba/licenseImage.do'/>",
			data : {
				who : aTag.data("who"),
				code : aTag.data("code")
			},
			method : "get",
			dataType : "json",
			success : function(resp) {
				let image = $("<img>").attr({
					'class' : "imgGroup",
					'src' : "data:image/*;base64," + resp.imgBase64
				});
				modalBody.html(image);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	});
</script>	
</body>
</html>