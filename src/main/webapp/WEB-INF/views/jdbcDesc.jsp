<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/jdbcDesc.jsp</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>

<pre>
	1. 벤더가 제공하는 드라이버를 빌드패스에 추가(ojdbc6.jar)
	2. driver class loading
	3. Connection 생성
	4. 쿼리 객체
		1) Statement (기본 쿼리 객체)
		2) PreparedStatement (선컴파일된 쿼리 객체)
		3) CallableStatement (프로시저 쿼리 객체)
	5. 쿼리 실행
		1) ResultSet executeQuery(sql) : select
		2) int(rowCount) executeUpdate(sql) : insert, update, delete
	6. ResultSet 핸들링
	7. 자원의 해제
</pre>
<%
	Map<String, Object> model = (Map) request.getAttribute("model");
	String[] headers =(String[]) model.get("headers");
	List<DataBasePropertyVO> list = (List) model.get("list");
%>
<table border=1>
	<thead>
	<%
		
 		out.print("<tr>");
 		for(String tmp : headers){
 			out.print("<td>"+tmp+"</td>");
 		}
 		out.print("</tr>");
		

	%>
	</thead>
	
	<tbody>
		<%
 			for(DataBasePropertyVO tmp : list){
 				out.print("<tr><td>"+tmp.getProperty_name()+"</td>");
 				out.print("<td>"+tmp.getProperty_value()+"</td>");
 				out.print("<td>"+tmp.getDescription()+"</td></tr>");
 			}
		 %>
	</tbody>
</table>
</body>
</html>