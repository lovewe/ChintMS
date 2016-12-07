<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>大屏方案管理页</title>
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
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<ul class="breadcrumb">
						<li><a href="#"><i class="icon-home"></i></a><span
							class="divider">&nbsp;</span></li>
						<li><a href="#">安全管理</a> <span class="divider">&nbsp;</span>
						</li>
						<li><a href="#">大屏方案管理</a><span class="divider-last">&nbsp;</span></li>
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
							<h4>
								<i class="icon-reorder"></i>大屏方案管理表
							</h4>
							<span class="tools"> <a href="#" class="btn btn-small element" onclick="showPlanPanle(1)"
								id="insertPlan"> <font style="font-size: 12px">添加</font>
							</a> <a href="#" class="btn btn-small element"
								onclick="showPlanPanle(2)" id="updatePlan()"><font
									style="font-size: 12px">更新</font></a>
									 <a href="#" class="btn btn-small element" onclick="delplan()" id="deletePlan">
									<font style="font-size: 12px">删除</font></a>
							</span>
						</div>
						<div class="widget-body">
							<table class="table table-striped table-bordered" id="planTable">
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE widget-->
				</div>
			</div>
			<!-- END ADVANCED TABLE widget-->
			<div class="modal hide" id="planModal">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">x</button>
					<h3>新增大屏方案</h3>
				</div>
				<div class="modal-body">
					<form class="bs-example bs-example-form">
					<div  style="display:none">
							<input type="text" class="form-control" name="id" >
						</div>
				
				
						<div class="input-group">
							<span class="input-group-addon">大&nbsp;&nbsp;屏&nbsp;&nbsp;方&nbsp;&nbsp;案&nbsp;&nbsp;全&nbsp;&nbsp;称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="text" class="form-control" name="name">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon">大&nbsp;&nbsp;&nbsp;屏&nbsp;&nbsp;&nbsp;方&nbsp;&nbsp;&nbsp;案&nbsp;&nbsp;英&nbsp;&nbsp;文&nbsp;&nbsp;简&nbsp;&nbsp;称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="text" class="form-control" name="shortName">
						</div>
						<div id="msgDiv" class="alert alert-error" style="display: none;">
				<strong>提示:</strong><span id="msgSpan"></span>
			</div>
					</form>

		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-primary" id="plan" onclick="optplan();">确&nbsp;&nbsp;定</a>
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
	<script type="text/javascript"
		src="<%=contextPath %>/jsp/screenManage/js/planManage.js"></script>
</body>
<!-- END BODY -->
</html>