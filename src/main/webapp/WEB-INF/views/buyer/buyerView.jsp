<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
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
		<th>거래처코드</th>
		<td>${buyer.buyer_id}</td>
	</tr>
	<tr>
		<th>거래처이름</th>
		<td>${buyer.buyer_name}</td>
	</tr>
	<tr>
		<th>거래처 상품분류</th>
		<td>${buyer.buyer_lgu}</td>
	</tr>
	<tr>
		<th>거래처 은행</th>
		<td>${buyer.buyer_bank}</td>
	</tr>
	<tr>
		<th>은행 계좌</th>
		<td>${buyer.buyer_bankno}</td>
	</tr>
	<tr>
		<th>예금주</th>
		<td>${buyer.buyer_bankname}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${buyer.buyer_zip}</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>${buyer.buyer_add1}</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>${buyer.buyer_add2}</td>
	</tr>
	<tr>
		<th>회사번호</th>
		<td>${buyer.buyer_comtel}</td>
	</tr>
	<tr>
		<th>팩스번호</th>
		<td>${buyer.buyer_fax}</td>
	</tr>
	<tr>
		<th>메일</th>
		<td>${buyer.buyer_mail}</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>${buyer.buyer_charger}</td>
	</tr>
	<tr>
		<th>TELEXT</th>
		<td>${buyer.buyer_telext}</td>
	</tr>
	<tr>
			<td colspan="2">
				<c:url value="/buyer/buyerUpdate.do" var="buyerUpdateURL">
					<c:param name="what" value="${buyer.buyer_id}"/>
				</c:url>
				<input type="button" value="수정" 
					onclick="location.href='${buyerUpdateURL}';"/>
				<input type="button" value="목록으로" class="btn btn-info" 
					onclick="location.href='<c:url value="/buyer/buyerList.do"/>';"
				/>
				<input type="button" value="뒤로가기" class="btn btn-info" 
					onclick="history.back();"
				/>
			</td>
		</tr>
	<tr>
		<th>거래품목조회</th>
		<td>
			<table>
					<thead>
						<tr>
							<th>상품명</th>						
							<th>매입가격</th>						
							<th>소비자가격</th>						
						</tr>
					</thead>
					<tbody>
			
							<c:set var="prodList" value="${buyer.prodList}"/>
							<c:if test="${not empty prodList}">
							<c:forEach items="${prodList}" var="tmp">
							<tr>	
								<td>${tmp.prod_name}</td>
								<td>${tmp.prod_cost}</td>
								<td>${tmp.prod_price}</td>
							</tr>
							</c:forEach>
							</c:if>
							
							<c:if test="${empty prodList}">
							<tr>
								<td colspan="3">
									거래품목이 없음.
								</td>
							</tr>
							</c:if>
					</tbody>
				</table>
		</td>
	</tr>
</table>
</body>
</html>