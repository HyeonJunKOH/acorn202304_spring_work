<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/coffee/upload.jsp</title>
</head>
<body>
	<script>
		alert("메뉴 등록에 성공했습니다.")
		location.href="${pageContext.request.contextPath}/gallery/list";
	</script>
</body>
</html>