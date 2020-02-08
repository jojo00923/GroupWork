<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<!-- 화면전환 되어서는 안됨. -->
	<!-- <li><a href="javascript:handler('STANDARD');">StandardJSP</a></li> -->
	<!-- <li><a href="#" onclick="handler('IMAGESTREAMING');">이미지스트리밍</a></li> -->
	<li><a href="#" data-command="STANDARD">StandardJSP</a></li> <!-- #:요청발생x -->
	<li><a href="#" data-command="GUGUDAN">구구단</a></li>
	<li><a href="#" data-command="IMAGESTREAMING">이미지스트리밍</a></li>
	<li><a href="#" data-command="VIDEOSTREAMING">동영상스트리밍</a></li>
	<li><a href="#" data-command="CALENDAR">달력</a></li>
	<li><a href="#" data-command="SESSIONTIMER">세션타이머</a></li>
</ul>
<form action="">
<!-- 파라미터는 servlet대상으로해서 넘어감 -->
	<input type="hidden" name="command"/>
</form>
<script type="text/javascript">//트래버싱을 매번 다시하기 때문에 밖으로 빼줌
	var commandTag = $("[name='command']");
	$("#left a").on("click",function(){
		let command = $(this).data("command"); //scope를 명확하게 표현하고 싶을때 let	
		commandTag.val(command);//값이 동적으로 채워짐
		commandTag.closest("form").submit(); //제일가까운폼태그
	});
</script>