var optFlag = 1	//1表示新增，2表示修改
var plantable= null;

/**
 * 显示大屏面板
 * @return
 */
function showPlanPanle(flag){
	$("#msgSpan").html("");
	$("#msgDiv").hide();
	//加载大屏方案
	//getPlanList();
	if(flag === 1){
		$("h3").html("新增大屏方案");
		optFlag = 1;
		//清空数据
		$("[name='id']").attr("value","");
		$("[name='name']").attr("value","");
		$("[name='shortName']").attr("value","");
		$("#planModal").modal("show");
	}else{
		//取得大屏方案表
		var table = $('#planTable').DataTable();
		//取得选中列
		var datas = table.rows('.selected').data();
		
		if(datas.length < 1){
			alert("请选择要修改的大屏方案");
			return;
		}else if(datas.length > 1){
			alert("修改大屏方案时最多选择一列!");
			return;
		}
		
		$("h3").html("更新大屏方案")
		optFlag = 2;
		//初始化数据
		$("[name='id']").attr("value",datas[0]['id']);
		$("[name='name']").attr("value",datas[0]['name']);
		$("[name='shortName']").attr("value",datas[0]['shortName']);
		$("#planModal").modal("show");
	}
}

/**
 * 新增或修改大屏方案
 * @return
 */
function optplan(){
	var url = "";
	if(optFlag === 1){
		//新增
		if(!checkSumbit()){return;}
		url =  mozi.contextPath +"/screenManage/addPlan.do";;
	}else{
		//修改
		if(!checkSumbit()){return;}
		url = mozi.contextPath +"/screenManage/updatePlan.do";;;
	}
	
	//取得大屏方案表
	var table = $('#planTable').DataTable();
	//取得选中行
	var datas = table.rows('.selected').data();
	$.ajax({
		url : url,
		type : 'post',
		data : {
//			id:datas[0]['id'],
			id:$("[name='id']").attr("value"),
			name : $("[name='name']").attr("value"),
			shortName : $("[name='shortName']").attr("value")
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$('#planModal').modal('hide');
				planTable.fnReloadAjax();
				planTable.fnDraw(); //重新加载数据
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
	if($("[name='name']").val() == null || $("[name='name']").val() === ""){
		$("#msgSpan").html(" 大屏方案全称不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='shortName']").val() == null || $("[name='shortName']").val() === ""){
		$("#msgSpan").html(" 大屏方案英文简称不能为空。");
		$("#msgDiv").show();
		return false;
	}
//	if($("[name='planName']").val() == null || $("[name='planmName']").val() === ""){
//		$("#msgSpan").html(" 屏幕选择类型不能为空。");
//		$("#msgDiv").show();
//		return false;
//	}
//	if($("[name='url']").val() == null || $("[name='url']").val() === ""){
//		$("#msgSpan").html(" url不能为空。");
//		$("#msgDiv").show();
//		return false;
//	}
	return true;
}

/**
 * 删除选中在大屏方案列
 * @return
 */
function delplan(){
	//取得大屏方案表
	var table = $('#planTable').DataTable();
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
				            "url": mozi.contextPath+"/screenManage/deletePlan.do",    
				            "dataType": "json",   
				            "data": {'id':Ids}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		planTable.fnReloadAjax();
				            		planTable.fnDraw(); //重新加载数据
				            		//页面刷新，重新加载
//				            		location.reload();
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
	planTable = $('#planTable').dataTable( {
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
		"sAjaxSource": mozi.contextPath +"/screenManage/plan.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", 'sWidth':'30%', "mDataProp": "id" ,"sDefaultContent" : "","sTitle" : "大屏方案ID"},
			{ "sClass": "hidden-phone", 'sWidth':'35%', "mDataProp": "name" ,"sDefaultContent" : "","sTitle" : "大屏方案全称"},
			{ "sClass": "hidden-phone", 'sWidth':'35%', "mDataProp": "shortName" ,"sDefaultContent" : "","sTitle" : "大屏方案英文简称"}
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#planTable tbody ').on('click', 'tr', function () {
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