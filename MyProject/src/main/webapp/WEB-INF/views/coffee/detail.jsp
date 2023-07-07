<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/detail.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<div class="container">
   <nav>
      <ul class="breadcrumb">
         <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath }/">Home</a>
         </li>
         <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath }/coffee/list">메뉴보기</a>
         </li>
         <li class="breadcrumb-item active">상세보기</li>
          <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath }/coffee/update_form?num=${dto.num }">수정하기</a>
         </li>
      </ul>
   </nav>
   <div class="card mb-3">
      <img class="card-img-top" src="${pageContext.request.contextPath}${dto.imagePath}"/>
      <div class="card-body">
         <p class="card-text">메뉴 이름: <strong>${dto.title}</strong></p>
         <p class="card-text">메뉴 설명: <strong>${dto.caption}</strong></p>
         <p><small>가격: <strong>${dto.price}</strong></small></p>
      </div>
   </div>
   <nav>
      <ul class="pagination justify-content-center">
         <c:choose>
            <c:when test="${dto.prevNum ne 0 }">
               <li class="page-item mr-3">
                     <a class="page-link" href="${pageContext.request.contextPath}/coffee/detail?num=${dto.prevNum}">&larr; Prev</a>
               </li>
            </c:when>
            <c:otherwise>
               <li class="page-item disabled mr-3">
                     <a class="page-link" href="javascript:">Prev</a>
               </li>
            </c:otherwise>
         </c:choose>
         <c:choose>
            <c:when test="${dto.nextNum ne 0 }">
               <li class="page-item">
                     <a class="page-link" href="${pageContext.request.contextPath}/coffee/detail?num=${dto.nextNum}">Next &rarr;</a>
               </li>
            </c:when>
            <c:otherwise>
               <li class="page-item disabled">
                     <a class="page-link" href="javascript:">Next</a>
               </li>
            </c:otherwise>
         </c:choose>         
      </ul>
   </nav>      
</div>
</body>
</html>