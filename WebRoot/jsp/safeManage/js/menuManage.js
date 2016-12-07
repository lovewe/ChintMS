var optFlag = 1	//1表示新增，2表示修改
var menuTable = null;
var ztree;

/**
 * 显示菜单面板
 * @return
 */
function showMenuPanle(flag){
	$("#msgSpan").html("");
	$("#msgDiv").hide();
	
	if(flag === 1){
		$("h3").html("新增菜单");
		optFlag = 1;
		//清空数据
		$("[name='permId']").attr("value","");
		$("[name='permId']").removeAttr("readonly");
		$("[name='permName']").attr("value","");
		$("[name='pPermId']").attr("value","");
		$("[name='url']").attr("value","");
		$("[name='menuClass']").attr("value","");
		$("[name='orderNum']").attr("value","");

	}else{
		//取得菜单表
		var table = $('#menuTable').DataTable();
		//取得选中列
		var datas = table.rows('.selected').data();
		
		if(datas.length < 1){
			alert("请选择要修改的菜单");
			return;
		}else if(datas.length > 1){
			alert("修改菜单时最多选择一列!");
			return;
		}
		
		$("h3").html("更新菜单")
		optFlag = 2;
		//初始化数据
		$("[name='permId']").attr("value",datas[0]['permId']);
		$("[name='permId']").attr("readonly","true");
		$("[name='permName']").attr("value",datas[0]['permName']);
		$("[name='pPermId']").attr("value",datas[0]['pPermId']);
		$("[name='url']").attr("value",datas[0]['url']);
		$("[name='menuClass']").attr("value",datas[0]['menuClass']);
		$("[name='orderNum']").attr("value",datas[0]['orderNum']);
	}
	
	//显示新增面板
	$("#menuModal").modal("show");
}

/**
 * 新增或修改菜单
 * @return
 */
function optMenu(){
	var url = "";
	if(optFlag === 1){
		//新增
		if(!checkSumbit()){return;}
		url =  mozi.contextPath +"/menuManage/addMenu.do";
	}else{
		//修改
		if(!checkSumbit()){return;}
		url = mozi.contextPath +"/menuManage/updateMenu.do";
	}
	//取得菜单表
	var table = $('#menuTable').DataTable();
	//取得选中列
	var datas = table.rows('.selected').data();
	$.ajax({
		url : url,
		type : 'post',
		data : {
			permId : $("[name='permId']").attr("value"),
			permName : $("[name='permName']").attr("value"),
			pPermId : $("[name='pPermId']").attr("value"),
			url : $("[name='url']").attr("value"),
			menuClass : $("[name='menuClass']").attr("value"),
			orderNum : $("[name='orderNum']").attr("value"),
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$('#menuModal').modal('hide');
				menuTable.fnReloadAjax();
				menuTable.fnDraw(); //重新加载数据
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
 * 新增或修改菜单
 * @return
 */
function optMenuSubmit(){
	if(!checkSumbit()){return;}
	var url = "";
	if(optFlag === 1){
		//新增
		url =  mozi.contextPath +"/menuManage/addMenu.do";
	}else{
		//修改
		url = mozi.contextPath +"/menuManage/updateMenu.do";
	}
	$("#menuForm").attr("action",url);
	$("#menuForm").submit();
}

/**
 * 表单提交时
 * @return
 */
function saveReport() { 
	// jquery 表单提交 
	return true; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
} 


/**
 * 检验表单
 * @return
 */
function checkSumbit(){
	if($("[name='permId']").val() == null || $("[name='permId']").val() === ""){
		$("#msgSpan").html(" 菜单ID不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='permName']").val() == null || $("[name='permName']").val() === ""){
		$("#msgSpan").html(" 菜单名不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='pPermId']").val() == null || $("[name='pPermId']").val() === ""){
		$("#msgSpan").html(" 菜单父ID不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='orderNum']").val() == null || $("[name='orderNum']").val() === ""){
		$("#msgSpan").html(" 菜单序列不能为空。");
		$("#msgDiv").show();
		return false;
	}
	
	return true;
}

/**
 * 删除选中在菜单列
 * @return
 */
function delMenu(){
	//取得菜单表
	var table = $('#menuTable').DataTable();
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
						var permIds = ""
							table.rows('.selected').indexes().each( function (i) {
							var data = table.row(i).data();
							if(permIds === ""){
								permIds = data['permId'];
							}else{
								permIds = permIds+","+data['permId'];
							}
						});
						//确认删除
						$.ajax( {   
				            "type": "POST",    
				           // "contentType": "application/json",   
				            "url": mozi.contextPath+"/menuManage/deleteMenu.do",    
				            "dataType": "json",   
				            "data": {'permId':permIds}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		menuTable.fnReloadAjax();
				            		menuTable.fnDraw(); //重新加载数据
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
	//菜单表数据加载
	menuTable = $('#menuTable').dataTable( {
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
		"sAjaxSource": mozi.contextPath +"/menuManage/queryMenuList.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", "mDataProp": "permId" ,"sDefaultContent" : "","sTitle" : "菜单ID"},
			{ "sClass": "hidden-phone", "mDataProp": "permName" ,"sDefaultContent" : "","sTitle" : "菜单名"},
			{ "sClass": "hidden-phone", "mDataProp": "pPermId" ,"sDefaultContent" : "","sTitle" : "父菜单ID"},
			{ "sClass": "hidden-phone", "mDataProp": "url" ,"sDefaultContent" : "","sTitle" : "菜单URL"},
			{ "sClass": "hidden-phone", "mDataProp": "menuClass" ,"sDefaultContent" : "","sTitle" : "菜单class"},
			{ "sClass": "hidden-phone", "mDataProp": "orderNum" ,"sDefaultContent" : "","sTitle" : "菜单顺序"},
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#menuTable tbody ').on('click', 'tr', function () {
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
	
	
	//新增
	$('#insertMenu').on('click',function(){
	});
	//更新
	$('#updateMenu').on('click',function(){
		//初始化公司树
	});
	
	App.init();
});