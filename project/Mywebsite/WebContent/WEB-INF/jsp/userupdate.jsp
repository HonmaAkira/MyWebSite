<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ更新</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<form action="Userupdate" method="POST">
	<div class="userupdate-container">
		<h2>ユーザ更新</h2>
		<c:choose>
			<c:when test = "${fail1 !=null}"><p class="text-danger">${fail1}</p></c:when>
			<c:when test = "${fail2 !=null}"><p class="text-danger">${fail2}</p></c:when>
		</c:choose>
		<input type ="hidden" name= "id" value="${user.id}">
		<div class="row">
			<label class="col-sm-3">ログインid</label> <p class="col-sm-8">${user.login_id}</p>
				<label class="col-sm-3">ユーザ名</label> <input type="text"
					class="form-control col-sm-8" name="name"
					aria-describedby="usernemeHelp" value="${user.name}">
			</div>
			<div class="row">
				<label class="col-sm-3">パスワード</label>
				<input type="password" class="form-control col-sm-8" name="pass"
					>
			</div>
			<div class="row">
				<label class="col-sm-3">パスワード(確認)</label>
				<input type="password" class="form-control col-sm-8" name="copypass"
					>
			</div>
			<div class="row">
				<label class="col-sm-3">生年月日</label>
				<input type="date" class="form-control col-sm-8" name="birth"
					value="${user.birth}">
			</div>

			<br>
			<h5>本当にこの内容でユーザデータを更新しても良いですか？</h5>
			<a class="btn btn-dark" href="Userlist" role="button">戻る</a>
			<button type="submit" class="btn btn-success">登録</button>
	</div>
	</form>
<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>