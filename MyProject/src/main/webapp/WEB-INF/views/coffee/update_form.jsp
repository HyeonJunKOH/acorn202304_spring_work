<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/coffee/update_form.jsp</title>
</head>
<body>
<div class="container">
	<h1>메뉴 수정 폼 입니다.</h1>
	<form action="${pageContext.request.contextPath}/coffee/update" method="post">
		<input type="hidden" name="num" value="${dto.num}" />
		<div>
			<label for="title">메뉴 이름</label>
			<input type="text" name="title" id="title" value="${dto.title }"/>
		</div>
		<div>
			<label for="caption">메뉴 설명</label>
			<input type="text" name="caption" id="caption" value="${dto.caption }"/>
		</div>
		<div>
			<label for="price">가격</label>
			<input type="text" name="price" id="price" value="${dto.price }"/>
		</div>
		<button type="submit">수정확인</button>
		<button type="reset">취소</button>
	</form>
</div>

<script>

</script>
</body>
</html>