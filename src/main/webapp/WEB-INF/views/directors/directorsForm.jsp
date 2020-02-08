<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.DirectorVO"%>
<%@page import="java.util.Set"%>
<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo"%>
<%@page import="java.util.Enumeration"%>
<%@page import="kr.or.ddit.enumpkg.DirectorInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function changeHandler(){
		document.forms[0].submit();
		//=document.directorForm
	}
</script>
</head>
<body>

<form name ="directorForm" method="post">
	<%
		Set<DirectorVO> dataSet = (Set)application.getAttribute("dataSet");
	%>
	<select name="director" required onchange="changeHandler();">
		<option value="">영화감독</option>
		<%--
		
		DirectorInfo[] info = DirectorInfo.values();
		String ptrn = "<option value='%s'>%s</option>";
		for(DirectorInfo info2 : info) {
			String nick = info2.getData().getNick();
			String path = info2.getData().getPath();
			//out.println(String.format(ptrn, path, nick!=null?nick:info2.getData().getName()));
			out.println(String.format(ptrn, info2.name(), nick!=null?nick:info2.getData().getName()));
		}
	
		--%>
		<%
			String ptrn = "<option value='%s'>%s</option>";
			for(DirectorVO tmp:dataSet){
				out.println(String.format(ptrn, tmp.getCode(), 
						Objects.toString(tmp.getNickname(),tmp.getName())));//닉네임이 없으면 이름을 넘김
						
			}
		%>
	</select>
</form>
</body>
<!-- <script type="text/javascript">
 	$('select').on('change',function(){
 		var form = $(this).parent("form");//파라미터생략가능
		form.submit();
	}) 
</script> -->
</html>