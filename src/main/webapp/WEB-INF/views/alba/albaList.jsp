<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Insert title here</title>
		<jsp:include page="/includee/preScript.jsp"></jsp:include>
	</head>
	
	<body>
		<table class="table">
		<thead>
			<tr>
				<th>No</th>
				<th>아이디</th>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
				<th>학력</th>
				<th>성별</th>
				<th>이메일</th>
			</tr>	
		</thead>
		<tbody id="listBody">
			
		</tbody>
		<tfoot>
		<tr>
			<td colspan="7">
				<div>
					<select id="searchType">
						<option value="">전체</option>
						<option value="id">아이디</option>
						<option value="name">이름</option>
						<option value="career">직업</option>
					</select>
					<input type="text" id="searchWord" value="${param.searchWord}"/>
					<input type="button" value="검색" id="searchBtn"/>
					<input type="button" value="새글쓰기" id="insertBtn"
					 onclick="location.href='<c:url value="/alba/albaInsert.do"/>';" />
				</div>
				<div id="pagingArea">
				</div>
			</td>
		</tr>
	</tfoot>
	</table>
<form id="searchForm"> <!-- 페이지번호를 클릭했을때, 검색버튼 눌렀을 때 실행될 폼 -->
	<input type="text" name="page" />
	<input type="text" name="searchType" value="${param.searchType}"/><!--검색조건이 유지되어야하기때문에 -->
	<input type="text" name="searchWord" value="${param.searchWord}"/>
</form>	
<script type="text/javascript">
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
			action = "<c:url value='/alba/albaList.do' />";
		}
		$.ajax({
			url : action,
			data : queryString,
			method : method,
			dataType : "json",
			success : function(resp) {//resp = pagingVO = 언마샬링된 객체
				let dataList = resp.dataList; 
				let trTags = [];
				if(dataList.length>0){//검색결과가 있는 경우
						$(dataList).each(function(index, alba){ //element - 알바 한 명 : tr태그(행)를 만들어야함
							let eachTr = $("<tr>");
							eachTr.append(
								$("<td>").text(alba.rnum),
								$("<td>").text(alba.al_id),
								$("<td>").html(
										$("<a>").attr({
											"href" : "<c:url value='/alba/albaView.do'/>?who="+alba.al_id
											//뷰
										})
										.text(alba.al_name)
								),
								$("<td>").text(alba.al_age),
								$("<td>").text(alba.al_address),
								$("<td>").text(alba.gr_code),
								$("<td>").text(alba.al_gen),
								$("<td>").text(alba.al_mail)
							);
							trTags.push(eachTr);
						}); //반복문의 끝
						
					}else{//없는경우
						trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"8"
							}).text("알바 목록이 없음.")		
							)//.html의끝
						)//push의 끝
					}//else의 끝
					
					listBody.empty();//비우고
					listBody.append(trTags);//채우기
					let pagingHTML = resp.pagingHTML;
					pagingArea.empty();
					pagingArea.html(pagingHTML);
					
			},
			error : function(xhr) {
				console.log(xhr.status)
			}
		});
		return false; //써밋취소
	});
	
	var searchBtn =$("#searchBtn").on("click",function(){
		let searchType = $("#searchType").val(); 	
		let searchWord = $("#searchWord").val();	
		searchForm.find("[name='searchType']").val(searchType);
		searchForm.find("[name='searchWord']").val(searchWord);
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	})
	
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