<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
	<table class="table table-striped table-dark">
	<tr>
			<th>상품아이디</th>
			<td>${prod.prod_id }</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prod_name }</td>
		</tr>
		<tr>
			<th>상품분류명</th>
			<td>${prod.lprod_nm }</td>
		</tr>
		<tr>
			<th>거래처정보</th>
			<td>
				<table class="table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>거래처명</th>
							<th>담당자명</th>
							<th>담당자연락처</th>
							<th>소재지</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:set var="buyer" value="${prod.buyer }"/>
							<td>
							<c:url value="/buyer/buyerView.do" var="buyerViewURL">
								<c:param name="what" value="${buyer.buyer_id}" />
							</c:url>
							<a href="${buyerViewURL}">${buyer.buyer_name}</a>
							</td>
							<td>${buyer.buyer_charger }</td>
							<td>${buyer.buyer_comtel }</td>
							<td>${buyer.buyer_add1 }</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prod_cost }</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prod_price }</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prod_sale }</td>
		</tr>
		<tr>
			<th>간략정보</th>
			<td>${prod.prod_outline }</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prod_detail }</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
				<img src="<c:url value='/prodImages/${prod.prod_img}'/>" />
			</td>
		</tr>
		<tr>
			<th>상품재고</th>
			<td>${prod.prod_totalstock }</td>
		</tr>
		<tr>
			<th>상품입고일</th>
			<td>${prod.prod_insdate }</td>
		</tr>
		<tr>
			<th>상품적정재고</th>
			<td>${prod.prod_properstock }</td>
		</tr>
		<tr>
			<th>상품크기</th>
			<td>${prod.prod_size }</td>
		</tr>
		<tr>
			<th>상품색상</th>
			<td>${prod.prod_color }</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prod_delivery }</td>
		</tr>
		<tr>
			<th>상품단위</th>
			<td>${prod.prod_unit }</td>
		</tr>
		<tr>
			<th>상품입고량</th>
			<td>${prod.prod_qtyin }</td>
		</tr>
		<tr>
			<th>상품판매량</th>
			<td>${prod.prod_qtysale }</td>
		</tr>
		<tr>
			<th>상품마일리지</th>
			<td>${prod.prod_mileage }</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/prod/prodUpdate.do" var="prodUpdateURL">
					<c:param name="what" value="${prod.prod_id}"/>
				</c:url>
				<input type="button" value="수정" 
					onclick="location.href='${prodUpdateURL}';"/>
				<input type="button" value="목록으로" class="btn btn-info" 
					onclick="location.href='<c:url value="/prod/prodList.do"/>';"/>
				<input type="button" value="뒤로가기" class="btn btn-info" 
					onclick="history.back();"
				/>
			</td>
		</tr>
		<tr>
			<th>상품구매자목록</th>
			<td>
				<table class="table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>구매자아이디</th>
							<th>이름</th>
							<th>휴대폰</th>
							<th>이메일</th>
							<th>거주지</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="memberList" value="${prod.memberList }" />
						<c:if test="${not empty memberList }">
							<c:forEach items="${memberList }" var="member">
								<tr>
									<td>${member.mem_id }</td>
									<td>${member.mem_name }</td>
									<td>${member.mem_hp }</td>
									<td>${member.mem_mail }</td>
									<td>${member.mem_add1 }</td>
									<td>${member.mem_mileage }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="6">구매자가 없음.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>