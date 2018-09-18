<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ一覧</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="userlist-container">
		<form action="Userlist" method="POST" class="form-control">
			<h2>ユーザ一覧</h2>
			<div class="userlist-serchform">
			<div class="form-group row">
				<label class="col-sm-2">ログインID</label> <input type="text"
					class="form-control col-sm-6" name="loginId"
					aria-describedby="emailHelp" placeholder="Enter loginId">
			</div>
			<div class="form-group row">
				<label class="col-sm-2">ユーザ名</label> <input type="text"
					class="form-control col-sm-6" name="name"
					placeholder="Enter Username">
			</div>
			<div class="form-group row">
				<label class="col-sm-2">生年月日</label> <input type="date"
					class="form-control col-sm-3" name="birth1" placeholder="Enter Birth">
				<h4 class="col-sm-1">～</h4>
				<input type="date" class="form-control col-sm-3" name="birth2"
					placeholder="Enter Birth">
			</div>
			<button type="submit" class="btn btn-primary">検索</button>
			</div>
		</form>
		<div class="userlist-table">
	<table class="table table-bordered">
		<thead class="thead-light">
			<tr>
				<th scope="col">ログインID</th>
				<th scope="col">ユーザ名</th>
				<th scope="col">生年月日</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${userlist}">
			<tr>
				<td>${user.login_id}</td>
				<td>${user.name}</td>
				<td>${user.birth}</td>
				<td><a class="btn btn-primary" href="Userdetail?id=${user.id}" role="button">詳細</a> <a
					class="btn btn-success" href="Userupdate?id=${user.id}" role="button">更新</a> <a
					class="btn btn-danger" href="Userdelete?id=${user.id}" role="button">削除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
		</div>
	<div class="row">
		<div class="pageArea">
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>
<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>