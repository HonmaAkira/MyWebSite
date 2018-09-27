<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品マスタ新規登録</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<form action="Masterentry" method="POST" enctype="multipart/form-data">
		<div class="masterentry-container">
			<h2>新規マスタ商品登録</h2>
			<c:if test= "${ErrMsg1!=null}"><p class="text-danger">${ErrMsg1}</p></c:if>
			<c:if test = "${ErrMsg2 != null}"><p class="text-danger">${ErrMsg2}</p></c:if>
			<c:if test = "${ErrMsg3 != null}"><p class="text-danger">${ErrMsg3}</p></c:if>
			<c:if test = "${ErrMsg4 != null}"><p class="text-danger">${ErrMsg4}</p></c:if>
			<div class="row">
				<label class="col-sm-3">商品カテゴリ</label>
				<input type="radio" name="category" value="1"><label class = "col-sm-1">ethnic</label>
				<input type="radio" name="category" value="2"><label class = "col-sm-1">classic</label>
				<input type="radio" name="category" value="3"><label class = "col-sm-1">drums</label>
				<input type="radio" name="category" value="4"><label class = "col-sm-1">accessory</label>
			</div>
			<div class="row">
				<label class="col-sm-3">商品名</label> <input type="text"
					class="form-control col-sm-8" name="name"
					aria-describedby="itemnemeHelp" placeholder="Enter item">
			</div>
			<div class="row">
				<label class="col-sm-3">商品詳細</label>
				<textarea class="form-control col-sm-8" name="detail" rows="3"
					placeholder="Enter detail"></textarea>
			</div>
			<div class="row">
				<label class="col-sm-3">価格</label> <input type="text"
					class="form-control col-sm-8" name="price"
					aria-describedby="priceHelp" placeholder="Enter price">
			</div>
			<div class="row">
				<label class="col-sm-3">商品画像</label> <input type="file"
					class="form-control col-sm-8" name="image"
					aria-describedby="imageHelp" placeholder="Enter image directory">
			</div>
			<br> <a class="btn btn-dark" href="Index" role="button">戻る</a>
			<button type="submit" class="btn btn-primary">新規登録</button>

		</div>
	</form>

<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>