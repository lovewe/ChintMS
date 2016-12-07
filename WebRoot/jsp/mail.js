jQuery(document).ready(function() {
	// initiate layout and plugins
	App.init();
	var widthFunction = function(){
		if($(window).width()>1000)
		{
			$("#top_menu").css("margin-left",($("#header").width()-$("#headlogo").outerWidth())/2 - $("#top_menu").outerWidth()/2);
		}else{
			$("#top_menu").css("margin-left",0);
		}
	}
	$(window).resize(function() {
		widthFunction();
	});
	widthFunction();
});
function showModel(){
	$("#passModal").modal("show");
}

/**
 * 修改密码
 * @return
 */
function updatePwd(){
	var pwd = $("[name='password']").attr("value");
	var pwd2 = $("[name='password2']").attr("value");
	var oldPwd = $("[name='oldpassword']").attr("value");
	if(oldPwd == ''){
		$("#msgSpan").html("旧密码不能为空!");
		$("#msgDiv").show();
		return ;
	}else if(pwd == ''){
		$("#msgSpan").html("新密码不能为空!");
		$("#msgDiv").show();
		return ;
	}else if(pwd2 == ''){
		$("#msgSpan").html("确认密码不能为空!");
		$("#msgDiv").show();
		return ;
	}else if(pwd != pwd2){
		$("#msgSpan").html("新密码与确认密码不一致,请重新输入!");
		$("#msgDiv").show();
		return ;
	}
	
	$.ajax({
		url : mozi.contextPath +"/userManage/updatePwd.do",
		type : 'post',
		data : {
			userName : name,
			password : pwd,
			oldPwd : $("[name='oldpassword']").attr("value")
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$("#msgSpan").html(resp.msg);
				$("#msgDiv").show();
			}else{
				$("#msgSpan").html(resp.msg);
				$("#msgDiv").show();
			}
		}
	});
}