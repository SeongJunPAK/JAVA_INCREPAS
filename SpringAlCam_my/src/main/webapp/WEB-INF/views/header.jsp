<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	
	#btn_home{
		margin-right: 640px;
	}
	
	#btn_join_form{
		
	}
	
	#btn_login_form{
		
	}
	
	#btn_logout{
		
	}
	
</style>

</head>
<body>

<!-- 헤더 -->
<div>
	<a href="index.jsp"><img id="btn_home" src="resources/image/logo.png" alt="알고싶지캠핑?"></a>
	<!-- onclick 시 로그인/회원가입 화면으로 이동하기
		 member파일 안에 새 jsp 파일 생성하여 작성해주세요
	 -->
	<a href="member/insert_form.do"><img id="btn_join_form" src="resources/image/btn_join.png" alt="회원가입"></a>
	<!-- 로그인 상태 조건식으로 체크하고 로그인<->로그아웃 변경하기 -->
	<a href="member/login_form.do"><img id="btn_login_form" src="resources/image/btn_login.png" alt="로그인"></a>
	<a href="member/logout.do" style="visibility: hidden;"><img id="btn_logout" src="resources/image/btn_logout.png" alt="로그아웃"></a>
</div>

</body>
</html>