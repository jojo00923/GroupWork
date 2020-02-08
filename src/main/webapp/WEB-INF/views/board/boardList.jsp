<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<c:if test="${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
</head>
<body>
<!-- 검색명을 지우고 페이지를 넘겨도 조건이 유지된다. -->
<form id="searchForm"> <!-- 입력받을때 ui와 전송할때 ui를 분리할 수 있음 -->
<input type="text" name="page" />
<input type="text" name="searchWord" value="${param.searchWord}"/>
<input type="text" name="searchType" value="${param.searchType}"/>
</form>

<div id="inputForm">
<select id="searchType">
	<option value="">전체</option>
	<option value="writer">작성자</option>
	<option value="title">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" id="searchWord" value="${param.searchWord}"/>
<input type="button" value="검색" id="searchBtn"/>
<input type="button" value="새글쓰기" id="insertBtn"/>
</div>


<table class="table">
	<thead>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>추천수</td>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
	

	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	$("#insertBtn").on("click",function(){
		location.href = "<c:url value='/board/boardInsert.do'/>";
	});
	
	var searchTypeTag = $("#searchType");
	searchTypeTag.val("${param.searchType}");
	var searchWordTag = $("#searchWord");
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	
	var searchForm = $("#searchForm").on("submit",function(event){
		event.preventDefault();
		let queryString = $(this).serialize();
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		if(!method){
			method = "get";
		}
		if(!action){//액션이없으면
			action = "<c:url value='/board/boardList.do'/>";
		}
		$.ajax({
		    url : action,
			data : queryString,
			method : method,
			dataType : "json", //응답을 받아올 형태 (accept요청헤더,contentType응답헤더를 결정)
			//application/json가 되어야하는데 java application은 object로 되니까 자바객체를 json으로 바꾸고 
			//byte로 바꾼후 스트림을 통해 실어보낸다.
			//받는쪽에서는 역직렬화,언마샬링
			success : function(resp) { //resp는 pagingVO
				
				let dataList = resp.dataList; 
				let trTags = [];
				if(dataList.length>0){//검색결과가 있는 경우
						$(dataList).each(function(index, board){
							let eachTr = $("<tr>");
							eachTr.append(
								$("<td>").text(board.bo_no),
								$("<td>").html(
									$("<a>").text(board.bo_title)
											.attr({
												href:"${pageContext.request.contextPath}/board/boardView.do?what="+ board.bo_no
											})
								),
								
								$("<td>").text(board.bo_writer),
								$("<td>").text(board.bo_date),
								$("<td>").text(board.bo_hit),
								$("<td>").text(board.bo_like)
							);
							trTags.push(eachTr);
						}); //반복문의 끝
						
					}else{//없는경우
						trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"6"
							}).text("게시글 목록이 없음.")		
						)//.html의끝
						
						)//push의 끝
					}//else의 끝
					
					//tr태그가 여러개 있을 수 있다는 가정을 하고 
					listBody.empty();//비우고
					listBody.append(trTags);//채우기
					let pagingHTML = resp.pagingHTML;
					pagingArea.empty(); 
					//**매번 비동기로 응답을 받을때마다 다 비워버리는데 아래 $(".pagination").on("click","a",function(event){ 
					//가 고정되지않아서 에러난다. .pagination--> pagingArea로 바꿔줌
					pagingArea.html(pagingHTML);
				
			}, //success의 끝
			error : function(xhr) {
				console.log(xhr.status)
			}
		});
		return false; //써밋취소
	});
	var inputForm = $("#inputForm");
	var searchBtn = $("#searchBtn").on("click",function(){
		let searchType = searchTypeTag.val(); //let은 scope가 괄호안으로 제한!!!
		let searchWord = searchWordTag.val();
		searchForm.find("[name='searchType']").val(searchType);
		searchForm.find("[name='searchWord']").val(searchWord);
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	
	pagingArea.on("click","a",function(event){
		event.preventDefault();//a태그의 클릭이벤트 취소
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;//안전빵

	});
	
	searchForm.submit();
</script>
</body>
</html>