<%@ page language="java" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String version = "20150413";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>请输入标题</title>
		<meta name="generator" content="editplus" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="keywords" content="选项一,选项二,选项三" />
		<meta http-equiv="description" content="选项" />
		<link rel="stylesheet" type="text/css" href="css/ipad.css" >
		<script type="text/javascript">
			var basePath='<%=basePath%>';
		</script>
	</head>
	<body>
		<div id="paidShow">
			<div val="chintGroup">集团公司</div>
			<div val="chintStock">股份公司</div>
			<div val="powerStationGeneral">新能源电站总览</div>
			<div val="annengPowerStation">安能电站</div>
			<div val="cnePowerStation">新能源电站</div>
			<div val="astrGlobalFactory">太阳能全球工厂</div>
		</div>
		<script type="text/javascript" src="js/jquery_1.8.3_min.js"></script>
		<script type="text/javascript" src="js/ipad.js"></script>
		<script type="text/JavaScript" >
			function rem(){
				if (window.innerWidth){
					winWidth = window.innerWidth;  
				}else if ((document.body) && (document.body.clientWidth)){
					winWidth = document.body.clientWidth;
				}  
				winWidth > 870 ?  document.getElementsByTagName("html")[0].style.fontSize="30px" : document.getElementsByTagName("html")[0].style.fontSize = winWidth * 0.04 +"px";
			}
			rem();	
		</script>
	</body>
</html>
