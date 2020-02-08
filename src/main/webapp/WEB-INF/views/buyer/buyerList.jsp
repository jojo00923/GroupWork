<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>

<form id="searchForm">
<input type="text" name="page" />
<input type="text" name="buyer_id" value="${param.buyer_id}"/>
<input type="text" name="lprod_gu" value="${param.lprod_gu}"/>
<input type="text" name="buyer_add1" value="${param.buyer_add1}"/>
</form>

<div id="inputForm">
<!-- 비동기라서 검색버튼 누르고도 선택조건이 남아있다. -->
<select name="buyer_id">
	<option value="">거래처명</option>
	<c:forEach items="${buyerList}" var="buyer">
		<option value="${buyer.buyer_id}">${buyer.buyer_name}</option>
	</c:forEach>
	
</select>
<select name="lprod_gu">
	<option value="">상품분류</option>
	
	<c:forEach items="${lprodList}" var="lprod">
		<option value="${lprod['lprod_gu']}">${lprod['lprod_nm']}</option>
	</c:forEach>
	
</select>
주소 : <input type="text" name="buyer_add1"/>
<input id="searchBtn" type="submit" value="검색"/>
<input type="button" value="신규등록" id="insertBtn"/>
</div>

<table class="table">
	<thead>
		<tr>
			<td>거래처코드</td>
			<td>거래처명</td>
			<td>거래은행</td>
			<td>주소1</td>
			<td>주소2</td>
			<td>회사번호</td>
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
			location.href = "<c:url value='/buyer/buyerInsert.do'/>";
		});
	var buyer_nameTag = $("[name='buyer_id']");
	var lprod_guTag = $("[name='lprod_gu']");
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var buyer_addTag = $("[name='buyer_add1']");
	var searchForm = $("#searchForm").on("submit",function(event){
		event.preventDefault(); 
		
		let queryString = $(this).serialize();
		let action = $(this).attr("action");
		let method = $(this).attr("method"); 
		if(!method){
			method = "get";
		}
		if(!action){//액션이없으면
			action = "<c:url value='/buyer/buyerList.do'/>";
		}
		
		$.ajax({
			url : action,
			data : queryString,
			method : method,
			dataType : "json",
			success : function(resp) {//resp는 pagingVO
				let dataList = resp.dataList;
				let trTags = [];
				if(dataList.length>0){
					$(dataList).each(function(index, buyer){
						let eachTr = $("<tr>");
						eachTr.append(
								$("<td>").text(buyer.buyer_id),
								$("<td>").html(
									$("<a>").text(buyer.buyer_name)
											.attr({
												href:"${pageContext.request.contextPath}/buyer/buyerView.do?what="
														+ buyer.buyer_id
											})
											
								),
								$("<td>").text(buyer.buyer_bank),
								$("<td>").text(buyer.buyer_add1),
								$("<td>").text(buyer.buyer_add2),
								$("<td>").text(buyer.buyer_comtel)
						);
						trTags.push(eachTr);
					});
				}else{
					trTags.push(
							$("<tr>").html(
								$("<td>").attr({
									colspan:"8"
								}).text("거래처 목록이 없음.")		
							)//.html의끝
							
					)//push의 끝
					
				}
				
				listBody.empty();
				listBody.append(trTags);
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

	
	var inputForm = $("#inputForm");
	var searchBtn = $("#searchBtn").on("click",function(){
		var inputTags = inputForm.find(":input[name]");
		$(inputTags).each(function(index, element){
			let name = $(this).attr("name");
			let value = $(this).val();
			
			let searchFormInput = searchForm.find("input[name="+name+"]");
			searchFormInput.val(value);
		});
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
		
	});
	
	
	//페이지가 넘어가도 조건이 유지되도록 처리!! 
	pagingArea.on("click","a",function(event){ 
		event.preventDefault();//이벤트 취소
		//this로 부터 페이지넘버 꺼내오기
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page); //form의 page value에 대입
		searchForm.submit();
		return false;
		
	})
	searchForm.submit();//페이지 랜더링되자마자 데이터를 비동기요청함.
</script>
</body>
</html>