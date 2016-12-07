var optFlag = 1	//1表示新增，2表示修改
var userTable = null;

/**
 * 显示用户面板
 * @return
 */
function showUserPanle(flag){
	$("#msgSpan").html("");
	$("#msgDiv").hide();
	//加载角色 
	getRoleList();
	if(flag === 1){
		$("h3").html("新增用户");
		optFlag = 1;
		//清空数据
		$("[name='personID']").attr("value","");
		$("[name='nickname']").attr("value","");
		$("#roleList").get(0).selectedIndex=1;
		$("[name='phoneNum']").attr("value","");
		$("#userModal").modal("show");
	}else{
		//取得菜单表
		var table = $('#userTable').DataTable();
		//取得选中列
		var datas = table.rows('.selected').data();
		
		if(datas.length < 1){
			alert("请选择要修改的用户");
			return;
		}else if(datas.length > 1){
			alert("修改用户时最多选择一列!");
			return;
		}
		
		$("h3").html("更新用户")
		optFlag = 2;
		//初始化数据
		$("[name='personID']").attr("value",datas[0]['personID']);
		$("[name='nickname']").attr("value",datas[0]['nickname']);
		$("#roleList").val(datas[0]['roleId']);
		$("[name='phoneNum']").attr("value",datas[0]['phoneNum']);
		$("#userModal").modal("show");
	}
}

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
	var table = $('#userTable').DataTable();
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
				userTable.fnReloadAjax();
				userTable.fnDraw(); //重新加载数据
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
 * 检验表单
 * @return
 */
function checkSumbit(){
	if($("[name='nickname']").val() == null || $("[name='nickname']").val() === ""){
		$("#msgSpan").html(" 用户名不能为空。");
		$("#msgDiv").show();
		return false;
	}
	return true;
}

/**
 * 删除选中在菜单列
 * @return
 */
function delUser(){
	//取得菜单表
	var table = $('#userTable').DataTable();
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
						var userIds = ""
							table.rows('.selected').indexes().each( function (i) {
							var data = table.row(i).data();
							if(userIds === ""){
								userIds = data['personID'];
							}else{
								userIds = userIds+","+data['personID'];
							}
						});
						//确认删除
						$.ajax( {   
				            "type": "POST",    
				           // "contentType": "application/json",   
				            "url": mozi.contextPath+"/userManage/deleteUser.do",    
				            "dataType": "json",   
				            "data": {'userIds':userIds}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		userTable.fnReloadAjax();
				            		userTable.fnDraw(); //重新加载数据
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

/**
 * 重置选中的用户密码
 * @return
 */
function resetPwd(){
	//取得菜单表
	var table = $('#userTable').DataTable();
	//取得选中列
	var datas = table.rows('.selected').data();
	//判断如果有选中
	if(datas.length > 0 ){
		$.confirm({
			'title' : '重置密码确认',
			'message' : '您将对选中的用户进行密码重置，确认继续?',
			'buttons'   : {
	            'Yes'   : {
	                'class' : 'blue',
	                'action': function(){
						var userIds = ""
							table.rows('.selected').indexes().each( function (i) {
							var data = table.row(i).data();
							if(userIds === ""){
								userIds = data['userId'];
							}else{
								userIds = userIds+","+data['userId'];
							}
						});
						//确认删除
						$.ajax( {   
				            "type": "POST",    
				           // "contentType": "application/json",   
				            "url": mozi.contextPath+"/userManage/resetPwd.do",    
				            "dataType": "json",   
				            "data": {'userIds':userIds}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		alert(resp.msg);
//				            		userTable.fnReloadAjax();
//				            		userTable.fnDraw(); //重新加载数据
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
		alert("请选择要重置密码的用户！");
	}
}

$(document).ready(function() {
	//用户表数据加载
	userTable = $('#userTable').dataTable( {
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
		"sAjaxSource": mozi.contextPath +"/userManage/queryUserList.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "personID" ,"sDefaultContent" : "","sTitle" : "用户ID"},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "nickname" ,"sDefaultContent" : "","sTitle" : "用户名"},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "roleName" ,"sDefaultContent" : "","sTitle" : "角色"},
			{ "sClass": "hidden-phone", "mDataProp": "phoneNum" ,"sDefaultContent" : "","sTitle" : "手机"}
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#userTable tbody ').on('click', 'tr', function () {
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