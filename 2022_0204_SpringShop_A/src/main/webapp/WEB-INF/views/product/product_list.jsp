<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">

	//jQuery 초기화
	
	$(document).ready(function(){
		   
		   //Timer 사용하는 이유: 현재화면 배치가 완료될 때까지
		   setTimeOut(show_message,100);
	});
	
	function show_message(){
		   // list.do?reason=logout
			if("${param.reason eq 'logout'}"=="true"){
				alert("로그인세션이 만료되었습니다\n다시 로그인 후 이용하세요");
			}
		   
	}

</script>

</head>
<body>

<%-- <jsp:include page="index.jsp"/> --%>

<%@ include file = "index.jsp" %>

<table align="center" width="600" border="1"
 style="border-collapse:collapse;font-size:8pt"
 bordercolor="navy" cellpadding="4" cellspacing="0">
	<tr bgcolor="#dedede">
		<th width="10%">제품번호</th>
		<th width="10%">이미지</th>
		<th width="35%">제품명</th>
		<th width="20%">제품가격</th>
		<th width="25%">비고</th>
	</tr>

	<!-- 등록상품이 없는 경우 -->
	<c:if test="${empty list }">
		<tr>
			<td colspan="5" align="center">
				<font color = red>등록된 상품이 없습니다</font>
			</td>
		</tr>
	
	</c:if>
	
	<!-- 등록상품이 있는 경우 -->
	<c:forEach var = "vo" items = "${list }">
	
		<tr align="center">
			<td>${vo.p_num }</td>
			<td><img src="../resources/images/${vo.p_image_s}" width="100" height="90"></td>
			<td>
			  <a href="view.do?p_idx=${vo.p_idx }">${vo.p_name }</a>
			</td>
			<td>
				할인가:<fmt:formatNumber value = "${vo.p_saleprice }"/>원<br>
				<font color="red">(${vo.p_sale_rate }% DC)</font>
			</td>
			<td>
				단가:<fmt:formatNumber value = "${vo.p_price }"/>원
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>









