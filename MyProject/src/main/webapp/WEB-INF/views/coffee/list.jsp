<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/coffee/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
   /* card 이미지 부모요소의 높이 지정 */
   .img-wrapper{
      height: 250px;
      /* transform 을 적용할대 0.3s 동안 순차적으로 적용하기 */
      transition: transform 0.3s ease-out;
   }
   /* 화면의 폭이 575px 이하 일때 적용할 css */
   @media(max-width: 576px){
		.img-wrapper{
			height: 350px;
		}
   }
   /* .img-wrapper 에 마우스가 hover 되었을때 적용할 css */
   .img-wrapper:hover{
      /* 원본 크기의 1.1 배로 확대 시키기*/
      transform: scale(1.1);
   }
   
   .card .card-text{
      /* 한줄만 text 가 나오고  한줄 넘는 길이에 대해서는 ... 처리 하는 css */
      display:block;
      white-space : nowrap;
      text-overflow: ellipsis;
      overflow: hidden;
   }
      .img-wrapper img{
         width: 100%;
         height: 100%;
         /* fill | contain | cover | scale-down | none(default) */
         /*   
            cover - 부모의 크기에 맞게 키운 후, 자른다. 비율은 일정하게 증가한다. 
            contain - 안잘린다. 대신 빈 공간이 남을 수 있다.
            fill - 부모의 크기에 딱 맞게, 비율 관계 없이 맞춘다.(이미지가 일그러질 수 있다.)
            scale-down - 가로, 세로 중에 큰 것을 부모의 크기에 맞춘 상태까지만 커지거나 작아지고, 비율은 일정하다.
         */
      object-fit: cover;   
      }
</style>
</head>
<body>
<div class="container">
      <a href="${pageContext.request.contextPath}/coffee/upload_form">메뉴 등록</a>
      <a href="${pageContext.request.contextPath}/"><span>메인 홈페이지</span></a>
      <a href="${pageContext.request.contextPath}/coffee/order_list" id="count"><span>장바구니</span></a>
      <h1>Coffee Menu</h1>
      <div class="row">
      <c:forEach var="tmp" items="${list }">
         <div class="col-sm-6 col-md-4 col-lg-3">
               <div class="card mb-3">
                  <a href="${pageContext.request.contextPath}/coffee/detail?num=${tmp.num}">
                        <div class="img-wrapper">
                           <img class="card-img-top" src="${pageContext.request.contextPath }${tmp.imagePath}" />
                        </div>
                  </a>
                  <div class="card-body">
                        <p class="card-text">메뉴 : <strong>${tmp.title}</strong></p>
                        <p class="card-text">메뉴설명: <strong>${tmp.caption}</strong></p>
                        <p>가격: <strong>${tmp.price}</strong></p>
                        <button data-num="${tmp.num }" data-title="${tmp.title }"data-price="${tmp.price }" class="addBtn">담기</button>
      	 				<button onclick="location.href=${pageContext.request.contextPath}/coffee/order_list">주문하기</button>
                  </div>
               </div>
            </div>
      </c:forEach>
      </div>
      <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
      <script>
      
	   		//만일 한번도 cart 에 담은적이 없다면 
	    	if(localStorage.cartList == undefined){
	    		//빈 배열 형태의 JSON 문자열을  cartList 라는 키값으로 저장한다. 
	    		localStorage.cartList="[]";
	    	}
	    	//JSON 문자열을  실제 배열로 변환
	    	const cartList=JSON.parse(localStorage.cartList);
	    	
	    	$(".addBtn").click(function(e){
	    		/*
	    		const num=e.target.getAttribute("data-num");
	    		const price=e.target.getAttribute("data-price");
	    		*/
	    		const num=$(e.target).attr("data-num");
	    		const title=$(e.target).attr("data-title");
	    		const price=$(e.target).attr("data-price");
	    		cartList.push({num, title, price});
	    		//localStorage 에 반영, cartList를 json 문자열로 변경해주기
	    		localStorage.cartList=JSON.stringify(cartList);
	  
	    	});
      </script>
      <nav>
   <ul class="pagination justify-content-center">
      <c:choose>
         <c:when test="${startPageNum ne 1 }">
            <li class="page-item">
                     <a class="page-link" href="${pageContext.request.contextPath}/coffee/list?pageNum=${startPageNum - 1}">Prev</a>
               </li>
         </c:when>
         <c:otherwise>
            <li class="page-item disabled">
                     <a class="page-link" href="javascript:">Prev</a>
            </li>
         </c:otherwise>
      </c:choose>
      <c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
         <c:choose>
            <c:when test="${i eq pageNum }">
               <li class="page-item active">
                        <a class="page-link" href="${pageContext.request.contextPath}/coffee/list?pageNum=${i}">${i }</a>
                     </li>
            </c:when>
            <c:otherwise>
               <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/coffee/list?pageNum=${i}">${i}</a>
                     </li>
            </c:otherwise>
         </c:choose>
      </c:forEach>
      <c:choose>
         <c:when test="${endPageNum lt totalPageCount }">
            <li class="page-item">
                     <a class="page-link" href="${pageContext.request.contextPath}/coffee/list?pageNum=${endPageNum + 1}">Next</a>
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
<%-- <script>
   // card 이미지의 부모 요소를 선택해서 imgLiquid  동작(jquery plugin 동작) 하기 
   $(".img-wrapper").imgLiquid();
</script> --%>
</body>
</html>