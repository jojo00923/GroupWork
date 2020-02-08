<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	int factorial(int num){
		if(num <= 0) throw new IllegalArgumentException(num+"는 연산 수행 불가");
		if(num==1) return num; 
		return num*factorial(num-1);
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="">
		<%
			String oper = request.getParameter("operand");
			int num = 1;
			/* if(oper == null || !oper.matches("[1-9]")){
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
			} */
			
			if(oper != null){
				num = Integer.parseInt(oper);
			}
			int a=1;
			for(int i=num; i>=1; i--){
				a *= i;
			}
		%>
	<input type="number" name="operand" value="<%=num%>"/>!
	<input type="submit" value="=" />
	<span>
		<%=a%>,
		recursive call value : <%=factorial(num) %>
		</span>
</form>
</body>
</html>