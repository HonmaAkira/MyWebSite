<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="row">
		<div class="col-md-2">
			<div class="Index-sub-list">
				<h5>カテゴリー</h5>
				<ul>
					<li><a href="Index?category_id=1">ethnic</a></li>
					<li><a href="Index?category_id=2">classic</a></li>
					<li><a href="Index?category_id=3">drums</a></li>
					<li><a href="Index?category_id=4">accessory</a></li>
				</ul>
			</div>
		</div>

		<div class="col-md-4">
				<div class="itemdetail-image">
				<img src="upload/${item.image}">
				</div>
		</div>

		<div class="col-md-4">
		<div class="itemdetail-contents">
			<div class="itemtitle&price">
				<h2>商品名：${item.name}</h2>
				<h3>価格：税込<fmt:formatNumber value="${item.price*1.08}" maxFractionDigits="0"/>円</h3>
						<h5>税抜<fmt:formatNumber value="${item.price}" maxFractionDigits="0"/>円</h5>
			</div>
			<div class="itemdetail">
				<p>${item.detail}</p>
			</div>
			<form action="Itemdetail" method="POST">
			<input type="hidden" name="id" value="${item.id}">
			<a class="btn btn-dark" href="Index" role="button">戻る</a>
			<button type="submit" class="btn btn-primary">カートへ入れる</button>
			</form>
		</div>
		</div>
	</div>
<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>