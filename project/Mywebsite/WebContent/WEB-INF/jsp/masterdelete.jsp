<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品マスタ削除</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="masterdelete-container">
		<h2>商品マスタ削除</h2>
			<h5>
				<i class="fas fa-exclamation-triangle"></i>商品名：${item.name}の商品データを削除します。<i
					class="fas fa-exclamation-triangle"></i>
			</h5>
			<form action="Masterdelete" method="POST">
			<input type="hidden" name="id" value="${item.id}">
			<a class="btn btn-dark" href="Masteritem" role="button">戻る</a>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-danger" data-toggle="modal"
				data-target="#exampleModal">削除</button>

			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">${item.name}</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">本当にこの商品データを削除してよろしいですか？</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">キャンセル</button>
							<button type="submit" class="btn btn-danger">削除する</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"
		integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em"
		crossorigin="anonymous"></script>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>