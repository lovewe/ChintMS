<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <%String contextPath = request.getContextPath();%>
     <%String phoneNo = request.getParameter("phoneNo");%>
     <%String amount = request.getParameter("amount");%>
     <%String price = request.getParameter("price");%>
     <%String user_id=request.getSession().getAttribute("user_id").toString();%> 
<!-- request.getAttribute("user_id").toString() --> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手机充值付款</title>
<meta charset="utf-8" />
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
  <meta content="" name="description" />
  <meta content="" name="author" />
  <jsp:include page="../../jsp/inc/taglibs.jsp"></jsp:include>
</head>
<body class="fixed-top"  style="font-size: 20px;">
 <div id="main-content3"> 
    			<div>
    				<span style="position: absolute;left:400px;top:180px">手机号：</span>
    				<span id="phoneNo"name="phoneNo"style="position: absolute;left:500px;top:180px"><%=phoneNo %></span> </br>
    
   					 <span style="position: absolute;left:400px;top:220px">用户ID：</span>
    				<span id="user_id"name="user_id"style="position: absolute;left:500px;top:220px"><%=user_id %></span> </br> 
    
    				<span style="position: absolute;left:400px;top:260px">金额：</span>
   					<span id="amount"name="amount"style="position: absolute;left:500px;top:260px"><%= amount %></span></br>
    
    				<span style="position: absolute;left:400px;top:300px">付款金额：</span>
  					<span id="price" name ="price"style="position: absolute;left:500px;top:300px"><fmt:formatNumber value="<%=price %>" pattern="0.00"/></span> 
    
    				<span style="position: absolute;left:400px;top:340px">订单信息</span>
   				    <span id="remark" name ="remark"style="position: absolute;left:500px;top:340px"><%=phoneNo %>自动充值 即时到帐<%= amount %>元话费  </span>
    			
		     
				  <span style="position: absolute;left:400px;top:380px">请输入支付密码:</span>
				 <input type="password" class="form-control" id="password" name="password" style="position: absolute;left:550px;top:380px" >
				 </div>
				  </div>
	 	<div class="modal-footer">
	 	      <div id="msgDiv" class="alert alert-error" style="display:none;position: absolute;left:400px;top:420px">
							<strong>提示:</strong><span id="msgSpan"></span>
						</div>
			    <a href="#" class="btn btn-primary" style="position: absolute;left:700px;top:460px" onclick="pay();">确认支付</a>
		</div>
	
	</div>     
 <script type="text/javascript" src="<%=contextPath%>/jsp/inpourManage/js/payment.js" charset="utf-8"></script>
</body>

</html>