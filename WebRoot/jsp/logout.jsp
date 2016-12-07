<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <title> 数字正泰 登录超时页面</title>
   <meta charset="utf-8" />
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div align="center">
	<h1>用户登录超时</h1>    
		<a href="#" onclick="jumpurl()">3秒后会自动跳转登录页面，也可点击本处直接跳转</a>
	</div>
	<script type="text/javascript">
		function jumpurl(){ 
			if(window.parent){
				window.parent.location.href='<%=contextPath %>/jsp/login/login.jsp';  
			}else{
				location='<%=contextPath %>/jsp/login/login.jsp';
			}
		}  
		setTimeout('jumpurl()',3000);
	</script>
</body>
<!-- END BODY -->
</html>