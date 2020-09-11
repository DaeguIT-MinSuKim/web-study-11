<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<style type="text/css">
	img {
		border-radius: 8px;
		padding: 5px;
		width: 200px;
		height: 3o0px;
	}
</style>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
	    $("#modify").on("click", function(){
			event.preventDefault();
			var res = productCheck();
			alert(res);
			$("form").submit();
	    });
	});
	
	function productCheck() {
		if ($('#name').val().length == 0) {
			alert("상품명을 써주세요.");
			$('#name').focus();
			return false;
		}
		if ($('#price').val().length == 0) {
			alert("가격을 써주세요");
			$('#price').focus();
			return false;
		}
		if (isNaN($('#price').val())) {
			alert("숫자를 입력해야 합니다");
			$('#price').focus();
			return false;
		}
		return true;
	}
	</script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>상품 수정 - 관리자 페이지</h1>
		<form method="post" enctype="multipart/form-data" name="frm">
			<input type="hidden" name="code" value="${product.code}"> <input
				type="hidden" name="nonmakeImg" value="${product.pictureUrl}">
			<table>
				<tr>
					<td>
						<c:choose>
							<c:when test="${empty product.pictureUrl}">
								<img src="upload/noimage.gif">
							</c:when>
							<c:otherwise>
								<img src="upload/${product.pictureUrl}">
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<table>
							<tr>
								<th style="width: 80px">상품명</th>
								<td><input id="name" type="text" name="name" value="${product.name}"
									size="80"></td>
							</tr>
							<tr>
								<th>가 격</th>
								<td><input id="price" type="text" name="price"
									value="${product.price}"> 원</td>
							</tr>
							<tr>
								<th>사 진</th>
								<td><input type="file" name="pictureUrl"><br>
									(주의사항 : 이미지를 변경하고자 할때만 선택하시오)</td>
							</tr>
							<tr>
								<th>설 명</th>
								<td><textarea cols="90" rows="10" name="description">${product.description}</textarea>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br> 
			<input id="modify" type="button" value="수정" onclick="return productCheck()"> 
			<input type="reset"	value="다시작성">
			<input type="button" value="목록" onclick="location.href='productList.do'">
		</form>
	</div>
</body>
</html>