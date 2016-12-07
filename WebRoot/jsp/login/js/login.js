jQuery(document).ready(function() {
	App.initLogin();
});

/**
 * 提交检验
 * @return
 */
function checkInput() {
	var checkRst = 0;
	var account = $("#user_name");
	if(util.trim(account.val()) == "") {
		// 请输入账户名！
		checkRst = 1;
  	}
	var password = $("#password");
  	if(util.trim(password.val()) == "") {
		// 请输入密码！
		checkRst = checkRst + 2;
    }
  	if (checkRst == 1) {
		$("#msgSpan").html(" 请输入用户名。");
		$("#msgDiv").show();
		return false;
  	} else if (checkRst == 2) {
		$("#msgSpan").html(" 请输入密码。");
		$("#msgDiv").show();
		return false;
  	} else if (checkRst == 3) {
		$("#msgSpan").html(" 请输入用户名和密码。");
		$("#msgDiv").show();
		return false;
  	}
  	return true;
}
/**
 * 登录操作
 * @return
 */
function login() {
	if (checkInput() == false) {
		return false;
	}
	
	var url = mozi.contextPath +'/login/login.do';
	//提交登录，并返回处理
	$.post(url, mozi.serializeObject($('#loginform')), function(result) {
		//登录成功，则跳转至main.jsp
		if (result.success) {
			//location.replace(mozi.contextPath + '/login/main.do?userName='+result.msg);
			var tempForm = document.createElement("form");
		    tempForm.action = mozi.contextPath+"/login/main.do";
		    tempForm.method = "post";
		    tempForm.style.display = "none";
	        var opt = document.createElement("textarea");
	        opt.name = "userName";
	        opt.value = result.msg;
	        alert(result.msg);
	        tempForm.appendChild(opt);
		    document.body.appendChild(tempForm);
		    tempForm.submit();
		} else {
			//登录失败，则提示信息
    		$("#msgSpan").html(result.msg);
    		$("#msgDiv").show();
		}
	}, 'json');
}
