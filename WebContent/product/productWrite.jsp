<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 등록</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css">
	<style type="text/css">
	table td{
		text-align: left;
	}
	</style>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
	    $("#reg").on("click", function(){
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
		<h1>상품 등록 - 관리자 페이지</h1>
		<form method="post" enctype="multipart/form-data" name="frm">
			<table>
				<tr>
					<th>상 품 명</th>
					<td><input id="name" type="text" name="name" size="80"></td>
				</tr>
				<tr>
					<th>가 격</th>
					<td><input id="price" type="text" name="price"> 원</td>
				</tr>
				<tr>
					<th>사 진</th>
					<td><input type="file" name="pictureUrl"><br>
						(주의사항 : 이미지를 변경하고자 할때만 선택하시오)</td>
				</tr>
				<tr>
					<th>설 명</th>
					<td><textarea cols="80" rows="10" name="description"></textarea></td>
				</tr>
			</table>
			<br> <input id="reg" type="button" value="등록">
			     <input type="reset" value="다시작성">
			     <input type="button" value="목록" onclick="location.href='productList.do'">
		</form>
	</div>
</body>
</html>