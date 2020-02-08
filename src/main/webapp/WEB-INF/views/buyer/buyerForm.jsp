<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<body>
<c:if test="${not empty message }"> <!-- 메세지라는 속성이 있으면 -->
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
${errors}
<form method="post">
<input type="hidden" name="buyer_id" value="${buyer.buyer_id}" />
<table class="table table-striped table-dark">
	<tr>
		<th>거래처이름</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_name" value="${buyer.buyer_name}" />
				<span class="error">${errors.buyer_name }</span>
		</td>
	</tr>
	<tr>
		<th>거래처 상품분류</th>
		<td>
				<select name="buyer_lgu">
					<option value="">상품분류</option>
					<c:forEach items="${lprodList}" var="lprodMap">
						<option value="${lprodMap['lprod_gu'] }">${lprodMap["lprod_nm"] }</option>
					</c:forEach>
				</select>
				<span class="error">${errors.buyer_lgu }</span>
		</td>
	</tr>
	<tr>
		<th>거래처 은행</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_bank" value="${buyer.buyer_bank}" />
				<span class="error">${errors.buyer_bank }</span>
		</td>
	</tr>
	<tr>
		<th>은행 계좌</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_bankno" value="${buyer.buyer_bankno}" />
				<span class="error">${errors.buyer_bankno }</span>
		</td>
	</tr>
	<tr>
		<th>예금주</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_bankname" value="${buyer.buyer_bankname}" />
				<span class="error">${errors.buyer_bankname }</span>
		</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>
		<input class="form-control" type="text" 
						name=buyer_zip value="${buyer.buyer_zip}" />
				<span class="error">${errors.buyer_zip }</span>
		</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_add1" value="${buyer.buyer_add1}" />
				<span class="error">${errors.buyer_add1 }</span>
		</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_add2" value="${buyer.buyer_add2}" />
				<span class="error">${errors.buyer_add2 }</span>
		</td>
	</tr>
	<tr>
		<th>회사번호</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_comtel" value="${buyer.buyer_comtel}" />
				<span class="error">${errors.buyer_comtel }</span>
		</td>
	</tr>
	<tr>
		<th>팩스번호</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_fax" value="${buyer.buyer_fax}" />
				<span class="error">${errors.buyer_fax }</span>
		</td>
	</tr>
	<tr>
		<th>메일</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_mail" value="${buyer.buyer_mail}" />
				<span class="error">${errors.buyer_mail }</span>
		</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_charger" value="${buyer.buyer_charger}" />
				<span class="error">${errors.buyer_charger }</span>
		</td>
	</tr>
	<tr>
		<th>TELEXT</th>
		<td>
		<input class="form-control" type="text" 
						name="buyer_telext" value="${buyer.buyer_telext}" />
				<span class="error">${errors.buyer_telext }</span>
		</td>
	</tr>
			<tr>
			<td colspan="2">
				<input type="submit" value="저장 " />
				<input type="reset" value="취소" />
			</td>
		</tr>
</table>
</form>
</body>
</html>