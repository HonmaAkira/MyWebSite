<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報</title>
<jsp:include page="/baselayout/head.html"/>

</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="userinfo-container">
		<div class="userinfo-contents">
			<div class="userinfo-title">
				<h3>ユーザ情報</h3>
			</div>
			<div class="userinfo-list">
				<p>
					ログインID：<span>${sessionScope.userinfo.login_id}</span>
				</p>
				<p>
					ユーザ名：<span>${sessionScope.userinfo.name}</span>
				</p>
				<p>
					生年月日：<span>${sessionScope.userinfo.birth}</span>
				</p>
			</div>
		</div>
		<div class="userinfo-buylist">
			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th style="width: 10%;"></th>
						<th scope="col">購入日時</th>
						<th scope="col">配送方法</th>
						<th scope="col">購入金額</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="bdblist" items="${buylist}">
					<tr>
						<td><a href="Buyhistory?id=${bdblist.id }" class="btn"><i class="fas fa-list"></i></a></td>
						<td>${bdblist.createDate}</td>
						<td>${bdblist.deliveryMethodName}</td>
						<td>${bdblist.totalPrice+bdblist.deliveryMethodPrice}円</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<a class="btn btn-dark" href="Index" role="button">戻る</a>
	</div>
<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>