<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ詳細</title>
<jsp:include page="/baselayout/head.html"/>

</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="userdetail-container">
		<h2>ユーザ詳細</h2>
		<div class="userdetail-list">
			<p>ログインID：<span>${user.login_id}</span></p>
			<p>
				ユーザ名：<span>${user.name}</span>
			</p>
			<p>
				生年月日：<span>${user.birth}
								</span>
			</p>

			<p>
				登録日時：<span>${user.create_data}</span>
			</p>
			<p>
				更新日時：<span>${user.update_data}</span>
			</p>
		</div>
		<br>
		<a class="btn btn-dark" href="Userlist" role="button">戻る</a>
	</div>
<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>