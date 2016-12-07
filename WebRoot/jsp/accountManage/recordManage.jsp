<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
  <meta charset="utf-8" />
  <title>用户管理页</title>
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
						<li><a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span></li>
						<li><a href="#">交易管理</a> <span class="divider">&nbsp;</span>	</li>
						<li><a href="#">交易记录</a><span class="divider-last">&nbsp;</span></li>
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
			               <h4><i class="icon-reorder"></i>交易记录</h4>
			               <span class="tools">
								<a href="#" class="btn btn-small element" onclick="showUserPanle(1)"><font style="font-size:12px">交易记录</font></a>
								<a href="#" class="btn btn-small element" onclick="showUserPanle(2)"><font style="font-size:12px">转账记录</font></a>
								<a href="#" class="btn btn-small element" onclick="delUser()"><font style="font-size:12px">充值记录</font></a>
			               </span>
			           </div>
			           <div class="widget-body">
			               <table class="table table-striped table-bordered" id="recordTable">
			           </table>
			           </div>
			       </div>
			       <!-- END EXAMPLE TABLE widget-->
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
   <script type="text/javascript" src="<%=contextPath %>/jsp/accountManage/js/recordManage.js"></script>
</body>
<!-- END BODY -->
</html>