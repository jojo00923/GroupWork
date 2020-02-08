<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<%-- <form action="<%=request.getContextPath()%>/07/modulization.do"> --%>
	<form>
		최소단 : <input name="minDan" type="number" min="2" max="9"/>
		최대단 : 
		<select name="maxDan">
			<option value="">단 선택</option>
			<%
				for(int dan=2; dan<=9; dan++){
					%>
					<option value="<%=dan%>"><%=dan%>단</option>
					<%
				}
			%>
		</select>
		<input type="submit" value="구구단"/>
		<input type="hidden" name="command" value="GUGUDANPRO"/>
	</form>
