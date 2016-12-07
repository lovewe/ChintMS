var optFlag = 1	//1表示新增，2表示修改
var urltable= null;

/**
 * 显示url面板
 * @return
 */
function showUrlPanle(flag){
	$("#msgSpan").html("");
	$("#msgDiv").hide();
	//加载方案
	getPlanList();
	if(flag === 1){
		$("h3").html("新增Url");
		optFlag = 1;
		//清空数据
		$("[name='id']").attr("value","");
		$("[name='planID']").attr("value","");
		$("#planList").get(0).selectedIndex=1;//下拉框显示列表中的第一条数据
		$("[name='url']").attr("value","");
		$("[name='fileName']").attr("value","");
		$("[name='imgFilePath']").attr("src","");
		$("#urlModal").modal("show");
	}else{
		//取得大屏方案表
		var table = $('#urlTable').DataTable();
		//取得选中列
		var datas = table.rows('.selected').data();
		
		if(datas.length < 1){
			alert("请选择要修改的url");
			return;
		}else if(datas.length > 1){
			alert("修改url时最多选择一列!");
			return;
		}
		//alert(111);
		$("#planList").attr("disabled",true);
		//alert(datas[0]['planID']);
		$("h3").html("更新url")
		optFlag = 2;
		//初始化数据
		$("[name='id']").attr("value",datas[0]['id']);
		$("[name='planID']").attr("value",datas[0]['planID']);
		$("#planList").val(datas[0]['planID']);
		$("[name='url']").attr("value",datas[0]['url']);
		$("[name='fileName']").attr("value","");
		$("#imgFile").attr('src',mozi.basePath+'/'+datas[0]['imgFilePath']);
		$("[name='imgFilePath']").attr("value",datas[0]['imgFilePath']);
		//显示新增面板
		$("#urlModal").modal("show");
	}
}
/**
 * 新增或修改URL
 * @return
 */
function optUrlSubmit(){
	if(!checkSumbit()){return;}
	var url = "";
	if(optFlag === 1){
		//新增
		url =  mozi.contextPath +"/screenManage/addURL.do";
	}else{
		//修改
		url = mozi.contextPath +"/screenManage/updateURL.do";
	}
	
	
	$("#urlForm").attr("action",url);
	$("#urlForm").submit();
	
//	$("#msgSpan").html(resp.msg);
//	$("#msgDiv").show();
//	urlTable.fnReloadAjax();
//	urlTable.fnDraw(); //重新加载数据
//	//页面刷新，重新加载
//	location.reload();
}

/**
 * 加载方案列表
 * @return
 */
function getPlanList(){
	$("#planList").empty(); 
	$.ajax({
		url : mozi.contextPath +"/screenManage/plan.do",
		type : 'post',
		async : false,
		data : {
		}, 
		dataType : 'json',
		success : function(resp) {
			var planList = resp.data;
			if(planList){
				for(var i =0;i<planList.length;i++){
					$("#planList").append("<option value='"+planList[i].id+"'>"+planList[i].name+"</option>");
				}
			}else{
				$("#PlanList").append("<option value='0'>大屏方案加载失败</option>");
			}
		}
	});
}

/**
 * 表单提交时
 * @return
 */
function saveReport() { 
	$("#urlForm").ajaxSubmit(function(resp) { 
	// 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容 
	if(resp.success){
		$('#urlModal').modal('hide');
		urlTable.fnReloadAjax();
		urlTable.fnDraw(); //重新加载数据
	}else{
		$("#msgSpan").html(resp.msg);
		$("#msgDiv").show();
	}
}); 
} 


/**
 * 新增或修改url
 * @return
 */
function opturl(){
	var url = "";
	if(optFlag === 1){
		//新增
		if(!checkSumbit()){return;}
		url =  mozi.contextPath +"/screenManage/addURL.do";;
	}else{
		//修改
		if(!checkSumbit()){return;}
		url = mozi.contextPath +"/screenManage/updateURL.do";;;
	}
	
	//取得大屏方案表
	var table = $('#urlTable').DataTable();
	//取得选中行
	var datas = table.rows('.selected').data();
	$.ajax({
		url : url,
		type : 'post',
		data : {
			id : $("[name='id']").attr("value"),
			//planID : $("[name='planID']").attr("value"),
			name : $("#planList").val(),
			planID : $("#planList").val(),
			//name : datas[0]['name'],
			url : $("[name='url']").attr("value"),
			fileName : $("[name='fileName']").attr("value"),
			imgFilePath : $("[name='imgFilePath']").attr("src"),
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$('#urlModal').modal('hide');
				urlTable.fnReloadAjax();
				urlTable.fnDraw(); //重新加载数据
				//页面刷新，重新加载
				location.reload();
			}else{
				$("#msgSpan").html(resp.msg);
				$("#msgDiv").show();
			}
		}
	});
}

/**
 * 检验表单
 * @return
 */
function checkSumbit(){
	if($("[name='url']").val() == null || $("[name='url']").val() === ""){
		$("#msgSpan").html(" url不能为空。");
		$("#msgDiv").show();
		return false;
	}
	
if(($("[name='fileName']").val() == null || $("[name='fileName']").val() == "") && 
		($("[name='imgFilePath']").val() == null || $("[name='imgFilePath']").val() === "")){
		$("#msgSpan").html("请上传图片文件。");
		$("#msgDiv").show();
		return false;
	 }
	return true;
}
/**
 * 设置文件
 * @return
 */
function setFile(){
//	var img = document.getElementById('imgFile');
//    var path = img.value;
	$("#imgFile").attr('src',$("[name='fileName']").val());
}

/**
 * 删除选中在大屏方案行
 * @return
 */
function delURL(){
	//取得大屏方案表
	var table = $('#urlTable').DataTable();
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
						var Ids = ""
							table.rows('.selected').indexes().each( function (i) {
							var data = table.row(i).data();
							if(Ids === ""){
								Ids = data['id'];
							}else{
								Ids = Ids+","+data['id'];
							}
						});
						//确认删除
						$.ajax( {   
				            "type": "POST",    
				           // "contentType": "application/json",   
				            "url": mozi.contextPath+"/screenManage/deleteURL.do",    
				            "dataType": "json",   
				            "data": {'id':Ids}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		urlTable.fnReloadAjax();
				            		urlTable.fnDraw(); //重新加载数据
				            		//页面刷新，重新加载
				            		location.reload();
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


$(document).ready(function() {
	//大屏方案表数据加载
	urlTable = $('#urlTable').dataTable( {
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
		"sAjaxSource": mozi.contextPath +"/screenManage/url.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "id" ,"sDefaultContent" : "","sTitle" : "urlID"},
//			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "planID" ,"sDefaultContent" : "","sTitle" : "planID"},
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "name" ,"sDefaultContent" : "","sTitle" : "大屏方案全称"},
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "url" ,"sDefaultContent" : "","sTitle" : "url"},
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "fileName" ,"sDefaultContent" : "","bVisible": false,"sTitle" : "上传图片"},//"bVisible": false,
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "imgFilePath" ,"sDefaultContent" : "","sTitle" : "上传图片"},
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#urlTable tbody ').on('click', 'tr', function () {
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