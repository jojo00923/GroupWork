<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error{
		color: red;
	}
</style>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session" />
</c:if>
<body>
${errors}
<form method="post" enctype="multipart/form-data">
	<table class="table table_bordered">
<%--  		<tr>
			<th>알바생코드</th>
			<td><input class="form-control" type="text" required
				name="al_id" value="${alba.al_id }" /><span class="error">${errors.al_id }</span></td>
		</tr>  --%>
		<input type="hidden" name="al_id" value="${alba.al_id }" />
		<tr>
			<th>이름</th>
			<td><input class="form-control" type="text" required
				name="al_name" value="${alba.al_name }" /><span class="error">${errors.al_name }</span></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input class="form-control" type="number" required
				name="al_age" value="${alba.al_age }" /><span class="error">${errors.al_age }</span></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input class="form-control" type="text" required
				name="al_address" value="${alba.al_address }" /><span class="error">${errors.al_address }</span></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input class="form-control" type="text" required
				name="al_hp" value="${alba.al_hp }" /><span class="error">${errors.al_hp }</span></td>
		</tr>
		<tr>
			<th>특기사항</th>
			<td><textarea name="al_spec">
				${alba.al_spec}
			</textarea></td>
		</tr>
		<tr>
			<th>경력사항</th>
			<td><input class="form-control" type="text" name="al_career"
				value="${alba.al_career }" /><span class="error">${errors.al_career }</span></td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<input type="radio" name="al_gen"  value="M" />남
				<input type="radio" name="al_gen"  value="F" />여
			</td>
		</tr>
		<tr>
			<th>혈액형</th>
			<td>
				<select name="al_btype">
					<option value="">혈액형</option>
					<option value="A">A형</option>
					<option value="B">B형</option>
					<option value="AB">AB형</option>
					<option value="O">O형</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input class="form-control" type="text" required
				name="al_mail" value="${alba.al_mail }" /><span class="error">${errors.al_mail }</span></td>
		</tr>
		<tr>
			<th>학력</th>
			<td>
				<select name="gr_code">
					<option value="">학력</option>
					<c:forEach items="${gradeList }" var="grade">
						<option value="${grade.gr_code}" >${grade.gr_name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>자격증</th>
			<td>
				<div>
					
					<select id="lic" name="lic_code" >
						<option value="">자격증</option>
						<c:forEach items="${licenseList }" var="license">
							<option value="${license.lic_code}" >${license.lic_name}</option>
						</c:forEach>
					</select>
					<input type="file" name="lic_image"/>
					<button type="button" class="lic-add">추가</button>
				</div>
				<div class="lic-list"></div>
			</td>
		</tr>
		<tr>
		<tr>
				<th>자격증</th>
				<td>
					<c:forEach items="${alba.licAlbaList }" var="licAlba" varStatus="vs">
						<c:if test="${not empty licAlba.lic_code }">
							<span>
								${licAlba.lic_name } 
								<input type="button" class="btn btn-info delBtn" value="삭제" 
										data-code="${licAlba.lic_code }"/>
								${!vs.last?"|":"" }
							</span>
						</c:if>
					</c:forEach>
				<div id="licenseArea" class="row">	
					<div class="form-group col-6">
						<select name="lic_codes" class="form-control" multiple size="6">
							<c:forEach items="${licenses }" var="license">
								<c:set var="matched" value="${false }"/>
								<c:forEach items="${alba.licAlbaList }" var="licAlba">
									<c:if test="${licAlba.lic_code eq license.lic_code }">
										<c:set var="matched" value="${true }"/>
									</c:if>
								</c:forEach>
								<option value="${license.lic_code }" class="${matched?'matched':'normal' }">${license.lic_name }</option>
							</c:forEach>
						</select>
						<span class="error form-control-plaintext">${errors["lic_codes"] }</span>
					</div>
					<div id="imageArea" class="col-6">
					
					</div>
				</div>
				</td>
			</tr>
		<!-- <tr>
			<th>첨부파일</th>
			<td>
				<input type="file" name="bo_file"/> 넘어가는 파일이 여러개
			</td>
		</tr> -->
		<tr>
			<td colspan="2">
				<input class="btn btn-success" type="submit" value="등록"> <!-- 클릭이후에 타겟이 form이 됨 -->
				<input class="btn btn-warning" type="button" value="목록" onclick="history.back();">
			</td>
		</tr>
	</table>
</form>
<script>


	$(function(){
		$(document).on('click', '.lic-add', function(){
			
			console.log("lic_add" + $(this));
			var licSize;
			if($('.lic-list').children().length + 1 >= '${licenseList.size()}'){
				return false;
			}
			
			$('.lic-list').append(
				 '<div>'
				+  '<select name="lic_code">'
				+  '  <option value="">자격증</option>'
				+  '  <c:forEach items="${licenseList}" var="license">'
				+  '    <option value="${license.lic_code}" >${license.lic_name}</option>'
				+  '  </c:forEach>'
				+  '</select>'
				+  '<input type="file" name="lic_image"/>'
				+  '<button type="button" class="lic-add">추가</button>'
				+  '<button type="button" class="lic-delete">삭제</button>'	
				+'</div>'
			);
		});
		$(document).on('click', '.lic-delete', function(){
			$(this).parent().remove();
		});
		
		
		// 혈액형데이터 수정화면에 넘기기
		$('[name="al_btype"]').val('${alba.al_btype}');
		
		// 라디오버튼 성별 데이터 수정화면에 넘기기
		$("input[value='${alba.al_gen}']").attr('checked', true);
		
		// 학력데이터 수정화면에 넘기기
		$('[name="gr_code"]').val('${alba.gr_code}');
		
		//자격증데이터 수정화면에 넘기기
		$("#lic > option[value='${alba.lic_code}']").attr('selected', 'true');
	})
	
	$(".lic-delete").on("click", function(){
		let span = $(this).parent("span");
		let attNo = span.data("attno");
		span.hide();
		let input = $("<input>").attr({
						"type":"text",
						"name":"delAttNos"
					}).val(attNo);
		boardForm.append(input);
	});
	
	$(function(){
		let imageArea = $("#imageArea");
		let licCodes = $("[name='licCodes']");
		let albaForm = $("#albaForm").on("reset", function(){
			imageArea.empty();
			licCodes.nextAll("[name='licImages']").remove();
		});
		//기존 자격증 삭제 처리시 테스트 코드
		$(".delBtn").on("click", function(){
			var dellicCode = $(this).data("code");
			albaForm.append(
					$("<input>").attr({
						type:"text"
							, name:"deleteLicCodes"
								, value:dellicCode
					})
			);
			$(this).closest("span").remove();
			$(licCodes).find("[value='"+dellicCode+"']").removeClass("matched");
		});	
		// 자격증 선택시, 이미지 태그 추가
		licCodes.on("blur", function(){
			$(this).nextAll("[name='licImages']").remove();
			imageArea.empty();
			var selectTag = $(this);
			var options = $(this).val();
			$(options).each(function(idx, optVal){
				selectTag.after(
						$("<input>").attr({
							type:"file"
								, 'class':"form-control"
									, name:"licImages"
										, required:true
										, id:"file_"+idx
						})	
				);
				imageArea.prepend(
						$("<img>").hide()
						.addClass("file_"+idx)
						.css({width:"100px", height:"100px"}),
						$("<br/>")		  
				);
			});
		});
		// 자격증 사본 미리보기
		$("#licenseArea").on("change", "[type='file']", function(){
			var files = $(this).prop("files");
			var id = $(this).prop("id");
			if(!files) return;
			for(let idx=0; idx<files.length; idx++){
				let reader = new FileReader();
				reader.onloadend = function(event){
					let imgTag = imageArea.find("."+id);
					imgTag.attr("src", event.target.result);
					imgTag.show();
				}
				reader.readAsDataURL(files[idx]);
			}
		});
	});
	
</script>
</body>
</html>