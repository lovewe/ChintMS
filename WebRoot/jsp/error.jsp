<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();
	String	errorMsg	= (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <title> 数字正泰 异常页面</title>
   <meta charset="utf-8" />
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div align="center">
		系统内部异常：<%=errorMsg %>
	</div>
</body>
<!-- END BODY -->
</html>