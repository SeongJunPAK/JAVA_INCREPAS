<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">
	#box{
		width: 1000px;
		margin: auto;
		margin-top: 100px;
	}
	
	img{
		width: 120px;
		height: 80px;
		
	}
	
</style>

</head>
<body>

	
	<fmt:setLocale value="ko"/>
	<div id = "box">
		<table class = "table table-striped table-hover">
			<!-- title -->
			<tr>
				<th>번호</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>가격</th>
				<th>분류</th>
			</tr>
			
			<!-- data -->
			<!-- for(ProductVo vo : list) -->
			<!-- 통화단위 : 지역지정  -->
			
			
			<c:forEach var="vo" items = "${list}">
				<tr>
					<td>${i.count }</td>
					<td><img src = ${vo.g_filename }></td>
					<td><a href = "${vo.g_link }" target = "_blank">${ vo.g_name}</a></td>
				
					
					<td><fmt:formatNumber   value = "${vo.g_price }"/>(원)</td>
					<td>${vo.g_category }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>