<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Enumeration"%>
<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<!-- 1.requestdescservlet과 동일한 결과로 응답생성
2.map -> enum으로 변경
3.클라이언트가 원하는 헤더를 선택할 수 있도록
4.headername파라미터의 존재여부에 따라 두번째 table 태그가 변경됨 -->
	<form action="">
		<select name="headerName">
				<option value="">헤더명 선택</option>
				<%
					Enumeration<String> names = request.getHeaderNames();
					String param = request.getParameter("headerName");
					String ptrn = "<option %s>%s</option>";
					
					Map<String, String> headers = new LinkedHashMap<>();//아래에서 쓸 맵 생성
				while (names.hasMoreElements()) {
					  String hName = names.nextElement();
					  String value = request.getHeader(hName); //어차피 아래에서 써야해서 반복문 돌릴때 구해놓는다.
					  headers.put(hName,value);					  
					  String selected = hName.equals(param)? "selected": ""; //옵션 클릭시 선택되어있게끔
					  //hName.equals(param)- null이 나올수 없다. param.equals(hName) -null일 가능성이있다.
					  out.println(String.format(ptrn, selected, hName));
				}
				%>
		</select>
	</form>
<table>
	<thead>
		<tr>
			<th>데이터명</th>
			<th>데이터값</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>프로토콜(Line)</th>
			<td><%= request.getProtocol() %></td>
		</tr>
		<tr>
			<th>request URI(Line)</th>
			<td><%= request.getRequestURI() %></td>
		</tr>
		<tr>
			<th>Http method(Line)</th>
			<td><%= request.getMethod() %></td>
		</tr>
		<tr>
			<th>only Post meta data(request Body 있을때)</th>
			<td>
			<%= request.getCharacterEncoding() %>,
			<%=request.getContentLength() %>,
			<%=request.getContentType() %>
			</td>
		</tr>
		<tr>
			<th>context Path(client side absolute path)</th>
			<td><%=request.getContextPath() %></td>
		</tr>
	</tbody>
</table>	
<table>
	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
		<%
			String specific = null;
			String trPtrn = "<tr><th>%s</th><td>%s</td></tr>";
			if(param !=null && param.trim().length()>0){ //whitespace체크 필수~!
				specific = request.getHeader(param);//헤더값가져오기
				if(specific!=null){//헤더 하나가 선택됐을때
					out.println(String.format(trPtrn, param, specific));
				}else{//선택된 헤더가 없을 때 모든헤더 보여주기
					for(Entry<String,String> entry:headers.entrySet()){
						out.println(String.format(trPtrn,entry.getKey(),entry.getValue()));
					}
				}
			}
		%>
	</tbody>
</table>
<%
	BrowserType browser = BrowserType.findBrowser(headers.get("user-agent"));
	String browserName = browser.getData().getName();
	
%>
<script type="text/javascript">
	alert("당신의 브라우저는 <%=browserName%> 입니다.");
		$("[name='headerName']").on("change",function(){
				var form = $(this).parent("form");//파라미터생략가능
				form.submit();
		})
</script>	
</body>
</html>