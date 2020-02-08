<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	</head>
	<body>
		<form id="imgForm" action="../image.do">
		<select name="image">
			<option value="">이미지선택</option>
			<%
				String ptrn = "<option %2$s value='%1$s'>%1$s</option>";
				String[] imageNames = (String[])request.getAttribute("imageNames");
				String imageName = (String)request.getAttribute("imageName");
				for(String name:imageNames){
					String selected = name.equals(imageName)?"selected":"";
					out.println(String.format(ptrn,name,selected));
				}
			%>
		</select>
		<input type="submit" value="전송"/>
		</form>
		<div id="imgContainer">
		<%
			if(imageName!=null){
		%>
			<img id="imgTag" src ="../image.do?image=<%=imageName%>"/>
		<%
			}
		%>
		</div>
		<script type="text/javascript">
			var imgContainer = $("#imgContainer");
			var imgTag = $("#imgTag");
			$("#imgForm").on("submit",function(event){
				event.preventDefault();
				return false; //?
			})
			
			$("select[name='image']").on("change",function(event){
				var value = $(this).val();
				console.log(imgTag.length);
				if(!imgTag.length){//0일때 true = imgtag가없을때
					//img태그만들고
					imgTag = $("<img>");
					imgContainer.html(imgTag).prop({
						id: "imgTag"
					});
				}
				//이미지태그가있을때
				var src = "../image.do?image="+ value;
				imgTag.attr("src",src);
			})
			
		</script>
	</body>
</html>