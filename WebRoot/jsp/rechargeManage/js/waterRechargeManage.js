var optFlag = 1	//1表示新增，2表示修改
var waterRechargeTable = null;

/**
 * 显示固话宽带充值面板
 * @return
 */
function showWaterRechargePanle(flag){
	$("#msgSpan").html("");
	$("#msgDiv").hide();
	if(flag === 1){
		$("h3").html("新增水煤电充值");
		optFlag = 1;
		//清空数据
		$("[name='rechargeId']").attr("value","");
		$("[name='accountNo']").attr("value","");
		$("#amount").get(0).selectedIndex=0;
		//$("#cmdWater").get(0).selectedIndex=0;
		//$("#cmdProvince").get(0).selectedIndex=0;
		//$("#cmdCity").get(0).selectedIndex=0;
		//$("#cmdPublicService").get(0).selectedIndex=0;
		$("#waterRechargeModal").modal("show");
	}else{
		//取得充值表
		var table = $('#waterRechargeTable').DataTable();
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
		$("[name='accountNo']").attr("value",datas[0]['accountNo']);
		$("#amount").val(datas[0]['amount']);
		$("#cmdWater").val(datas[0]['cmdW']);
		$("#cmdProvince").val(datas[0]['cmdProvince']);
		$("#cmdCity").val(datas[0]['cmdCity']);
		$("#cmdPublicService").val(datas[0]['cmdPublicService']);
	
		$("#waterRechargeModal").modal("show");
	}
}
/**
 * 新增或修改充值记录
 * @return
 */
