<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/uploadform.jsp</title>
</head>
<body>
	<div class="container">
		<form action="upload" method="post" enctype="multipart/form-data">
			<input type="text" name="title" placeholder="제목입력..."/>
			<br />
			파일 선택<input type="file" name="myFile"/>
			<br />
			<button type="submit">업로드</button>
		</form>
	</div>
</body>
</html>