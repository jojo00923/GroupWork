<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h4>서블릿 3.0 방식의 파일 업로드 처리</h4>
<pre>
	client UI 구성 방법
	1. form -> input(type="file") 태그
	2. method="post"로 컨텐츠 body영역 구성
	3. body영역을 여러개로 분리할 수 있는 enctype 설정
		multipart/form-data 
		
	server side 처리 방법
	1. Part API 사용 설정
		서버사이드 처리자(servlet)에 대한 multipart-config 설정
		location : chunk 단위로 전달되는 파일이 임시로 저장되는 영역
		max-file-size : file 하나당 업로드 제한 크기
		max-request-size : 하나의 요청의 업로드 제한 크기
		file-threshold-size(임계치) : 임계치 미만의 크기 - 메모리를 임시 저장 공간으로 사용
	2. request.getPart, getParts를 사용해서 Part에 대한 정보를 확보
		Part(servlet 3.0) : 입력 태그 하나를 통해 서버로 전달된 파트에 대한 정보를 캡슐화 
		 	c.f) servlet 2.5에서는 Part를 사용할 수 없음.
		 			--> commons-fileupload, cos.jar 로 대체가능 ☆
		1) 2진 데이터 처리 : middle-tier에 저장
		2) 메타 데이터 처리 : file name, 'save name', file size, content type... db에 저장 			
</pre>
<form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data">
<input type="text" name="param"/> 
<input type="text" name="param"/> 
<input type="file" name="uploadFile"/> 
<input type="file" name="uploadFile"/> 
<input type="submit" value="전송"/>
</form>
</body>
</html>