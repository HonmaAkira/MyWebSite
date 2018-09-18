<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品マスタ一覧</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="masteritem-container">
		<h2>商品マスタ一覧</h2>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th scope="col">商品名</th>
					<th scope="col">価格</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemlist}">
					<tr>
						<td>${item.name}</td>
						<td>${item.price}円</td>
						<td><c:if test="${sessionScope.userinfo.id ==1 }">
						<a class="btn btn-primary" href="Masterdetail?id=${item.id}" role="button">詳細</a> <a
						class="btn btn-success" href="Masterupdate?id=${item.id}" role="button">更新</a> <a
						class="btn btn-danger" href="Masterdelete?id=${item.id}" role="button">削除</a>
						</c:if>
					</tr>
				</c:forEach>

			</tbody>
		</table>


	</div>
<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>