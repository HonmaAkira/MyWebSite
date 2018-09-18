<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<form action="Newentry" method="POST">
		<div class="newentry-container">
			<h2>新規ユーザ登録</h2>
			<c:choose>
				<c:when test = "${fail1 !=null}"><p class="text-danger">${fail1}</p></c:when>
				<c:when test = "${fail2 !=null}"><p class="text-danger">${fail2}</p></c:when>
				<c:when test = "${fail2 !=null}"><p class="text-danger">${fail3}</p></c:when>
			</c:choose>
			<div class="row">
				<label class="col-sm-3">ログインID</label>
				<input type="text" class="form-control col-sm-8" name="loginId" aria-describedby="loginHelp" placeholder="Enter loginId">
			</div>

			<div class="row">
				<label class="col-sm-3">ユーザ名</label>
				<input type="text" class="form-control col-sm-8" name="name" aria-describedby="nameHelp" placeholder="Enter name">
			</div>

			<div class="row">
				<label class="col-sm-3">パスワード</label>
				<input type="password" class="form-control col-sm-8" name="password" aria-describedby="passHelp" placeholder="Enter password">
			</div>

			<div class="row">
				<label class="col-sm-3">パスワード(確認)</label>
				<input type="password" class="form-control col-sm-8" name="copypassword" aria-describedby="passconHelp" placeholder="Enter password">
			</div>

			<div class="row">
				<label class="col-sm-3">生年月日</label>
				<input type="date" class="form-control col-sm-8" name="birth" aria-describedby="birthHelp" placeholder="Enter birthday">
			</div>

			<a class="btn btn-dark" href="Index">戻る</a>
			<button type="submit" class="btn btn-primary">新規登録</button>
		</div>
	</form>

<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>