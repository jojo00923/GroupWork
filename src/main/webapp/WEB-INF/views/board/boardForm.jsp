<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
</head>
<body>
<form id="boardForm" method="post" enctype="multipart/form-data">
<input type="hidden" name="bo_no" value="${board.bo_no}" />
	<table class="table table-striped table-dark">
	
		<tr>
			<th>제목</th>
			<td>
			<input class="form-control" type="text" 
						name="bo_title" value="${board.bo_title}" />
				<span class="error">${errors.bo_title }</span>
			</td>
		</tr>
		<tr>
			<th>기존파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="attatch"
					varStatus="vs">
					<span data-attno="${attatch.att_no}">${attatch.att_filename } <!-- 아이디속성은 숫자로만 구성할 수 없음 -->
						<button type="button" class="delBtn">삭제</button>
						${not vs.last?"|":""}</span>
				</c:forEach>
			</td>
		</tr>
		
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" name="bo_file" multiple/> <!-- 넘어가는 파일이 여러개 -->
			</td>
		</tr>
		
		
				<tr>
			<th>내용</th>
			<td>
				<textarea id="bo_content" name="bo_content" class="form-control">${board.bo_content }</textarea>
				<span class="error">${errors.bo_content }</span>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
			<input class="form-control" type="text" 
						name="bo_writer" value="${board.bo_writer}" />
				<span class="error">${errors.bo_writer }</span>
			</td>
		</tr>
	
		<tr>
			<th>비번</th>
			<td>
			<input class="form-control" type="text" 
						name="bo_pass" value="${board.bo_pass}" />
				<span class="error">${errors.bo_pass }</span>
			</td>
		</tr>

		<tr>
			<th>아이피</th>
			<td>
			<input class="form-control" type="text" 
						name="bo_ip" value="${pageContext.request.remoteAddr}" readonly />
				<span class="error">${errors.bo_ip }</span>
			</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>
			<input class="form-control" type="text" 
						name="bo_mail" value="${board.bo_mail}" />
				<span class="error">${errors.bo_mail }</span>
			</td>
		</tr>
	
		
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-success" />
				<input type="reset" value="취소" class="btn btn-warning"/>
				<input type="button" value="목록으로" class="btn btn-info" 
					onclick="location.href='<c:url value="/board/boardList.do"/>';"
				/>
			</td>
		</tr>
	</table>
	
	
	
</form>
<c:url value='/board/imageUpload.do' var="uploadUrl">
	<c:param name="type" value="Image"/> <!-- 이미지가 업로드되고있다는 의미의 쿼리스트링 -->
</c:url>
<script type="text/javascript">
CKEDITOR.replace('bo_content',{
	filebrowserImageUploadUrl  : "${uploadUrl}" 
});

var boardForm = $("#boardForm");

//<input type="hidden" name="delAttNos" value="1" 지우려는 파일의 번호/> <!-- 삭제할 첨부파일 담을 목적 -->
$(".delBtn").on("click",function(){
	let span = $(this).parent("span");
	let attNo = span.data("attno");
	span.hide();
	let input = $("<input>").attr({
		"type" : "text",
		"name" : "delAttNos" //같은이름으로 여러개의 파라미터가 넘어감 -- getParameterValues사용
	}).val(attNo); //동적으로 input태그 만듦
	boardForm.append(input);
	
});

</script>
</body>
</html>