function runBoxToShow(){
	var el = $('.box-login');
	if (getParameterByName('box').length) {
		switch(getParameterByName('box')) {
			case "register" :
				el = $('.box-register');
				break;
			case "forgot" :
				el = $('.box-forgot');
				break;
			default :
				el = $('.box-login');
				break;
		}
	}
	el.show().addClass("animated flipInX").on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
		$(this).removeClass("animated flipInX");
	});
};

//function to return the querystring parameter with a given name.
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex.exec(location.search);
	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
};

function runLoginValidator(){
	var form = $('.form-login');
	var errorHandler = $('.errorHandler', form);
	form.validate({
		rules : {
			username : {
				minlength : 2,
				required : true
			},
			password : {
				minlength : 6,
				required : true
			}
		},
		submitHandler : function(form) {
			errorHandler.hide;
			return false;
		},
		invalidHandler : function(event, validator) {//display error alert on form submit
			errorHandler.show();
			return true;
		}
	});
}

 function runSetDefaultValidation() {
	$.validator.setDefaults({
		errorElement : "span", // contain the error msg in a small tag
		errorClass : 'help-block',
		errorPlacement : function(error, element) {// render error placement for each input type
			if (element.attr("type") == "radio" || element.attr("type") == "checkbox") {// for chosen elements, need to insert the error after the chosen container
				error.insertAfter($(element).closest('.form-group').children('div').children().last());
			} else if (element.attr("name") == "card_expiry_mm" || element.attr("name") == "card_expiry_yyyy") {
				error.appendTo($(element).closest('.form-group').children('div'));
			} else {
				error.insertAfter(element);
				// for other inputs, just perform default behavior
			}
		},
		ignore : ':hidden',
		success : function(label, element) {
			label.addClass('help-block valid');
			// mark the current input as valid and display OK icon
			$(element).closest('.form-group').removeClass('has-error');
		},
		highlight : function(element) {
			$(element).closest('.help-block').removeClass('valid');
			// display OK icon
			$(element).closest('.form-group').addClass('has-error');
			// add the Bootstrap error class to the control group
		},
		unhighlight : function(element) {// revert the change done by hightlight
			$(element).closest('.form-group').removeClass('has-error');
			// set error class to the control group
		}
	});
};

/**
 * 提交检验
 * @return
 */
function checkInput() {
	var checkRst = 0;
	var account = $("#username");
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
 * 登录提交
 * @return
 */
function loginSumbit(){
	if (checkInput() == false) {
		return false;
	}
	var url = mozi.contextPath +'/login/login.do';
	console.log("showTye="+$('input:radio[name=showType]:checked').val());
	//提交登录，并返回处理
	$.ajax({
		url: url,
		data: {user_name: $("#username").val(),password:$("#password").val(),showType:$('input:radio[name=showType]:checked').val()},
		dataType: 'json',
		method: "POST",
		async: false,
		success: function(result) {
			if (result.success) {
				//location.replace(mozi.contextPath + '/login/main.do?userName='+result.msg);
				var tempForm = document.createElement("form");
			    tempForm.action = mozi.contextPath+"/login/main.do";
			    tempForm.method = "post";
			    tempForm.style.display = "none";
			    var msgs = result.msg.split(":");
		        var opt = document.createElement("textarea");
		        opt.name = "userName";
		        //opt.value = result.msg;
		        opt.value = msgs[0];
		        var showType = document.createElement("textarea");
		        showType.name = "showType";
		        //opt.value = result.msg;
		        showType.value = msgs[2];
		        tempForm.appendChild(opt);
		        tempForm.appendChild(showType);
			    document.body.appendChild(tempForm);
	  		    
			    //删除cookie
			    deleteCookie("CHINTBI_USER");
			    deleteCookie("CHINTBI_KEY");
		    	//表单提交前，先保存免登录cookie
			    addCookie("CHINTBI_USER", msgs[0], '/', 1);
			    addCookie("CHINTBI_KEY", msgs[1], '/', 1);
			    
			    tempForm.submit();
			} else {
				//登录失败，则提示信息
	    		$("#msgSpan").html(result.msg);
	    		$("#msgDiv").show();
			}
		},
		complete: function(data) {
			//alert("complete:"+data);
		}
	});
}

/**
*免登录
*/
function checkLogin() {
	//如果cookie存在,则自动提交
	var cookieValue = getCookie("CHINTBI_USER");
	var cookiekey = getCookie("CHINTBI_KEY");
	var isNeedLogin = $("#isNeedLogin").val();
	if(isNeedLogin == "false" || !cookieValue || cookieValue == "" || !cookiekey || cookiekey == "") {
		 //alert(cookieValue);
	} else {
	  	//cookie 的内容
		var loginUserName = getCookie("CHINTBI_USER");
		var dataKey = getCookie("CHINTBI_KEY");
		
		var url = mozi.basePath +'/login/login.do';
	
		//提交登录，并返回处理
		$.post(url, {user_name:loginUserName,isCookieLogin:"true",cookieKey:dataKey}, function(result) {
			//登录成功，则跳转至main.jsp
			if (result.success) {
				var tempForm = document.createElement("form");
			    tempForm.action = mozi.contextPath+"/login/main.do";
			    tempForm.method = "post";
			    tempForm.style.display = "none";
			    var msgs = result.msg.split(":");
		        var opt = document.createElement("textarea");
		        opt.name = "userName";
		        opt.value = msgs[0];
		        tempForm.appendChild(opt);
			    document.body.appendChild(tempForm);
			    tempForm.submit();
			} else {
				//登录失败，则提示信息。但是Cookie登录失败的情况不报错。
				if (result.msg != "cookieloginFail") {
		    		$("#msgSpan").html(result.msg);
		    		$("#msgDiv").show();
				}
			}
		}, 'json');
	}
}
