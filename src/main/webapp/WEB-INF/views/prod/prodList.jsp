<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.util.List"%>
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
<!-- 입력폼 , 전송폼을 따로 만들어주어서 검색명을 지우고 페이지를 넘겨도 조건이 유지되도록 한다. -->
<form id="searchForm">
<input type="text" name="page" />
<input type="text" name="prod_lgu" value="${param.prod_lgu}"/>
<input type="text" name="prod_buyer" value="${param.prod_buyer}"/>
<input type="text" name="prod_name" value="${param.prod_name}"/>
</form>
<div id="inputForm">
<select name="prod_lgu">
	<option value="">상품분류</option>
	<c:forEach items="${lprodList }" var="lprodMap">
		<option value="${lprodMap['lprod_gu'] }">${lprodMap["lprod_nm"] }</option>
	</c:forEach>
</select>
<select name="prod_buyer">
	<option value="">거래처</option>
	<c:forEach items="${buyerList }" var="buyer">
		<option value="${buyer.buyer_id }">${buyer.buyer_name }</option>
	</c:forEach>
</select>
상품명 : <input type="text" name="prod_name" value="${param.prod_name}" />
<input type="button" value="검색" id="searchBtn"/>
<input type="button" value="신규등록" id="insertBtn"/>
</div>


<table class="table">
	<thead>
		<tr>
			<td>상품코드</td>
			<td>상품명</td>
			<td>입고일</td>
			<td>구매가</td>
			<td>판매가</td>
			<td>마일리지</td>
			<td>거래처명</td>
			<td>상품분류명</td>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
	

	<tfoot>
		<tr>
			<td colspan="8">
				<div id="pagingArea">
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	$("#insertBtn").on("click",function(){
		location.href = "<c:url value='/prod/prodInsert.do'/>";
	});
	var prod_buyerTag = $("[name='prod_buyer']");
	var prod_lguTag = $("[name='prod_lgu']");
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm").on("submit",function(event){
		event.preventDefault(); //써밋취소
		//요청이 발생하지 않는 구조가 됨. 코드에 의해 비동기로 요청이 발생하게 끔 만들기
		let queryString = $(this).serialize();
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		if(!method){
			method = "get";
		}
		if(!action){//액션이없으면
			action = "<c:url value='/prod/prodList.do'/>";
		}
		$.ajax({
		    //url : action이라는 속성에 의해 결정, 없어서 생략하면 현재 브라우저의 주소 "전체"가 됨 (그럼 url뒤에 계속 페이지가 붙는다.->에러남)
		    url : action,
			data : queryString, //모든 input태그를 통해 전달할 값들. get방식쓰니까 쿼리스트링.
			method : method,
			dataType : "json", //응답을 받아올 형태 (accept요청헤더,contentType응답헤더를 결정)
			//application/json가 되어야하는데 java application은 object로 되니까 자바객체를 json으로 바꾸고 
			//byte로 바꾼후 스트림을 통해 실어보낸다.
			//받는쪽에서는 역직렬화,언마샬링
			success : function(resp) { //resp는 pagingVO
				//console.log(resp);
				
				let dataList = resp.dataList; 
				let trTags = [];
				if(dataList.length>0){//검색결과가 있는 경우
						$(dataList).each(function(index, prod){ //element - 상품 한 건 : prod, tr태그(행)를 만들어야함
							let eachTr = $("<tr>");
							eachTr.append(
								$("<td>").text(prod.prod_id),
								$("<td>").html(
									$("<a>").text(prod.prod_name)
											.attr({
												href:"${pageContext.request.contextPath}/prod/prodView.do?what="+ prod.prod_id
											})
								),
								
								$("<td>").text(prod.prod_insdate),
								$("<td>").text(prod.prod_cost),
								$("<td>").text(prod.prod_price),
								$("<td>").text(prod.prod_mileage),
								$("<td>").text(prod.buyer_name),
								$("<td>").text(prod.lprod_nm)
							);
							trTags.push(eachTr);
						}); //반복문의 끝
						
					}else{//없는경우
						trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"8"
							}).text("제품 목록이 없음.")		
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
		//input폼에있는 값을 가져와서 폼태그에 넣어주고 써밋해준다.
		var inputTags = inputForm.find(":input[name]"); //입력받는 태그들중 name속성을 가진애들을 다 가져온다.
		$(inputTags).each(function(index, element){
			let name = $(this).attr("name"); //현재 input태그가 가지고 있는 name속성값
						//element
			let value = $(this).val();
			let searchFormInput = searchForm.find("input[name="+name+"]"); //동일한 name값을 가지는 element selecting
			searchFormInput.val(value);
		});
		//검색할때마다 1페이지를 요청할 수 있도록 조건 설정****
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	
	prod_buyerTag.val("${param.prod_buyer}"); //selected설정
	prod_lguTag.val("${param.prod_lgu}");

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