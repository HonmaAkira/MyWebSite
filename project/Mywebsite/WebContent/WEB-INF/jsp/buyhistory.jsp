<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
<jsp:include page="/baselayout/head.html"/>

</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="buyhistory-display">
	<div class="buyhistory-container">
		<h2>購入詳細</h2>
		<table class="table table-bordered">
		<thead class="thead-light">
			<tr>
				<th scope="col">購入日時</th>
				<th scope="col">配送方法</th>
				<th scope="col">合計金額</th>
			</tr>
		</thead>
			<tr>
				<td>${BuyresultBDB.createDate}</td>
				<td>${BuyresultBDB.deliveryMethodName }</td>
				<td>${BuyresultBDB.totalPrice+BuyresultBDB.deliveryMethodPrice}円</td>
			</tr>
			</table>
			<br>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
				<th>商品名</th>
				<th>単価</th>
				</tr>
			</thead>
			<c:forEach var="buylist" items="${BuyItemList}">
			<tr>
				<td>${buylist.name}</td>
				<td>${buylist.price}円</td>
			</tr>
			</c:forEach>
			<tr>
				<td>${BuyresultBDB.deliveryMethodName}</td>
				<td>${BuyresultBDB.deliveryMethodPrice}円</td>
			</tr>

		</table>
	<br>
	<a class="btn btn-dark" href="Index" role="button">戻る</a>

	</div>
	</div>
<jsp:include page="/baselayout/footer.jsp"/>

</body>
</html>