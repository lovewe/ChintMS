<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
    <%String contextPath = request.getContextPath();%>
      <%String wideNo = request.getParameter("wideNo");%>
     <%String amount = request.getParameter("amount");%>
     <%String cmdProvince = request.getParameter("cmdProvince");%>
      <%String cmdCity = request.getParameter("cmdCity");%>
      <%String cmdPublicService = request.getParameter("cmdPublicService");%>
      <%String user_id=request.getSession().getAttribute("user_id").toString();%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宽带充值付款</title>
<meta charset="utf-8" />
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
  <meta content="" name="description" />
  <meta content="" name="author" />
  <jsp:include page="../../jsp/inc/taglibs.jsp"></jsp:include>
</head>
<body class="fixed-top"  style="font-size: 20px;">
         <div id="main-content3"> 
               <div >
                    <span style="position: absolute;left:400px;top:180px">用户ID：</span>
                    <span id="user_id"name="user_id"style="position: absolute;left:500px;top:180px"><%=user_id %></span> </br> 
                    
                    <span style="position: absolute;left:400px;top:220px">宽带账号：</span>
                    <span id="wideNo" name="wideNo"style="position: absolute;left:500px;top:220px"><%=wideNo %></span></br>
                   
                   <span style="position: absolute;left:400px;top:260px">金额:</span> 
                    <span id="amount" name="amount"style="position: absolute;left:500px;top:260px"><%=amount %></span> </br>
                
                   <span style="position: absolute;left:400px;top:300px">省:</span> 
                   <span id="cmdProvince" name="cmdProvince"style="position: absolute;left:430px;top:300px"> <%=cmdProvince %></span>     </br>
                
                	<span style="position: absolute;left:600px;top:300px">市:</span> 
                     <span id="cmdCity" name="cmdCity"style="position: absolute;left:630px;top:300px"><%=cmdCity %></span>  </br>
               
                   <span style="position: absolute;left:400px;top:340px">公共服务单位:</span>
                  <span id="cmdPublicService" name="cmdPublicService"style="position: absolute;left:530px;top:340px"><%=cmdPublicService %> </span>
                 
                 <span style="position: absolute;left:400px;top:380px">订单信息:</span>
                 <span id="remark" name ="remark"style="position: absolute;left:500px;top:380px"><%=wideNo %>自动充值 即时到帐<%= amount %>元话费  </span>
         </div>
            <div class="input-group">
					<span style="position: absolute;left:400px;top:420px">请输入支付密码:</span>
					<input type="password" class="form-control col-xs-3"  id="password" name="password" style="position: absolute;left:550px;top:420px"onblur="check()">
			     		</div>
			     		<div id="msgDiv" class="alert alert-error" style="display:none;position: absolute;left:400px;top:460px">
							<strong>提示:</strong><span id="msgSpan"></span>
						</div>
	 	<div class="modal-footer">
			    <a href="#" class="btn btn-primary" style="position: absolute;left:700px;top:500px" onclick="Sub();">确认支付</a>
		</div>
		</div>>	      
 <script type="text/javascript" src="<%=contextPath%>/jsp/inpourManage/js/pay.js" charset="utf-8"></script>
</body>
</html>