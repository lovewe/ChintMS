<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>

<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>手机充值</title>
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
						<li><a href="#">充值管理</a> <span class="divider">&nbsp;</span>	</li>
						<li><a href="#">手机充值管理</a><span class="divider-last">&nbsp;</span></li>
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
			               <h4><i class="icon-reorder"></i>手机充值管理表</h4>
			               <span class="tools">
								<a href="#" class="btn btn-small element" onclick="showPhoneRechargePanle(1)"><font style="font-size:12px">新增</font></a>
								<a href="#" class="btn btn-small element" onclick="showPhoneRechargePanle(2)"><font style="font-size:12px">修改</font></a>
								<a href="#" class="btn btn-small element" onclick="deletePhoneRecharge()"><font style="font-size:12px">删除</font></a>
			               </span>
			           </div>
			           <div class="widget-body">
			               <table class="table table-striped table-bordered" id="phoneRechargeTable">
			           </table>
			           </div>
			       </div>
			       <!-- END EXAMPLE TABLE widget-->
			    </div>
			</div>
			<!-- END ADVANCED TABLE widget-->
			<div class="modal hide" id="phoneRechargeModal">
			    <div class="modal-header">
			        <button class="close" data-dismiss="modal">x</button>
			        <h3>手机充值</h3>
			    </div>
			    <div class="modal-body">
					<form class="bs-example bs-example-form">
						<div  style="display:none">
							<input type="text"  name="rechargeId" >
						</div>
			      		<div class="input-group">
			         		<span class="input-group-addon">手机号码:</span>
			         		<input type="text" class="form-control" id="phoneNo" name="phoneNo"  onblur="isPhoneNo()">
			         		<span id="tI"></span>
			      		</div>
			      		<br>
			      		<div class="input-group">
							<span class="input-group-addon">面&nbsp;值&nbsp;:&nbsp;&nbsp;</span>
							<select id="amount" name="amount" onchange="aa()"> 
							<option value="0">请选择充值金额</option>
						   	<option value="10">10</option>
						   	<option value="20">20</option>
						   	<option value="30">30</option>
						   	<option value="50">50</option>
						   	<option value="100">100</option>
						   	<option value="200">200</option>
						   	<option value="300">300</option>
						   	<option value="500">500</option>
						   </select>
						</div>
						<div class="input-group">
			         		<span class="input-group-addon">售&nbsp;价&nbsp;:&nbsp;&nbsp;</span>
			         		<span id="price"></span>
			      		</div>
						<br>
						<div id="msgDiv" class="alert alert-error" style="display:none;">
							<strong>提示:</strong><span id="msgSpan"></span>
						</div>
					</form>
			    </div>
			    <div class="modal-footer">
			        <a href="#" class="btn btn-primary" id="PhoneRecharge" onclick="optPhoneRecharge();">确&nbsp;&nbsp;定</a>
			        <a href="#" class="btn" data-dismiss="modal">关 &nbsp;&nbsp;闭</a>
			    </div>  
			</div>
		</div>
	</div>
   <script type="text/javascript" src="<%=contextPath %>/jsp/rechargeManage/js/phoneRechargeManage.js"></script>
</body>

</html>