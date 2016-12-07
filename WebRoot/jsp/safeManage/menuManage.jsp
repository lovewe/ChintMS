<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
  <meta charset="utf-8" />
  <title>菜单管理页</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
  <meta content="" name="description" />
  <meta content="" name="author" />
  <jsp:include page="../../jsp/inc/taglibs.jsp"></jsp:include>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
	<div id="main-content3">
         <!-- BEGIN PAGE CONTAINER-->
         <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->   
            <div class="row-fluid">
               <div class="span12">
					<ul class="breadcrumb">
						<li><a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span></li>
						<li><a href="#">安全管理</a> <span class="divider">&nbsp;</span>	</li>
						<li><a href="#">菜单管理</a><span class="divider-last">&nbsp;</span></li>
					</ul>
				</div>
			</div>
			<!-- END PAGE HEADER-->  
			<!-- BEGIN ADVANCED TABLE widget-->
			<div class="row-fluid">
			    <div class="span12">
			        <!-- BEGIN EXAMPLE TABLE widget-->
			       <div class="widget">
			           <div class="widget-title">
			               <h4><i class="icon-reorder"></i>菜单管理表</h4>
			               <span class="tools">
								<a href="#" class="btn btn-small element" onclick="showMenuPanle(1)" id="insertMenu"><font style="font-size:12px">添加</font></a>
								<a href="#" class="btn btn-small element" onclick="showMenuPanle(2)" id="updateMenu"><font style="font-size:12px">更新</font></a>
								<a href="#" class="btn btn-small element" onclick="delMenu()" id="deleteMenu"><font style="font-size:12px">删除</font></a>
<!--								btn mini black-->
			               </span>
			            
			           </div>
			           <div class="widget-body">
			               <table class="table table-striped table-bordered" id="menuTable" ></table>
			       		</div>
			      		<!-- END EXAMPLE TABLE widget-->
			    	</div>
				</div>
				<!-- END ADVANCED TABLE widget-->
			</div>
			<div class="modal2 hide" id="menuModal">
			    <div class="modal-header">
			        <button class="close" data-dismiss="modal">x</button>
			        <h3>新增菜单</h3>
			    </div>
			    <div class="modal-body"  style="height:180px;">
					<form class="bs-example bs-example-form" id="menuForm" name="menuForm" action="" enctype="multipart/form-data" method="post" onsubmit="return saveReport();">
						<div class="input-group">
							<span class="input-group-addon">菜&nbsp;&nbsp;单&nbsp;&nbsp;ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="text" class="form-control col-xs-3"  name="permId">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="input-group-addon">菜&nbsp;&nbsp;单&nbsp;&nbsp;名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			         		<input type="text" class="form-control"  name="permName">
			     		</div>
			      		<br>
						<div class="input-group">
						   <span class="input-group-addon">父&nbsp;&nbsp;菜&nbsp;&nbsp;单&nbsp;&nbsp;ID:&nbsp;&nbsp;</span>
						   <input type="text" class="form-control"  name="pPermId">
						   &nbsp;&nbsp;&nbsp;&nbsp;
						   <span class="input-group-addon">菜&nbsp;&nbsp;单&nbsp;&nbsp;URL:&nbsp;&nbsp;&nbsp;&nbsp;</span>
						   <input type="text" class="form-control" name="url">
						</div>
						<br>
						<div class="input-group">
						   <span class="input-group-addon">菜&nbsp;&nbsp;单&nbsp;&nbsp;Class:&nbsp;&nbsp;</span>
						   <input type="text" class="form-control" name="menuClass">
						   &nbsp;&nbsp;&nbsp;&nbsp;
						   <span class="input-group-addon">菜&nbsp;&nbsp;单&nbsp;&nbsp;序&nbsp;&nbsp;列:&nbsp;&nbsp;</span>
						   <input type="text" class="form-control" name="orderNum">
						</div>
		
						<div id="msgDiv" class="alert alert-error" style="display:none;top:350px; position: absolute;">
							<strong>提示:</strong><span id="msgSpan"></span>
						</div>
					</form>
			    </div>
			    <div class="modal-footer">
			        <a href="#" class="btn btn-primary" id="menu" onclick="optMenu();">确&nbsp;&nbsp;定</a>
			        <a href="#" class="btn" data-dismiss="modal">关 &nbsp;&nbsp;闭</a>
			    </div>  
			</div>
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	<!-- BEGIN JAVASCRIPTS -->
   <!-- Load javascripts at bottom, this will reduce page load time -->
   <!-- ie8 fixes -->
   <!--[if lt IE 9]>
   <![endif]-->   
   <script type="text/javascript" src="<%=contextPath %>/jsp/safeManage/js/menuManage.js" charset="utf-8"></script>  
</body>
<!-- END BODY -->
</html>