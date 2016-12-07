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
<body>
	<div id="main-content3">
         <div class="container-fluid">
            <div class="row-fluid">
               <div class="span12">
					<ul class="breadcrumb">
						<li><a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span></li>
						<li><a href="#">充值</a> <span class="divider">&nbsp;</span>	</li>
						<li><a href="#">水煤电充值</a><span class="divider-last">&nbsp;</span></li>
					</ul>
				</div>
			</div>
			   
						<div  style="display:none">
							<input type="text"  name="rechargeId" >
						</div>
						<div class="input-group">
			         		<span style="position: absolute;left:400px;top:180px">水煤电缴费类型：</span> 
			         		<select id="cmdWater"name="cmdWater"style="position: absolute;left:500px;top:180px"></select>  
			         	</div> 
						<div class="input-group">
			         		   <span style="position: absolute;left:400px;top:220px"> 省:</span> 
			         		   <select id="cmdProvince" name="cmdProvince"style="position: absolute;left:420px;top:220px"> </select>  
							 <span style="position: absolute;left:650px;top:220px"> 市:</span> 
							 <select id="cmdCity" name="cmdCity"style="position: absolute;left:670px;top:220px"></select>
							    </div>
			      		  <div class="input-group">
			         		<span class="input-group-addon"style="position: absolute;left:400px;top:260px">公共事业单位:&nbsp;&nbsp;</span>
			         		<select id="cmdPublicService"name="cmdPublicService"style="position: absolute;left:500px;top:260px"></select>  
			         	</div> 
			      		<div class="input-group">
			         		<span class="input-group-addon"style="position: absolute;left:400px;top:300px">户号:&nbsp;&nbsp;</span>
			         		<input type="text" class="form-control" id="accountNo" name="accountNo" style="position: absolute;left:500px;top:300px">
			         		<span id="tI"style="position: absolute;left:720px;top:340px"></span>
			      		</div>
			      		<br>
			      		<div class="input-group">
							<span class="input-group-addon"style="position: absolute;left:400px;top:340px">面值:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<select id="amount" name="amount"style="position: absolute;left:500px;top:340px"> 
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
			      		<br>
						<div id="msgDiv" class="alert alert-error" style="display:none;position: absolute;left:400px;top:380px"">
							<strong>提示:</strong><span id="msgSpan"></span>
						</div>
			    </div>
			    <div class="modal-footer">
			        <a href="#" class="btn btn-primary" id="WaterInpour" style="position: absolute;left:700px;top:420px" onclick="tempSubmit();">立即充值</a>
			    </div>  
	</div>
   <script type="text/javascript" src="<%=contextPath %>/jsp/inpourManage/js/waterInpour.js"></script>
    <script type="text/javascript">  
     addressInit('cmdWater','cmdProvince', 'cmdCity', 'cmdPublicService');  
   </script> 
</body>
</html>