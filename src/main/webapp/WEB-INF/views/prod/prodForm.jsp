<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<c:if test="${not empty message }"> <!-- 메세지라는 속성이 있으면 -->
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
${errors}
<form method="post" enctype="multipart/form-data">
<input type="hidden" name="prod_id" value="${prod.prod_id }" />
	<table class="table table-bordered" >
<%-- 		 <tr> 
 			<th>상품아이디</th>
 			<td>${prod.prod_id }
 			<input class="form-control" type="text" required name="prod_id" value="${prod.prod_id }" />
			</td> 
		</tr> --%> 
		<tr>
			<th>상품명</th>
			<td>
				<input class="form-control" type="text" 
						name="prod_name" value="${prod.prod_name }" />
				<span class="error">${errors.prod_name }</span>
			</td>
		</tr>
		<tr>
			<th>상품분류코드</th>
			<td>
				<select name="prod_lgu">
					<option value="">상품분류</option>
					<c:forEach items="${lprodList }" var="lprodMap">
						<option value="${lprodMap['lprod_gu'] }">${lprodMap["lprod_nm"] }</option>
					</c:forEach>
				</select>
				
			<span class="error">${errors.prod_lgu }</span></td>
		</tr>
		<tr>
			<th>거래처코드</th>
			<td>
				<select name="prod_buyer">
					<option value="">거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option value="${buyer.buyer_id }" class="${buyer.buyer_lgu }">${buyer.buyer_name }</option>
					</c:forEach>
				</select>
			<span class="error">${errors.prod_buyer }</span></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><input class="form-control" type="number" required
				name="prod_cost" value="${prod.prod_cost }" /><span class="error">${errors.prod_cost }</span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input class="form-control" type="number" required
				name="prod_price" value="${prod.prod_price }" /><span
				class="error">${errors.prod_price }</span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input class="form-control" type="number" required
				name="prod_sale" value="${prod.prod_sale }" /><span class="error">${errors.prod_sale }</span></td>
		</tr>
		<tr>
			<th>간략정보</th>
			<td><input class="form-control" type="text" required
				name="prod_outline" value="${prod.prod_outline }" /><span
				class="error">${errors.prod_outline }</span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input class="form-control" type="text" name="prod_detail"
				value="${prod.prod_detail }" /><span class="error">${errors.prod_detail }</span></td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td><input type="file" name="prod_image" accept="image/*"/><span class="error">${errors.prod_img }</span></td>
		</tr>
		<tr>
			<th>상품재고</th>
			<td><input class="form-control" type="number" required
				name="prod_totalstock" value="${prod.prod_totalstock }" /><span
				class="error">${errors.prod_totalstock }</span></td>
		</tr>
		<tr>
			<th>상품입고일</th>
			<td><input class="form-control" type="text" name="prod_insdate"
				value="${prod.prod_insdate }" /><span class="error">${errors.prod_insdate }</span></td>
		</tr>
		<tr>
			<th>상품적정재고</th>
			<td><input class="form-control" type="number" required
				name="prod_properstock" value="${prod.prod_properstock }" /><span
				class="error">${errors.prod_properstock }</span></td>
		</tr>
		<tr>
			<th>상품크기</th>
			<td><input class="form-control" type="text" name="prod_size"
				value="${prod.prod_size }" /><span class="error">${errors.prod_size }</span></td>
		</tr>
		<tr>
			<th>상품색상</th>
			<td><input class="form-control" type="text" name="prod_color"
				value="${prod.prod_color }" /><span class="error">${errors.prod_color }</span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input class="form-control" type="text"
				name="prod_delivery" value="${prod.prod_delivery }" /><span
				class="error">${errors.prod_delivery }</span></td>
		</tr>
		<tr>
			<th>상품단위</th>
			<td><input class="form-control" type="text" name="prod_unit"
				value="${prod.prod_unit }" /><span class="error">${errors.prod_unit }</span></td>
		</tr>
		<tr>
			<th>상품입고량</th>
			<td><input class="form-control" type="number" name="prod_qtyin"
				value="${prod.prod_qtyin }" /><span class="error">${errors.prod_qtyin }</span></td>
		</tr>
		<tr>
			<th>상품판매량</th>
			<td><input class="form-control" type="number"
				name="prod_qtysale" value="${prod.prod_qtysale }" /><span
				class="error">${errors.prod_qtysale }</span></td>
		</tr>
		<tr>
			<th>상품마일리지</th>
			<td><input class="form-control" type="number"
				name="prod_mileage" value="${prod.prod_mileage }" /><span
				class="error">${errors.prod_mileage }</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장 " />
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>

</form>
<script type="text/javascript">
	var buyerTag = $("[name='prod_buyer']").val("${prod.prod_buyer}");
	$("[name='prod_lgu']").on("change",function(){
		let lgu = $(this).val();  //현재선택된 option의 value잡기
		buyerTag.find("option:gt(0)").hide(); //prompt text는 언제나 보임
 		buyerTag.find("option."+lgu).show(); //현재선택한lgu값을 class로 갖는것만 보이게
     	}).val("${prod.prod_lgu}");
	 
	$( "[name='prod_insdate']" ).datepicker({
		dateFormat:"yy-mm-dd"
	});
	
</script>
</body>
</html>