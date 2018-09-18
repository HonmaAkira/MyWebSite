<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確定</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="buyfinish-container">
		<h2>注文が確定しました</h2>
		<h5>ご購入ありがとうございます</h5>
		<h5>またのご利用をお待ちしております</h5>
		<br>
		<h3>購入詳細</h3>
		<table class="table table-bordered">
		<thead class="thead-light">
			<tr>
				<th scope="col">購入日時</th>
				<th scope="col">配送方法</th>
				<th scope="col">合計金額</th>
			</tr>
		</thead>
			<tr>
				<td>${finishBDB.createDate}</td>
				<td>${finishBDB.deliveryMethodName }</td>
				<td><fmt:formatNumber value="${(finishBDB.totalPrice*1.08)+(finishBDB.deliveryMethodPrice*1.08)}" maxFractionDigits="0"/>円</td>
			</tr>
			</table>

		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
				<th>商品名</th>
				<th>単価</th>
				</tr>
			</thead>
			<c:forEach var="item" items="${BuyItemList}">
			<tr>
				<td>${item.name}</td>
				<td><fmt:formatNumber value="${item.price*1.08}" maxFractionDigits="0"/>円</td>
			</tr>
			</c:forEach>
			<tr>
				<td>${finishBDB.deliveryMethodName}</td>
				<td><fmt:formatNumber value="${finishBDB.deliveryMehodPrice*1.08}" maxFractionDigits="0"/>円</td>
			</tr>

		</table>
		<a class="btn btn-success" href="Userinfo?id=${sessionScope.userinfo.id}" role="button">ユーザ画面へ進む</a>
		<a class="btn btn-warning" href="Index" role="button">買い物を続ける</a>
	</div>

<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>