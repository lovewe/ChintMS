
/**
 * 跳转到付款页面
 * 
 */
function tempSubmit(opt,val){
	if(!checkSumbit()){return;}
	
	var tempForm = document.createElement("form");
	
	tempForm.action =  mozi.contextPath +"/wideInpour/wide.do";;
    tempForm.method = "post";
    tempForm.style.display = "none";
    var opt1 = document.createElement("textarea");
    opt1.name = "wideNo";
    opt1.value = $("[name='wideNo']").val();
    var opt2 = document.createElement("textarea");
    opt2.name = "amount";
    opt2.value = $("[name='amount']").val();
    tempForm.appendChild(opt1);
    tempForm.appendChild(opt2);
    var opt3 = document.createElement("textarea");
    opt3.name = "cmdProvince";
    opt3.value = $("[name='cmdProvince']").val();
    tempForm.appendChild(opt3);
    var opt4 = document.createElement("textarea");
    opt4.name = "cmdCity";
    opt4.value =$("[name='cmdCity']").val();
    tempForm.appendChild(opt4);
    var opt5 = document.createElement("textarea");
    opt5.name = "cmdPublicService";
    opt5.value = $("[name='cmdPublicService']").val();
    tempForm.appendChild(opt5);
    document.body.appendChild(tempForm);
    tempForm.submit();
    
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
{name:'海南', cityList:[           
{name:'白沙', publicServiceList:['中国电信海南分公司(白沙宽带)',]}, 
{name:'保亭', publicServiceList:['中国电信海南分公司(保亭宽带)',]}, 
{name:'昌江', publicServiceList:['中国电信海南分公司(昌江宽带)',]}, 
{name:'儋县', publicServiceList:['中国电信海南分公司(儋县宽带)',]}, 
{name:'东方', publicServiceList:['中国电信海南分公司(东方宽带)',]}, 
{name:'海口', publicServiceList:['中国电信海南分公司(海口宽带)',]}, 
{name:'海南全省', publicServiceList:['中国电信海南分公司(固话)','中国电信海南分公司(宽带)',]}, 
{name:'临高', publicServiceList:['中国电信海南分公司(临高宽带)',]}, 
{name:'陵水', publicServiceList:['中国电信海南分公司(陵水宽带)',]}, 
{name:'琼海', publicServiceList:['中国电信海南分公司(琼海宽带)',]}, 
{name:'琼中', publicServiceList:['中国电信海南分公司(琼中宽带)',]}, 
{name:'三亚', publicServiceList:['中国电信海南分公司(三亚宽带)',]}, 
{name:'屯昌', publicServiceList:['中国电信海南分公司(屯昌宽带)',]}, 
{name:'万宁', publicServiceList:['中国电信海南分公司(万宁宽带)',]}, 
{name:'五指山', publicServiceList:['中国电信海南分公司(五指山宽带)',]}, 
]},
{name:'河南', cityList:[           
{name:'安阳', publicServiceList:['河南省电信公司(安阳)',]}, 
{name:'鹤壁', publicServiceList:['河南省电信公司(鹤壁)',]}, 
{name:'济源', publicServiceList:['河南省电信公司(济源)',]}, 
{name:'焦作', publicServiceList:['河南省电信公司(焦作)',]},     
{name:'开封', publicServiceList:['河南省电信公司(开封)',]}, 
{name:'洛阳', publicServiceList:['河南省电信公司(洛阳)',]},     
{name:'漯河', publicServiceList:['河南省电信公司(漯河)',]}, 
{name:'南阳', publicServiceList:['河南省电信公司(南阳)',]},     
{name:'平顶山', publicServiceList:['平煤集团信通公司固话','平煤集团信通公司宽带',]}, 
{name:'濮阳', publicServiceList:['河南省电信公司(濮阳)',]},     
{name:'三门峡', publicServiceList:['河南省电信公司(三门峡)',]}, 
{name:'商丘', publicServiceList:['河南省电信公司(商丘)',]},     
{name:'新乡', publicServiceList:['河南省电信公司(新乡)',]}, 
{name:'信阳', publicServiceList:['河南省电信公司(信阳)',]},     
{name:'许昌', publicServiceList:['河南省电信公司(许昌)',]}, 
{name:'周口', publicServiceList:['河南省电信公司(周口)',]}, 
{name:'驻马店', publicServiceList:['河南省电信公司(驻马店)',]},
]},
{name:'黑龙江', cityList:[           
{name:'大庆', publicServiceList:['电信黑龙江省分公司（大庆固话）','电信黑龙江省分公司（大庆宽带）',]}, 
{name:'黑龙江全省', publicServiceList:['（固话）中国电信股份有限公司','（宽带）中国电信股份有限公司',]}, 
]},
{name:'湖北', cityList:[           
{name:'黄冈', publicServiceList:['湖北广电网络宽带费',]}, 
{name:'荆门', publicServiceList:['湖北广电网络宽带费',]},  
{name:'荆州', publicServiceList:['湖北广电网络宽带费',]}, 
{name:'潜江', publicServiceList:['湖北广电网络宽带费',]},  
{name:'神农架林区', publicServiceList:['湖北广电网络宽带费',]}, 
{name:'十堰', publicServiceList:['湖北广电网络宽带费',]},  
{name:'随州', publicServiceList:['湖北广电网络宽带费',]}, 
{name:'天门', publicServiceList:['湖北广电网络宽带费',]},  
{name:'武汉', publicServiceList:['湖北广电网络宽带费',]}, 
{name:'仙桃', publicServiceList:['湖北广电网络宽带费',]},  
{name:'咸宁', publicServiceList:['湖北广电网络宽带费',]}, 
{name:'襄阳', publicServiceList:['湖北广电网络宽带费',]},  
{name:'宜昌', publicServiceList:['湖北广电网络宽带费',]}, 
]},
{name:'江苏', cityList:[           
{name:'苏州', publicServiceList:['苏州电信公司','苏州市电信公司',]}, 
]},
{name:'江西', cityList:[           
{name:'抚州', publicServiceList:['江西电信（抚州ADSL宽带）','江西电信（抚州绑定号码宽带）','江西新联通（抚州ADSL宽带）','江西新联通（抚州LAN宽带）','江西新联通（抚州固话）',]}, 
{name:'赣州', publicServiceList:['江西电信（赣州ADSL宽带）','江西电信（赣州LAN宽带）','江西电信（赣州绑定号码宽带）','江西电信（赣州固话）','江西新联通（赣州ADSL宽带）','江西新联通（赣州LAN宽带）','江西新联通（赣州固话）',]}, 
{name:'吉安', publicServiceList:['江西电信（吉安ADSL宽带）','江西电信（吉安LAN宽带）','江西电信（吉安绑定号码宽带）','江西电信（吉安固话）','江西新联通（吉安ADSL宽带）','江西新联通（吉安LAN宽带）','江西新联通（吉安固话）',]}, 
{name:'景德镇', publicServiceList:['江西电信（景德镇LAN宽带）','江西电信（景德镇绑定号码宽带）','江西电信（景德镇固话）','江西新联通（景德镇ADSL宽带）','江西新联通（景德镇LAN宽带）','江西新联通（景德镇固话）','','',]},  
{name:'九江', publicServiceList:['江西电信（九江ADSL宽带）','江西电信（九江LAN宽带）','江西电信（九江绑定号码宽带）','江西电信（九江固话）','江西新联通（九江ADSL宽带）','江西新联通（九江LAN宽带）','江西新联通（九江固话）',]}, 
{name:'南昌', publicServiceList:['江西电信（南昌ADSL宽带）','江西电信（南昌LAN宽带）','江西电信（南昌绑定号码宽带）','江西电信（南昌固话）','江西新联通（南昌ADSL宽带）','江西新联通（南昌LAN宽带）','江西新联通（南昌固话）',]},  
{name:'萍乡', publicServiceList:['江西电信（萍乡ADSL宽带）','江西电信（萍乡LAN宽带）','江西电信（萍乡绑定号码宽带）','江西电信（萍乡固话）','江西新联通（萍乡ADSL宽带）','江西新联通（萍乡LAN宽带）','江西新联通（萍乡固话）',]}, 
{name:'上饶', publicServiceList:['江西电信（上饶ADSL宽带）','江西电信（上饶LAN宽带）','江西电信（上饶绑定号码宽带）','江西新联通（上饶ADSL宽带）','江西新联通（上饶LAN宽带）',]},  
{name:'新余', publicServiceList:['江西电信（新余ADSL宽带）','江西电信（新余LAN宽带）','江西电信（新余绑定号码宽带）','江西新联通（新余LAN宽带）',]}, 
{name:'宜春', publicServiceList:['江西电信（宜春ADSL宽带）','江西电信（宜春LAN宽带）','江西电信（宜春绑定号码宽带）','江西新联通（宜春ADSL宽带）','江西新联通（宜春LAN宽带）','江西新联通（宜春固话）',]},  
{name:'鹰潭', publicServiceList:['江西电信（鹰潭ADSL宽带）','江西电信（鹰潭LAN宽带）','江西电信（鹰潭绑定号码宽带）','江西电信（鹰潭固话）','江西新联通（鹰潭ADSL宽带）','江西新联通（鹰潭固话）',]},  
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
	
	if($("[name='cmdPublicService']").val() == null || $("[name='cmdPublicService']").val() === ""){
		$("#msgSpan").html("公共事业单位。");
		$("#msgDiv").show();
		return false;
	}
	return true;
}


