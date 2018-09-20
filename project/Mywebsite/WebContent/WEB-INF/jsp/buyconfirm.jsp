<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認</title>
<jsp:include page="/baselayout/head.html"/>
</head>
<body>
<jsp:include page="/baselayout/header.jsp"/>
<div class="buyconfirm-container">
		<h2>購入確認</h2>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th colspan="2">商品名</th>

					<th colspan="2">単価</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${cart}">
				<tr>
					<td colspan="2">${item.name}</td>
					<td colspan="2"><fmt:formatNumber value="${item.price*1.08}" maxFractionDigits="0"/>円</td>
				</tr>
			</c:forEach>
				<tr>
					<th colspan="2" class="bg-light text-dark">すべての小計</th>
					<td><fmt:formatNumber value="${bdb.totalPrice*1.08}" maxFractionDigits="0"/>円</td>
				</tr>
				<tr>
					<th colspan="2" class="bg-light text-dark">送料</th>
					<td><fmt:formatNumber value="${bdb.deliveryMethodPrice*1.08}" maxFractionDigits="0"/>円</td>
				</tr>
				<tr>
					<th colspan="2" class="bg-light text-danger">合計</th>
					<td class="text-danger"><fmt:formatNumber value="${(bdb.totalPrice*1.08)+(bdb.deliveryMethodPrice*1.08)}" maxFractionDigits="0"/>円</td>
				</tr>
			</tbody>
		</table>

		<a class="btn btn-dark" href="Buy" role="button">戻る</a>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  購入
</button>
<form action = "Buyfinish" method = "POST">
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">ユーザ名${userinfo.name}さん</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       	内容をご確認の上、よろしければ購入ボタンを押してください
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">キャンセル</button>
        <button type="submit" class="btn btn-primary">購入する</button>
      </div>
    </div>
  </div>
</div>
</form>
	</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>

<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>