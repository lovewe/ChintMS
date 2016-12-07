var optFlag = 1	//1表示新增，2表示修改
var phoneRechargeTable = null;

/**
 * 显示手机充值面板
 * @return
 */
function showPhoneRechargePanle(flag){
	$("#msgSpan").html("");
	$("#msgDiv").hide();
	if(flag === 1){
		$("h3").html("新增充值");
		optFlag = 1;
		//清空数据
		$("[name='rechargeId']").attr("value","");
		$("[name='phoneNo']").attr("value","");
		$("#amount").get(0).selectedIndex=0;
		$("[name='price']").attr("text","");
		$("#phoneRechargeModal").modal("show");
	}else{
		//取得充值表
		var table = $('#phoneRechargeTable').DataTable();
		//取得选中列
		var datas = table.rows('.selected').data();
		
		if(datas.length < 1){
			alert("请选择要修改的充值记录");
			return;
		}else if(datas.length > 1){
			alert("修改充值记录时最多选择一列!");
			return;
		}
		
		$("h3").html("更新充值记录")
		optFlag = 2;
		//初始化数据
		$("[name='rechargeId']").attr("value",datas[0]['rechargeId']);
		$("[name='phoneNo']").attr("value",datas[0]['phoneNo']);
		$("#amount").val(datas[0]['amount']);
		$("[name='price']").attr("text",datas[0]['price']);
		$("#phoneRechargeModal").modal("show");
	}
}
/**
 * 新增或修改充值记录
 * @return
 */
function optPhoneRecharge(){
	var url = "";
	if(optFlag === 1){
		//新增
		if(!checkSumbit()||!isPhoneNo()){return;}
		url =  mozi.contextPath +"/phoneRechargeManage/insertPhoneRecharge.do";
	}else{
		//修改
		if(!checkSumbit()){return;}
		url = mozi.contextPath +"/phoneRechargeManage/updatePhoneRecharge.do";
	}
	
	//取得充值表
	var table = $('#phoneRechargeTable').DataTable();
	//取得选中列
	var datas = table.rows('.selected').data();
	$.ajax({
		url : url,
		type : 'post',
		data : {
			rechargeId : $("[name='rechargeId']").attr("value"),
			phoneNo : $("[name='phoneNo']").attr("value"),
			amount : $("#amount").val(),
			price : $("#price").text()
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$('#phoneRechargeModal').modal('hide');
				phoneRechargeTable.fnReloadAjax();
				phoneRechargeTable.fnDraw(); //重新加载数据
				//页面刷新，重新加载
//        		location.reload();
			}else{
				$("#msgSpan").html(resp.msg);
				$("#msgDiv").show();
			}
		}
	});
}
/**
 * 删除选中在充值列
 * @return
 */
function deletePhoneRecharge(){
	//取得充值表
	var table = $('#phoneRechargeTable').DataTable();
	//取得选中列
	var datas = table.rows('.selected').data();
	//判断如果有选中
	if(datas.length > 0 ){
		$.confirm({
			'title' : '删除确认',
			'message' : '您将进行删除选中项，确认删除?',
			'buttons'   : {
	            'Yes'   : {
	                'class' : 'blue',
	                'action': function(){
						var phoneRechargeIds = ""
							table.rows('.selected').indexes().each( function (i) {
							var data = table.row(i).data();
							if(phoneRechargeIds === ""){
								phoneRechargeIds = data['rechargeId'];
							}else{
								phoneRechargeIds = phoneRechargeIds+","+data['rechargeId'];
							}
						});
						//确认删除
						$.ajax( {   
				            "type": "POST",    
				           // "contentType": "application/json",   
				            "url": mozi.contextPath+"/phoneRechargeManage/deletePhoneRecharge.do",    
				            "dataType": "json",   
				            "data": {'phoneRechargeIds':phoneRechargeIds}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		phoneRechargeTable.fnReloadAjax();
				            		phoneRechargeTable.fnDraw(); //重新加载数据
				            		location.reload();//页面刷新，重新加载
				            	}else{
				            		alert(resp.msg);
				            	}
				            }   
				        });
	                }
	            },
	            'No'    : {
	                'class' : 'gray',
	                'action': function(){
	                	return;
	            	}  // Nothing to do in this case. You can as well omit the action property.
	            }
	        }
		})
	}else{
		alert("请选择要删除的项！");
	}
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
 * 验证输入电话号码
 */
function isPhoneNo(){
	var str_phoneNo = $("[name='phoneNo']").val();
	var spanNode = document.getElementById("tI");
	var strRegex =  /^[1][3458][0-9]{9}$/;
	var re=new RegExp(strRegex);
	if (re.test(str_phoneNo)){
		spanNode.innerHTML = "";
	return true;
	}else{
		spanNode.innerHTML = "格式错误,请输入正确的11位手机号".fontcolor("red");
	return false;
	}
	
}

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


$(document).ready(function() {
	//用户表数据加载
	phoneRechargeTable = $('#phoneRechargeTable').dataTable( {
//		"bProcessing": true,
//		"bServerSide": true,
//		"sPaginationType": "bootstrap",
		'bLengthChange': true, //是否允许自定义每页显示条数.
		"oLanguage": { //国际化配置  
            "sProcessing" : "正在获取数据，请稍后...",    
            "sLengthMenu" : "显示 _MENU_ 条",    
            "sZeroRecords" : "没有您要搜索的内容",    
            "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",    
            "sInfoEmpty" : "记录数为0",    
            "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
            "sInfoPostFix" : "",    
            "sSearch" : "搜索",    
            "sUrl" : "",    
            "oPaginate": {    
                "sFirst" : "第一页",    
                "sPrevious" : "上一页",    
                "sNext" : "下一页",    
                "sLast" : "最后一页"    
            }  
        }, 
		"sAjaxSource": mozi.contextPath +"/phoneRechargeManage/selectPhoneRechargeList.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "rechargeId" ,"sDefaultContent" : "","sTitle" : "充值ID"},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "phoneNo" ,"sDefaultContent" : "","sTitle" : "手机号"},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "amount" ,"sDefaultContent" : "","sTitle" : "充值金额"},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "price" ,"sDefaultContent" : "","sTitle" : "售价"},
			
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#phoneRechargeTable tbody ').on('click', 'tr', function () {
		//对表格增加单击监听
        var  chk =$(this).find("#uniform-span");   
		
		if ( chk.hasClass('checked') ) {
			chk.removeClass('checked');
			 $(this).removeClass('selected');
        }
        else {
        	chk.removeClass('checked');
        	chk.addClass('checked');
        	$(this).addClass('selected');
        }
	});
	
	App.init();
});