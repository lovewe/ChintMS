<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String version = "20160315";%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>欢迎登录数字正泰大屏</title>
		<meta name="generator" content="editplus" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="keywords" content="选项一,选项二,选项三" />
		<meta http-equiv="description" content="选项" />
		<script type="text/javascript">
			var mozi = mozi || {};
			mozi.contextPath = '<%=contextPath%>';
			mozi.basePath = '<%=basePath%>';
			mozi.version = '<%=version%>';
		</script>
		<link rel="stylesheet" href="<%=contextPath%>/static/css/login.css"/>
	</head>
	<body>
		<form id="loginform" class="form-login" action="<%=contextPath%>/login/login.do"  method="post">
			<div class="frame">
				<div class="box">
					<div class="logo"></div>
					<div class="login">
						<div class="user">
							<input type="text" id="username" name="username"/>
						</div>
						<div class="password">
							<input type="password" id="password" name="password" />
						</div>
				
						<div class="submit" onclick="return loginSumbit();"></div>
						<div id="msgDiv" class="prompt"><strong>警告！</strong><span id="msgSpan">错误</span></div>
					</div>
				</div>
			</div>
		</form>
		<script type="text/javascript">
			if(navigator.appName == "Microsoft Internet Explorer"){
				//alert(navigator.appVersion);
				if(navigator.userAgent.indexOf("MSIE 6.0")>0){
					//alert("IE6");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/respond.min.js\"></scr"+"ipt>");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/excanvas.min.js\"></scr"+"ipt>");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/jQuery/jquery-1.11.1.min.js\"></scr"+"ipt>");
				}else if(navigator.userAgent.indexOf("MSIE 7.0")>0){
					//alert("IE7");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/respond.min.js\"></scr"+"ipt>");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/excanvas.min.js\"></scr"+"ipt>");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/jQuery/jquery-1.11.1.min.js\"></scr"+"ipt>");
				}else if(navigator.userAgent.indexOf("MSIE 8.0")>0){
					//alert("IE8");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/respond.min.js\"></scr"+"ipt>");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/excanvas.min.js\"></scr"+"ipt>");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/jQuery/jquery-1.11.1.min.js\"></scr"+"ipt>");
				}else if(navigator.userAgent.indexOf("MSIE 9.0")>0){
					//alert("IE9");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/jQuery/jquery-2.1.1.min.js\"></scr"+"ipt>");
				}else{
					//alert("其他IE");
					document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/jQuery/jquery-2.1.1.min.js\"></scr"+"ipt>");
				}
			}else{
				//alert("非IE");
				document.write("<script src=\"<%=contextPath%>/rapido/assets/plugins/jQuery/jquery-2.1.1.min.js\"></scr"+"ipt>");
			}
		</script>
		<script type="text/javascript" src="<%=contextPath%>/static/js/util.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=contextPath%>/rapido/assets/js/login.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=contextPath%>/static/js/jquery.cookie.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=contextPath%>/static/js/cookieUtil.js" charset="utf-8"></script>
		<script>
			jQuery(document).ready(function() {
				$('input:text:first').focus(); //把焦点放在第一个文本框
				var $inp = $('input'); //所有的input元素
				$inp.keypress(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
				    var key = e.which; //e.which是按键的值
				    //如果是回车，则自动登录
				    if (key == 13) {
				       loginSumbit();
				    }
				});
			});
		</script>
	</body>
</html>