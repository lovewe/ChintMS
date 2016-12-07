var table;
var roleStatus=0;//修改
var ztree;
var oldMenu="";
var setting = {
		check: {
			enable: true,
			chkboxType: { "Y" : "ps", "N" : "ps"}
		},
		view: {
			dblClickExpand: true
		},
		data: {  
            simpleData: {  
                /**  
                如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId  
                并且让数据满足父子关系。*/                    
                enable: true,//true / false 分别表示 使用 / 不使用 简单数据模式  
                idKey: "id",  
                pIdKey: "pid",  
                rootPId: 0                            
            }  
        },
		async: {
			enable: true, //启用异步加载
			dataType: "json", 
			url:mozi.contextPath+"/menuManage/getMenuTree.do", //调用的后台的方法
			contentType: "application/json",
			autoParam:["id"],  //向后台传递的参数
//			otherParam:{}, //额外的参数
//			dataFilter: filter
		},
		callback: {
			beforeClick: beforeClick,
			onAsyncSuccess : onAsyncSuc,
			onCheck: onCheck
		}
}; 
	function onAsyncSuc(event, treeId, treeNode, msg){
	}
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("roleMenuTree");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
//		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("roleMenuTree"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		for (var i=0, l=nodes.length; i<l; i++) {
		          v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var roleObj = $("#userRole");
		roleObj.attr("value", v);
	}
	
	function showMenu() {
//		var roleObj = $("#userRole");
//		var roleOffset = $("#userRole").offset();
//		$("#menuContent").css({left:"65px", top:"250px"}).slideDown("fast");
//		$("body").bind("mousedown", onBodyDown);
	}
	
		
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
		
	}
		
//	function onBodyDown(event) {
//		if (!(event.target.id == "menuBtn" || event.target.id == "userRole" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
//		hideMenu();
//	  }
//	} 
	
