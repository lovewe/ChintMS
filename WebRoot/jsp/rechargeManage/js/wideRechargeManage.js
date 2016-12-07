var optFlag = 1	//1表示新增，2表示修改
var wideRechargeTable = null;

/**
 * 显示固话宽带充值面板
 * @return
 */
function showWideRechargePanle(flag){
	$("#msgSpan").html("");
	$("#msgDiv").hide();
	if(flag === 1){
		$("h3").html("新增固话宽带充值");
		optFlag = 1;
		//清空数据
		$("[name='rechargeId']").attr("value","");
		$("[name='wideNo']").attr("value","");
		$("#amount").get(0).selectedIndex=0;
		//$("#cmdProvince").get(0).selectedIndex=0;
		//$("#cmdCity").get(0).selectedIndex=0;
		//$("#cmdPublicService").get(0).selectedIndex=0;
		//$("[name='price']").attr("text","");
		$("#wideRechargeModal").modal("show");
	}else{
		//取得充值表
		var table = $('#wideRechargeTable').DataTable();
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
		$("[name='wideNo']").attr("value",datas[0]['wideNo']);
		$("#amount").val(datas[0]['amount']);
		$("#cmdProvince").val(datas[0]['cmdProvince']);
		$("#cmdCity").val(datas[0]['cmdCity']);
		$("#cmdPublicService").val(datas[0]['cmdPublicService']);
		//$("[name='price']").attr("text",datas[0]['price']);
		$("#wideRechargeModal").modal("show");
	}
}
/**
 * 新增或修改充值记录
 * @return
 */
