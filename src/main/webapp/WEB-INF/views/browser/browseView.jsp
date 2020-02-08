<%@page import="kr.or.ddit.web05_model2.FileWrapper"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<style>
	div{
		float:left;
		margin-right: 50px;
	}
</style>
</head>
<body>
<div>
<ul id="leftUL">
<%
	String contextRootRP = (String) request.getAttribute("contextRootRP");
	FileWrapper[] wrappers = (FileWrapper[]) request.getAttribute("listWrappers");
	for(FileWrapper tmp : wrappers){
		
		
		%>
		<li class="<%=tmp.getClassName()%>" id="<%=tmp.getId() %>">
			<%=tmp.getName() %></li>
		<%
	}
%>
</ul>
</div>
<div>
<ul id="rightUL">
<%
	 contextRootRP = (String) request.getAttribute("contextRootRP");
	 wrappers = (FileWrapper[]) request.getAttribute("listWrappers");
	for(FileWrapper tmp : wrappers){
		
		
		%>
		<li class="<%=tmp.getClassName()%>" id="<%=tmp.getId() %>">
			<%=tmp.getName() %></li>
		<%
	}
%>
</ul>
</div>


<form id="brForm">
	<input type="hidden" name="browsingURL" />
	<!-- 폼은기본적으로 동기요청. 취소시켜야함 -->
</form>
<script type="text/javascript">
	var brForm = $("#brForm");
	var leftUL = $("#leftUL");
	var rightUL = $("#rightUL");
	brForm.on("submit",function(event){
		event.preventDefault();//동기요청 취소
		let queryString = $(this).serialize();//데이터가 여러개(리스트 또는 배열 형태)일 경우 자주 사용하며, 받는 부분에서 name이 선언되어야 정상적으로 받을 수 있다.
		$.ajax({
			data : queryString,
			dataType : "json", //객체의형태로받음
			success : function(resp) {//listWrappers 배열
				if(!resp) return;//없을때
				let liTags = [];
				$(resp).each(function(index, fileWrapper){//인덱스,엘리먼트참조
					console.log(fileWrapper);
					liTags.push($("<li>").attr({
						"id" : fileWrapper.id,
						"class" : fileWrapper.className
					}).text(fileWrapper.name));
				
				});
					leftUL.empty();
					leftUL.append(liTags);
			},
			error : function(xhr) {
				console.log(xhr.status)
			}
		});
	});
	
	var browsingURL = brForm.find("[name='browsingURL']");
	leftUL.on("dblclick", ".dir", function(){ //버블링★★★★★
		//.on( events [, selector ] [, data ], handler )    ---- A function to execute when the event is triggered. 
		var path = $(this).prop("id");
		console.log(path);
		browsingURL.val(path);
		brForm.submit();
	});
	
</script>
</body>
</html>










