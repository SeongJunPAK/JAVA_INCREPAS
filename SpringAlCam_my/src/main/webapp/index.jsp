<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>

<!-- BootStrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/main.css">


</head>
<body>

<!-- 전체 박스 -->
<div id="box">
	
	<!-- 헤더 -->
	<div id="header">
		<%@ include file="WEB-INF/views/header.jsp" %>
	</div>
	
	<!-- 메뉴 -->
	<div id="nav">
		<%@ include file="WEB-INF/views/menu.jsp" %>
	</div>
	
	<!-- 메인 -->
	<div id="main">
	
		<!-- 메뉴가 없거나 home인 경우 -> 메인화면 띄우기 -->
		<c:if test="${ empty param.menu }">
			<!-- 키워드검색 -->
			<div id="search">
				<%@ include file="WEB-INF/views/search.jsp" %>
			</div>			
			
			<!-- 공지사항 -->	
			<div id="notice">
				<%@ include file="WEB-INF/views/notice.jsp" %>
			</div>
			
			<!-- 인기글(조회수) -->
			<div id="best">
				<%@ include file="WEB-INF/views/best.jsp" %>
			</div>
		</c:if>
		
		
		
		<!-- 1. 캠핑장소검색 누른 경우(menu=place) -->
		<c:if test="${ (param.menu eq 'place') }">
			<%@include file="WEB-INF/views/place/place_list.jsp" %>	
		</c:if>
		
		
		<!-- 2. 캠핑장소추천 누른 경우(menu=recommend_place) -->
		<c:if test="${ (param.menu eq 'recommend_place') }">
			<%@include file="WEB-INF/views/recommend_place/r_place_list.jsp" %>	
		</c:if>
		
		
		<!-- 3. 캠핑용품검색 누른 경우(menu=goods) -->
		<c:if test="${ (param.menu eq 'goods') }">
			<%@include file="WEB-INF/views/goods/goods_list.jsp" %>	
		</c:if>
		
		<!-- 4. 고객센터 누른 경우(menu=notice) -->
		<c:if test="${ (param.menu eq 'notice') }">
			<%@include file="WEB-INF/views/notice/notice_list.jsp" %>	
		</c:if>
		
		<!-- 5. 마이페이지 누른 경우(menu=member) -->
		<c:if test="${ (param.menu eq 'member') }">
			<%@include file="WEB-INF/views/member/member_list.jsp" %>	
		</c:if>
		
	
	</div>
	
	<!-- 푸터 -->
	<div id="footer">
		<%@ include file="WEB-INF/views/footer.jsp" %>
	</div>


</div>


</body>
</html>