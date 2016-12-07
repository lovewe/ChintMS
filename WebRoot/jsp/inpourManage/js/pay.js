/**
 * 新增充值
 * @return
 */
function Sub(){
	var date=getDate();
	var url = "";
	if(!check()){
		return false;
	}
	else{
		url =  mozi.contextPath +"/wideInpour/insertWide.do";
	$.ajax({
		url : url,
		type : 'post',
		data : {
			user_id :  $("#user_id").text(),
			password : $("[name='password']").attr("value"),
		    wideNo : $("#wideNo").text(),
			amount :  $("#amount").text(),
			cmdProvince: $("#cmdProvince").text(),
			cmdCity : $("#cmdCity").text(),
			cmdPublicService :  $("#cmdPublicService").text(),
			creatTime: date,
			subAccountType: '01',
			rechargeType :'02',
			changeType: '01',
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
						 //fnDraw(); //重新加载数据
						}
			}
		}
			});
	
	}
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

function check(){
	if($("[name='password']").val() == null || $("[name='password']").val() === ""){
		$("#msgSpan").html(" 密码不能为空。");
		$("#msgDiv").show();
		return false;
	}
	return true;
}



