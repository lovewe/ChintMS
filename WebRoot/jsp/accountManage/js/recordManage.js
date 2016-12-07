var optFlag = 1	//1表示新增，2表示修改
var recordTable = null;


/**
 * 加载角色列表
 * @return
 */
function getRoleList(){
	$("#roleList").empty(); 
	$.ajax({
		url : mozi.contextPath +"/roleManage/getRoles.do",
		type : 'post',
		async : false,
		data : {
		}, 
		dataType : 'json',
		success : function(resp) {
			var roleList = resp.data;
			if(roleList){
				for(var i =0;i<roleList.length;i++){
					$("#roleList").append("<option value='"+roleList[i].roleId+"'>"+roleList[i].roleName+"</option>");
				}
			}else{
				$("#roleList").append("<option value='0'>角色列表加载失败</option>");
			}
		}
	});
}

/**
 * 新增或修改用户
 * @return
 */
function optUser(){
	var url = "";
	if(optFlag === 1){
		//新增
		if(!checkSumbit()){return;}
		url =  mozi.contextPath +"/userManage/addUser.do";;
	}else{
		//修改
		if(!checkSumbit()){return;}
		url = mozi.contextPath +"/userManage/updateUser.do";;;
	}
	
	//取得菜单表
	var table = $('#recordTable').DataTable();
	//取得选中列
	var datas = table.rows('.selected').data();
	$.ajax({
		url : url,
		type : 'post',
		data : {
			personID : $("[name='personID']").attr("value"),
			nickname : $("[name='nickname']").attr("value"),
			roleId : $("#roleList").val(),
			phoneNum : $("[name='phoneNum']").attr("value")
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$('#userModal').modal('hide');
				recordTable.fnReloadAjax();
				recordTable.fnDraw(); //重新加载数据
				//页面刷新，重新加载
//        		location.reload();
			}else{
				$("#msgSpan").html(resp.msg);
				$("#msgDiv").show();
			}
		}
	});
}


$(document).ready(function() {
	//用户表数据加载
	recordTable = $('#recordTable').dataTable( {
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
		"sAjaxSource": mozi.contextPath +"/accountManage/selectTransferBILL.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "createTime" ,"sDefaultContent" : "","sTitle" : "创建时间"},
			{ "sClass": "hidden-phone", 'sWidth':'15%', "mDataProp": "orderNote" ,"sDefaultContent" : "","sTitle" : "名称"},
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "orderID" ,"sDefaultContent" : "","sTitle" : "交易号"},
			{ "sClass": "hidden-phone", 'sWidth':'10%', "mDataProp": "tranAmount" ,"sDefaultContent" : "","sTitle" : "金额"},
			{ "sClass": "hidden-phone", 'sWidth':'10%',"mDataProp": "state" ,"sDefaultContent" : "","sTitle" : "状态"}
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#recordTable tbody ').on('click', 'tr', function () {
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