function optWaterRecharge(){
	var url = "";
	if(optFlag === 1){
		//新增
		
		if(!checkSumbit()){return;}//判断checkSubmit是否为空,为空则进入方法
		url =  mozi.contextPath +"/waterRechargeManage/insertWaterRecharge.do";
	}else{
		//修改
		if(!checkSumbit()){return;}
		url = mozi.contextPath +"/waterRechargeManage/updateWaterRecharge.do";
	}
	
	//取得充值表
	var table = $('#waterRechargeTable').DataTable();
	//取得选中列
	var datas = table.rows('.selected').data();
	$.ajax({
		url : url,
		type : 'post',
		data : {
			rechargeId : $("[name='rechargeId']").attr("value"),
			accountNo : $("[name='accountNo']").attr("value"),
			amount : $("#amount").val(),
			cmdWater:$("#cmdWater").val(),
			cmdProvince:$("#cmdProvince").val(),
			cmdCity : $("#cmdCity").val(),
			cmdPublicService : $("#cmdPublicService").val(),
			//price : $("#price").text()
		}, 
		dataType : 'json',
		success : function(resp) {
			if(resp.success){
				$('#waterRechargeModal').modal('hide');
				waterRechargeTable.fnReloadAjax();
				waterRechargeTable.fnDraw(); //重新加载数据
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
function deleteWaterRecharge(){
	//取得菜单表
	var table = $('#waterRechargeTable').DataTable();
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
						var rechargeIds = ""
							table.rows('.selected').indexes().each( function (i) {
							var data = table.row(i).data();
							if(rechargeIds === ""){
								rechargeIds = data['rechargeId'];
							}else{
								rechargeIds = rechargeIds+","+data['rechargeId'];
							}
						});
						//确认删除
						$.ajax( {   
				            "type": "POST",    
				           // "contentType": "application/json",   
				            "url": mozi.contextPath+"/waterRechargeManage/deleteWaterRecharge.do",    
				            "dataType": "json",   
				            "data": {'rechargeIds':rechargeIds}, //以json格式传递   
				            "success": function(resp) {
				            	if(resp.success){
				            		waterRechargeTable.fnReloadAjax();
				            		waterRechargeTable.fnDraw(); //重新加载数据
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
 * 四级联动水煤电缴费类型、省、市、公共服务单位
 * @return
 */

var addressInit = function(_cmdWater,_cmdProvince, _cmdCity, _cmdPublicService, defaultWater,defaultProvince, defaultCity, defaultPublicService)  
{  
    var cmdWater = document.getElementById(_cmdWater);
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
      function changeWater()  
    {  
        cmdProvince.options.length = 0;  
        cmdProvince.onchange = null;  
        if(cmdWater.selectedIndex == -1)return;  
        var item = cmdWater.options[cmdWater.selectedIndex].obj;  
        for(var i=0; i<item.provinceList.length; i++)  
        {  
            cmdAddOption(cmdProvince, item.provinceList[i].name, item.provinceList[i]);  
        }  
        cmdSelect(cmdProvince, defaultProvince);  
        changeProvince();  
        cmdProvince.onchange = changeProvince;  
    } 

 
    for(var i=0; i<waterList.length; i++)  
    {  
        cmdAddOption(cmdWater, waterList[i].name, waterList[i]);  
    }  
    cmdSelect(cmdWater, defaultWater);  
    changeProvince();  
    cmdWater.onchange = changeWater;  
}  
   
var waterList = [  
{name:'电费' , provinceList:[
{name:'安徽', cityList:[           
{name:'安庆', publicServiceList:['安庆供电公司','怀宁供电公司','潜山供电公司','宿松供电公司','太湖供电公司','桐城供电公司','望江供电公司','岳西供电公司',]}, 
{name:'蚌埠', publicServiceList:['凤阳供电公司','固镇供电公司','怀远供电公司','五河供电公司','蚌埠供电公司','蚌埠供电公司',]}, 
{name:'亳州', publicServiceList:['亳州供电公司','利辛供电公司','蒙城供电公司','涡阳供电公司',]}, 
{name:'池州', publicServiceList:['东至供电公司','贵池供电公司','青阳供电公司','石台供电公司','池州供电公司',]},
{name:'滁州', publicServiceList:['滁州城郊供电公司','定远供电公司','来安供电公司','明光供电公司','全椒供电公司','天长供电公司','滁州供电公司',]}, 
{name:'阜阳', publicServiceList:['阜阳供电公司','阜南供电公司','阜阳农电公司','界首供电公司','临泉供电公司','太和供电公司','颍上供电公司',]},
{name:'合肥', publicServiceList:['合肥供电公司','巢湖供电公司','庐江供电公司','肥东供电公司','肥西供电公司','长丰供电公司',]}, 
{name:'淮北', publicServiceList:['濉溪县供电公司','淮北供电公司',]},
{name:'淮南', publicServiceList:['凤台供电公司','潘集供电公司','寿县供电公司','谢桥供电公司','淮南供电公司',]},
{name:'黄山', publicServiceList:['黄山供电公司','黄山区供电公司','祁门供电公司','歙县供电公司','休宁供电公司','黟县供电公司',]},
{name:'六安', publicServiceList:['霍邱供电公司','霍山供电公司','金寨供电公司','六安供电公司','六安农电公司','舒城供电公司','叶集供电公司',]},
{name:'马鞍山', publicServiceList:['含山供电公司','和县供电公司','马鞍山供电公司','当涂供电公司',]},
{name:'宿州', publicServiceList:['砀山供电公司','灵璧供电公司','泗县供电公司','宿州农电供电公司','萧县供电公司','埇桥供电公司','宿州供电公司',]},
{name:'铜陵', publicServiceList:['枞阳供电公司','铜陵县供电公司','铜陵供电公司',]},
{name:'芜湖', publicServiceList:['繁昌县供电公司','南陵县供电公司','无为供电公司','芜湖县供电公司','芜湖供电公司',]},
{name:'宣城', publicServiceList:['宣城供电公司','广德供电公司','绩溪供电公司','泾县供电公司','旌德供电公司','郎溪供电公司','宁国供电公司',]},
]},
{name:'北京', cityList:[           
{name:'北京', publicServiceList:['国网北京市电力公司（网络电表）',]},
]},
{name:'福建', cityList:[           
{name:'福建全省', publicServiceList:['国网福建省电力有限公司（银行代收）',]}, 
{name:'福州', publicServiceList:['国网福州供电公司','国网长乐市供电公司','国网福清市供电公司','国网连江县供电公司','国网罗源县供电公司','国网闽侯县供电公司','国网闽清县供电公司','国网平潭供电公司','国网永泰县供电公司',]},
{name:'龙岩', publicServiceList:['国网龙岩供电公司','国网长汀县供电公司','国网连城县供电公司','国网龙岩市新罗区供电公司','国网上杭县供电公司','国网武平县供电公司','国网永定县供电公司','国网漳平市供电公司',]},
{name:'南平', publicServiceList:['国网南平供电公司','国网光泽县供电公司','国网建瓯市供电公司','国网建阳市供电公司','国网南平市延平区供电公司','国网浦城县供电公司','国网邵武市供电公司','国网顺昌县供电公司','国网松溪县供电公司','国网武夷山市供电公司','国网政和县供电公司',]},
{name:'宁德', publicServiceList:['国网宁德供电公司','国网福安市供电公司','国网福鼎市供电公司','国网古田县供电公司','国网屏南县供电公司','国网寿宁县供电公司','国网霞浦县供电公司','国网柘荣县供电公司','国网周宁县供电公司',]},
{name:'莆田', publicServiceList:['国网湄洲湾供电公司','国网莆田供电公司','国网仙游供电公司',]},
{name:'泉州', publicServiceList:['国网泉州供电公司','国网安溪县供电公司','国网德化县供电公司','国网惠安县供电公司','国网晋江市供电公司','国网南安市供电公司','国网石狮市供电公司','国网永春县供电公司',]},
{name:'三明', publicServiceList:['国网三明供电公司','国网大田县供电公司','国网建宁县供电公司','国网将乐县供电公司','国网明溪县供电公司','国网宁化县供电公司','国网清流县供电公司','国网沙县供电公司','国网泰宁县供电公司','国网永安市供电公司','国网尤溪县供电公司',]},
{name:'厦门', publicServiceList:['国网厦门供电公司',]},
{name:'漳州', publicServiceList:['国网漳州供电公司','国网长泰县供电公司','国网常山供电公司','国网东山县供电公司','国网华安县供电公司','国网龙海市供电公司','国网南靖县供电公司','国网平和县供电公司','国网云霄县供电公司','国网漳浦县供电公司','国网诏安县供电公司',]},
]},
{name:'甘肃', cityList:[           
{name:'白银', publicServiceList:['国网甘肃省电力公司（白银）',]},
{name:'定西', publicServiceList:['国网甘肃省电力公司（定西）',]}, 
{name:'甘南藏族自治州', publicServiceList:['国网甘肃省电力公司（甘南）',]},
{name:'嘉峪关', publicServiceList:['国网甘肃省电力公司（嘉峪关）',]},
{name:'金昌', publicServiceList:['国网甘肃省电力公司（金昌）',]},
{name:'酒泉', publicServiceList:['国网甘肃省电力公司（酒泉）',]},
{name:'兰州', publicServiceList:['国网甘肃省电力公司（兰州）',]},
{name:'临夏回族自治州', publicServiceList:['国网甘肃省电力公司（临夏）',]},
{name:'陇南', publicServiceList:['国网甘肃省电力公司（陇南）',]},
{name:'平凉', publicServiceList:['国网甘肃省电力公司（平凉）',]},
{name:'庆阳', publicServiceList:['国网甘肃省电力公司（庆阳）',]},
{name:'天水', publicServiceList:['国网甘肃省电力公司（天水）',]},
{name:'武威', publicServiceList:['国网甘肃省电力公司(武威）',]},
{name:'张掖', publicServiceList:['国网甘肃省电力公司（张掖）',]},
]},
{name:'广东', cityList:[           
{name:'潮州', publicServiceList:['潮安供电局','潮州供电局','广东电网公司(银行代收)','饶平供电局',]},
{name:'东莞', publicServiceList:['东莞供电局','广东电网公司(银行代收)',]},
{name:'佛山', publicServiceList:['佛山供电局','广东电网公司(银行代收)',]},
{name:'广州', publicServiceList:['广州供电局',]},
{name:'河源', publicServiceList:['广东电网公司(银行代收)','和平供电局','河源供电局','连平供电局','龙川供电局','紫金供电局',]},
{name:'惠州', publicServiceList:['广东电网公司(银行代收)','惠州供电局','龙门供电局',]},
{name:'江门', publicServiceList:['广东电网公司(银行代收)','江门供电局',]},
{name:'揭阳', publicServiceList:['广东电网公司(银行代收)','惠来供电局','揭东供电局','揭西供电局','揭阳供电局','普宁供电局',]},
{name:'茂名', publicServiceList:['电白供电局','广东电网公司(银行代收)','化州供电局','茂名供电局','信宜供电局','',]},
{name:'梅州', publicServiceList:['大埔供电局','丰顺供电局','广东电网公司(银行代收)','蕉岭供电局','梅县供电局','梅州供电局','平远供电局','五华供电局','兴宁供电局',]},
{name:'清远', publicServiceList:['广东电网公司(银行代收)','连南供电局','连山供电局','连州供电局','清远供电局','阳山供电局','英德供电局',]},
{name:'汕头', publicServiceList:['潮南供电局','潮阳供电局','澄海供电局','广东电网公司(银行代收)','南澳供电局','汕头供电局',]},
{name:'汕尾', publicServiceList:['广东电网公司(银行代收)','海丰供电局','陆丰供电局','陆河供电局','汕尾供电局',]},
{name:'韶关', publicServiceList:['广东电网公司(银行代收)','乐昌供电局','南雄供电局','曲江供电局','仁化供电局','乳源供电局','韶关供电局','始兴供电局','翁源供电局','新丰供电局',]},
{name:'深圳', publicServiceList:['深圳供电局',]},
{name:'阳江', publicServiceList:['广东电网公司(银行代收)','阳春供电局','阳江供电局',]},
{name:'云浮', publicServiceList:['广东电网公司(银行代收)','罗定供电局','新兴供电局','郁南供电局','云浮供电局',]},
{name:'湛江', publicServiceList:['广东电网公司(银行代收)','雷州供电局','廉江供电局','吴川供电局','湛江供电局',]},
{name:'肇庆', publicServiceList:['德庆供电局','封开供电局','广东电网公司(银行代收)','怀集供电局','肇庆供电局',]},
{name:'中山', publicServiceList:['广东电网公司(银行代收)','中山供电局',]},
{name:'珠海', publicServiceList:['广东电网公司(银行代收)','珠海供电局',]},
]},
{name:'广西', cityList:[           
{name:'百色', publicServiceList:['百色供电局','隆林供电有限公司','田东供电公司','田阳供电公司',]},
{name:'崇左', publicServiceList:['北海','北海供电局','合浦供电公司',]},
{name:'防城港', publicServiceList:['崇左供电局','扶绥供电公司','凭祥供电公司',]},
{name:'贵港', publicServiceList:['东兴供电公司','防城港供电局','防城供电公司',]},
{name:'桂林', publicServiceList:['贵港供电局','桂平供电公司','平南供电公司',]},
{name:'', publicServiceList:['桂林城郊供电公司','桂林供电局','临桂供电公司','灵川供电公司','兴安供电公司','阳朔供电公司','永福供电公司',]},
{name:'河池', publicServiceList:['大化供电公司','东兰供电公司','河池供电局','环江供电公司','金城江供电公司','罗城供电公司','宜州供电公司',]},
{name:'来宾', publicServiceList:['合山供电公司','来宾供电局','武宣供电公司','象州供电公司','忻城供电公司','兴宾供电公司',]},
{name:'柳州', publicServiceList:['柳城供电公司','柳江供电公司','柳州供电局','鹿寨供电公司','融安供电公司',]},
{name:'南宁', publicServiceList:['宾阳供电公司','横县供电公司','隆安供电公司','马山供电公司','南宁供电局','平果供电公司','上林供电公司','武鸣供电公司','邕宁供电公司',]},
{name:'钦州', publicServiceList:['灵山供电公司','浦北供电公司','钦州城郊供电公司','钦州供电局',]},
{name:'梧州', publicServiceList:['梧州供电局',]},
{name:'玉林', publicServiceList:['北流供电公司','陆川县供电公司','玉林市供电局',]},
]},
{name:'贵州', cityList:[           
{name:'安顺', publicServiceList:['安顺供电局','安顺开发区供电局','安顺市郊供电局','关岭供电局','纳雍供电局','平坝供电局','普定供电局','镇宁供电局','织金供电局','紫云供电局',]},
{name:'毕节', publicServiceList:['毕节供电局','毕节市郊供电局','大方县供电局','赫章供电局','金沙供电局','黔西供电局','威宁供电局',]}, 
{name:'贵阳', publicServiceList:['贵阳供电局','白云供电局','长顺供电局','惠水供电局','开阳供电局','龙里供电局','罗甸供电局','乌当供电局','修文供电局',]},
{name:'六盘水', publicServiceList:['六枝供电局','盘县供电局','水城供电局','钟山供电局',]},
{name:'黔东南', publicServiceList:['麻江供电局','丹寨供电局','岑巩供电局','从江供电局','黄平供电局','剑河供电局','锦屏供电局','凯里供电局','凯里市郊供电局','雷山供电局','黎平供电局','榕江供电局','三穗供电局','施秉供电局','台江供电局','天柱供电局','镇远供电局',]},
{name:'黔南', publicServiceList:['都匀经济开发区供电局','都匀市郊供电局','独山供电局','贵定供电局','平塘供电局','三都供电局','瓮安供电局','福泉供电局','荔波供电局','都匀供电局',]},
{name:'黔西南', publicServiceList:['安龙县供电局','册亨县供电局','普安供电局','晴隆县供电局','望谟县供电局','兴仁县供电局','兴义供电局','兴义市电力有限责任公司','贞丰县供电局',]},
{name:'铜仁', publicServiceList:['德江供电局','江口供电局','石阡供电局','思南供电局','松桃供电局','铜仁供电局','铜仁市郊供电局','万山供电局','','沿河供电局','印江供电局','玉屏供电局',]},
{name:'遵义', publicServiceList:['赤水供电局','道真供电局','凤冈供电局','湄潭供电局','仁怀供电局','绥阳供电局','桐梓供电局','务川供电局','习水供电局','余庆供电局','正安供电局','遵义城郊供电局','遵义供电局','遵义县供电局',]},
]},
{name:'海南', cityList:[           
{name:'白沙', publicServiceList:['海南电网公司白沙供电局',]}, 
{name:'保亭', publicServiceList:['海南电网公司保亭供电局',]},
{name:'昌江', publicServiceList:['海南电网公司昌江供电局',]},
{name:'澄迈', publicServiceList:['海南电网公司澄迈供电局',]},
{name:'儋县', publicServiceList:['海南电网公司儋州供电局',]}, 
{name:'定安', publicServiceList:['海南电网公司定安供电局',]},
{name:'东方', publicServiceList:['海南电网公司东方供电局',]},
{name:'海口', publicServiceList:['海南电网公司海口供电局',]},
{name:'乐东', publicServiceList:['海南电网公司乐东供电局',]},
{name:'临高', publicServiceList:['海南电网公司临高供电局',]},
{name:'陵水', publicServiceList:['海南电网公司陵水供电局',]},
{name:'琼海', publicServiceList:['海南电网公司琼海供电局',]},
{name:'琼中', publicServiceList:['海南电网公司琼中供电局',]},
{name:'三亚', publicServiceList:['海南电网公司三亚供电局',]},
{name:'屯昌', publicServiceList:['海南电网公司屯昌供电局',]},
{name:'万宁', publicServiceList:['海南电网公司万宁供电局',]},
{name:'文昌', publicServiceList:['海南电网公司文昌供电局',]},
{name:'五指山', publicServiceList:['海南电网公司五指山供电局',]},
]},
{name:'河北', cityList:[           
{name:'保定', publicServiceList:['安国市供电公司','安新县供电公司','保定市供电公司','博野县供电公司','定兴县供电公司','定州市供电公司','阜平供电公司','高碑店市供电公司','高阳县供电公司','涞水县供电公司','涞源县供电公司','蠡县供电公司','满城县供电公司','清苑县供电公司','曲阳县供电公司','容城县供电公司','顺平县供电公司','唐县供电公司','望都县供电公司','雄县供电公司','徐水县供电公司','易县供电公司','涿州市供电公司',]},
{name:'沧州', publicServiceList:['泊头市供电公司','渤海新区供电公司','沧县供电公司','沧州供电公司','东光县供电公司','海兴县供电公司','河间市供电公司','黄骅市供电公司','孟村县供电公司','南皮县供电公司','青县供电公司','任丘市供电公司','肃宁县供电公司','吴桥县供电公司','献县供电公司','盐山县供电公司',]},
{name:'承德', publicServiceList:['承德供电公司',]},
{name:'邯郸', publicServiceList:['成安县供电公司','磁县供电公司','大名县供电公司','肥乡县供电公司','峰峰矿区供电公司','馆陶县供电公司','广平县供电公司','邯郸供电公司','邯郸县供电公司','鸡泽县供电公司','临漳县供电公司','邱县供电公司','曲周县供电公司','涉县供电公司','魏县供电公司','武安市供电公司','永年县供电公司',]},
{name:'衡水', publicServiceList:['安平县供电公司','阜城县供电公司','故城县供电公司','衡水供电公司','冀州市供电公司','景县供电公司','饶阳县供电公司','深州市供电公司','桃城区供电公司','武强县供电公司','武邑县供电公司',]},
{name:'廊坊', publicServiceList:['霸州市供电公司','大厂县供电有限公司','大城县供电有限公司','固安县供电有限公司','廊坊供电公司','三河市供电有限公司','文安县供电有限公司','香河县供电有限公司','永清县供电有限公司',]},
{name:'秦皇岛', publicServiceList:['昌黎供电分公司','抚宁供电分公司','卢龙供电分公司','秦皇岛电力公司',]},
{name:'石家庄', publicServiceList:['高邑县供电公司','藁城市供电公司','晋州市供电公司','井陉矿区供电公司','井陉县供电公司（井陉矿区）','灵寿县供电公司','鹿泉市供电公司','栾城县供电公司','平山县供电公司','深泽县供电公司','石家庄供电公司','无极县供电公司','辛集市供电公司','新乐市供电公司','行唐县供电公司','元氏县供电公司','赞皇县供电公司','赵县供电公司','正定县供电公司',]},
{name:'唐山', publicServiceList:['丰南供电公司','丰润供电公司','乐亭供电公司','滦南供电公司','滦县供电公司','迁安供电公司','迁西供电公司','唐山供电公司','玉田供电公司','遵化供电公司',]},
{name:'邢台', publicServiceList:['柏乡县供电公司','大曹庄供电公司','广宗县供电公司','巨鹿县供电公司','临城县供电公司','临西县供电公司','隆尧县供电公司','内丘县供电公司','南宫市供电公司','南和县供电公司','宁晋县供电公司','平乡县供电公司','清河县供电公司','任县供电公司','沙河市供电公司','威县供电公司','新河县供电公司','邢台供电公司','邢台县供电公司',]},
{name:'张家口', publicServiceList:['张家口供电公司',]},
]},
{name:'河南', cityList:[           
{name:'安阳', publicServiceList:['国网河南省电力公司（安阳市）','国网河南省电力公司（安阳县）','国网河南省电力公司（滑县）','国网河南省电力公司（林州）','国网河南省电力公司（内黄）','国网河南省电力公司（汤阴）',]},
{name:'鹤壁', publicServiceList:['国网河南省电力公司（鹤壁）','国网河南省电力公司（浚县）','国网河南省电力公司（淇县）',]}, 
{name:'济源', publicServiceList:['国网河南省电力公司（济源）',]},
{name:'焦作', publicServiceList:['国网河南省电力公司（博爱）','国网河南省电力公司（焦作）','国网河南省电力公司（孟州）','国网河南省电力公司（沁阳）','国网河南省电力公司（温县）','国网河南省电力公司（武陟）','国网河南省电力公司（修武）',]},   
{name:'开封', publicServiceList:['国网河南省电力公司（开封市）','国网河南省电力公司（开封县）','国网河南省电力公司（兰考）','国网河南省电力公司（杞县）','国网河南省电力公司（通许县）','国网河南省电力公司（尉氏）',]},
{name:'洛阳', publicServiceList:['国网河南省电力公司（栾川）','国网河南省电力公司（洛宁）','国网河南省电力公司（洛阳）','国网河南省电力公司（孟津）','国网河南省电力公司（汝阳）','国网河南省电力公司（嵩县）','国网河南省电力公司（新安）','国网河南省电力公司（偃师）','国网河南省电力公司（伊川）','国网河南省电力公司（宜阳）',]},    
{name:'漯河', publicServiceList:['国网河南省电力公司（临颍）','国网河南省电力公司（漯河）','国网河南省电力公司（舞阳）',]}, 
{name:'南阳', publicServiceList:['国网河南省电力公司（邓州）','国网河南省电力公司（方城县）','国网河南省电力公司（内乡）','国网河南省电力公司（南阳）','国网河南省电力公司（南召县）','国网河南省电力公司（社旗）','国网河南省电力公司（唐河）','国网河南省电力公司（桐柏）','国网河南省电力公司（西峡）','国网河南省电力公司（淅川）','国网河南省电力公司（新野）','国网河南省电力公司（镇平）',]},
{name:'平顶山', publicServiceList:['国网河南省电力公司(华辰)','国网河南省电力公司（宝丰）','国网河南省电力公司（华辰石龙区）','国网河南省电力公司（郏县）','国网河南省电力公司（鲁山）','国网河南省电力公司（平顶山）','国网河南省电力公司（汝州）','国网河南省电力公司（叶县）',]}, 
{name:'濮阳', publicServiceList:['国网河南省电力公司（范县）','国网河南省电力公司（南乐县）','国网河南省电力公司（濮阳市）','国网河南省电力公司（濮阳县）','国网河南省电力公司（清丰）','国网河南省电力公司（台前县）',]},    
{name:'三门峡', publicServiceList:['国网河南省电力公司（灵宝）','国网河南省电力公司（卢氏）','国网河南省电力公司（三门峡）','国网河南省电力公司（陕县）','国网河南省电力公司（渑池）',]}, 
{name:'商丘', publicServiceList:['国网河南省电力公司（民权）','国网河南省电力公司（宁陵）','国网河南省电力公司（商丘）','国网河南省电力公司（睢县）','国网河南省电力公司（夏邑）','国网河南省电力公司（永城）','国网河南省电力公司（虞城）','国网河南省电力公司（柘城）',]},     
{name:'新乡', publicServiceList:['国网河南省电力公司（新乡）','国网河南省电力公司（长垣）','国网河南省电力公司（封丘）','国网河南省电力公司（辉县）','国网河南省电力公司（获嘉）','国网河南省电力公司（卫辉）','国网河南省电力公司（新乡县）','国网河南省电力公司（延津）','国网河南省电力公司（原阳县）',]},
{name:'信阳', publicServiceList:['国网河南省电力公司（固始）','国网河南省电力公司（光山县）','国网河南省电力公司（淮滨）','国网河南省电力公司（潢川）','国网河南省电力公司（罗山县）','国网河南省电力公司（商城）','国网河南省电力公司（息县）','国网河南省电力公司（新县）','国网河南省电力公司（信阳）',]},    
{name:'许昌', publicServiceList:['国网河南省电力公司（长葛）','国网河南省电力公司（襄城）','国网河南省电力公司（许昌）','国网河南省电力公司（许昌县）','国网河南省电力公司（鄢陵）','国网河南省电力公司（禹州）',]},
{name:'郑州', publicServiceList:['国网河南省电力公司（郑州）','国网河南省电力公司（巩义）','国网河南省电力公司（荥阳）','国网河南省电力公司（新郑）','国网河南省电力公司（登封）','国网河南省电力公司（新密）','国网河南省电力公司（中牟）',]},
{name:'周口', publicServiceList:['国网河南省电力公司（郸城）','国网河南省电力公司（泛区局）','国网河南省电力公司（扶沟）','国网河南省电力公司（淮阳）','国网河南省电力公司（鹿邑）','国网河南省电力公司（商水）','国网河南省电力公司（沈丘）','国网河南省电力公司（太康）','国网河南省电力公司（西华）','国网河南省电力公司（项城）','国网河南省电力公司（周口）',]},
{name:'驻马店', publicServiceList:['国网河南省电力公司（泌阳）','国网河南省电力公司（平舆）','国网河南省电力公司（确山）','国网河南省电力公司（汝南）','国网河南省电力公司（上蔡）','国网河南省电力公司（遂平）','国网河南省电力公司（西平）','国网河南省电力公司（新蔡）','国网河南省电力公司（正阳）','国网河南省电力公司（驻马店）',]},
]},
{name:'黑龙江', cityList:[           
{name:'大庆', publicServiceList:['大庆市电业局','国网大庆供电公司',]}, 
{name:'大兴安岭', publicServiceList:['国网大兴安岭供电公司',]},
{name:'哈尔滨', publicServiceList:['国网哈尔滨供电公司','哈尔滨市电力公司',]},
{name:'鹤岗', publicServiceList:['国网鹤岗供电公司',]},
{name:'黑河', publicServiceList:['国网黑河供电公司',]},
{name:'鸡西', publicServiceList:['国网鸡西供电公司',]},
{name:'佳木斯', publicServiceList:['国网佳木斯供电公司',]},
{name:'牡丹江', publicServiceList:['国网牡丹江供电公司',]},
{name:'七台河', publicServiceList:['国网七台河供电公司',]},
{name:'齐齐哈尔', publicServiceList:['国网齐齐哈尔供电公司',]},
{name:'双鸭山', publicServiceList:['国网双鸭山供电公司',]},
{name:'绥化', publicServiceList:['国网绥化供电公司',]},
{name:'伊春', publicServiceList:['国网伊春供电公司',]},
]},
{name:'湖北', cityList:[   
{name:'鄂州', publicServiceList:['鄂州供电公司','鄂州供电公司（银行代收）',]},
{name:'恩施', publicServiceList:['恩施供电公司','恩施供电公司（银行代收）',]},
{name:'黄冈', publicServiceList:['黄冈供电公司','黄冈供电公司（银行代收）',]}, 
{name:'黄石', publicServiceList:['黄石供电公司','黄石供电公司（银行代收）',]},
{name:'荆门', publicServiceList:['荆门供电公司','荆门供电公司（银行代收）',]},  
{name:'荆州', publicServiceList:['荆州供电公司（含天门、仙桃、潜江）','荆州市供电公司（银行代收）',]},
{name:'神农架林区', publicServiceList:['神农架林区供电公司',]},
{name:'十堰', publicServiceList:['十堰供电公司','十堰供电公司（银行代收）',]},
{name:'随州', publicServiceList:['随州供电公司','随州供电公司（银行代收）',]},
{name:'武汉', publicServiceList:['武汉供电公司','武汉供电公司（银行代收）',]},
{name:'咸宁', publicServiceList:['咸宁供电公司','咸宁供电公司（银行代收）',]},
{name:'襄樊', publicServiceList:['襄阳供电公司','襄阳供电公司（银行代收）',]},
{name:'孝感', publicServiceList:['孝感供电公司','孝感供电公司（银行代收）',]},
{name:'宜昌', publicServiceList:['宜昌供电公司','宜昌供电公司（银行代收）',]},
]},
{name:'湖南', cityList:[           
{name:'长沙', publicServiceList:['国网湖南省电力公司长沙供电分公司','湖南省电力公司(长沙)',]},

]},
{name:'吉林', cityList:[           
{name:'白城', publicServiceList:['国网白城供电公司','国网吉林大安市供电有限公司','国网吉林省白城市城郊','国网吉林洮南市供电有限公司','国网吉林通榆县供电有限公司','国网吉林镇赉县供电有限公司',]},
{name:'白山', publicServiceList:['国网白山供电公司','国网吉林白山市江源区供电有限公司','国网吉林省白山市城郊',]}, 
{name:'长春', publicServiceList:['国网长春供电公司','国网吉林德惠市供电有限公司','国网吉林九台市供电有限公司','国网吉林农安县供电有限公司','国网吉林省长春市城郊','国网吉林省长春市双阳区','国网吉林榆树市供电有限公司',]},
{name:'吉林', publicServiceList:['国网吉林市供电公司','国网吉林桦甸市供电有限公司','国网吉林蛟河市供电有限公司','国网吉林磐石市供电有限公司','国网吉林省吉林市城郊','国网吉林舒兰市供电有限公司','国网吉林永吉县供电有限公司',]},
{name:'辽源', publicServiceList:['国网辽源供电公司','国网吉林东丰县供电有限公司','国网吉林东辽县供电有限公司','国网吉林省辽源市城郊',]},
{name:'四平', publicServiceList:['国网吉林梨树县供电有限公司','国网吉林省四平市城郊','国网吉林省直辖公主岭市','国网吉林双辽市供电有限公司','国网吉林伊通满族自治县供电有限公司','国网四平供电公司',]},
{name:'松原', publicServiceList:['国网吉林长岭县供电有限公司','国网吉林扶余市供电有限公司','国网吉林前郭尔罗斯县供电有限公司','国网吉林乾安县供电有限公司','国网吉林省松原市城郊','国网松原供电公司',]},
{name:'通化', publicServiceList:['国网吉林辉南县供电有限公司','国网吉林集安市供电有限公司','国网吉林柳河县供电有限公司','国网吉林梅河口市供电有限公司','国网吉林省通化市城郊','国网通化供电公司',]},
{name:'延边州', publicServiceList:['国网吉林敦化市供电有限公司','国网吉林和龙市供电有限公司','国网吉林珲春市供电有限公司','国网吉林龙井市供电有限公司','国网吉林省延吉市城郊','国网吉林图们市供电有限公司','国网吉林汪清县供电有限公司','国网延边供电公司',]},
]},
{name:'江苏', cityList:[           
{name:'常州', publicServiceList:['常州供电公司',]},
{name:'淮安', publicServiceList:['淮安供电公司',]},
{name:'连云港', publicServiceList:['连云港供电公司',]},
{name:'南京', publicServiceList:['南京供电公司','南京电力（银行代收）',]},
{name:'南通', publicServiceList:['南通供电公司',]},
{name:'苏州', publicServiceList:['苏州供电公司','苏州电力（银行代收）',]},
{name:'宿迁', publicServiceList:['宿迁供电公司',]},
{name:'泰州', publicServiceList:['泰州供电公司',]},
{name:'无锡', publicServiceList:['无锡供电公司',]},
{name:'徐州', publicServiceList:['徐州供电公司',]},
{name:'盐城', publicServiceList:['盐城供电公司',]},
{name:'扬州', publicServiceList:['扬州供电公司',]},
{name:'镇江', publicServiceList:['镇江供电公司',]},
]},
{name:'江西', cityList:[           
{name:'抚州', publicServiceList:['抚州市供电公司(不含临川、高新产业园区)','高新技术产业园区供电公司','临川区供电公司','崇仁县供电公司','东乡县供电公司','广昌县供电公司','金溪县供电公司','乐安县供电公司','黎川县供电公司','南城县供电公司','南丰县供电公司','宜黄县供电公司','资溪县供电公司',]},
{name:'赣州', publicServiceList:['赣州市供电公司(不含开发区、南康区)','赣州市开发区供电公司','南康区供电公司','赣县供电公司','安远县供电公司','崇义县供电公司','大余县供电公司','定南县供电公司','会昌县供电公司','龙南县供电公司','宁都县供电公司','全南县供电公司','瑞金市供电公司','上犹县供电公司','石城县供电公司','信丰县供电公司','兴国县供电公司','寻乌县供电公司','于都县供电公司',]},
{name:'吉安', publicServiceList:['吉州区供电公司','安福县供电公司','吉安县供电公司','吉水县供电公司','井冈山市供电公司','遂川县供电公司','泰和县供电公司','万安县供电公司','峡江县供电公司','新干县供电公司','永丰县供电公司','永新县供电公司','吉安市供电公司',]},
{name:'景德镇', publicServiceList:['景德镇市供电公司(不含昌江区)','昌江区供电公司','赣东北供电公司(乐平、鄱阳城区)','乐平市供电公司(不含城区)','浮梁县供电公司',]}, 
{name:'九江', publicServiceList:['九江市供电公司(不含庐山区)','庐山区供电公司','德安县供电公司','都昌县供电公司','共青城市供电公司','湖口县供电公司','九江县供电公司','彭泽县供电公司','瑞昌市供电公司','武宁县供电公司','星子县供电公司','修水县供电公司','永修县供电公司',]},
{name:'南昌', publicServiceList:['南昌市供电公司(东湖、西湖、青云谱区)','青山湖区供电公司','湾里区供电公司','南昌市桑海区供电公司','昌北供电公司','安义县供电公司','红谷滩供电公司','进贤县供电公司','南昌县供电公司','新建区供电公司',]}, 
{name:'萍乡', publicServiceList:['萍乡市供电公司(不含安源、湘东区)','安源区供电公司','湘东区供电公司','莲花县供电公司','芦溪县供电公司','上栗县供电公司',]},
{name:'上饶', publicServiceList:['上饶市供电公司(不含信州区)','信州区供电公司','德兴市供电公司','广丰县供电公司','横峰县供电公司','鄱阳县供电公司(不含城区)','铅山县供电公司','上饶县供电公司','万年县供电公司','婺源县供电公司','弋阳县供电公司','余干县供电公司','玉山县供电公司',]},
{name:'新余', publicServiceList:['新余市供电公司(不含渝水区)','渝水区供电公司','分宜县供电公司',]},
{name:'宜春', publicServiceList:['宜春市供电公司(不含袁州区)','袁州区供电公司','丰城市供电公司','奉新县供电公司','高安市供电公司','靖安县供电公司','上高县供电公司','铜鼓县供电公司','万载县供电公司','宜丰县供电公司','樟树市供电公司',]},
{name:'鹰潭', publicServiceList:['鹰潭市供电公司(不含月湖区)','月湖区供电公司','贵溪市供电公司','余江县供电公司',]},
]},
{name:'辽宁', cityList:[           
{name:'鞍山', publicServiceList:['鞍山供电公司',]},
{name:'本溪', publicServiceList:['本溪供电公司',]},
{name:'朝阳', publicServiceList:['朝阳供电公司',]},
{name:'大连', publicServiceList:['大连供电公司','大连市联合收费处',]},
{name:'丹东', publicServiceList:['丹东市电业公司','丹东供电公司',]},
{name:'抚顺', publicServiceList:['抚顺供电公司',]},
{name:'阜新', publicServiceList:['阜新供电公司',]},
{name:'葫芦岛', publicServiceList:['葫芦岛供电公司',]},
{name:'锦州', publicServiceList:['锦州供电公司',]},
{name:'辽阳', publicServiceList:['辽阳供电公司',]},
{name:'盘锦', publicServiceList:['盘锦供电公司',]},
{name:'沈阳', publicServiceList:['沈阳市供电公司','沈阳供电公司',]},
{name:'铁岭', publicServiceList:['铁岭供电公司',]},
{name:'营口', publicServiceList:['营口供电公司',]},
]},
{name:'内蒙古', cityList:[           
{name:'赤峰', publicServiceList:['阿鲁科尔沁旗供电公司','敖汉旗供电公司','巴林右旗供电公司','巴林左旗供电公司','赤峰供电公司','红山区供电公司','喀喇沁旗供电公司','克什克腾旗供电公司','林西县供电公司','宁城县供电公司','平庄供电公司','松山区供电公司','翁牛特旗供电公司','元宝山区供电公司',]},
{name:'呼伦贝尔', publicServiceList:['国网阿荣旗供电公司','国网陈巴尔虎旗供电公司','国网额尔古纳市供电公司','国网鄂伦春旗供电公司','国网鄂温克旗供电公司','国网莫力达瓦旗供电公司','国网新巴尔虎右旗供电公司','国网新巴尔虎左旗供电公司','呼伦贝尔供电公司',]}, 
{name:'通辽', publicServiceList:['国网库伦旗供电公司','霍林河农电局','通辽供电公司','通辽市郊区农电局','通辽市开鲁县农电局','通辽市科尔沁区农电局','通辽市科左后旗农电局','通辽市科左中旗农电局','通辽市奈曼旗农电局','扎鲁特旗农电局',]},
{name:'兴安盟', publicServiceList:['国网科右中旗供电公司','国网突泉县供电公司','国网乌兰浩特市供电公司（原市农电）','国网扎赉特旗供电公司','科右前旗供电有限公司','兴安供电公司（乌兰浩特市区）',]},
]},
{name:'青海', cityList:[           
{name:'果洛藏族自治州', publicServiceList:['果洛供电公司',]},
{name:'海北藏族自治州', publicServiceList:['海北供电公司',]},  
{name:'海东地区', publicServiceList:['海东供电公司',]},
{name:'海南藏族自治州', publicServiceList:['海南供电公司',]},
{name:'海西蒙古族藏族自治州', publicServiceList:['海西供电公司',]},
{name:'黄南藏族自治州', publicServiceList:['黄化供电公司',]},
{name:'西宁', publicServiceList:['西宁供电公司',]},
]},
{name:'山东', cityList:[           
{name:'滨州', publicServiceList:['滨城区供电公司','滨州供电公司','博兴县供电公司','惠民县供电公司','无棣县供电公司','阳信县供电公司','沾化县供电公司',]},
{name:'德州', publicServiceList:['德州供电公司','乐陵市供电公司','临邑县供电公司','陵县供电公司','宁津县供电公司','平原县供电公司','齐河县供电公司','庆云县供电公司','武城县供电公司','夏津县供电公司','禹城市供电公司',]},
{name:'东营', publicServiceList:['东营供电公司','东营区供电公司','广饶县供电公司','河口区供电公司','垦利县供电公司','利津县供电公司',]},
{name:'菏泽', publicServiceList:['曹县供电公司','成武县供电公司','单县供电公司','定陶县供电公司','东明县供电公司','菏泽供电公司','巨野县供电公司','鄄城县供电公司','郓城县供电公司',]},
{name:'济南', publicServiceList:['济南供电公司','平阴县供电公司','长清区供电公司','历城区供电公司','济阳县供电公司','商河县供电公司','章丘市供电公司',]},
{name:'济宁', publicServiceList:['济宁供电公司','嘉祥县供电公司','金乡县供电公司','梁山县供电公司','曲阜市供电公司','任城区供电公司','泗水县供电公司','微山县供电公司','汶上县供电公司','鱼台县供电公司','邹城市供电公司',]},
{name:'莱芜', publicServiceList:['莱芜供电公司',]},
{name:'聊城', publicServiceList:['茌平县供电公司','东阿县供电公司','高唐县供电公司','冠县供电公司','聊城供电公司','临清市供电公司','莘县供电公司','阳谷县供电公司',]},
{name:'临沂', publicServiceList:['临沂供电公司','苍山县供电公司','费县供电公司','莒南县供电公司','临沭县供电公司','蒙阴县供电公司','平邑县供电公司','郯城县供电公司','沂南县供电公司','沂水县供电公司',]},
{name:'青岛', publicServiceList:['青岛供电公司','平度市供电公司','即墨市供电公司','胶州市供电公司','黄岛区供电公司','莱西市供电公司',]},
{name:'日照', publicServiceList:['莒县供电公司','日照供电公司','五莲县供电公司',]},
{name:'泰安', publicServiceList:['泰安供电公司','东平县供电公司','肥城市供电公司','宁阳县供电公司','新泰市供电公司',]},
{name:'威海', publicServiceList:['荣成市供电公司','乳山市供电公司','威海供电公司','文登市供电公司',]},
{name:'潍坊', publicServiceList:['安丘市供电公司','昌乐县供电公司','昌邑市供电公司','高密市供电公司','寒亭区供电公司','临朐县供电公司','青州市供电公司','山东省电力集团公司潍坊供电公司','寿光市供电公司','诸城市供电公司',]},
{name:'烟台', publicServiceList:['烟台供电公司','牟平区供电公司','长岛县供电公司','龙口市供电公司','福山供电公司','海阳市供电公司','莱阳市供电公司','莱州市供电公司','蓬莱市供电公司','栖霞市供电公司','招远市供电公司',]},
{name:'枣庄', publicServiceList:['枣庄供电公司',]},
{name:'淄博', publicServiceList:['高青县供电公司','桓台县供电公司','沂源县供电公司','淄博供电公司',]},
]},
{name:'山西', cityList:[           
{name:'长治', publicServiceList:['国网长治供电公司',]},
{name:'大同', publicServiceList:['国网大同供电公司',]}, 
{name:'晋城', publicServiceList:['国网晋城供电公司',]},
{name:'晋中', publicServiceList:['国网晋中供电公司',]},
{name:'临汾', publicServiceList:['国网临汾供电公司',]},
{name:'吕梁', publicServiceList:['国网吕梁供电公司',]},
{name:'朔州', publicServiceList:['国网朔州供电公司',]},
{name:'太原', publicServiceList:['国网太原供电公司',]},
{name:'忻州', publicServiceList:['国网忻州供电公司',]},
{name:'阳泉', publicServiceList:['国网阳泉供电公司',]},
{name:'运城', publicServiceList:['国网运城供电公司',]},
]},
{name:'陕西', cityList:[           
{name:'安康', publicServiceList:['安康供电公司',]},
{name:'宝鸡', publicServiceList:['宝鸡电力公司',]}, 
{name:'汉中', publicServiceList:['国网陕西省电力公司(汉中)',]},
{name:'商洛', publicServiceList:['商洛供电公司',]},
{name:'铜川', publicServiceList:['铜川电力公司',]},
{name:'渭南', publicServiceList:['渭南电力公司',]},
{name:'西安', publicServiceList:['西咸新区供电公司','西安供电公司',]},
{name:'咸阳', publicServiceList:['咸阳供电公司',]},
{name:'延安', publicServiceList:['延安供电公司',]},
{name:'榆林', publicServiceList:['榆林电力公司',]},
]},
{name:'上海', cityList:[           
{name:'上海', publicServiceList:['上海市电力公司','上海市电力公司（银行代收）','上海市电力公司（条形码）',]},
]},
{name:'四川', cityList:[           
{name:'阿坝藏族羌族', publicServiceList:['国网阿坝供电公司','国网阿坝县供电公司','国网黑水县供电公司','国网红原县供电公司','国网金川县供电公司','国网理县供电公司','国网马尔康县供电公司','国网壤塘县供电公司','国网若尔盖县供电公司','国网松潘县供电公司','国网汶川县供电公司','国网小金县供电公司',]},
{name:'巴中', publicServiceList:['国网巴中供电公司','四川省电力公司(巴中)',]},
{name:'成都', publicServiceList:['国网成都供电公司','国网天府新区供电公司','成都市电力公司（银行代收）','天府新区供电公司（银行代收）',]},
{name:'达川', publicServiceList:['四川省电力公司（达州）',]},
{name:'达州', publicServiceList:['国网达州供电公司',]},
{name:'德阳', publicServiceList:['国网德阳供电公司','国网罗江县供电公司','国网什邡市供电公司','四川省电力公司(德阳)',]},
{name:'甘孜藏族自治州', publicServiceList:['国网甘孜供电公司',]},
{name:'广安', publicServiceList:['国网广安供电公司','国网邻水县供电公司','四川省电力公司（广安）',]},
{name:'广元', publicServiceList:['国网广元供电公司','四川省电力公司(广元)',]},
{name:'乐山', publicServiceList:['国网乐山供电公司','国网马边县供电公司','国网沐川县供电公司','四川省电力公司(乐山)',]},
{name:'凉山', publicServiceList:['国网凉山供电公司','囯网德昌县供电公司','国网甘洛县供电公司','国网会东县供电公司','国网会理县供电公司','国网雷波县供电公司','国网宁南县供电公司','国网喜德县供电公司','国网越西县供电公司','四川省电力公司(西昌)',]},
{name:'泸州', publicServiceList:['国网泸州供电公司','国网泸县供电公司','四川省电力公司(泸州)','四川省电力公司（古蔺县）',]},
{name:'眉山', publicServiceList:['国网眉山供电公司','四川省电力公司(眉山)',]},
{name:'绵阳', publicServiceList:['国网绵阳供电公司','四川省电力公司（绵阳）',]},
{name:'内江', publicServiceList:['国网内江供电公司','四川省电力公司(内江)',]},
{name:'南充', publicServiceList:['国网南充供电公司','四川省电力公司(南充)',]},
{name:'攀枝花', publicServiceList:['国网攀枝花供电公司','四川省电力公司(攀枝花)',]},
{name:'遂宁', publicServiceList:['四川明珠水利电力股份有限公司','遂宁市电费（四川明星电力股份有限公司）',]},
{name:'雅安', publicServiceList:['国网宝兴县供电公司','国网汉源县供电公司','国网芦山县供电公司','国网石棉县供电公司','国网天全县供电公司','国网雅安市名山供电公司','国网雅安市雨城供电公司','国网荥经县供电公司',]},
{name:'宜宾', publicServiceList:['国网宜宾供电公司','四川省电力公司（宜宾）',]},
{name:'资阳', publicServiceList:['国网资阳供电公司','四川省电力公司(资阳)',]},
{name:'自贡', publicServiceList:['国网自贡供电公司','四川省电力公司（自贡）',]},
]},
{name:'云南', cityList:[           
{name:'保山', publicServiceList:['云南电网有限责任公司',]},
{name:'楚雄彝族自治州', publicServiceList:['楚雄鹿城供电局','大姚供电有限公司','禄丰供电有限公司','牟定供电有限公司','南华供电有限公司','双柏供电有限公司','姚安供电有限公司','永仁供电有限公司','元谋供电有限公司','云南电网有限责任公司',]},
{name:'大理', publicServiceList:['大理供电局','宾川供电有限公司','洱源供电有限公司','鹤庆供电有限公司','剑川供电有限公司','弥渡供电有限公司','南涧供电有限公司','巍山供电有限公司','祥云供电有限公司','漾濞供电有限公司','永平供电有限公司','云龙供电有限公司',]},
{name:'德宏傣族景颇族自治州', publicServiceList:['德宏供电局','瑞丽供电局',]},
{name:'迪庆藏族自治州', publicServiceList:['迪庆供电局',]},
{name:'红河哈尼族彝族自治州', publicServiceList:['个旧供电有限公司','河口瑶族自治县供电有限责任公司','红河供电局','红河县供电有限公司','建水供电有限公司','开远供电有限公司','泸西供电有限公司','绿春供电有限公司','弥勒供电有限公司','屏边供电有限公司','石屏供电有限公司','元阳供电有限公司','云南电网有限责任公司',]},
{name:'昆明', publicServiceList:['昆明供电局','安宁供电有限公司','富民供电有限公司','晋宁供电有限公司','禄劝供电有限公司','南方电网（昆明市）','石林供电有限公司','嵩明供电有限公司','寻甸供电有限公司','宜良供电有限公司','云南电网有限责任公司',]},
{name:'丽江', publicServiceList:['丽江供电局',]},
{name:'梁河', publicServiceList:['云南电网公司德宏梁河供电局',]},
{name:'临沧', publicServiceList:['临沧供电局','凤庆供电有限公司','耿马供电有限公司','双江供电有限公司','云南电网公司临沧沧源供电局','云南电网公司临沧临翔供电局','云南电网公司临沧永德供电局','云南电网公司临沧镇康供电局','云县供电有限公司',]},
{name:'陇川', publicServiceList:['云南电网公司德宏陇川供电局',]},
{name:'芒市', publicServiceList:['云南电网公司德宏芒市供电局',]},
{name:'怒江傈僳族自治州', publicServiceList:['怒江供电局',]},
{name:'普洱', publicServiceList:['江城供电有限公司','景东供电有限公司','景谷供电有限公司','澜沧供电有限公司','孟连供电有限公司','墨江供电有限公司','宁洱供电有限公司','普洱供电局','西盟供电有限公司','云南电网公司普洱思茅供电局','云南电网有限责任公司','镇沅供电有限公司',]},
{name:'曲靖', publicServiceList:['曲靖供电局','富源供电有限公司','会泽供电有限公司','陆良供电有限公司','罗平供电有限公司','马龙供电有限公司','师宗供电有限公司','宣威供电有限公司','云南电网公司曲靖麒麟供电局','云南电网公司曲靖沾益供电局','云南电网有限责任公司',]},
{name:'西双版纳傣族自治州', publicServiceList:['勐海供电有限公司','勐腊供电有限公司','西双版纳供电局','云南电网公司西双版纳景洪供电局','云南电网有限责任公司',]},
{name:'盈江', publicServiceList:['云南电网公司德宏盈江供电局',]},
{name:'玉溪', publicServiceList:['玉溪供电局','澄江供电有限公司','峨山供电有限公司','华宁供电有限公司','江川供电有限公司','通海供电有限公司','新平供电有限公司','易门供电有限公司','元江供电有限公司','云南电网有限责任公司',]},
{name:'昭通', publicServiceList:['昭通供电局','大关供电有限公司','鲁甸供电有限公司','巧家供电有限公司','水富供电有限公司','威信供电有限公司','盐津供电有限公司','彝良供电有限公司','永善供电有限公司','镇雄供电有限公司',]}  
]},
{name:'浙江', cityList:[           
{name:'杭州', publicServiceList:['杭州市电力局(市区)','萧山供电局','余杭供电局','富阳供电局','临安供电局','桐庐供电局','淳安县供电局','建德市供电局',]},
{name:'湖州', publicServiceList:['安吉县供电局','湖州电力局','德清县供电局','长兴县供电局',]},
{name:'嘉兴', publicServiceList:['嘉兴电力滨海分局','海宁市供电局','平湖市供电局','嘉善县供电局','嘉兴电力局','桐乡市供电局','海盐县供电局',]},
{name:'金华', publicServiceList:['金华电业局','义乌市供电局','义乌市供电局','武义县供电局','浦江县供电局','兰溪市供电局','东阳市供电局','永康市供电局','磐安县供电局',]},
{name:'丽水', publicServiceList:['庆元县供电局','龙泉市电力公司','云和县电力公司','松阳县供电局','丽水电业局','青田县电力公司','景宁畲族自治县电力公司','莲都供电局','缙云县电力公司','遂昌县电力工业局',]},
{name:'宁波', publicServiceList:['宁海供电局','余姚市供电局','鄞州供电局','宁波电业局','奉化市供电局','象山供电局','慈溪市供电局',]},
{name:'衢州', publicServiceList:['衢州电力局','常山县供电局','开化县供电局','龙游县供电局','江山市供电局',]},
{name:'绍兴', publicServiceList:['新昌电力公司','绍兴电力局','绍兴电力柯桥供电分局','诸暨市供电局','嵊州市电力公司','上虞电力公司',]},
{name:'台州', publicServiceList:['玉环县电力公司','台州市椒江电力公司','临海市电力公司','温岭市电力公司','三门县电力公司','仙居县供电局','台州市电力公司','天台县供电公司','台州市黄岩电力公司','台州市路桥区电力公司',]},
{name:'温州', publicServiceList:['温州市龙湾永强供电公司','文成县电业局','温州电力局','泰顺县供电局','乐清市供电局','洞头县供电局','苍南县供电局','永嘉县电业局','平阳县供电局','瑞安市供电局',]},
{name:'', publicServiceList:['','','','','','',]}  
]},
{name:'重庆', cityList:[           
{name:'重庆', publicServiceList:['国网重庆市电力公司','涪陵电力实业股份有限公司',]}, 
]},
]},
{name:'燃气费' , provinceList:[
{name:'安徽', cityList:[           

{name:'池州', publicServiceList:['池州市港华燃气有限公司（银行代缴）',]},
{name:'滁州', publicServiceList:['定远县深燃天然气有限公司','明光深燃天然气有限公司',]}, 
{name:'合肥', publicServiceList:['合肥燃气集团有限公司','长丰深燃天然气有限公司','肥东深燃天然气有限公司','肥西深燃天然气有限公司','合肥燃气集团有限公司（银行代缴）','合肥中石油昆仑燃气有限公司','庐江县川东燃气有限公司',]}, 
{name:'马鞍山', publicServiceList:['马鞍山市港华燃气有限公司（银行代缴）',]},
{name:'铜陵', publicServiceList:['铜陵港华燃气有限公司',]},
{name:'芜湖', publicServiceList:['芜湖中燃城市燃气发展有限公司',]},
]},
{name:'北京', cityList:[           
{name:'北京', publicServiceList:['北京市燃气集团有限责任公司','北京市燃气公司',]},
]},
{name:'福建', cityList:[           
{name:'福州', publicServiceList:['长乐华润燃气有限公司','福清华润燃气有限公司','福州华润燃气公司','福州华润燃气有限公司','连江华润燃气有限公司','罗源华润燃气有限公司','闽侯华润燃气有限公司','平潭华润燃气有限公司','永泰华润燃气有限公司',]},
{name:'厦门', publicServiceList:['厦门市华润燃气费',]},
]},
{name:'甘肃', cityList:[           
{name:'白银', publicServiceList:['白银市天然气有限公司',]},
]},
{name:'广东', cityList:[           
{name:'佛山', publicServiceList:['佛山市燃气集团股份有限公司',]},
{name:'广州', publicServiceList:['广州燃气集团(东区分公司)','广州燃气集团(西区分公司)','广州燃气集团(南区分公司)','广州燃气集团（花都）','广州燃气集团（增城）','广州燃气集团（南沙）',]},
{name:'江门', publicServiceList:['江门华润燃气有限公司',]}, 
{name:'清远', publicServiceList:['英德华润燃气有限公司',]},
{name:'汕尾', publicServiceList:['海丰深燃中顺燃气有限公司',]}, 
{name:'深圳', publicServiceList:['深圳市燃气集团股份有限公司',]},
{name:'珠海', publicServiceList:['珠海港泰管道燃气有限公司',]},
]},
{name:'广西', cityList:[           
{name:'梧州', publicServiceList:['梧州深燃天然气有限公司',]},
]},
{name:'贵州', cityList:[           
{name:'贵阳', publicServiceList:['贵州燃气(贵阳)',]},
]},
{name:'海南', cityList:[           
{name:'儋县', publicServiceList:['海南中石油昆仑港华燃气有限公司儋州分公司',]}, 
{name:'海口', publicServiceList:['海口市民生管道燃气有限公司',]},
{name:'琼海', publicServiceList:['海南中石油昆仑港华燃气有限公司琼海分公司',]},
{name:'万宁', publicServiceList:['海南中石油昆仑港华燃气有限公司万宁分公司',]},
]},
{name:'河北', cityList:[           
{name:'邯郸', publicServiceList:['大名华润燃气有限公司',]},
{name:'衡水', publicServiceList:['衡水华润燃气有限公司','枣强华润燃气有限公司',]}, 
{name:'秦皇岛', publicServiceList:['秦皇岛市富阳热力有限责任公司',]},
{name:'石家庄', publicServiceList:['河北潜能燃气股份有限公司','石家庄新奥燃气有限公司',]}, 
{name:'唐山', publicServiceList:['迁安华润燃气有限公司',]},
{name:'邢台', publicServiceList:['邢台燃气有限责任公司',]}, 
]},
{name:'河南', cityList:[           
{name:'安阳', publicServiceList:['安阳华润燃气有限公司','安阳县华润燃气有限公司','安阳新城华润燃气有限公司','滑县华润燃气有限公司','内黄华润燃气有限公司','汤阴华润燃气有限公司',]},
{name:'鹤壁', publicServiceList:['浚县华润燃气有限公司','天伦燃气集团',]}, 
{name:'济源', publicServiceList:['济源中裕燃气有限公司',]},
{name:'焦作', publicServiceList:['焦作中裕燃气有限公司',]},   
{name:'开封', publicServiceList:['开封西纳天然气有限公司兰考分公司',]},
{name:'洛阳', publicServiceList:['洛宁华润燃气有限公司',]},    
{name:'漯河', publicServiceList:['漯河中裕燃气有限公司',]}, 
{name:'南阳', publicServiceList:['南阳郑燃燃气有限公司','新野县天伦燃气有限公司',]},
{name:'平顶山', publicServiceList:['平顶山燃气有限责任公司',]}, 
{name:'濮阳', publicServiceList:['南乐华润燃气有限公司','濮阳华润燃气有限公司','濮阳市天伦燃气有限公司','濮阳县沣源天然气有限责任公司',]},    
{name:'三门峡', publicServiceList:['三门峡中裕燃气有限公司','三门峡中裕燃气有限公司陕县分公司',]}, 
{name:'商丘', publicServiceList:['河南绿源燃气有限公司民权分公司','永城中裕燃气有限公司',]},     
{name:'新乡', publicServiceList:['新乡新奥燃气有限公司',]},
{name:'信阳', publicServiceList:['','','','','','',]},    
{name:'许昌', publicServiceList:['许昌市天伦燃气有限公司',]},
{name:'郑州', publicServiceList:['登封郑燃燃气有限公司','郑州华润燃气股份有限公司','郑州市上街区天伦燃气有限公司',]},
{name:'周口', publicServiceList:['太康县潜能天然气有限公司','周口市天然气有限公司',]},
]},
{name:'黑龙江', cityList:[           
{name:'哈尔滨', publicServiceList:['哈尔滨市中庆燃气有限公司',]}, 
{name:'佳木斯', publicServiceList:['佳木斯中燃',]},
]},
{name:'湖北', cityList:[           
{name:'荆门', publicServiceList:['沙洋华润燃气有限公司',]},  
{name:'荆州', publicServiceList:['荆州市天然气发展有限责任公司','监利天然气有限责任公司',]},
{name:'武汉', publicServiceList:['武汉市天然气有限公司','武汉市车都天然气有限公司',]},
{name:'仙桃', publicServiceList:['仙桃中油燃气有限责任公司',,]}, 
{name:'襄阳', publicServiceList:['襄阳华润燃气有限公司',]},
]},
{name:'湖南', cityList:[           
{name:'长沙', publicServiceList:['长沙华润燃气有限公司',]},
{name:'郴州', publicServiceList:['郴州华润燃气有限公司',]},  
{name:'衡阳', publicServiceList:['衡阳市天然气有限责任公司',]},
]},
{name:'江苏', cityList:[           
{name:'常州', publicServiceList:['常州港华燃气费','常州金坛港华燃气有限公司','常州新奥燃气发展有限公司',]},
{name:'南京', publicServiceList:['南京江宁华润燃气有限公司','南京市港华燃气公司（银行代缴）',]},
{name:'南通', publicServiceList:['海安新奥燃气有限公司','南通大众燃气有限公司（银行代缴）','启东华润燃气有限公司','如皋益有燃气有限公司',]},   
{name:'苏州', publicServiceList:['苏州华润燃气有限公司','苏州市燃气公司',]},
{name:'宿迁', publicServiceList:['沭阳华润燃气有限公司','宿迁华润燃气有限公司',]},   
{name:'泰州', publicServiceList:['泰州港华燃气有限公司',]},
{name:'无锡', publicServiceList:['无锡华润燃气有限公司',]},   
{name:'徐州', publicServiceList:['徐州港华燃气有限公司',]},
{name:'盐城', publicServiceList:['建湖中石油昆仑燃气有限公司',]},   
{name:'镇江', publicServiceList:['镇江华润燃气有限公司',]},
]},
{name:'江西', cityList:[           
{name:'赣州', publicServiceList:['赣县深燃天然气有限公司','赣州深燃天然气有限公司',]},
{name:'景德镇', publicServiceList:['景德镇深燃天然气有限公司',]}, 
{name:'九江', publicServiceList:['九江深燃天然气有限公司','九江县深燃天然气有限公司',]},
{name:'南昌', publicServiceList:['江西燃气公司（南昌）',]}, 
{name:'瑞金市', publicServiceList:['瑞金深燃天然气有限公司',]},
{name:'上饶', publicServiceList:['江西昌江燃气有限公司','江西省铅山深燃天然气有限公司',]},
{name:'新余', publicServiceList:['新余燃气有限公司',]},
{name:'宜春', publicServiceList:['江西深燃天然气有限公司','宜春深燃天然气有限公司',]},
{name:'鹰潭', publicServiceList:['贵溪华润燃气有限公司','鹰潭华润燃气有限公司',]},
]},
{name:'辽宁', cityList:[           
{name:'鞍山', publicServiceList:['鞍山市燃气总公司','海城华润燃气有限公司',]},
{name:'大连', publicServiceList:['大连市联合收费处',]},
{name:'盘锦', publicServiceList:['盘锦国华燃气有限公司',]},
{name:'沈阳', publicServiceList:['沈阳燃气股份有限公司','沈西燃气公司','沈南燃气公司','新北燃气公司',]},
{name:'营口', publicServiceList:['营口市燃气公司',]},
]},
{name:'宁夏', cityList:[           
{name:'银川', publicServiceList:['宁夏哈纳斯天然气公司',]},
]},
{name:'山东', cityList:[           
{name:'菏泽', publicServiceList:['成武县潜能燃气有限公司','东明万吉天然气实业有限公司','菏泽市广菏天然气有限公司','天伦曹县中天燃气',]},
{name:'济南', publicServiceList:['济华燃气','济南港华燃气有限公司','济南热电（供暖费）',]},  
{name:'济宁', publicServiceList:['济宁潜能燃气有限公司','金乡县潜能燃气有限公司',]},  
{name:'临沂', publicServiceList:['临沂中裕能源（覆盖经济开发区）','临沂中裕燃气有限公司（覆盖临沂兰山区）',]},  
{name:'青岛', publicServiceList:['青岛市公用事业收费服务中心(便民卡充值)',]},  
{name:'潍坊', publicServiceList:['安丘华润燃气有限公司','青州华润燃气有限公司','潍坊港华燃气有限公司','潍坊华润燃气有限公司',]},  
{name:'烟台', publicServiceList:['烟台新奥燃气发展有限公司',]},
]},
{name:'山西', cityList:[           
{name:'晋中', publicServiceList:['太原煤炭气化（集团）晋中燃气有限公司',]},
{name:'太原', publicServiceList:['太原煤炭气化(集团)有限责任公司（银行代缴）','太原天然气有限公司',]},   
]},
{name:'上海', cityList:[           
{name:'上海', publicServiceList:['上海大众燃气[户号]','上海燃气集团有限公司（条形码）','上海市青浦煤气管理所',]},
]},
{name:'四川', cityList:[           
{name:'成都', publicServiceList:['成都城市燃气有限责任公司','成都市温江区兴能天然气公司','成都天府新区华天兴能燃气公司','龙泉驿华油兴能天然气','青白江区博能燃气','四川联发天然气有限责任公司',]}, 
{name:'泸州', publicServiceList:['泸州华润燃气（缴费）','泸州华润燃气（预存）',]},
{name:'绵阳', publicServiceList:['绵阳燃气集团天然气分公司',]},
{name:'攀枝花', publicServiceList:['攀枝花华润燃气有限公司',]},
]},
{name:'天津', cityList:[           
{name:'天津', publicServiceList:['天津能源津安热电（采暖费）',]},
]},
{name:'云南', cityList:[           
{name:'昆明', publicServiceList:['云南中石油昆仑燃气有限公司','昆明华润燃气有限公司',]},
]},
{name:'浙江', cityList:[           
{name:'杭州', publicServiceList:['杭州天然气有限公司','杭州港华燃气有限公司','杭州钱江燃气','亚盛加气站','富阳华润燃气有限公司',]}, 
{name:'嘉兴', publicServiceList:['嘉兴市燃气集团有限公司',]}, 
{name:'宁波', publicServiceList:['宁波兴光燃气集团有限公司',]}, 
{name:'绍兴', publicServiceList:['绍兴柯桥中国轻纺城管道燃气有限公司','绍兴市燃气有限公司',]}, 
{name:'台州', publicServiceList:['台州中燃爱思开城市燃气发展有限公司',]}, 
{name:'温州', publicServiceList:['温州市燃气有限公司','乐清华润燃气有限公司',]}, 
]},
{name:'重庆', cityList:[           
{name:'重庆', publicServiceList:['中国燃气（重庆公司）','重庆川港燃气有限公司','重庆佳渝燃气','重庆凯源燃气','重庆潜能燃气股份有限公司','重庆市万州天然气公司','重庆市永川区石油天然气公司','重庆市渝川燃气有限责任公司',]}, 
]},
]},
{name:'水费' , provinceList:[
{name:'安徽', cityList:[           
{name:'安庆', publicServiceList:['潜山县自来水公司',]}, 
{name:'蚌埠', publicServiceList:['蚌埠自来水公司（代办）',]}, 
{name:'池州', publicServiceList:['池州市供水有限公司',]},
{name:'滁州', publicServiceList:['滁州市自来水公司',]}, 
{name:'阜阳', publicServiceList:['阜阳市供水总公司','颍上县自来水公司',]},
{name:'合肥', publicServiceList:['肥东县自来水厂','合肥供水集团有限公司','合肥龙岗自来水有限公司','庐江县供水有限责任公司',]}, 
{name:'淮南', publicServiceList:['淮南市自来水公司（代办）',]},
{name:'黄山', publicServiceList:['安徽省黄山市歙县自来水公司','黄山市自来水有限公司',]},
{name:'六安', publicServiceList:['安徽六安市自来水公司','六安市城南供水有限公司',]},
{name:'马鞍山', publicServiceList:['和县和州自来水有限公司','马鞍山首创水务有限责任公司','马鞍山首创水务有限责任公司（银行代缴）',]},
{name:'芜湖', publicServiceList:['芜湖华衍水务',]},
{name:'宣城', publicServiceList:['宁国水务有限公司',]},
]},
{name:'北京', cityList:[           
{name:'北京', publicServiceList:['北京市自来水集团（全部费用）','北京市自来水集团（按月缴纳）','北京大兴自来水（兴润水务）','北京房山良乡自来水（良泉水业）','北京通州自来水（潞洲水务）',]},
]},
{name:'福建', cityList:[           
{name:'福州', publicServiceList:['福州市自来水公司',]},
{name:'龙岩', publicServiceList:['龙岩水务集团有限公司',]},
{name:'南平', publicServiceList:['南平水务发展有限公司',]},
{name:'宁德', publicServiceList:['福鼎市自来水有限公司','福安市中闽水务有限责任公司',]},
{name:'莆田', publicServiceList:['莆田市自来水有限公司','莆田市湄洲湾自来水公司',]},
{name:'泉州', publicServiceList:['泉州市自来水有限公司','德化县自来水公司','福建省石狮供水股份有限公司','永春县自来水公司',]},
{name:'三明', publicServiceList:['福建省建宁县闽江源水有限公司','福建省水利投资集团（尤溪）水务有限公司',]},
{name:'厦门', publicServiceList:['厦门水务集团有限公司',]},
{name:'漳州', publicServiceList:['龙海城投水务（龙水自来水）','漳州发展水务集团有限公司',]},
]},
{name:'广东', cityList:[           
{name:'东莞', publicServiceList:['东莞市东江水务有限公司',]},
{name:'佛山', publicServiceList:['佛山市水业集团有限公司市区供水分公司','佛山市顺德区供水有限公司','瀚蓝环境股份有限公司',]},
{name:'广州', publicServiceList:['广州南沙粤海水务有限公司','广州市自来水公司',]},
{name:'河源', publicServiceList:['河源市水业集团发展有限公司',]}, 
{name:'惠州', publicServiceList:['惠州市供水有限公司',]},
{name:'江门', publicServiceList:['江门融浩水业股份有限公司','台山市自来水有限公司',]}, 
{name:'揭阳', publicServiceList:['揭阳市自来水公司',]},
{name:'茂名', publicServiceList:['茂名市水务投资集团有限公司',]}, 
{name:'汕头', publicServiceList:['汕头市澄海区自来水公司','汕头市自来水总公司',]},
{name:'汕尾', publicServiceList:['海丰县供水总公司','汕尾市供水总公司',]}, 
{name:'韶关', publicServiceList:['广东仁化银龙供水有限公司','韶关市自来水公司',]},
{name:'深圳', publicServiceList:['深圳水务集团',]}, 
{name:'阳江', publicServiceList:['阳春市自来水公司','阳江市自来水公司',]},
{name:'肇庆', publicServiceList:['肇庆肇水水务发展（端州）','肇庆高新区粤海水务有限公司','肇庆肇水水务发展（鼎湖）','肇庆肇水水务发展（封开）','肇庆肇水水务发展（广宁）','肇庆肇水水务发展（四会）',]}, 
{name:'中山', publicServiceList:['中山市供水有限公司（城区）','中山市供水有限公司（板芙）','中山市供水有限公司（东凤）','中山市供水有限公司（东升）','中山市供水有限公司（港口）','中山市供水有限公司（古镇）','中山市供水有限公司（三乡）','中山市供水有限公司（神湾）','中山市民众水务有限公司','中山市南头供水有限公司','中山市稔益供水有限公司(横栏镇)',]},
{name:'珠海', publicServiceList:['珠海水务集团有限公司',]}, 
]},
{name:'广西', cityList:[           
{name:'北海', publicServiceList:['广西北海市供水有限责任公司',]},
{name:'崇左', publicServiceList:['广西扶绥县自来水厂','宁明县水厂',]},   
{name:'桂林', publicServiceList:['桂林市自来水公司',]},
{name:'河池', publicServiceList:['大化瑶族自治县自来水公司','南丹县自来水公司',]},
{name:'柳州', publicServiceList:['柳州威立雅水务有限公司',]},
{name:'南宁', publicServiceList:['宾阳县自来水厂','广西绿城水务股份有限公司',]},
{name:'钦州', publicServiceList:['钦州市开投水务有限公司',]},
{name:'玉林', publicServiceList:['玉林市自来水公司',]},
]},
{name:'贵州', cityList:[           
{name:'安顺', publicServiceList:['镇宁布依族苗族自治县自来水公司',]},
{name:'贵阳', publicServiceList:['贵阳北控水务有限责任公司',]},   
{name:'六盘水', publicServiceList:['六盘水市红果经济开发区水资源开发有限公司',]},
{name:'黔东南州', publicServiceList:['贵州省施秉县供水公司','黄平县自来水公司','锦屏县自来水公司','凯里市自来水公司','榕江县自来水公司',]},  
{name:'黔西南州', publicServiceList:['册亨县自来水公司',]},
{name:'遵义', publicServiceList:['贵州水投水务绥阳有限责任公司','遵义市播州区供水公司','遵义市供排水有限责任公司',]},  
]},
{name:'海南', cityList:[           
{name:'三亚', publicServiceList:['三亚中法供水有限公司',]},
]},
{name:'河北', cityList:[           
{name:'保定', publicServiceList:['保定市供水总公司',]},
{name:'沧州', publicServiceList:['沧州市供水排水集团有限公司','黄骅市供水公司','南皮县自来水公司','任丘市城乡供水总公司',]}, 
{name:'承德', publicServiceList:['平泉县自来水公司',]},
{name:'廊坊', publicServiceList:['廊坊市清泉供水有限责任公司',]}, 
{name:'秦皇岛', publicServiceList:['秦皇岛北戴河供水总公司',]},
{name:'石家庄', publicServiceList:['石家庄自来水公司',]}, 
{name:'唐山', publicServiceList:['迁安市自来水公司',]},
{name:'邢台', publicServiceList:['任县自来水公司',]}, 
]},
{name:'河南', cityList:[           
{name:'鹤壁', publicServiceList:['鹤壁市城市水务（集团）有限责任公司',]}, 
{name:'焦作', publicServiceList:['焦作市水务有限责任公司',]},   
{name:'开封', publicServiceList:['开封市供水总公司','兰考良龙水务有限公司','通许县碧泉自来水有限公司','尉氏县自来水公司',]},
{name:'洛阳', publicServiceList:['洛阳北控水务集团有限公司',]},    
{name:'漯河', publicServiceList:['漯河市清源供水有限公司',]}, 
{name:'南阳', publicServiceList:['北控南阳水务集团','内乡县自来水公司','西峡县自来水公司',]},
{name:'平顶山', publicServiceList:['平顶山市自来水有限公司','汝州市自来水公司','舞钢市天源水务有限责任公司',]}, 
{name:'濮阳', publicServiceList:['濮阳市自来水公司',]},    
{name:'三门峡', publicServiceList:['卢氏县自来水公司',]}, 
{name:'商丘', publicServiceList:['民权县水务公司',]},     
{name:'新乡', publicServiceList:['新乡首创水务有限责任公司',]},
{name:'信阳', publicServiceList:['信阳市供水集团有限公司',]},    
{name:'许昌', publicServiceList:['许昌瑞贝卡水业有限公司',]},
{name:'郑州', publicServiceList:['郑州自来水投资控股有限公司','新密市自来水公司','郑州高新供水有限责任公司',]},
{name:'周口', publicServiceList:['周口市银龙水务有限公司',]},
{name:'驻马店', publicServiceList:['驻马店市中业自来水有限公司',]},
]},
{name:'黑龙江', cityList:[           
{name:'哈尔滨', publicServiceList:['哈尔滨供水集团有限责任公司','哈尔滨市松北供排水有限公司',]}, 
]},
{name:'湖北', cityList:[     
{name:'鄂州', publicServiceList:['鄂州市水务集团',]}, 
{name:'恩施', publicServiceList:['恩施市自来水有限责任公司','来凤县自来水公司',]},                  
{name:'黄冈', publicServiceList:['黄冈市自来水公司',]}, 
{name:'黄石', publicServiceList:['大冶市水务集团公司','黄石市自来水公司',]},  
{name:'荆州', publicServiceList:['荆州水务集团有限公司','监利县自来水公司',]},
{name:'十堰', publicServiceList:['十堰市郧阳区供水有限公司',]},
{name:'武汉', publicServiceList:['武汉市水务公司','武汉长江供水实业股份有限公司','武汉市蔡甸区自来水公司','武汉市东西湖自来水公司',]},
{name:'襄樊', publicServiceList:['老河口市供水二公司',]}, 
{name:'襄阳', publicServiceList:['宜城天河供水公司','枣阳市供水总公司',]},
{name:'孝感', publicServiceList:['安陆市自来水公司','孝感自来水','云梦县自来水公司',]},
{name:'宜昌', publicServiceList:['宜昌桑德三峡水务有限公司','当阳市自来水公司','宜昌市鸦鹊岭自来水厂','宜都市供水总公司','枝江市金润源水务有限公司','秭归县自来水公司',]},
]},
{name:'湖南', cityList:[           
{name:'长沙', publicServiceList:['长沙供水有限公司','浏阳经济技术开发区水务有限公司','浏阳市自来水公司',]},
{name:'常德', publicServiceList:['常德市西洞庭泓利供水有限公司','常德市自来水公司','汉寿县自来水公司','津市市自来水公司有限责任公司','澧县自来水公司','临澧县自来水公司','石门县自来水公司',]}, 
{name:'郴州', publicServiceList:['桂东八面山水业有限责任公司','资兴市自来水公司',]},
{name:'衡阳', publicServiceList:['常宁市自来水公司','衡阳市南岳区水电站','衡阳市自来水有限公司','湖南省衡阳水利局','祁东县曹口堰供水公司','祁东县供水公司',]}, 
{name:'娄底', publicServiceList:['湖南省冷水江水费','新化县自来水',]},
{name:'邵阳', publicServiceList:['武冈市自来水公司',]}, 
{name:'湘潭', publicServiceList:['湘潭中环水务有限公司',]},
{name:'湘西', publicServiceList:['保靖县自来水公司','花垣县自来水公司','吉首八月湖水资源开发有限责任公司','吉首市城市供水总公司','龙山县城镇供水公司','泸溪县自来水公司',]}, 
{name:'益阳', publicServiceList:['益阳市大通湖区自来水公司',]},
{name:'永州', publicServiceList:['江永县自来水公司','祁阳县黎家坪综合供水公司','新田县自来水公司',]}, 
{name:'岳阳', publicServiceList:['岳阳市自来水公司','岳阳市自来水有限公司',]},
{name:'张家界', publicServiceList:['桑植县自来水公司','张家界市自来水公司',]}, 
{name:'株洲', publicServiceList:['攸县自来水公司','株洲市自来水有限责任公司',]},
]},
{name:'吉林', cityList:[           
{name:'白城', publicServiceList:['洮南市自来水有限责任公司',]},
{name:'长春', publicServiceList:['长春市自来水公司',]},
{name:'吉林', publicServiceList:['吉林市水务集团有限公司',]},
{name:'辽源', publicServiceList:['辽源市自来水公司',]},
{name:'延边州', publicServiceList:['敦化市供水有限责任公司',]},
]},
{name:'江苏', cityList:[           
{name:'常州', publicServiceList:['常州通用自来水有限公司','江河港武水务（常州）有限公司','金坛市自来水公司',]},
{name:'淮安', publicServiceList:['淮安自来水有限公司',]},
{name:'连云港', publicServiceList:['连云港市自来水有限责任公司','连云港市自来水有限责任公司（邮政）',]},
{name:'南京', publicServiceList:['南京江宁水务集团有限公司（银行代缴）','南京水务集团有限公司（银行代缴）','南京远古水业股份有限公司（大厂区自来水）','南京远古水业股份有限公司（六合区自来水）',]},
{name:'南通', publicServiceList:['海安县区域供水有限公司','南通市自来水公司','启东市吕四自来水厂','启东市自来水厂','如皋自来水有限公司','通州区自来水总公司',]},
{name:'苏州', publicServiceList:['苏州工业园区清源华衍水务有限公司','常熟中法水务有限公司','常熟中法水务有限公司（银行代收）','昆山经济技术开发区自来水有限公司','昆山市巴城自来水公司','昆山市巴城自来水公司（银行代缴）','昆山市花桥自来水厂（后付费）','昆山市花桥自来水厂（预付费）','昆山市自来水集团有限公司','昆山周市自来水厂','苏州高新区自来水有限公司（银行代缴）','苏州工业园区清源华衍水务有限公司（银行代缴）','苏州市吴中区自来水公司（银行代缴）','苏州市相城区自来水公司（银行代缴）','苏州自来水有限公司（银行代缴）','太仓市自来水有限公司','太仓市自来水有限公司（银行代缴）','吴江华衍水务有限公司','张家港市给排水公司',]},
{name:'宿迁', publicServiceList:['江苏深水水务有限公司','宿迁银控自来水有限公司','宿迁正源自来水有限公司',]},
{name:'泰州', publicServiceList:['泰州金州水务有限公司','江苏省泰兴市自来水公司','靖江市华汇供水有限公司','泰州市高港自来水有限公司','兴化市自来水总公司',]},
{name:'无锡', publicServiceList:['无锡市自来水有限公司(后付费)','无锡市自来水有限公司(预付费)','宜兴水务集团有限公司',]},
{name:'徐州', publicServiceList:['徐州市铜山区自来水公司',]},
{name:'盐城', publicServiceList:['射阳水务有限责任公司',]},
{name:'扬州', publicServiceList:['扬州自来水有限责任公司','扬州自来水有限责任公司（银行代缴）','江都自来水有限公司','扬州江源供水有限公司','仪征市水达供水有限公司',]},
{name:'镇江', publicServiceList:['丹阳水务集团有限公司','镇江市自来水公司',]},

]},
{name:'江西', cityList:[           
{name:'赣州', publicServiceList:['赣州水务集团有限责任公司','江西省于都县自来水公司',]},
{name:'吉安', publicServiceList:['吉安水务集团有限公司',]},
{name:'景德镇', publicServiceList:['江西省景德镇水务有限责任公司',]}, 
{name:'九江', publicServiceList:['九江市水务有限公司','九江县水务有限公司',]},
{name:'南昌', publicServiceList:['南昌水业','江西自来水公司（南昌）','南昌县供水有限责任公司',]}, 
{name:'上饶', publicServiceList:['余干县供水有限责任公司',]},
{name:'新余', publicServiceList:['江西自来水公司（新余）',]},
{name:'宜春', publicServiceList:['宜春市供水有限公司','丰城市供水有限责任公司','丰城市剑邑供水有限责任公司',]},
{name:'鹰潭', publicServiceList:['鹰潭市供水有限公司',]},
]},
{name:'辽宁', cityList:[           
{name:'鞍山', publicServiceList:['鞍山市自来水总公司',]},
{name:'本溪', publicServiceList:['本溪市自来水公司',]}, 
{name:'大连', publicServiceList:['大连市联合收费处',]},
{name:'丹东', publicServiceList:['丹东市自来水总公司',]},
{name:'沈阳', publicServiceList:['沈阳水务集团','沈阳胜科水务有限公司','沈阳水务集团（光大）',]}, 
]},
{name:'内蒙古', cityList:[           
{name:'包头', publicServiceList:['包头市供水总公司',]},
{name:'鄂尔多斯', publicServiceList:['鄂尔多斯市东胜区供水总公司（东胜区）',]},   
{name:'呼和浩特', publicServiceList:['呼和浩特市供排水有限责任公司',]},
]},
{name:'宁夏', cityList:[           
{name:'银川', publicServiceList:['银川中铁水务集团有限公司',]},
]},
{name:'山东', cityList:[           
{name:'德州', publicServiceList:['德州市供水总公司（营业处）','德州市供水总公司（开发区分公司）','德州市供水总公司（天衢营业处）','德州市供水总公司（运河营业处）',]},
{name:'菏泽', publicServiceList:['鄄城县自来水公司','郓城县自来水公司',]}, 
{name:'济南', publicServiceList:['济南水务集团有限公司','济南水务西城公司（户表）','济南水务西城公司（总表）',]},
{name:'莱芜', publicServiceList:['莱芜市自来水公司',]},
{name:'临沂', publicServiceList:['临沂实康水务有限公司',]},
{name:'青岛', publicServiceList:['青岛市公用事业收费服务中心(便民卡充值)','青岛西海岸公用事业集团水务有限公司',]},
{name:'泰安', publicServiceList:['宁阳县华龙水业阳光景园小区','宁阳县华龙水业有限公司','泰安市自来水有限公司',]},
{name:'潍坊', publicServiceList:['潍坊市自来水有限公司',]},
{name:'烟台', publicServiceList:['牟平区供水总公司','烟台经济开发区自来水有限公司','烟台市自来水有限公司','烟台水务清泉有限公司',]},
{name:'枣庄', publicServiceList:['枣庄市供水总公司',]},
{name:'淄博', publicServiceList:['淄博市自来水公司',]},
]},
{name:'山西', cityList:[           
{name:'长治', publicServiceList:['长治市供水总公司',]},
{name:'晋中', publicServiceList:['寿阳县自来水公司',]},
{name:'太原', publicServiceList:['太原市自来水公司（银行代缴）',]},
{name:'阳泉', publicServiceList:['平定县自来水公司（银行代缴）',]}, 
]},
{name:'陕西', cityList:[           
{name:'安康', publicServiceList:['岚皋县自来水公司',]},
{name:'汉中', publicServiceList:['镇巴县自来水公司',]}, 
{name:'西安', publicServiceList:['陕西西安阎良航城水务',]},
]},
{name:'上海', cityList:[           
{name:'上海', publicServiceList:['上海城投水务(集团)有限公司','上海浦东威立雅自来水有限公司','上海金山新城自来水有限公司','上海南汇自来水有限公司[条形码]','上海市奉贤自来水','上海市松江自来水公司','上海市嘉定自来水公司[条形码]','上海金山自来水有限公司[条形码]','上海浦东新区自来水有限公司','上海青浦自来水有限公司','上海市北宝山自来水有限公司','上海市松江西部自来水','上海市松江东部自来水','上海浦东新区自来水有限公司[条形码]','上海城投水务（集团）有限公司［条形码］','上海星火中法供水有限公司',]},
]},
{name:'四川', cityList:[           
{name:'成都', publicServiceList:['成都市自来水公司',]}, 
{name:'凉山', publicServiceList:['会东县自来水有限责任公司','盐源县自来水公司',]},
{name:'泸州', publicServiceList:['兴泸水务城市供水公司（普通用户）','兴泸水务城市供水公司（预存用户）','兴泸水务合江水业公司（普通用户）','兴泸水务合江水业公司（预存用户）','兴泸水务江南水业公司（普通用户）','兴泸水务江南水业公司（预存用户）','兴泸水务泸县分公司（普通用户）','兴泸水务泸县分公司（预存用户）','兴泸水务纳溪水业公司（普通用户）','兴泸水务纳溪水业公司（预存用户）',]},
{name:'绵阳', publicServiceList:['绵阳市水务（集团）有限公司',]},
{name:'南充', publicServiceList:['南部福康供水有限责任公司',]},
{name:'遂宁', publicServiceList:['遂宁市水费（四川明星电力股份有限公司）',]},
{name:'宜宾', publicServiceList:['宜宾市二次供水公司','宜宾市清源水务集团有限公司',]},
]},
{name:'天津', cityList:[           
{name:'天津', publicServiceList:['静海水务有限公司','天津泰达津联自来水有限公司',]},
]},
{name:'新疆', cityList:[           
{name:'乌鲁木齐', publicServiceList:['乌鲁木齐水业集团有限公司',]},
]},
{name:'云南', cityList:[           
{name:'大理', publicServiceList:['祥云县供排水有限责任公司',]},
{name:'昆明', publicServiceList:['昆明通用水务自来水有限公司','昆明通用水务自来水有限公司（银行渠道）','清源水务自来水公司（呈贡区）',]}, 
{name:'怒江傈僳族自治州', publicServiceList:['兰坪白族普米族自治县供排水有限责任公司',]},
{name:'曲靖', publicServiceList:['师宗自来水有限公司',]}, 
{name:'昭通', publicServiceList:['绥江县自来水厂',]},
]},
{name:'浙江', cityList:[           
{name:'杭州', publicServiceList:['杭州市水务控股集团有限公司','杭州余杭水务有限公司','杭州临安自来水有限公司','杭州萧山供水有限公司','杭州富阳水务有限公司','杭州滨江水务公司','淳安县水务有限公司',]}, 
{name:'湖州', publicServiceList:['湖州市水务集团有限公司','德清县水务有限公司','湖州市水务集团有限公司南浔分公司','浙江安吉水务有限公司','浙江长兴水务有限公司',]}, 
{name:'嘉兴', publicServiceList:['海宁市水务投资集团有限公司','海盐县天仙河自来水经营有限公司','嘉善县水务投资有限公司','嘉善县幽澜自来水有限公司','嘉兴市嘉源给排水有限公司','平湖市乍浦自来水厂','平湖市自来水有限公司','桐乡市凤栖自来水有限公司',]}, 
{name:'金华', publicServiceList:['金华市自来水公司','义乌市自来水有限公司','义乌市城西自来水有限公司','义乌市强胜自来水有限公司','义乌市卫星自来水有限公司',]}, 
{name:'丽水', publicServiceList:['龙泉市供排水有限责任公司',]}, 
{name:'宁波', publicServiceList:['宁波市自来水有限公司','慈溪市横河自来水厂','慈溪市自来水有限公司','宁波市奉化自来水总公司','宁海县供水有限公司','象山县石浦镇水务有限公司','象山县自来水有限公司','余姚市第二自来水有限公司','余姚市自来水有限公司',]}, 
{name:'衢州', publicServiceList:['江山市自来水厂','浙江衢州水业集团有限公司',]}, 
{name:'绍兴', publicServiceList:['绍兴柯桥供水有限公司','绍兴市上虞区供水有限公司','绍兴市自来水有限公司','新昌县沃洲自来水有限公司','浙江省嵊州市城市自来水有限公司','诸暨市自来水有限公司',]}, 
{name:'台州', publicServiceList:['临海市灵江水务集团有限公司','台州椒北供水有限公司','台州市路桥自来水有限公司','台州自来水有限公司','天台县自来水有限公司','温岭市供水有限公司','玉环县自来水有限公司','浙江黄岩自来水公司','浙江永安水务集团有限公司',]}, 
{name:'温州', publicServiceList:['温州市公用集团自来水公司','乐清市供水集团有限公司','瑞安市市区自来水有限公司','瑞安市集镇供水有限公司','瑞安市塘下供水有限公司','苍南县龙港水业有限公司','苍南县自来水有限公司','平阳县鳌江自来水分公司','平阳县公用事业投资有限公司水头自来水分公司','温州市洞头水务发展有限公司自来水分公司','温州市瓯海梧田街道自来水厂','永嘉县瓯北自来水有限公司','永嘉县上塘自来水有限公司',]}, 
]},
{name:'重庆', cityList:[           
{name:'重庆', publicServiceList:['重庆市二次供水公司','重庆市渝山水资源公司','长寿自来水','武隆县自来水公司','重庆蔡同水务有限公司','重庆东渝自来水','重庆凤华二次供水服务有限公司','重庆江东水务公司','重庆开洲水资源开发有限公司','重庆两江水务有限公司','重庆南城水务有限公司','重庆市大学城水务技术开发有限公司','重庆市兴源供水技术有限公司涂山自来水厂','重庆万盛自来水公司','重庆渝荣水务有限公司','重庆中法供水有限公司','重庆市合川区自来水公司','重庆渝南自来水公司','重庆巴南区道角供水公司',]},
{name:'重庆城区', publicServiceList:['重庆永川侨立水务','重庆市东部水务技术开发有限公司','重庆市万州区自来水公司',]}, 
]},
]}
];
  


/**
 * 检验表单
 * @return
 */
function checkSumbit(){
	if($("[name='accountNo']").val() == null || $("[name='accountNo']").val() === ""){
		$("#msgSpan").html(" 户号不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='amount']").val() == null || $("[name='amount']").val() === ""){
		$("#msgSpan").html("充值金额不能为空。");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='cmdWater']").val() == null || $("[name='cmdWater']").val() === ""){
		$("#msgSpan").html("水煤电缴费类型不为空");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='cmdProvince']").val() == null || $("[name='cmdProvince']").val() === ""){
		$("#msgSpan").html("省份不为空");
		$("#msgDiv").show();
		return false;
	}
	if($("[name='cmdCity']").val() == null || $("[name='cmdCity']").val() === ""){
		$("#msgSpan").html("城市不为空。");
		$("#msgDiv").show();
		return false;
	}
	
	if($("[name='cmdPublicService']").val() == null || $("[name='cmdPublicService']").val() === ""){
		$("#msgSpan").html("公共事业单位。");
		$("#msgDiv").show();
		return false;
	}
	return true;
}


$(document).ready(function() {
	//用户表数据加载
	waterRechargeTable = $('#waterRechargeTable').dataTable( {
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
		"sAjaxSource": mozi.contextPath +"/waterRechargeManage/selectWaterRechargeList.do",
		"sAjaxDataProp":"data",
		"aoColumns": [
			{ "sWidth":"8px","sClass":"","mDataProp": "checkTd" ,"sDefaultContent" : '<div id="uniform-undefined" class="checker"><span id="uniform-span"><input type="checkbox" class="checkboxes" value="1"/></span><div>',"sTitle" : ''},
			{ "sClass": "hidden-phone", 'sWidth':'20%', "mDataProp": "rechargeId" ,"sDefaultContent" : "","sTitle" : "充值ID"},
			{ "sClass": "hidden-phone", 'sWidth':'15%', "mDataProp": "accountNo" ,"sDefaultContent" : "","sTitle" : "户号"},
			{ "sClass": "hidden-phone", 'sWidth':'10%', "mDataProp": "amount" ,"sDefaultContent" : "","sTitle" : "充值金额"},
			{ "sClass": "hidden-phone", 'sWidth':'12%', "mDataProp": "cmdWater" ,"sDefaultContent" : "","sTitle" : "水煤电缴费类型"},
			{ "sClass": "hidden-phone", 'sWidth':'8%', "mDataProp": "cmdProvince" ,"sDefaultContent" : "","sTitle" : "省份"},
			{ "sClass": "hidden-phone", 'sWidth':'8%', "mDataProp": "cmdCity" ,"sDefaultContent" : "","sTitle" : "城市"},
			{ "sClass": "hidden-phone", 'sWidth':'25%', "mDataProp": "cmdPublicService" ,"sDefaultContent" : "","sTitle" : "公共事业单位"},
	
			]//$_GET['sColumns']将接收到aoColumns传递数据
	});
	
	$('#waterRechargeTable tbody ').on('click', 'tr', function () {
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