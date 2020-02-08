<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/paramForm.jsp</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js">	
</script>
</head>
<body>
<pre>
	<a href="../param.do">param.do</a>
	<button id="clickBtn" type="button">클릭으로 요청 발생</button> <!-- 폼이없을때는 type = submit 의미없음 -->
</pre>
<!-- 훈련생 : 이름, 전화번호, 주소, 성별, 학력, 전공, 자격증, 생일 -->
<form action="../param.do" method="post">
	<pre>
이름 : <input type="text" name="name"/>
주소 : <input type="text" name="address"/>
생일 : <input type="date" name="birth" pattern="\d{8}" placeholder="20190101"/>
성별 : <label><input type="radio" name="gender" value="F"/>여</label><label><input type="radio" name="gender" value="M"/>남</label>
휴대폰 : <input type="text" name="hp" pattern="\d{3}-\d{3,4}-\d{4}" placeholder="010-000-0000"/>
학력 : <select name="grade">
<option value="">학력</option>
<option value="G001">초대졸</option>
<option value="G002">대졸</option>
<option value="G003">석사</option>
<option value="G004">박사</option>
</select>
자격증 : <select multiple name="license">
<option value="L001">정보처리기사</option>
<option value="L002">정보처리산업기사</option>
<option value="L003">정보보안기사</option>
<option value="L004">정보보안산업기사</option>
<option value="L005">SQLD</option>
<option value="L006">SQLP</option>
</select>
	</pre>
	<button type="submit">submit버튼</button>
</form>
</body>
<script type="text/javascript">
		$('#clickBtn').on('click',function(event){
			location.href="../param.do";
		})
</script>
</html>