<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>固话宽带充值</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
  <meta content="" name="description" />
  <meta content="" name="author" />
  <jsp:include page="../../jsp/inc/taglibs.jsp"></jsp:include>
</head>
<body class="fixed-top">
	<div id="main-content3">
         <div class="container-fluid">
            <div class="row-fluid">
               <div class="span12">
					<ul class="breadcrumb">
						<li><a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span></li>
						<li><a href="#">充值管理</a> <span class="divider">&nbsp;</span>	</li>
						<li><a href="#">水煤电充值管理</a><span class="divider-last">&nbsp;</span></li>
					</ul>
				</div>
			</div>
			<div class="row-fluid">
			    <div class="span12">
			       <div class="widget">
			           <div class="widget-title">
			               <h4><i class="icon-reorder"></i>固话宽带充值管理表</h4>
			               <span class="tools">
								<a href="#" class="btn btn-small element" onclick="showWaterRechargePanle(1)"><font style="font-size:12px">新增</font></a>
								<a href="#" class="btn btn-small element" onclick="showWaterRechargePanle(2)"><font style="font-size:12px">更新</font></a>
								<a href="#" class="btn btn-small element" onclick="deleteWaterRecharge()"><font style="font-size:12px">删除</font></a>
			               </span>
			           </div>
			           <div class="widget-body">
			               <table class="table table-striped table-bordered" id="waterRechargeTable">
			           </table>
			           </div>
			       </div>
			       <!-- END EXAMPLE TABLE widget-->
			    </div>
			</div>
			<!-- END ADVANCED TABLE widget-->
			<div class="modal hide" id="waterRechargeModal">
			    <div class="modal-header">
			        <button class="close" data-dismiss="modal">x</button>
			        <h3>水煤电充值</h3>
			    </div>
			    <div class="modal-body">
					<form class="bs-example bs-example-form">
						<div  style="display:none">
							<input type="text"  name="rechargeId" >
						</div>
						  <div class="input-group">
			         		<span class="input-group-addon">水煤电缴费类型:</span>
			         		<select id="cmdWater"name="cmdWater"></select>  
			         	</div> 
						<div class="input-group">
			         		     省：<select id="cmdProvince" name="cmdProvince"> </select></br> 
							    市：<select id="cmdCity" name="cmdCity"></select> 
			         		</div>
			      		  <div class="input-group">
			         		<span class="input-group-addon">公共事业单位:</span>
			         		<select id="cmdPublicService"name="cmdPublicService"></select>  
			         	</div> 
			      		<div class="input-group">
			         		<span class="input-group-addon">户号:</span>
			         		<input type="text" class="form-control"  name="accountNo" >
			         		<span id="tI"></span>
			      		</div>
			      		<div class="input-group">
							<span class="input-group-addon">面值:</span>
							<select id="amount" name="amount"> 
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
						<div id="msgDiv" class="alert alert-error" style="display:none;">
							<strong>提示:</strong><span id="msgSpan"></span>
						</div>
					</form>
			    </div>
			    <div class="modal-footer">
			        <a href="#" class="btn btn-primary" id="waterRecharge" onclick="optWaterRecharge();">确&nbsp;&nbsp;定</a>
			        <a href="#" class="btn" data-dismiss="modal">关 &nbsp;&nbsp;闭</a>
			    </div>  
			</div>
		</div>
	</div>
   <script type="text/javascript" src="<%=contextPath %>/jsp/rechargeManage/js/waterRechargeManage.js"></script>
    <script type="text/javascript">  
     addressInit('cmdWater','cmdProvince', 'cmdCity', 'cmdPublicService', '水','陕西', '宝鸡市', '金台区');  
   </script> 
  
</body>
</html>