$(document).ready(function() {
	
	 ztree=$.fn.zTree.init($("#roleMenuTree"), setting);
	 table = $('#roleTable').dataTable( {
//		"bProcessing": true,
//		"bServerSide": true,
//		"sPaginationType": "bootstrap",
		'bLengthChange': true, //是否允许自定义每页显示条数.
		"oLanguage": { //国际化配置  
//            "sProcessing" : "正在获取数据，请稍后...",    
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
		"sAjaxSource": mozi.contextPath +"/roleManage/getRoles.do",
		"sAjaxDataProp":"data",
//		"fnServerData": function ( sSource, aoData, fnCallback ) {
//            $.ajax( {
//                "dataType": 'json',
//                "type": "POST",
//                "url": sSource,
//                "data": aoData,
//                "success": fnCallback
//            } );
//        },
		"aoColumns": [
//{ "sWidth":"8px","mDataProp": null ,"sTitle" : '<input type="checkbox" class="group-checkable" data-set="#roleTable.checkboxes" />'
//	,"sDefaultContent" : '<span class="checker"><input type="checkbox" class="checkboxes" /></span>'
//   },

{ "sWidth":"8px","sClass":"","mDataProp": null ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span class="" id="uniform-span"><input class="no-margin" type="checkbox" style="opacity: 0;"></span></div>',"sTitle" : ''},
{ "sClass": "hidden-phone", "mDataProp": "roleId" ,"sDefaultContent" : "","sTitle" : "角色ID"},
{ "sClass": "hidden-phone", "mDataProp": "roleName" ,"sDefaultContent" : "","sTitle" : "角色名"},
{ "sClass": "hidden-phone", "mDataProp": "description" ,"sDefaultContent" : "","sTitle" : "描述"},
{ "sClass": "hidden-phone", "mDataProp": "status" ,"sDefaultContent" : "","sTitle" : "状态"},{
    "sClass": "hidden-phone",
    "mDataProp": "sn",
    "bSearchable": false,
    "bStorable": false,
    "fnRender": function (obj) {
    return '<span class="label label-success">Approved</span>';
    }
    }
        ]
	} );
	 
	$('#roleTable tbody ').on('click', 'tr', function () {
		
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
	} );
	
	
	$('#deleteButton').click(function(){
		var roleIds="";
		var tables = $('#roleTable').DataTable();
		var datas = tables.rows('.selected').data();
		
		
		if(datas.length>0){
			
			$.confirm({
				'title' : '删除确认',
				'message' : '您将进行删除选中项，确认删除?',
				'buttons'   : {
		            'Yes'   : {
		                'class' : 'blue',
		                'action': function(){
		                	tables.rows('.selected').indexes().each( function (i) {
		         			   var data = tables.row(i).data();
		         			   roleIds +=data['roleId']+",";
		         			});
		         		roleIds = roleIds.substring(0,roleIds.length - 1);
		         		 $.ajax({
		         				url : mozi.contextPath +"/roleManage/deleteRole.do",
		         				type : 'post',
		         				data : {
		         					roleIds : roleIds
		         				},
		         				dataType : 'json',
		         				success : function(result) {
		         					tables.rows('.selected').remove().draw( false );
		         				}
		         			});
		                }
		            },
		            'No'    : {
		                'class' : 'gray',
		                'action': function(){
		                	return;
		            	}  
		            }
		        }
			})
		}
		else{
			alert("请先选择一个");
			return false;
		}
    });
	
	$('#updateRole').click(function(){
		var tables = $('#roleTable').DataTable();
		var datas = tables.rows('.selected').data();
		if(datas.length != 1){
			alert("请选择一个");
			return false;
		}
		else{
			var data = datas[0];
			$.ajax({
				url : mozi.contextPath +"/menuManage/getMenuByroleId.do",
				type : 'post',
				data : {
					roleId : data['roleId']
				},
				dataType : 'json',
				success : function(result) {
					var node ,v="";
					ztree.checkAllNodes(false);
					$(result).each(function(i,val) {
						//node = ztree.getNodeByParam("title",val.permId,null);
						node = ztree.getNodeByParam("id",val.permId,null);
						v += val.permName + ",";
						node.checked=true;
						ztree.updateNode(node,true);
					});
					//重新刷新页面节点效果
//					ztree.refresh();
					var nodes = ztree.getCheckedNodes(); 
					for (var i = 0, l = nodes.length; i < l; i++) {
						//如果是叶子节点，则重新设置
						if(nodes[i].isParent){
							
						}else{
							ztree.checkNode(nodes[i], true, true);
						}
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);
					var roleObj = $("#userRole");
					roleObj.attr("value", v);
					oldMenu = v;
					$("[name='roleId']").attr("value",data['roleId']);
					$("[name='roleName']").attr("value",data['roleName']);
					$("[name='description']").attr("value",data['description']);
					$("[name='status']").attr("value",data['status']);
					$("#roleTitle").html("修改角色");
					document.getElementById("msgDiv").style.display="none";
					$("#roleModal").modal("show");
				    
				},
				failure:function(result) {
					console.log("修改失败!");
				}
			});
			roleStatus = 0;
			return false; 
		}
    });
	
	$('#roleUpdate').click(function(){
		if(!checkSumbit()){
			return false;
		}
		else{
		var table = $('#roleTable').dataTable();
		
		
		var url,info;
		if(roleStatus ==0){
			url = mozi.contextPath +"/roleManage/updateRole.do";
			info="修改成功!";
		}
		else{
			url = mozi.contextPath +"/roleManage/insertRole.do";
			info="增加成功!";
		}
		
		var nodes = ztree.getCheckedNodes(true);
		var v="";
		if(oldMenu != $("#userRole").attr("value")){ //权限被修改
			$(nodes).each(function(i,val) {
//				   v += val.title + ",";
				   v += val.id + ",";
			});
			if (v.length > 0 ) v = v.substring(0, v.length-1);
		}
		
		$.ajax({
			url : url,
			type : 'post',
			data : {
				roleId : $("[name='roleId']").attr("value"),
				roleName : $("[name='roleName']").attr("value"),
				description : $("[name='description']").attr("value"),
				tableauIp : $("[name='tableauIp']").attr("value"),
				tableauPort : $("[name='tableauPort']").attr("value"),
				tableauUser : $("[name='tableauUser']").attr("value"),
				status : $("[name='status']").attr("value"),
				menuIds : v
			}, 
			dataType : 'json',
			success : function(result) {
				$('#roleModal').modal('hide');
				table.fnReloadAjax();
				table.fnDraw(); //重新加载数据
			},
			failure:function(result) {
				info = ((roleStatus == 0)?'修改失败!':'增加失败!');
				console.log(info);
			}
		});
		}
    });
	
   $('#insertRole').click(function(){
	   ztree.checkAllNodes(false);
	   $("[name='roleId']").attr("value","");
	   $("[name='roleName']").attr("value","");
	   $("[name='description']").attr("value","");
	   $("[name='tableauIp']").attr("value","");
	   $("[name='tableauPort']").attr("value","");
	   $("[name='tableauUser']").attr("value","");
	   $("[name='status']").attr("value","");
	   $("#userRole").attr("value","");
	   roleStatus = 1;
	   $("#roleTitle").html("新增角色");
	   document.getElementById("msgDiv").style.display="none";
	   $("#roleModal").modal("show");
    });
   
   /**
    * 检验表单
    * @return
    */
  function checkSumbit(){
	var name = $("[name='roleName']").attr("value");
    var description = $("[name='description']").attr("value");
	var userRole = $("#userRole").attr("value");
	var tableauIp = $("[name='tableauIp']").attr("value");
	var tableauPort = $("[name='tableauPort']").attr("value");
	var tableauUser = $("[name='tableauUser']").attr("value");
  	if(name == ""){
  		$("#msgSpan").html(" 角色名不能为空。");
  		$("#msgDiv").show();
  		return false;
  	}
  	else if(description == ""){
  		$("#msgSpan").html(" 描述不能为空。");
  		$("#msgDiv").show();
  		return false;
  	}
  	else if(tableauIp == ""){
  		$("#msgSpan").html(" tableauIp不能为空。");
  		$("#msgDiv").show();
  		return false;
  	}
	else if(tableauPort == ""){
		$("#msgSpan").html(" tableauPort描述不能为空。");
		$("#msgDiv").show();
		return false;
	}
  	else if(tableauUser == ""){
  		$("#msgSpan").html(" tableauUser描述不能为空。");
  		$("#msgDiv").show();
  		return false;
  	}
  	else if(userRole==""){
  		$("#msgSpan").html(" 权限不能为空。");
  		$("#msgDiv").show();
  		return false;
  	}
  	return true;
  }
   
    function retrieveData( sSource, aoData, fnCallback ) {   
        //将客户名称加入参数数组   
//        aoData.push( { "name": "customerName", "value": $("#customerName").val() } );   
      
        $.ajax( {   
            "type": "POST",    
            "contentType": "application/json",   
            "url": sSource,    
            "dataType": "json",   
            "data": JSON.stringify(aoData), //以json格式传递   
            "success": function(resp) {   
                fnCallback(resp.returnObject); //服务器端返回的对象的returnObject部分是要求的格式  
            }   
        });   
    }  

    
	
	
	App.init();
	
	
	
} );
