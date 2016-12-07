<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib uri="../WEB-INF/tld/mozi.tld" prefix="mozi"%>
<%
	String menu = (String)request.getAttribute("menuStr");
	String userName = (String)request.getAttribute("userName");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <title> 数字正泰</title>
   <meta charset="utf-8" />
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
<%@ include file="../jsp/inc/taglibs.jsp" %>
    <script type="text/javascript">
    var name ='<%=userName %>';
    function doSubmit(url) {
    	//初始化高度
    	var ifm= document.getElementById("rightMain"); 
    	
    	if(ifm != null){
    		//ifm.height = 630;
    		if(url.indexOf('?') > 0){
    			ifm.src = mozi.contextPath + url+"&userName=<%=userName %>";
	    	}else{
	    		ifm.src = mozi.contextPath + url+"?userName=<%=userName %>";
	    	}
    	}
    }
    
    function iFrameHeight(){
		var pTar = null;
		if (document.getElementById("rightMain")){
			pTar = document.getElementById("rightMain");
		}else{
			eval('pTar = rightMain;');
		}
		if (pTar && !window.opera){
			//begin resizing iframe
			pTar.style.display="block"
			if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){
				//ns6 syntax
				if(pTar.contentDocument.body.offsetHeight > 150){
					pTar.height = pTar.contentDocument.body.offsetHeight;
				}else{
					pTar.height = 150;
				}
				
				//pTar.width = pTar.contentDocument.body.scrollWidth+20;
			}else if (pTar.Document && pTar.Document.body.scrollHeight){
				//ie5+ syntax
				if(pTar.Document.body.scrollHeight > 150){
					pTar.height = pTar.Document.body.scrollHeight;
				}else{
					pTar.height = 150;
				}
				//pTar.width = pTar.Document.body.scrollWidth;
			}
		} 
    }
    
    function setIFrameSrc(){
		var pTar = null;
		if (document.getElementById("rightMain")){
			pTar = document.getElementById("rightMain");
		}else{
			eval('pTar = rightMain;');
		}
		pTar.src="<%=basePath %>/jsp/content.jsp";
    }
    </script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
   <!-- BEGIN HEADER -->
   <div id="header" class="navbar navbar-inverse navbar-fixed-top">
       <!-- BEGIN TOP NAVIGATION BAR -->
       <div class="navbar-inner">
           <div class="container-fluid">
               <!-- BEGIN LOGO -->
               <a class="brand" id="headlogo" href="#" onClick="setIFrameSrc()">
                   <img src="../static/img/chintLogo4.png" />
               </a>
               <!-- END LOGO -->
               <!-- BEGIN RESPONSIVE MENU TOGGLER -->
               <a class="btn btn-navbar collapsed" id="main_menu_trigger" data-toggle="collapse" data-target=".nav-collapse">
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="arrow"></span>
               </a>
               <!-- END RESPONSIVE MENU TOGGLER -->
               <div id="top_menu" class="nav notify-row">
                   <!-- BEGIN NOTIFICATION -->
                   <ul class="nav top-menu">
                       <!-- BEGIN SETTINGS -->
                       <li class="dropdown">
                           <font style="font-size:28px;line-height:40px;" face="微软雅黑" color="#FFFFFF"><b>数字正泰民商宝后台管理系统</b></font>
                       </li>
                       <!-- END SETTINGS -->

                   </ul>
               </div>
               <!-- END  NOTIFICATION -->
               <div class="top-nav ">
                   <ul class="nav pull-right top-menu" >
                       <!-- BEGIN USER LOGIN DROPDOWN -->
                       <li class="dropdown">
                           <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                               <img src="../static/img/avatar1_small.jpg" alt="">
                               <span class="username"><font color="#FFFFFF"><%=userName %></font></span>
                               <b class="caret"></b>
                           </a>
                           <ul class="dropdown-menu">
<!--                           		<li class="divider"></li>-->
<!--								<li><a href="#" onclick="showModel()"><i class="icon-lock"></i>修改密码</a></li>-->
								<li class="divider"></li>
								<li><a href="./logout.do?userName=<%=userName %>"><i class="icon-key"></i>用户登出</a></li>
                           </ul>
                       </li>
                       <!-- END USER LOGIN DROPDOWN -->
                   </ul>
                   <!-- END TOP NAVIGATION MENU -->
               </div>
           </div>
       </div>
       <!-- END TOP NAVIGATION BAR -->
   </div>
   <!-- END HEADER -->
   <!-- BEGIN CONTAINER -->
   <div id="container" class="row-fluid">
      <!-- BEGIN SIDEBAR -->
      <div id="sidebar" class="nav-collapse collapse">

         <div class="sidebar-toggler hidden-phone"></div>   

         <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
         <div class="navbar-inverse">
            <form class="navbar-search visible-phone">
               <input type="text" class="search-query" placeholder="Search" />
            </form>
         </div>
         <!-- END RESPONSIVE QUICK SEARCH FORM -->
         <!-- BEGIN SIDEBAR MENU -->
          <%=menu %>
         <!-- END SIDEBAR MENU -->
      </div>
      <!-- END SIDEBAR -->
      <div class="modal hide" id="passModal">
		    <div class="modal-header">
		        <button class="close" data-dismiss="modal">x</button>
		        <h3>修改密码</h3>
		    </div>
		    <div class="modal-body">
				<form class="bs-example bs-example-form">
		      		<div class="input-group">
		         		<span class="input-group-addon">原&nbsp;&nbsp;密&nbsp;&nbsp;码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		         		<input type="password" class="form-control"  name="oldpassword">
		      		</div>
		      		<br>
					<div class="input-group">
					   <span class="input-group-addon">新&nbsp;&nbsp;密&nbsp;&nbsp;码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					   <input type="password" class="form-control" name="password">
					</div>
					<br>
					<div class="input-group">
					   <span class="input-group-addon">确&nbsp;&nbsp;认&nbsp;&nbsp;密&nbsp;&nbsp;码:&nbsp;&nbsp;</span>
					   <input type="password" class="form-control" name="password2">
					</div>
					<br>
					<div id="msgDiv" class="alert alert-error" style="display:none;">
						<strong>提示:</strong><span id="msgSpan"></span>
					</div>
				</form>
		    </div>
		    <div class="modal-footer">
		        <a href="#" class="btn btn-primary" id="user" onclick="updatePwd();">确&nbsp;&nbsp;定</a>
		        <a href="#" class="btn" data-dismiss="modal">关 &nbsp;&nbsp;闭</a>
		    </div>  
		</div>
      <!-- BEGIN PAGE -->  
      <div id="main-content">
         <!-- BEGIN PAGE CONTAINER-->
         <iframe name="right" id="rightMain" src="<%=contextPath%>/login/content.do"  width="99%" onLoad="iFrameHeight();" style="border:0; overflow:hidden;"></iframe>
         <!-- END PAGE CONTAINER-->
      </div>
      <!-- END PAGE -->  
   </div>
   <!-- END CONTAINER -->
   <!-- BEGIN FOOTER -->
   <div id="footer">
       ©2015-2016 数字正泰-Chint Data 版权所有
       <div class="span pull-right">
         <span class="go-top"><i class="icon-arrow-up"></i></span>
      </div>
   </div>
   <!-- END FOOTER -->
   <!-- BEGIN JAVASCRIPTS -->
   <script type="text/javascript" src="<%=contextPath%>/jsp/mail.js"></script>
   <!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>