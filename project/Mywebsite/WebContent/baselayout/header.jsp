<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!-- header start -->
	<header>
			<div class="site-logo">
				<a href="Index"><img src="img/logo.png"></a>
			</div>
			<div class="header-list">
				<ul>
					<li><a href="Logout"><button class="header-button" type="button" class="btn btn-dark">ログアウト</button></a></li>
					<li><a href="Newentry"><button class="header-button" type="button" class="btn btn-dark">新規登録</button></a></li>
					<li><a href="Userinfo?id=${sessionScope.userinfo.id}"><button class="header-button-user" type="button" class="btn btn-dark"><i class="fas fa-address-card">マイページ</i></button></a></li>
					<li><a href="Cart"><button class="header-button-cart" type="button" class="btn btn-warning"><i class="fas fa-shopping-cart"></i></button></a></li>
					<li class="username"><b>ようこそ${sessionScope.userinfo.name}さん</b></li>
					<c:if test = "${sessionScope.userinfo.id==1}">
						<li><a href="Masterentry"><button class="header-button-master" type="button">商品マスタ
								新規登録</button></a></li>
					<li><a href="Masteritem"><button class="header-button-master" type="button">商品マスタ
								一覧</button></a></li>
					</c:if>

				</ul>
			</div>
	</header>
	<!-- header end -->