<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	: 서버에서 만들어져서 브라우저에 저장. 나중에 다시 서버로 되돌아간다.
	Http Stateless 의 단점을 보완하기 위한 '최소한의 상태정보'를 클라이언트 쪽에 저장하는 구조.
	쿠키는 기본적으로 header를 통해 전달되는 데이터	
	** session - 최소한의 상태정보를 서버쪽에 저장하는 구조
	
	
	쿠키 사용 단계
S
	1. 쿠키 생성
	2. 응답에 실어 클라이언트쪽으로 전송
C
	3. 브라우저가 '자기 쿠키 저장소'에 저장
	4. 다음번 요청에 쿠키 포함시켜 서버로 재전송
S
	5. 요청에 포함된 쿠키를 통해 상태를 복원	
	ex)노래 재생목록	
	
	<%
		String value= URLEncoder.encode("한글 쿠키","UTF-8");
		//1.
		Cookie sampleCookie = new Cookie("sampleCookie",value); //쿠키는 header에서 주고받기 때문에 인코딩 고려!
		sampleCookie.setSecure(true); //ssl
		//2.
		response.addCookie(sampleCookie);
		
		//5.복원
		
		/* Cookie[] cookies = request.getCookies();
		String search = null;
		if(cookies!=null){
			for(Cookie tmp :cookies){
				if("sampleCookie".equals(tmp.getName())){
					search = URLDecoder.decode(tmp.getValue(),"UTF-8");
					break;
				}
			}
		} */
		
		/* value = URLEncoder.encode("domain Test cookie","UTF-8");
		Cookie domainTestCookie = new Cookie("domainCookie",value);
		domainTestCookie.setDomain("www.naver.com");//? 전혀 일치하지 않아서 실행시 khj.com으로 바뀜
		response.addCookie(domainTestCookie);
		
		//모든서버에 대해서 다 넘기고싶을때		
		Cookie allDomainCookie = new Cookie("allDomain","AllDomainCookie");
		allDomainCookie.setDomain(".khj.com");
		response.addCookie(allDomainCookie); 
 */
 
 		Cookie allPathCookie = new Cookie("allPathCookie", "all~path");
 		allPathCookie.setPath(request.getContextPath()+"/07"); 
 		response.addCookie(allPathCookie);
 		
 		Cookie longLiveCookie = new Cookie("longLiveCookie","Long~");
 		//longLiveCookie.setMaxAge(60*60*24*3); //3일동안 살려놓음
 		longLiveCookie.setMaxAge(0); //남기자마자 지워짐.
 		//longLiveCookie.setPath("/"); //webstudy01외에 다른곳에서도 보임
 		response.addCookie(longLiveCookie);
%>
	<%-- <%=search %> --%>
	
	<a href="viewCookie.jsp">동일 경로에서 쿠키 확인하러 가기</a>
	<a href="<%=request.getContextPath()%>/07/viewCookie.jsp">다른 경로에서 쿠키 확인하러 가기</a>
	
	
	쿠키 속성
	1.name (required)
	2.value (required): 특수문자 고려(encoding/decoding)
	3.domain(host) : 쿠키의 재전송 여부를 결정하는 기준
		c.f) domain 구조(레벨 구조), 하위레벨이 상위 레벨에 포함 구조.
		3레벨(GTLD global top level domain) www(hostname).naver.com
		4레벨(NTLD national top level domain) www.naver.co.kr, news.naver.co.kr
		ex) .naver.com 
			=> 해당 기관에 소속된 모든 호스트를 대상으로 재전송.
	4.path : 쿠키의 재전송 여부를 결정하는 기준
			 생략하면, 쿠키의 생성 경로가 그대로 반영(기본값).
			 쿠키의 path로 발생하는 요청이나 path이하의 경로로 발생하는 요청에 대해서만 재전송.
	5.expires/max-age : 쿠키의 저장 시간을 제어(기본값, 현재 세션과 동일).
		max-age(0) : 쿠키 삭제(주의! 나머지 속성이 동일한 경우에 한함.)
		max-age(-1) : 브라우저 종료시 삭제됨.
	6.secure
	
	***name,domain,path가 하나!! 하나라도 다르면 다른 쿠키가 된다.
</pre>
</body>
</html>