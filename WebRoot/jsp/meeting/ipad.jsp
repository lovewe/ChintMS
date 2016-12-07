<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>请输入标题</title>
		<meta name="generator" content="editplus" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="keywords" content="选项一,选项二,选项三" />
		<meta http-equiv="description" content="选项" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
		<meta content="telephone=no" name="format-detection" />
		<link rel="stylesheet" type="text/css" href="css/ipad.css" />
		<script type="text/javascript">
			var basePath ='<%=basePath%>';
		</script>
	</head>
	<body>
		<div id="console">
			<div class="refresh">刷新</div>
		</div>
		<div id="showframe" class="clearfix">
			<div class="showbox">
				<div class="shownum">1</div>	
			</div>
			<div class="showbox">
				<div class="shownum">2</div>	
			</div>
			<div class="showbox">
				<div class="shownum">3</div>	
			</div>
			<div class="showbox">
				<div class="shownum">4</div>	
			</div>
			<div class="showbox">
				<div class="shownum">5</div>	
			</div>
			<div class="showbox">
				<div class="shownum">6</div>	
			</div>
			<div class="showbox">
				<div class="shownum">7</div>	
			</div>
			<div class="showbox">
				<div class="shownum">8</div>	
			</div>
			<div class="showbox">
				<div class="shownum">9</div>	
			</div>
			<div class="showbox">
				<div class="shownum">10</div>	
			</div>
			<div class="showbox">
				<div class="shownum">11</div>	
			</div>
			<div class="showbox">
				<div class="shownum">12</div>	
			</div>
		</div>
		<div id="list">
			<div class="listcanvas" id="listcanvas">
				<div class="larrow">&lt</div>
				<div class="rarrow">&gt</div>
			</div>
		</div>
		<div id="moveclone">
			<img src="" alt="">
			<span></span>
		</div>
		<script type="text/javascript" src="js/jquery_1.8.3_min.js"></script>
		<script type="text/javascript" src="js/ipad.js"></script>
	</body>
</html>
