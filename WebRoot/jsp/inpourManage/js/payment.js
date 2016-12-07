/**
 * 新增充值
 * @return
 */
function pay(){
	var date=getDate();
	var url = "";
	if(!checkSumbit()){
		return false;
	}
	else{
		url =  mozi.contextPath +"/phoneInpour/insertPhone.do";
	$.ajax({
		url : url,
		type : 'post',
		data : {
			user_id :  $("#user_id").text(),
			password : $("[name='password']").attr("value"),
			phoneNo : $("#phoneNo").text(),
			amount : $("#amount").text(),
			price : $("#price").text(),
			creatTime: date,
			subAccountType:'01',
			changeType: '01',
			rechargeType :'01',
			state :'00',
			remark : $("#remark").text(),
			
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				if(resp.msg!="null")
					{
					 $("#msgSpan").html(resp.msg);
					 $("#msgDiv").show();
					}else
						{
						$("#msgSpan").html("支付成功");
						 $("#msgDiv").show();
						}
			}
		}
			});
	
	}
}
/*
 * 判断是否支付成功
 */
function check(){
    var flag=true;
    var msg =content.msg.value;
    if(msg!=null){
     alert(msg);
     flag=false;
    }else
    alert('支付成功')
   }

Date.prototype.Format = function(format){ 

	var o = { 
	"M+" : this.getMonth()+1, //month 
	"d+" : this.getDate(), //day 
	"h+" : this.getHours(), //hour 
	"m+" : this.getMinutes(), //minute 
	"s+" : this.getSeconds(), //second 
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
	"S" : this.getMilliseconds() //millisecond 
	}

	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	}
	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	 } 
	} 
	return format; 
	}


function getDate(){

	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd hh:mm:ss");
	return currentTime;
	}




function checkSumbit(){
	if($("[name='password']").val() == null || $("[name='password']").val() === ""){
		$("#msgSpan").html(" 密码不能为空。");
		$("#msgDiv").show();
		return false;
	}
	return true;
}

