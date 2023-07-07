<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/coffee/order</title>
</head>
<body>
	<div class="container">
		<h2>주문 페이지</h2>
		<ul id="orders">
		
		</ul>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
	<script>
		const orderList=JSON.parse(localStorage.cartList);
		if(orderList != undefined){
			orderList.forEach(function(item){
				const $li = $("<li>").text(item.num);
				$("#orders").append($li);
			});
		}else{
			console.log("주문목록이 없습니다.");
		}
		
	</script>
</body>
</html>