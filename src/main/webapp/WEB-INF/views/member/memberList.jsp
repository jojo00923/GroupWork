<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<style>
	td{
		border : 1px solid black;
	}
	table{
		border-collapse :collapse;
	}
	.flag{
		height: 20px;
		width: 20px;
	}
</style>
<body>
<input type="image" id="ko" src="<c:url value='/images/korea.jpg'/>" class="flag"/>
<input type="image" id="en" src="<c:url value='/images/america.jpg'/>" class="flag"/>
<script type="text/javascript">
$(".flag").on("click",function(){
	let id = $(this).prop("id");
	location.href = "?language="+id;
});
</script>
<c:choose>
		<c:when test="${param.language eq 'ko'}">
			<fmt:setLocale value="ko"/>						
		</c:when>
		<c:when test="${param.language eq 'en'}">
			<fmt:setLocale value="en"/>						
		</c:when>
		<c:otherwise>
			<fmt:setLocale value="${pageContext.request.locale}"/>
		</c:otherwise>		
</c:choose>
	<table  class="table">
		<thead>
		
			<tr>
				<fmt:bundle basename="kr.or.ddit.msg.message" >
				<th><fmt:message key="member.mem_row"/></th>
				<th><fmt:message key="member.mem_id"/></th>
				<th><fmt:message key="member.mem_name"/></th>
				<th><fmt:message key="member.mem_hp"/></th>
				<th><fmt:message key="member.mem_mail"/></th>
				<th><fmt:message key="member.mem_add1"/></th>
				<th><fmt:message key="member.mem_mileage"/></th>
				</fmt:bundle>
			</tr>
		</thead>
		<tbody id="listBody">
		
		</tbody>
		
		<tfoot>
		<tr>
			<td colspan="7">
				<div>
					<select id="searchType">
					<fmt:bundle basename="kr.or.ddit.msg.message" >
						<option value=""><fmt:message key="search_all"/></option>
						<option value="id"><fmt:message key="search_id"/></option>
						<option value="name"><fmt:message key="search_nm"/></option>
						<option value="address"><fmt:message key="search_addr"/></option>
					</fmt:bundle>	
					</select>
					<input type="text" id="searchWord" value="${param.searchWord}"/>
					<input type="button" value="검색" id="searchBtn"/>
		<!-- MemberlistControllerServlet으로  searchType,searchWord,페이지번호를 넘겨야한다. -->
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
<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
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
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	
	var availableTags = [
	    "ActionScript",
	    "AppleScript",
	    "Asp",
	    "BASIC",
	    "C",
	    "C++",
	    "Clojure",
	    "COBOL",
	    "ColdFusion",
	    "Erlang",
	    "Fortran",
	    "Groovy",
	    "Haskell",
	    "Java",
	    "JavaScript",
	    "Lisp",
	    "Perl",
	    "PHP",
	    "Python",
	    "Ruby",
	    "Scala",
	    "Scheme"
	  ];
	  $( "#searchWord" ).autocomplete({
	    source: availableTags
	  });


	//모달창 뜨는 순간 이벤트
	$("#exampleModalLong").on("show.bs.modal", function(event){
		//console.log(event);
		//console.log(event.relatedTarget);
		let aTag = $(event.relatedTarget);
		//console.log(aTag.data("who"));
		let modalBody = $(this).find(".modal-body");
		$.ajax({
			url : "<c:url value='/member/memberView.do'/>",
			data : {
				who : aTag.data("who")//파라미터 보내기
			},
			method : "get",
			dataType : "html",
			success : function(resp) {//html소스
				modalBody.html(resp);
			},
			error : function(xhr) {
				console.log(xhr.status)
			}
		});
	});
	
	var searchTypeTag = $("#searchType");
	searchTypeTag.val("${param.searchType}");
	var searchWordTag = $("#searchWord");
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm").on("submit",function(event){
		event.preventDefault(); //22222써밋취소
		let queryString = $(this).serialize();
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		if(!method){
			method = "get";
		}
		if(!action){//액션이없으면
			action = "<c:url value='/member/memberList.do' />";
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
						$(dataList).each(function(index, member){ //element - 상품 한 건 : prod, tr태그(행)를 만들어야함
							let eachTr = $("<tr>");
							eachTr.append(
								$("<td>").text(member.rn),
								$("<td>").text(member.mem_id),
								$("<td>").html(
										$("<a>").attr({
											"href":"#",
											"data-toggle":"modal",
											"data-target":"#exampleModalLong"	
										})
										.text(member.mem_name)
										.data("who",member.mem_id) //datamap이 만들어지고 map의 key = who
								),
								$("<td>").text(member.mem_hp),
								$("<td>").text(member.mem_mail),
								$("<td>").text(member.mem_add1),
								$("<td>").text(member.mem_mileage)
							);
							trTags.push(eachTr);
						}); //반복문의 끝
						
					}else{//없는경우
						trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"7"
							}).text("회원 목록이 없음.")		
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
	var searchBtn = $("#searchBtn").on("click",function(){
		let searchType = searchTypeTag.val(); //let은 scope가 괄호안으로 제한!!!
		let searchWord = searchWordTag.val();
		searchForm.find("[name='searchType']").val(searchType);
		searchForm.find("[name='searchWord']").val(searchWord);
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	
	//ul태그의 a태그 select
	//비동기요청시 ".pagination"는 pagingArea.empty()로 인해 사라진다.
	//사라진요청에 대해서는 이벤트핸들러가 적용되지 않기 때문에 사라지지 않는 pagingArea을 대신 사용
	pagingArea.on("click","a",function(event){
		event.preventDefault();//a태그의 클릭이벤트 취소
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;//안전빵

	});
	
	searchForm.submit(); //검색을 누르지 않더라도 searchform에 요청이 발생하게끔
	//= 동기는 껍데기만 가져오고. 비동기를 통해 껍데기에 넣을 데이터를 가져와서 끼워넣는다.
	//network를 봤을 때 요청이 2개발생(동기,비동기)
	
</script>
</body>
</html>