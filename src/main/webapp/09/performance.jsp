<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.member.dao.MemberDAOImpl"%>
<%@page import="kr.or.ddit.member.dao.IMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Performance Check (Response Time)</h4>
<pre>
	response time : processing time(0.00001) + latency time(9.9999)
	소요시간 비중에 따라 튜닝할 대상을 정한다.
	<%
		//a001 사용자 한 번 조회, 한 번 출력 : 25ms
		//a001 사용자 100번 조회, 100번출력 : 1000ms
		//a001 사용자 한 번 조회, 100번출력 : 20ms
		//a001 사용자 100번 조회, 100번출력(pooling) : 20ms미만
		IMemberDAO memberDAO = MemberDAOImpl.getInstance();
		long start = System.currentTimeMillis();
		for(int i=1;i<=100;i++){
			MemberVO member = memberDAO.selectMember("a001");
			out.println(member.getMem_name()+", " +member.getMem_hp());
			//listener는 기본 connection을 20개정도 생성함. 100번돌리면 에러
			//시스템계정에서 변경 alter system set sessions=200 scope=spfile;
			//관리자 cmd에서 stopdb후 startdb
		}
		long end = System.currentTimeMillis();
		out.println(String.format("소요시간 : %d ms", (end-start)));
	%>
</pre>
</body>
</html>