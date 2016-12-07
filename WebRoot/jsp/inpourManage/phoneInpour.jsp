<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
  <meta charset="utf-8" />
  <title>充值 </title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta content="" name="description" />
  <meta content="" name="author" />
  <jsp:include page="../../jsp/inc/taglibs.jsp"></jsp:include>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body  style="font-size: 20px;">
	<div id="main-content3">
         <div class="container-fluid">
            <div class="row-fluid">
               <div class="span12">
					<ul class="breadcrumb">
						<li><a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span></li>
						<li><a href="#">充值</a> <span class="divider">&nbsp;</span>	</li>
						<li><a href="#">手机充值</a><span class="divider-last">&nbsp;</span></li>
					</ul>
				</div>
			</div>
			 
					
						<div  style="display:none">
							<input type="text"  name="phoneRechargeId" >
						</div>
			      		<div class="input-group">
			         		<span style="position: absolute;left:400px;top:180px">手机号码:</span>
			         		<input type="text" class="form-control" id="phoneNo" name="phoneNo" style="position: absolute;left:500px;top:180px">
			         		<span class="error"style="display:none;position: absolute;left:720px;top:180px" >请输入正确的手机号</span>
			         		 <ul>
                                 <li class="province"style="list-style-type:none;position: absolute;left:730px;top:180px"> <span></span></li>
                                <li class="operators"style="list-style-type:none;position: absolute;left:760px;top:180px"><span></span></li>
                              </ul>
			      		</div>
			      	<div class="input-group">
							 	<span style="position: absolute;left:400px;top:220px">面值:</span>
							<select id="amount" name="amount" onchange="aa()"style="position: absolute;left:500px;top:220px"> 
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
			         		<span style="position: absolute;left:400px;top:260px">售价:</span>
			         		<span id="price"style="position: absolute;left:500px;top:260px"></span>
			      		</div>
					
						<div id="msgDiv" class="alert alert-error" style="display:none;position: absolute;left:400px;top:300px">
							<strong>提示:</strong><span id="msgSpan"></span>
						</div>
				
			   
			    <div class="modal-footer">
			         <a href="#" class="btn btn-primary" style="position: absolute;left:600px;top:340px" onclick="tempSubmit();">立即充值</a>
			    </div>  
		</div>
	</div>
   <script type="text/javascript" src="<%=contextPath%>/jsp/inpourManage/js/phoneInpour.js" charset="utf-8"></script> 
 
   
</body>
</html>