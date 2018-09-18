<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品マスタ詳細</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="masterdetail-container">
		<h2>商品マスタ詳細</h2>
		<div class="masterdetail-list">
			<p>
				商品カテゴリ：<span>${item.category_id}</span>
			</p>
			<p>
				商品名：<span>${item.name}</span>
			</p>
			<p>
				商品詳細：<span>${item.detail}</span>
			</p>
			<p>
				価格：<span>${item.price}円</span>
			</p>
			<img src ="upload/${item.image}">
		</div>
		<br>
		<a class="btn btn-dark" href="Masteritem" role="button">戻る</a>
	</div>
<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>