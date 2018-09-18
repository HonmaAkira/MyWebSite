<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品マスタ更新</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="masterupdate-container">
		<h2>商品マスタ更新</h2>
		<c:if test="${ErrMsg1!=null}">
			<p class="text-danger">${ErrMsg1}</p>
		</c:if>
		<c:if test="${ErrMsg2 != null }">
			<p class="text-danger">${ErrMsg2}</p>
		</c:if>
		<form action="Masterupdate" method="POST"
			enctype="multipart/form-data">
			<input type="hidden" name="id"
				value=<c:if test="${item.id!=null}">"${item.id}"</c:if>>
			<div class="row">
				<label class="col-sm-3">商品カテゴリ</label> <input type="radio"
					name="category" value="1"
					<c:if test="${item.category_id==1}">checked="checked"</c:if>><label
					class="col-sm-1">ethnic</label> <input type="radio" name="category"
					value="2"
					<c:if test="${item.category_id==2}">checked="checked"</c:if>><label
					class="col-sm-1">classic</label> <input type="radio"
					name="category" value="3"
					<c:if test="${item.category_id==3}">checked="checked"</c:if>><label
					class="col-sm-1">drums</label> <input type="radio" name="category"
					value="4"
					<c:if test="${item.category_id==4}">checked="checked"</c:if>><label
					class="col-sm-1">accessory</label>
			</div>
			<div class="row">
				<label class="col-sm-3">商品名</label> <input type="text"
					class="form-control col-sm-8" name="name"
					aria-describedby="itemnemeHelp" value=${item.name}
					>
			</div>
			<div class="row">
				<label class="col-sm-3">商品詳細</label>
				<textarea class="form-control col-sm-8" name="detail" rows="3"
					>"${item.detail}"</textarea>
			</div>
			<div class="row">
				<label class="col-sm-3">価格</label> <input type="text"
					class="form-control col-sm-8" name="price"
					aria-describedby="priceHelp" value=${item.price}
					>
			</div>
			<div class="row">
				<label class="col-sm-3">商品画像</label> <input type="file"
					class="form-control col-sm-8" name="image"
					aria-describedby="imageHelp">
			</div>
			<br>
			<h5>本当にこの内容で商品データを更新しても良いですか？</h5>
			<a class="btn btn-dark" href="Masteritem" role="button">戻る</a>
			<button type="submit" class="btn btn-success">登録</button>

		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>