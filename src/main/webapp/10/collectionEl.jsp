<%@page import="java.util.Properties"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>EL을 이용한 Collection 접근 방법</h4>
<%
	String[] array = new String[]{"element1","element2","element3"};
	pageContext.setAttribute("array", array);

	List<String> list = new ArrayList<>();
	list.add("listElemnet1");
	list.add("listElemnet2");
	pageContext.setAttribute("list", list);
	
	Map<String,Object> map = new HashMap<>();
	map.put("key1","mapValue1");
	map.put("key2","mapValue2");
	map.put("key-3","mapValue3");
	pageContext.setAttribute("map", map);
	pageContext.setAttribute("member", new MemberVO("a001","asdfasdf"));
	Set<String> set = new HashSet<>();
	set.add("serValue1");
	set.add("serValue2");
	pageContext.setAttribute("set", set);
	Properties props = new Properties();
	props.put("prop1","propValue1");
	props.put("prop2","propValue2");
	pageContext.setAttribute("props", props);
%>
${array[1]}, <%=array[1] %>
${array[4]}, <%-- array[4] --%>
**EL은 exception대신 whitespace 출력

\${list.get(4)}, ${list[4]}
**오른쪽 형태를 더 많이 쓴다.

${map.get("key2")}, ${map.key2 }
**톰캣6에서는 메서드 호출못함~!
**순서가 없고 element가 아닌 entry이다.

${map.get("key-3")}, ${map.key-3} 
** -가 연산자로 인식이됨.
**java의 식별자 규칙에 따른 식별자만 사용 가능

${map["key-3"]}
** 대신 연산배열 구조를 사용할 수 있다.

${member.getMem_pass()}, ${member.mem_pass} , ${member["mem_pass"]}

\${member.mem-pass}

${set}
**set은 element접근 불가

${props.get("prop1")}, ${props.prop1}, ${props["prop1"]}
**사용방법이 map과 동일
</body>
</html>