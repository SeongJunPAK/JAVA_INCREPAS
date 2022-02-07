<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL -->
<%@ taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
  #box{
      width: 1000px;
      margin: auto;
      margin: 50px; 
  }
  
  table {
	  width: 100%;
  }
  
  th{
      text-align: center;
      font-weight: bold;
  }
  
  #title{
      text-align: center;
      
      font-size: 25px;
      font-weight: bold;
  }
  
  #empty_message{
      text-align: center;
      color: red;
      font-size: 15px;
      font-weight: 800;
  }
  
  input[value='수정']{
     border: 1px solid black;
     background: skyblue;
  }
  
  input[value='삭제']{
     border: 1px solid black;
     background: pink;
  }
</style>


<script type="text/javascript">
  
   function del(m_idx){
	   
	   //alert('삭제할 m_idx = ' + m_idx);
	   
	   if(confirm("정말 삭제하시겠습니까?")==false)return;
	   
	   //              MemberDeleteAction 
	   location.href = "delete.do?m_idx=" + m_idx ;
   }
   
   //수정폼 띄우기
   function modify_form(m_idx){
	   //              MemberModifyFormAction   
	   location.href = "modify_form.do?m_idx=" + m_idx;
	   
   }

</script>


</head>
<body>
  <div id="box">
      <h1 id="title">::::회원목록::::</h1>
      <div style="text-align: right;  margin-top: 20px; margin-bottom: 20px;">
            
            <!-- 로그인이 안되었을 경우 -->
            <c:if test="${ empty sessionScope.user }">
	            <input class="btn  btn-success"   type="button"  value="로그인"   
	                   onclick="location.href='login_form.do'">
	            <input class="btn  btn-primary"   type="button"  value="회원가입" 
	                   onclick="location.href='insert_form.do'">
            </c:if>
            
            <!-- 로그인이 된상태 -->
            <c:if test="${ not empty sessionScope.user }">
               <b>${ user.m_name }</b>님 환영합니다
               <input class="btn  btn-success"   type="button"  value="로그아웃"   
	                   onclick="location.href='logout.do'">
            
            </c:if>       
                   
                   
      </div>
      
      <table class="table  table-striped">
           <!-- title -->
           <tr class="success">
               <th>번호</th>
               <th>이름</th>
               <th>아이디</th>
               <th>비밀번호</th>
               <th>우편번호</th>
               <th>주소</th>
               <th>아이피</th>
               <th>가입일자</th>
               <th>회원구분</th>
               <th>편집</th> 
           </tr>
           
           <!-- Data가 없는경우  -->
           <c:if test="${ empty list }">
              <tr>
                  <td id="empty_message" colspan="10">회원정보가 없습니다</td>
              </tr>
           </c:if>
           
           <!-- Data가 있는경우 -->
           <!--  for(MemberVo vo : list)  -->
           <c:forEach var="vo"  items="${ list }">
               <tr>
                   <td>${ vo.m_idx }</td>
                   <td>${ vo.m_name }</td>
                   <td>${ vo.m_id }</td>
                   <td>${ vo.m_pwd_hidden }</td>
                   <td>${ vo.m_zipcode }</td>
                   <td>${ vo.m_addr }</td>
                   <td>${ vo.m_ip }</td>
                   <td>${ fn:substring(vo.m_regdate,0,10) }</td>
                   <td>${ vo.m_grade }</td>
                   <td>
                       <!-- 로그인된상태에서 로그인유저가 관리자가 아닐경우  --> 
                       <c:if test="${ (not empty user) and (user.m_grade ne '관리자') }">
                       		<font color=red>편집권한 없습니다</font> 
                       </c:if>
                       <!-- 로그인유저가 관리자 일때만 가능토록  -->
                       <c:if test="${ user.m_grade eq '관리자' }">
	                       <input type="button"  value="수정" onclick="modify_form('${ vo.m_idx}');">
	                       <input type="button"  value="삭제" onclick="del('${ vo.m_idx }');">
                       </c:if>
                   </td>
               </tr>
           </c:forEach>
           
      
      </table>   
  </div>
</body>
</html>








