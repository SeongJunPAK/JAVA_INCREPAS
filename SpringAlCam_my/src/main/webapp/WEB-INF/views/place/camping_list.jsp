<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
	<div>
		<table>
			<tr>
				<th>장소</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>대표사진</th>
				<th>경도</th>
				<th>위도</th>
			</tr>
			<c:forEach var="vo" items="${list}">
					<tr>
						<td>${vo.p_name} </td>
						<td>${vo.p_addr}</td>
						<td>${vo.p_tel}</td>
						<td><img src="${vo.p_filename }"></td>
						<td>${vo.p_x}</td>
						<td>${vo.p_y}</td>
					</tr>
			</c:forEach>				
		</table>	
	</div>
</body>
</html>