<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/coffee/update.jsp</title>
</head>
<body>
	<script>
		alert("${id} 님 메뉴가 수정 되었습니다.");
		location.href="${pageContext.request.contextPath}/coffee/detail?num=${param.num}";
	</script>
</body>
</html>