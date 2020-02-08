<%@page import="java.io.InputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>applicationDesc.jsp</title>
</head>
<body>
<h4>ServletContext application</h4>
<pre>
	: 현재 컨텍스트(웹어플리케이션,서버)에 대한 정보를 가진 객체
	CAC(Context Aware Computing) - 주변상황 인지 컴퓨팅,중심은 서블릿이 된다.
	
	1.MIME 확인 : <%=application.getMimeType("test.jpg") %>
			servers - web.xml에 확장자에 대한 마임이 설정되어있음
	2.로그 기록 : 서버대상. 디버깅 목적, 튜닝 목적의 시스템 분석
	  ★로깅 프레임웍
	<%
		application.log("테스트 로그 메시지"); //콘솔에 기록됨 클라이언트에게는 보이지 않음
	%>
	3.서버에 대한 정보 확인
		1) Dynamic Web Module version 확인 : 3.1(서블릿 스펙버전)
			<%=application.getMajorVersion()+"."+application.getMinorVersion() %>
		2) 서버에 대한 정보 
			<%=application.getServerInfo() %>
		3) <%=application.getContextPath() %>
	4.***** 웹 리소스 확보 : fileSystemPath getRealPath(url)
			InputStream getResourceAsStream(url)
			
			미션) /images/Desert.jpg를 06폴더에 복사하기.
			<%=application.getRealPath("/images/Desert.jpg") %> <!--  서버주소를 동적으로 받기 위해 -->
			url을 입력하면 파일시스템경로를 리턴해준다.
			url로부터 파일시스템 절대경로를 유추해서 찾기.
			06에 복사
			<%=application.getRealPath("/06") %>

			<%
			//파일시스템 절대경로를 통해 객체생성
			//File srcFile =new File(application.getRealPath("/images/Desert.jpg")); //네트워크 접근을 위한 가상의 경로
			File targetFolder =new File(application.getRealPath("/06"));
			//File targetFile = new File(targetFolder,srcFile.getName()); 
			File targetFile = new File(targetFolder,"Desert.jpg"); 
			
			byte[] buffer = new byte[1024];
			int length = -1;
			try(
				//FileInputStream fis = new FileInputStream(srcFile);
				InputStream fis = application.getResourceAsStream("/images/Desert.jpg"); //이파일에 해당하는 inputstream을 가져옴
				FileOutputStream fos = new FileOutputStream(targetFile);
			){
				// 읽어들이기
				while((length = fis.read(buffer))!=-1) {//1kb를 읽을수 있음
					// 출력하기
					fos.write(buffer,0,length);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//webcontent는 임시폴더라서 여기서 확인
			//D:\A_TeachingMaterial\7.JspSpring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0
			%>
</pre>
<img src="<%=application.getContextPath() %>/06/Desert.jpg"/>
</body>
</html>