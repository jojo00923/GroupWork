<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>browseView</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<%-- ${pageContext.request.contextPath} = req.contextPath--%>
	</head>
	<body>
		<% 
		
		File[] list = (File[])request.getAttribute("list");
		String param = (String)request.getAttribute("param");
		String clickptrn = "<a href=%s/browse/contextBrowsing.do?path=%s>%s</a><br>";
		String ptrn = "<p>%s</p></br>";
		String result = "";
				
		for(File f : list) {
			if(f.isDirectory()){//폴더일 때
				 result = String.format
						(clickptrn,request.getContextPath(), param+"/"+f.getName(), f.getName());
				 out.println(result);
			}else{//파일일 때
				 result = String.format(ptrn,f.getName());
				 out.println(result);
			}
		}
		%>
	</body>
</html>