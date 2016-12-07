<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用数字正泰</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function init(){
			var tempForm = document.createElement("form");
		    tempForm.action = "<%=basePath%>login/main.do";
		    tempForm.method = "post";
		    tempForm.style.display = "none";
	        var opt = document.createElement("textarea");
	        opt.name = "userName";
	        opt.value = "screenUser";
	        tempForm.appendChild(opt);
		    document.body.appendChild(tempForm);
		    tempForm.submit();
	    }
	</script>
  </head>
  
  <body onload="init()">
    	系统正在处理中...
  </body>
</html>
