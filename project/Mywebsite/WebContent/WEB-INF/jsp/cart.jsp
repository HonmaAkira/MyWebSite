<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート情報</title>
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
			<div class="col-md-8">
			<div class="cart-image-contents">

			<h2>カートアイテム</h2>

			<h3>${CartErrMsg}</h3>
				<div class="row">
					<c:forEach var="item" items="${cart}" varStatus="var">
					<div class="col-md-4">
						<div class="image-card">
							<img src="upload/${item.image}">
							<p>${item.name}</p>
							<p>税込<fmt:formatNumber value="${item.price*1.08}" maxFractionDigits="0"/>円</p>
							<a class="btn btn-danger" href="Itemdelete?index=${var.index}" role="button">削除</a>
						</div>
					</div>
					</c:forEach>
				</div>

				<br>
				<br>

			<div>
				<a class="btn btn-dark" href="Index" role="button">戻る</a>
				<c:if test="${empty CartErrMsg}">
				<a class="btn btn-primary" href="Buy" role="button">レジへ進む</a>
				</c:if>

			</div>

			</div>
		</div>
	</div>

<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>