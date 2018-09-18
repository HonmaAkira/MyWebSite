<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>perc.shop</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<form action="Itemsearch" method="GET">
	<div class="Index-container">
		<div class="row">
			<div class="col-md-2">
				<div class="Index-sub-list">
					<h5>カテゴリー</h5>
					<ul>
						<li><a href="Index?category_id=1"><b>ethnic</b></a></li>
						<li><a href="Index?category_id=2"><b>classic</b></a></li>
						<li><a href="Index?category_id=3"><b>drums</b></a></li>
						<li><a href="Index?category_id=4"><b>accessory</b></a></li>
					</ul>
				</div>
			</div>


			<div class="col-md-8">
				<div class="Index-seach-form">


						<div class="form-group row">
							<label class="col-sm-3">商品検索<i class="fas fa-search"></i></label>
							<input type="text" class="form-control col-sm-8" name="name"
								aria-describedby="itemHelp" placeholder="Enter item">
						</div>
						<div class="row">
							<label class="col-sm-3">価格検索<i class="fas fa-search"></i></label>
							<input type="text" class="form-control col-sm-3" name="price1"
								aria-describedby="itemHelp" placeholder="Enter price">
							 <h4 class="col-sm-1" align="center">～</h4>
							<input type="text" class="form-control col-sm-3" name="price2"
								aria-describedby="itemHelp" placeholder="Enter price">
						</div>
						<button type="submit" class="btn btn-primary col-sm-2">検索</button>


					<c:choose>
						<c:when test ="${CategoryNumber!=null }">

							<p>検索結果${CategoryNumber}件表示</p>
						</c:when>
						<c:when test ="${Countlist!=null}">

							<p>検索結果${Countlist}件表示</p>
						</c:when>
						<c:when test ="${CategoryNumber==null }">

							<p>検索結果${count}件表示</p>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="index-item">
		<div class="row">
			<c:choose>
				<c:when test="${Categorylist!=null }">
					<c:forEach var="category" items="${Categorylist}">
						<div class="col-md-4">
							<div class="image-card">
								<a href="Itemdetail?id=${category.id}"><img
									src="upload/${category.image}"></a>
								<h3>${category.name}</h3>
								<h3>${category.price}円</h3>
							</div>
						</div>
					</c:forEach>
				</c:when>
			<c:when test ="${Categorylist==null }">
			<c:forEach var="item" items="${itemlist}">
				<div class="col-md-4">
					<div class="image-card">
						<a href="Itemdetail?id=${item.id}"><img
							src="upload/${item.image}"></a>
						<h3>${item.name}</h3>
						<h4>税込<fmt:formatNumber value="${item.price*1.08}" maxFractionDigits="0"/>円</h4>
						<h6>税抜<fmt:formatNumber value="${item.price}" maxFractionDigits="0"/>円</h6>
					</div>
				</div>
			</c:forEach>
			</c:when>
		</c:choose>


		</div>

		<br>
		<div class="row">
			<div class="pageArea">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
					<!--pagenum参照した結果nullだったばあい  -->
					<c:if test="${pagenum==null}">
						<li class="disable page-item"><a>Previous</a></li>
					</c:if>
					<!--1を含む何かしらの値がある場合 -->
					<c:if test="${pagenum!=1 }">
						<li class="page-item"><a class="page-link" href="Itemsearch?searchword=${searchword}&pagenum=${pageNum}">Previous</a></li>
					</c:if>
					<!--ページインデックス EL式は分からないのでスクリプト式で記述する -->

						<li class="page-item"><a class="page-link" href="#">1</a></li>

						<li class="page-item"><a class="page-link" href="#">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	</form>
	<jsp:include page="/baselayout/footer.jsp" />

</body>
</html>