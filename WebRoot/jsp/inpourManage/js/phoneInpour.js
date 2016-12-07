/**
 * 把值传到付款页面
 * 
 */
function tempSubmit(opt,val){
	
	if(!checkSumbit()){return;}
	
	var tempForm = document.createElement("form");
	tempForm.action =  mozi.contextPath +"/phoneInpour/phone.do";;
    tempForm.method = "post";
    tempForm.style.display = "none";
    
    var opt1 = document.createElement("textarea");
    opt1.name = "phoneNo";
    opt1.value = $("[name='phoneNo']").val();
     var opt2 = document.createElement("textarea");
    opt2.name = "amount";
    opt2.value = $("[name='amount']").val();
    tempForm.appendChild(opt1);
    tempForm.appendChild(opt2);
    var opt3 = document.createElement("textarea");
    opt3.name = "price";
    opt3.value = document.getElementById('amount').value*0.989;
    tempForm.appendChild(opt3);
    var opt = document.createElement("textarea");
    opt.name = "rechargeId";
    opt.value = $("[name='rechargeId']").val();
    tempForm.appendChild(opt);
    document.body.appendChild(tempForm);
    tempForm.submit();
   
}

/**
 * 充值金额打折
 * @return
 */
function aa(){
	var a =document.getElementById('amount').value*0.989;
	a = parseFloat(a.toFixed(2));
	document.getElementById("price").innerHTML=a;
}
/**
 * 调用接口查询归属地及运营商
 * 
 */
var tel;
var ajax = function () {
    $.ajax({
        type: "get",
        url: 'http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=' + tel,
        dataType: "jsonp",
        jsonp: "callback",
        success: function (data) {
            console.log(data);
            $('.error').css('display', 'none');
            var province = data.province,
                operators = data.catName,
                num = data.telString;
            $('.province span').html(province);
            str=operators;
            str = str.substring(2,4)
            $('.operators span').html(str);
        },
        error: function () {
            $('li span').html('');
            $('.error').css('display', 'block');
        }
    });
}
/**
 * 电话号码格式
 *
 */
var reg = /^(13|15|17|18)[0-9]{9}$/;//点击查询
$('.button').click(function () {
    tel = $('input[type=text]').val();
    if (tel) {
        if (reg.test(tel)) {
            ajax();
        } else {
            $('li span').html('');
            $('.error').css('display', 'block');
        }
    }
});
$('#phoneNo').blur(function (event) {
    tel = $('#phoneNo').val();

        if (tel) {
            if (reg.test(tel)) {
                ajax();
            } else {
                $('li span').html('');
                $('.error').css('display', 'block');
            }
        }
    
});



/**
 * 检验表单
 * @return
 */
function checkSumbit(){
	if($("[name='phoneNo']").val() == null || $("[name='phoneNo']").val() === ""){
		$("#msgSpan").html(" 手机号不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='amount']").val() == null || $("[name='amount']").val() === ""|| $("[name='amount']").val() == "0"){
		$("#msgSpan").html("充值金额不能为空。");
		$("#msgDiv").show();
		return false;
	}
	return true;
}


