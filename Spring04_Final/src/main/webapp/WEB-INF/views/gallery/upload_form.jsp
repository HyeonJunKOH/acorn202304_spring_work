<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/upload_form.jsp</title>
</head>
<body>
	<div class="container">
		<h3>갤러리 업로드 폼 입니다.</h3>
		<form action="upload" method="post">
			<div>
				<label for="caption">이미지 설명</label>
				<textarea name="caption" id="caption"rows="10"></textarea>
			</div>
			<div>
				<label for="myGallery">첨부파일</label>
				<input type="file" name="myGallery" id="myGallery"/>
			</div>
			<button type="submit">업로드</button>
		</form>
	</div>
</body>
</html>