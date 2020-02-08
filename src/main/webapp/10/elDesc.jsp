<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>EL(Expression Language, 표현 언어)</h4>
<pre>
	: 표현식을 대체하기 위한 "스크립트 형태"의 언어
	1. ★★ 네개의 영역(scope)를 통해 공유되는 속성(attribute)을 표현할 목적의 언어.★★
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "서블릿컨텍스트 속성");
		pageContext.setAttribute("sameName", "페이지에 있는 속성");
		request.setAttribute("sameName", "요청에 있는 속성");
	%>
	<%=pageContext.getAttribute("pageAttr") %>, ${pageAttr} 	★ scope의 식별이 안되기 때문에 범위가 제일 좁은 scope에서부터 찾아서 출력
	<%=session.getAttribute("sessionAttr") %>, ${sessionAttr}
	
	${sameName}
	${requestScope.sameName}	★이름이 겹치는 경우 콕 찝어서 사용할 때
								★빠른 접근을 위해 sessionScope이상은 scope를 명시해준다.
								
	<%=application.getAttribute("asdf") %> ${asdf} ★ EL은 필요없는 null문자가 출력되지 않는다.
	
	2. 연산자 지원 : 연산의 중심이 "연산자"이다. 피연산자(속성,값)
		1) 산술연산자(+-*/) : EL안에서 +는 concat 지원안됨.
			${3+5}, ${"3"+"5"} ==> EL이 문자를 내부적으로 파싱해서 둘 다 8 출력
			<%--${"3"+"a"} ==> java.lang.NumberFormatException: For input string: "a"  --%>
			${3+a} ==> a를 속성으로 간주하는 데 없어서 null. 내부적으로  0으로 변환 
			${abc-def}
			
			${3*4.5}, ${3/5} => EL은 실수연산을 기본으로 한다.
		2) 논리연산자 : &&(and), ||(or), !(not)  웬만하면 키워드로 사용!
			${true and true} , ${true and asdf }, ${asdf or true}, 
			${true or asdf}, ${false and asdf}, ${not asdf }
		3) 비교연산자 : >(gt), &lt;(lt), >=(ge), &lt;=(le), ==(eq), !=(ne)
			<%-- ${3 lt 5 }, ${4 ne 8 }, ${pageAttr eq "페이지 속성" } --%>
		4) 삼항연산자 : 조건식? 참표현:거짓표현
			${ 3 lt asdf ? "참":"거짓"}
		5) 단항연산자 : empty
			<%
			List list = new ArrayList();
			list.add("test");
			pageContext.setAttribute("asdf", list);
			%>
			${empty asdf } 
			-1.속성이 있는지 확인
			-2.null 체크
			-3.null이 아니면 타입체크후  length체크, collection이면 size체크
	3. 속성형태의 자바 객체에 대한 접근방법
		<%
			MemberVO member = new MemberVO("a001","asdfasdf");
			pageContext.setAttribute("member", member);
		%>
		${member.mem_id} =member.getMem_id()   속성명xx getter 호출. 자바bean규약으로 만들어져 있기 때문에 getter를 통해 가져올 수 있다.
		${member.getTest()}, ${member.test}
	4. 속성형태의 Collection에 대한 접근방법
		<a href="collectionEl.jsp">collectionEl.jsp 참고</a>
	5. EL의 기본객체 (11개)
		: pageScope, requestScope, sessionScope, applicationScope
		<a href="elObject.jsp">elObject.jsp참고</</a>
</pre>
</body>
</html>