function optWideRecharge(){
	var url = "";
	if(optFlag === 1){
		//新增
		
		if(!checkSumbit()||!isWideNo()){return;}
		url =  mozi.contextPath +"/wideRechargeManage/insertWideRecharge.do";
	}else{
		//修改
		if(!checkSumbit()){return;}
		url = mozi.contextPath +"/wideRechargeManage/updateWideRecharge.do";
	}
	
	//取得充值表
	var table = $('#wideRechargeTable').DataTable();
	//取得选中列
	var datas = table.rows('.selected').data();
	$.ajax({
		url : url,
		type : 'post',
		data : {
			rechargeId : $("[name='rechargeId']").attr("value"),
			wideNo : $("[name='wideNo']").attr("value"),
			amount : $("#amount").val(),
			cmdProvince:$("#cmdProvince").val(),
			cmdCity : $("#cmdCity").val(),
			cmdPublicService : $("#cmdPublicService").val(),
			//price : $("#price").text()
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$('#wideRechargeModal').modal('hide');
				wideRechargeTable.fnReloadAjax();
				wideRechargeTable.fnDraw(); //重新加载数据
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
function deleteWideRecharge(){
	//取得菜单表
	var table = $('#wideRechargeTable').DataTable();
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
						var wideRechargeIds = ""
							table.rows('.selected').indexes().each( function (i) {
							var data = table.row(i).data();
							if(wideRechargeIds === ""){
								wideRechargeIds = data['rechargeId'];
							}else{
								wideRechargeIds = wideRechargeIds+","+data['rechargeId'];
							}
						});
						//确认删除
						$.ajax( {   
				            "type": "POST",    
				           // "contentType": "application/json",   
				            "url": mozi.contextPath+"/wideRechargeManage/deleteWideRecharge.do",    
				            "dataType": "json",   
				            "data": {'wideRechargeIds':wideRechargeIds}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		wideRechargeTable.fnReloadAjax();
				            		wideRechargeTable.fnDraw(); //重新加载数据
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
/**
 * 三级联动省、市、公共服务单位
 * @return
 */

var addressInit = function(_cmdProvince, _cmdCity, _cmdPublicService, defaultProvince, defaultCity, defaultPublicService)  
{  
    var cmdProvince = document.getElementById(_cmdProvince);  
    var cmdCity = document.getElementById(_cmdCity);  
    var cmdPublicService = document.getElementById(_cmdPublicService);  
       
    function cmdSelect(cmd, str)  
    {  
        for(var i=0; i<cmd.options.length; i++)  
        {  
            if(cmd.options[i].value == str)  
            {  
                cmd.selectedIndex = i;  
                return;  
            }  
        }  
    }  
    function cmdAddOption(cmd, str, obj)  
    {  
        var option = document.createElement("OPTION");  
        cmd.options.add(option);  
        option.innerHTML = str;  
        option.value = str;  
        option.obj = obj;  
    }  
       
    function changeCity()  
    {  
//      alert(cmdPublicService);
       cmdPublicService.options.length = 0;  
        if(cmdCity.selectedIndex == -1)return;  
        var item = cmdCity.options[cmdCity.selectedIndex].obj;  
        for(var i=0; i<item.publicServiceList.length; i++)  
        {  
            cmdAddOption(cmdPublicService, item.publicServiceList[i], null);  
        }  
        cmdSelect(cmdPublicService, defaultPublicService);  
    }  
    function changeProvince()  
    {  
        cmdCity.options.length = 0;  
        cmdCity.onchange = null;  
        if(cmdProvince.selectedIndex == -1)return;  
        var item = cmdProvince.options[cmdProvince.selectedIndex].obj;  
        for(var i=0; i<item.cityList.length; i++)  
        {  
            cmdAddOption(cmdCity, item.cityList[i].name, item.cityList[i]);  
        }  
        cmdSelect(cmdCity, defaultCity);  
        changeCity();  
        cmdCity.onchange = changeCity;  
    }  
       
    for(var i=0; i<provinceList.length; i++)  
    {  
        cmdAddOption(cmdProvince, provinceList[i].name, provinceList[i]);  
    }  
    cmdSelect(cmdProvince, defaultProvince);  
    changeProvince();  
    cmdProvince.onchange = changeProvince;  
}  
   
var provinceList = [  
{name:'安徽', cityList:[           
{name:'安庆', publicServiceList:['中国电信安徽分公司（安庆固话）','中国电信安徽分公司（安庆宽带）',]}, 
{name:'蚌埠', publicServiceList:['中国电信安徽分公司（蚌埠固话）','中国电信安徽分公司（蚌埠宽带）',]}, 
{name:'亳州', publicServiceList:['中国电信安徽分公司（亳州固话）','中国电信安徽分公司（亳州宽带）',]}, 
{name:'池州', publicServiceList:['中国电信安徽分公司（池州固话）','中国电信安徽分公司（池州宽带）',]},
{name:'滁州', publicServiceList:['中国电信安徽分公司（滁州固话）','中国电信安徽分公司（滁州宽带）',]}, 
{name:'阜阳', publicServiceList:['中国电信安徽分公司（阜阳固话）','中国电信安徽分公司（阜阳宽带）',]},
{name:'合肥', publicServiceList:['中国电信安徽分公司（合肥合肥固话）','中国电信安徽分公司（合肥宽带）',]}, 
{name:'淮北', publicServiceList:['中国电信安徽分公司（淮北固话）','中国电信安徽分公司（淮北宽带）',]},
{name:'淮南', publicServiceList:['中国电信安徽分公司（淮南固话）','中国电信安徽分公司（淮南宽带）',]},
{name:'黄山', publicServiceList:['中国电信安徽分公司（黄山固话）','中国电信安徽分公司（黄山宽带）',]},
{name:'六安', publicServiceList:['中国电信安徽分公司（六安固话）','中国电信安徽分公司（六安宽带）',]},
{name:'马鞍山', publicServiceList:['中国电信安徽分公司（马鞍山固话）','中国电信安徽分公司（马鞍山宽带）',]},
{name:'宿州', publicServiceList:['中国电信安徽分公司（宿州固话）','中国电信安徽分公司（宿州宽带）',]},
{name:'铜陵', publicServiceList:['中国电信安徽分公司（铜陵固话）','中国电信安徽分公司（铜陵宽带）',]},
{name:'芜湖', publicServiceList:['芜湖电信有限公司','中国电信安徽分公司（芜湖固话）','中国电信安徽分公司（芜湖宽带）',]},
{name:'宣城', publicServiceList:['中国电信安徽分公司（宣城固话）','中国电信安徽分公司（宣城宽带）',]},
]},
{name:'北京', cityList:[           
{name:'北京', publicServiceList:['（固话）中国联合网络通信有限公司北京市分公司','（后付费固话）中国铁通北京分公司','（宽带）中国联合网络通信有限公司北京市分公司','（宽带）中国移动通信集团北京有限公司',]}, 
     
]},
{name:'福建', cityList:[           
{name:'福建全省', publicServiceList:['暂没有可用的公共事业单位',]}, 
{name:'福州', publicServiceList:['中国电信福建省分公司(仓山区)','中国电信福建省分公司(长乐市)','中国电信福建省分公司(福清市)','中国电信福建省分公司(鼓楼区)','中国电信福建省分公司(晋安区)','中国电信福建省分公司(连江县)','中国电信福建省分公司(罗源县)','中国电信福建省分公司(马尾区)','中国电信福建省分公司(闽侯县)','中国电信福建省分公司(闽清县)','中国电信福建省分公司(平潭县)','中国电信福建省分公司(台江区)','中国电信福建省分公司(永泰县)',]}, 
{name:'龙岩', publicServiceList:['中国电信福建省分公司(长汀县)','中国电信福建省分公司(连城县)','中国电信福建省分公司(龙岩市)','中国电信福建省分公司(上杭县)','中国电信福建省分公司(武平县)','中国电信福建省分公司(永定县)','中国电信福建省分公司(漳平市)',]}, 
{name:'南平', publicServiceList:['中国电信福建省分公司(光泽县)','中国电信福建省分公司(建瓯市)','中国电信福建省分公司(建阳市)','中国电信福建省分公司(浦城县)','中国电信福建省分公司(邵武市)','中国电信福建省分公司(顺昌县)','中国电信福建省分公司(松溪县)','中国电信福建省分公司(武夷山市)','中国电信福建省分公司(延平区)','中国电信福建省分公司(政和县)',]},
{name:'宁德', publicServiceList:['中国电信福建省分公司(福安市)','中国电信福建省分公司(福鼎市)','中国电信福建省分公司(古田县)','中国电信福建省分公司(宁德市)','中国电信福建省分公司(屏南县)','中国电信福建省分公司(寿宁县)','中国电信福建省分公司(霞浦县)','中国电信福建省分公司(周宁县)',]}, 
{name:'莆田', publicServiceList:['中国电信福建省分公司(城厢区)','中国电信福建省分公司(涵江区)','中国电信福建省分公司(荔城区)',]},
{name:'泉州', publicServiceList:['中国电信福建省分公司(德化县)','中国电信福建省分公司(丰泽区)','中国电信福建省分公司(惠安县)','中国电信福建省分公司(金门县)','中国电信福建省分公司(晋江市)','中国电信福建省分公司(鲤城区)','中国电信福建省分公司(洛江区)','中国电信福建省分公司(南安市)','中国电信福建省分公司(泉港区)','中国电信福建省分公司(石狮市)','中国电信福建省分公司(永春县)',]}, 
{name:'三明', publicServiceList:['中国电信福建省分公司(建宁县)','中国电信福建省分公司(明溪县)','中国电信福建省分公司(泰宁县)','中国电信福建省分公司(永安市)',]},
{name:'厦门', publicServiceList:['中国电信福建省分公司(海沧区)','中国电信福建省分公司(湖里区)','中国电信福建省分公司(集美区)','中国电信福建省分公司(思明区)','中国电信福建省分公司(同安区)','中国电信福建省分公司(翔安区)','中国电信厦门分公司',]}, 
{name:'漳州', publicServiceList:['中国电信福建省分公司(长泰县)','中国电信福建省分公司(东山县)','中国电信福建省分公司(华安县)','中国电信福建省分公司(龙海市)','中国电信福建省分公司(龙文区)','中国电信福建省分公司(南靖县)','中国电信福建省分公司(平和县)','中国电信福建省分公司(芗城区)','中国电信福建省分公司(云霄县)','中国电信福建省分公司(漳浦县)','中国电信福建省分公司(诏安县)',]},
]},
{name:'广东', cityList:[           
{name:'广东全省', publicServiceList:['（固话）中国联通','（宽带）中国联通',]}, 
{name:'深圳', publicServiceList:['（固话）中国电信集团','（宽带）中国电信集团','中国电信深圳分公司（ADSL宽带）','中国电信深圳分公司（固话）',]},     
]},
{name:'黑龙江', cityList:[           
{name:'大庆', publicServiceList:['电信黑龙江省分公司（大庆固话）','电信黑龙江省分公司（大庆宽带）',]}, 
{name:'黑龙江全省', publicServiceList:['（固话）中国电信股份有限公司','（宽带）中国电信股份有限公司',]}, 
]},
{name:'辽宁', cityList:[           
{name:'丹东', publicServiceList:['丹东联通公司(固话宽带)',]}, 
{name:'沈阳', publicServiceList:['沈阳市电信公司(固话)','沈阳市联通公司(固话宽带)',]}, 
]},
{name:'上海', cityList:[           
{name:'上海', publicServiceList:['中国电信股份有限公司上海分公司','上海联通公司（条形码）','铁通上海分公司','铁通上海分公司(条形码)','中国电信股份有限公司上海分公司(条形码)','中国移动通信集团上海有限公司',]}, 
]},
{name:'四川', cityList:[           
{name:'成都', publicServiceList:['成都市电信ADSL话费','成都市电信固话后付费','成都市电信固话预付费','成都市电信宽带费','成都市电信小灵通话费',]}, 
]},
{name:'浙江', cityList:[           
{name:'杭州', publicServiceList:['华数宽带']}, 
]},
{name:'重庆', cityList:[           
{name:'重庆', publicServiceList:['重庆电信公司','重庆市联通公司','重庆市铁通公司',]}, 
]},
];
  

/**
 * 验证输入固话宽带账号
 */
function isWideNo(){
	var str_wideNo = $("[name='wideNo']").val();
	var spanNode = document.getElementById("tI");
	var strRegex = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
	var re=new RegExp(strRegex);
	if (re.test(str_wideNo)){
		spanNode.innerHTML = "";
	return true;
	}else{
		spanNode.innerHTML = "格式错误,请输入正确的固话宽带账号".fontcolor("red");
	return false;
	}
	
}
/**
 * 检验表单
 * @return
 */
function checkSumbit(){
	if($("[name='wideNo']").val() == null || $("[name='wideNo']").val() === ""){
		$("#msgSpan").html(" 固话宽带不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='amount']").val() == null || $("[name='amount']").val() === ""){
		$("#msgSpan").html("充值金额不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='cmdProvince']").val() == null || $("[name='cmdProvince']").val() === ""){
		$("#msgSpan").html("省份");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='cmdCity']").val() == null || $("[name='cmdCity']").val() === ""){
		$("#msgSpan").html("城市。");
		$("#msgDiv").show();
		return false;
	}
	/*
	if($("[name='cmdPublicService']").val() == null || $("[name='cmdPublicService']").val() === ""){
		$("#msgSpan").html("公共事业单位。");
		$("#msgDiv").show();
		return false;
	}*/
	return true;
}


$(document).ready(function() {
	//用户表数据加载
	wideRechargeTable = $('#wideRechargeTable').dataTable( {
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
		"sAjaxSource": mozi.contextPath +"/wideRechargeManage/selectWideRechargeList.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "rechargeId" ,"sDefaultContent" : "","sTitle" : "固话宽带充值ID"},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "wideNo" ,"sDefaultContent" : "","sTitle" : "固话宽带账号"},
			{ "sClass": "hidden-phone", 'sWidth':'10%', "mDataProp": "amount" ,"sDefaultContent" : "","sTitle" : "充值金额"},
			{ "sClass": "hidden-phone", 'sWidth':'10%', "mDataProp": "cmdProvince" ,"sDefaultContent" : "","sTitle" : "省份"},
			{ "sClass": "hidden-phone", 'sWidth':'10%', "mDataProp": "cmdCity" ,"sDefaultContent" : "","sTitle" : "城市"},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "cmdPublicService" ,"sDefaultContent" : "","sTitle" : "公共事业单位"},
	
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#wideRechargeTable tbody ').on('click', 'tr', function () {
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