<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<form action="Buyconfirm" method="POST">
		<div class="buy-container">
			<h2>購入</h2>

			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th colspan="2">商品名</th>
						<th>単価</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="item" items="${cart}">
						<tr>
							<td colspan="2">${item.name}</td>
							<td><fmt:formatNumber value="${item.price*1.08}" maxFractionDigits="0"/>円</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="2"></td>
						<td><c:if test="${ErrMsg != null}">
								<p class="text-danger">${ErrMsg}</p>
							</c:if> <input type="radio" name="delivery" value="1">通常配送 <input
							type="radio" name="delivery" value="2">お急ぎ配送 <input
							type="radio" name="delivery" value="3">時間指定配送</td>
					</tr>
				</tbody>

			</table>

			<a class="btn btn-dark" href="Cart" role="button">戻る</a>
			<button type="submit" class="btn btn-primary">購入画面へ</button>
		</div>
	</form>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>