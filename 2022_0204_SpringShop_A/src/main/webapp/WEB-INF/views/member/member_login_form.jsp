<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      width: 400px;
      margin: auto;
      margin-top: 100px;
   }
   
   input[type='button']{
       width: 80px;
   } 
</style>

<script type="text/javascript">
  function  send(f){
	  
	  var m_id  = f.m_id.value.trim();
	  var m_pwd = f.m_pwd.value.trim();
	  
	  if(m_id == ''){
		  alert('아이디를 입력하세요');
		  f.m_id.value='';
		  f.m_id.focus();
		  return;
	  }
	  
	  if(m_pwd == ''){
		  alert('비밀번호를 입력하세요');
		  f.m_pwd.value='';
		  f.m_pwd.focus();
		  return;
	  }
	  
	  f.action = "login.do"; // MemberLoginAction
	  f.submit();//전송
	  
  }

  
  $(document).ready(function(){
	 
	  setTimeout(show_message,100);
	  
  });
  
  
  function show_message(){
	  // /member/login_form.do?reason=fail_id

      if("${ param.reason eq 'fail_id'}"=="true"){
    	  
    	  alert('아이디가 틀렸습니다');
    	  
      }	
	  
      // /member/login_form.do?reason=fail_pwd
	  if("${ param.reason eq 'fail_pwd'}"=="true"){
    	  
    	  alert('비밀번호가 틀렸습니다');
    	  
      }	
	  
  }
  
</script>



</head>
<body>
   <form>
	   <div id="box">
	       <div class="panel panel-primary">
		      <div class="panel-heading">로그인</div>
		      <div class="panel-body">
		          <table class="table  table-striped">
		              <tr>
		              	 <th>아이디</th>
		              	 <td><input name="m_id"></td>
		              </tr>
		              <tr>
		              	 <th>비밀번호</th>
		              	 <td><input type="password" name="m_pwd"></td>
		              </tr>
		              <tr>
		                 <td colspan="2" align="center">
		                      <input class="btn btn-primary"  type="button"  value="로그인"    onclick="send(this.form);">
		                      <input class="btn btn-success"  type="button"  value="메인보기"  onclick="location.href='../product/list.do'">
		                      <input class="btn btn-info"     type="button"  value="회원가입"  onclick="location.href='insert_form.do'"> 
		                 </td>
		              </tr>
		          </table>
		      </div>
		   </div>
	   </div>
   </form>



</body>
</html>