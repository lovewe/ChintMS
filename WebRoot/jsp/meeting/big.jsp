<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = (String)request.getAttribute("userName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>显示页面</title>
		<meta name="generator" content="editplus" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="keywords" content="选项一,选项二,选项三" />
		<meta http-equiv="description" content="选项" />
		<link rel="stylesheet" type="text/css" href="css/ipad.css" />
		<script type="text/javascript">
			var basePath ='<%=basePath%>';
			var userName ='screenUser';
		</script>
	</head>
	<body>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<iframe src=""></iframe>
		<script type="text/javascript" src="js/jquery_1.8.3_min.js"></script>
		<script type="text/javascript">
			/*
			var a =[1,2,3,4,5,6];
			var b = a.slice(0);
			delete b[1]
			console.log(a);	
			console.log(b);	
			*/
			/*模拟登陆*/
			$.ajax({
				url:basePath+"login/simulationLogin.do",
				type:"POST",
				data:{"user_name":userName,"password":"e10adc3949ba59abbe56e057f20f883e"},
				dataType:"json",
				success: function(data){				},
				error: function (er) {
					
				}
			})
			
		
			var oldData = new Object();
			var copyData = new Array(); //复制数组进行比对操作
			var pushData = new Array(); //定义新数组存储比对后的数据用来填充渲染界面
			var kept;
			setInterval(function(){
				$.ajax({
					url:basePath+"show/getReports.do",
					type:"GET",
					dataType:"json",
					success: function(data){
						var newData = data.obj;
						pushData = [];
						copyData = [];
						copyData = newData.slice(0); //定义复制数组
						/*判断页面是否已经刷新*/
						if(newData.length < 1){
							$("iframe").attr("src","");
						}else if(JSON.stringify(newData) == JSON.stringify(oldData)){
							
						}else{
							if(typeof oldData.length == "undefined"){	//判断存储的旧数据是否为空
								for(var a=0;a<newData.length;a++){
									$("iframe").eq(newData[a].index).attr("src",newData[a].url+"&userName="+userName);
								}
								oldData = newData;
							}else if(newData.length>oldData.length){	//判断新数据是否大于旧数据
								for(var b=0;b<newData.length;b++){
									for(var c=0;c<oldData.length;c++){
										if(newData[b].index == oldData[c].index && newData[b].url == oldData[c].url){
											delete copyData[b];		
										}
									}
								}
									
								for(var d=0;d<copyData.length;d++){		//对复制数组操作后进行过滤处理，生成实际有用的填充数据数组
									if(typeof copyData[d]!="undefined"){
										pushData.push(copyData[d]);	
									}
								}
								for(var e=0;e<pushData.length;e++){		//填充对比后的数据
									$("iframe").eq(pushData[e].index).attr("src",pushData[e].url+"&userName="+userName);	
								}
								oldData = newData;
							}else if(newData.length<=oldData.length){	//此处为记录数组索引，非当前索引清空页面iframe，减少对dom的操作
								for(var f=0;f<newData.length;f++){
									for(var g=0;g<oldData.length;g++){
										if(newData[f].index == oldData[g].index && newData[f].url == oldData[g].url){
											kept = newData[f].index;
										}
									}
								}
								for(var h=0;h<oldData.length;h++){
									if(oldData[h].index!=kept){
										$("iframe").eq(oldData[h].index).attr("src","");
									}
								}	
								for(var i=0;i<newData.length;i++){
									$("iframe").eq(newData[i].index).attr("src",newData[i].url+"&userName="+userName);		
								}
								oldData = newData;
							}
						}
					},
					error: function (er) {
						
					}
				})
			},5000);
			
		</script>
	</body>
</html>
