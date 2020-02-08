<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<c:if test="${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
</head>
<body>

	<table class="table table-striped table-dark">
	<tr>
			<th>게시물번호</th>
			<td id="bo_no">${board.bo_no}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.bo_title}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="attatch"
					varStatus="vs"
				>
					${attatch.att_filename } ${not vs.last?"|":""}
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.bo_content}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.bo_writer}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.bo_date}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.bo_hit}</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>
				<span id="likeArea">${board.bo_like}</span>
				<c:if test="${not board.recommended}">
					<input type="button" id="likeBtn" value="추천" class="btn btn-info"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
						<table class="table-bordered">
								<thead class="thead-dark">
									<tr>
										<th>첨부파일명</th>
										<th>파일크기</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:set var="replyList" value="${board.attatchList}"/>
										<c:if test="${not empty attatchList }">
											<c:forEach items="${attatchList }" var="attach">
												<tr>
													<td>${attach.att_filename}</td>
													<td>${attach.att_size }</td>
												</tr>
											</c:forEach>
										</c:if>
									<c:if test="${empty attatchList }">
										<tr>
											<td colspan="2">첨부파일이 없음</td>
										</tr>
									</c:if>
								</tbody>
							</table>
			
			</td>
		</tr>
		
		
		<tr>
			<th>댓글</th>
			<td>
				<table class="table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>댓글번호</th>
							<th>댓글내용</th>
							<th>작성일</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:set var="replyList" value="${board.replyList}"/>
							<c:if test="${not empty replyList }">
								<c:forEach items="${replyList }" var="reply">
									<tr>
										<td>${reply.rep_no}</td>
										<td>${reply.rep_content }</td>
										<td>${reply.rep_writer }</td>
										<td>${reply.rep_date }</td>
									</tr>
								</c:forEach>
							</c:if>
						<c:if test="${empty replyList }">
							<tr>
								<td colspan="4">댓글이 없음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/board/boardUpdate.do" var="boardUpdateURL">
					<c:param name="what" value="${board.bo_no}"/>
				</c:url>
				<input type="button" value="게시물 수정" class="btn btn-info"
					onclick="location.href='${boardUpdateURL}';"/>
				
				<input type="button" id="deleteBtn" value="삭제" class="btn btn-success" 
					data-toggle="modal" data-target="#deleteModal"
				/>
				<input type="button" value="목록으로" class="btn btn-info" 
					onclick="location.href='<c:url value="/board/boardList.do"/>';"
				/>
				<input type="button" value="뒤로가기" class="btn btn-info" 
					onclick="history.back();"
				/>
			</td>
		</tr>
	</table>
	
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
     <form id="deleteForm" action="<c:url value='/board/boardDelete.do'/>" method="post">
        	<input type="hidden" name="bo_no" value="${board.bo_no }" />
        	비밀번호 : <input type="password" name="bo_pass" class="form-control"/>
     </form> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" id="modalDelBtn" class="btn btn-primary">삭제</button>
        
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
let deleteForm = $("#deleteForm");
let deleteModal = $("#deleteModal").on("hidden.bs.modal",function(){//비번입력 후 close - open하면 비번이 남아있는 단점해결
	deleteForm[0].reset(); //jquery는 모든 element를 배열로 관리  
});
$("#modalDelBtn").on("click",function(){//모달이 닫히지 않은 상태로 써밋되는 것 - 뒤로가기에서 다시 입력이 안됐었음 -->해결
	//써밋발생
	deleteForm.submit();
	//모달닫기
	deleteModal.modal("hide");
});

	let span = $("#likeArea");
$("#likeBtn").on("click", function(event){
	let likeBtn = $(this);
	$.ajax({
		url : "${pageContext.request.contextPath}/board/boardLike.do",
		data : {
			what:${board.bo_no}
		},
		method : "get",
		dataType : "json",
		success : function(resp) {
			if(resp.success){
				let txt = span.text();
				console.log(span);
				span.text(parseInt(txt)+1);
				likeBtn.prop("disabled", true);
			}else{
				alert(resp.message);
			}
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	});
});
			


</script>
</body>
</html>