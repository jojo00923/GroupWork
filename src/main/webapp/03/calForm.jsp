<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/calForm.jsp</title>
<script type="text/javascript" src= "<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		var resultArea = $("#resultArea");
		$("#calForm").on("submit",function(event){
			event.preventDefault();//동기요청 취소시키기
			let action = $(this).attr("action");
			let queryString = $(this).serialize(); //파라미터 가져옴
			//console.log(data);
			let httpMethod = $(this).attr("method");
			 $.ajax({//★비동기요청 
				url : action, 			// 1.요청을 보낼 주소
				data : queryString, 	// 2.보낼 거
				method : httpMethod?httpMethod:"get", //3.보낼방법 : 폼에 메소드존재여부에 따라
				dataType: "json",	// 4.type결정 : html(text/html),xml(application/xml),json(application/json),text(text/plain)
				//c가 요구하는 형태로 설정한다. (req headers에 accept속성과 resp headers의 contenttype이 한 쌍)
				
				success : function(resp){//datatype에따라다름
						console.log(resp);
						resultArea.empty();
						resultArea.append(resp.result);
						resultArea.append(resp.now);				
				},
				error : function(xhr){
					console.log(xhr.status);
				}
			});  
			 
			return false;
		});
	});
</script>
</head>
<body>

<form id="calForm" action="<%=request.getContextPath() %>/calculator.do">
	<input type="number" name="leftOp" />
	<select name="operator">
		<%
			OperatorType[] operatorTypes = OperatorType.values();
			String ptrn = "<option value='%s'>%s</option>";
			for(OperatorType temp : operatorTypes){
				out.println(
					String.format(ptrn, temp.name(), temp.getSign())		
				);
			}
		%>
	</select>
	<input type="number" name="rightOp" />
	<button type="submit">=</button>
</form>
<div id="resultArea">


</div>
</body>
</html>










