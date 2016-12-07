<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
  <meta charset="utf-8" />
  <title>角色管理页</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
  <meta content="" name="author" />
  <jsp:include page="../../jsp/inc/taglibs.jsp"></jsp:include>
  <link rel="stylesheet" href="../static/css/zTree.css" type="text/css">
  <style type="text/css">
  #roleModal .modal-body {
   height: 650px;
   }
  </style>
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
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->     
					<ul class="breadcrumb">
						<li><a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span></li>
						<li><a href="#">安全管理</a> <span class="divider">&nbsp;</span>	</li>
						<li><a href="#">角色管理</a><span class="divider-last">&nbsp;</span></li>
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
			               <h4><i class="icon-reorder"></i>管理表</h4>
			               <span class="tools">
                               <a href="#" class="btn btn-small element" id="insertRole"><font style="font-size:12px">添加</font></a>
								<a href="#" class="btn btn-small element" id="updateRole"><font style="font-size:12px">更新</font></a>
								<a href="#" class="btn btn-small element" id="deleteButton"><font style="font-size:12px">删除</font></a>
			                   <!--  a href="javascript:;" class="icon-remove"></a>-->
			                   
			               </span>
			           </div>
			           <div class="widget-body">
			               <table class="table table-striped table-bordered" id="roleTable" >
			           </table>
			           </div>
			       </div>
			       <!-- END EXAMPLE TABLE widget-->
			    </div>
			</div>
			
			<div class="modal hide" id="roleModal">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">x</button>
        <h3 id="roleTitle">新增角色</h3>
    </div>
    <div class="modal-body" style="height:340px;">
	    <form class="bs-example bs-example-form" role="form">
			<div  style="display:none">
				<input type="text"  name="roleId" >
			</div>
			<div class="input-group">
				<span class="input-group-addon">角&nbsp;&nbsp;&nbsp;&nbsp;色&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;:</span>
				<input type="text" class="form-control"  name="roleName">
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</span>
				<input type="text" class="form-control"  name="description">
			</div>
			<div class="input-group">
				<span class="input-group-addon">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</span>
				<input type="text" class="form-control"  id="userRole" name="roles" readonly value=""  onclick="showMenu();" >
			</div>
			<div id="menuContent" class="menuContent" style=" left:100px;top:144px; position: absolute;">
				<ul id="roleMenuTree" class="ztree" style="margin-top:0;width:260px;height:170px"></ul>
			</div>
			<div id="msgDiv" class="alert alert-error" style="display:none; top:330px; position: absolute;">
				<strong>提示:</strong><span id="msgSpan"></span>
			</div>
	   </form>
    </div>
    <div class="modal-footer">
		<a href="#" class="btn btn-primary" id="roleUpdate">确 定</a>
		<a href="#" class="btn" data-dismiss="modal">关 闭</a>
	</div> 
</div>  
			<!-- END ADVANCED TABLE widget-->
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	<!-- BEGIN JAVASCRIPTS -->
   <!-- Load javascripts at bottom, this will reduce page load time -->
   <!-- ie8 fixes -->
   <!--[if lt IE 9]>
   <![endif]-->   
   <script type="text/javascript" src="../static/assets/data-tables/jquery.dataTables.js"></script>
   <script type="text/javascript" src="../static/assets/data-tables/jquery.dataTables.fnReloadAjax.js"></script>
   <script type="text/javascript" src="../static/assets/data-tables/DT_bootstrap.js"></script>
   <script type="text/javascript" src="../jsp/safeManage/js/roleManage.js"></script>
</body>
<!-- END BODY -->
</html>