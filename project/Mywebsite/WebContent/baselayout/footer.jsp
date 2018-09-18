<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- footer start -->
<footer>
	<div class="footer-address">
		<ul>
			<li>〒@@@-@@@@</li>
			<li>@@県???市******区\\\</li>
			<li>043-\\\-@@@@</li>
		</ul>
	</div>

	<div class="footer-button">
		<c:if test ="${sessionScope.userinfo.id==1}">
		<li><a href="Userlist">
				<button class="footer-button-user" type="button"
					class="btn btn-warning">
					<i class="fas fa-address-card">ユーザ一覧</i>
				</button>
		</a></li>
		</c:if>
	</div>
</footer>
<!-- footer end -->