<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<div class="Login-display">
		<div class="Login-display-Transparent">
			<div class="Login-container">
				<div class="Login-site-logo">
					<img src="img/logo (1).png">
				</div>
				<c:if test= "${errMsg!=null}"><p class="text-danger">ログインIDまたはパスワードが異なります</p></c:if>
				<div class="Login-site-contents">
					<form action="Login" method="POST">
						<div class="form-group-loginId">
							<input type="text" class="form-control" name="loginId"
								aria-describedby="loginHelp" placeholder="Enter loginId">
						</div>

						<div class="form-group-loginPass">
							<input type="password" class="form-control" name="password"
								aria-describedby="passwordHelp" placeholder="Enter password">
						</div>
						<button type="submit" class="btn btn-dark">ログイン</button>
					</form>
					<p class="Login-message">新規登録がまだの方</p>
					<a href="Newentry"><button type="button"
							class="btn btn-primary">新規登録へ</button></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>