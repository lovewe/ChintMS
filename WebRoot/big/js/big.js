$(function(){
	/*左侧菜单图片展示*/
	$.ajax({
		url:basePath+"/menuManage/getMenuByUserName.do",
		type:"GET",
		data:{"userName":userName},
		dataType:"json",
		success: function(data){
			for(var i=0;i<data.length;i++){
				$("#canvas").append("<div class='tab'  tableauUrl='"+TbasePath+data[i].url+"&userName="+userName+"'>"+				 
										"<div class='show'>"+
											"<img src='"+basePath+data[i].imgFilePath+"' alt=''>"+
											"<span>"+data[i].permName+"</span>"+
										"</div>"+
									"</div>");
			}
		},
		error: function (er) {
			alert("请求超时，请重试~！");	
		}
	})
	/*左侧菜单收缩特效*/
	var tabHeight = parseInt($("#canvas .tab:first").height());
	var tabHMb = tabHeight+parseInt($("#canvas .tab:first").css("margin-bottom")); 
	var canvasWidth = parseInt($("#canvas").outerWidth()) + parseInt($(".simulation").outerWidth());
	$(".up").click(function(){
		$("#canvas .tab:first").animate({
			"margin-top":-tabHeight	
		},function(){
			$("#canvas .tab:first").appendTo($("#canvas")).css("margin-top",0);	
		})
	});
	$(".down").click(function(){
		$("#canvas .tab:last").insertBefore($("#canvas .tab:first")).css("margin-top",-tabHMb).animate({
			"margin-top":0	
		})
	});
	var showimg = true;
	$(".label").click(function(){
		if (parseInt($("#menu").css("left"))<0)
		{
			$("#menu").animate({
				"left":0
			})
			showimg = false;
		}else{
			$("#menu").animate({
				"left":-canvasWidth
			},function(){
				$(".control").animate({"opacity":0},500);
				showimg = true;
			})
		}
	});
	$(".control").mouseover(function(){
		if($(this).css("opacity") == 0){
			$(this).animate({"opacity":1},300);
		}
	}).mouseleave(function(){
		if(parseInt($("#menu").css("left")) < 0&&showimg){
			$(this).animate({"opacity":0},500);
		}
	});
	/*功能逻辑区域*/
	var width = 7680;//$(window).width();
	var height = 3240; $(window).height();
	$(".tbox").width(width/4);
	$(".tbox").height(height/3);
	var boxTop = $("#menu").offset().top;
	var boxLeft = $(".tab").offset().left;
	var isMouseDown = false;
	var mouseDownPosiX = 0;
	var mouseDownPosiY = 0;
	var nodeDownPosiX = 0;
	var nodeDownPosiY = 0;
	var thisNo = 0;
	var tableauUrl = "";
	/*模拟屏幕区*/
	var simulationLeft = $(".simulation").offset().left;								 //定义模拟显示区左部坐标
	var simulationRight = simulationLeft + parseInt($(".simulation").outerWidth()); 	//定义模拟显示区右部坐标
	var simulationBottom = boxTop + parseInt($(".simulation").outerHeight());	//定义模拟显示区底部坐标
	var imgsrc = "";
	var showboxHeight = $(".simulation .showbox:first").outerHeight();
	$("#menu").on("mousedown",".tab",function(event){
		var shownodeDownPosiX = $(this).offset().left;
		var shownodeDownPosiY = $(this).offset().top-1;
		imgsrc = $(this).find("img").attr("src");
		$("#menu").append("<div class='move' style='top:"+(shownodeDownPosiY-boxTop)+"px;left:"+(shownodeDownPosiX-boxLeft)+"px'>"+
							"<img src='"+$(this).find("img").attr("src")+"' alt=''>"+
							"<span>"+$(this).find("span").text()+"</span>"+	
						"</div>")
		mouseDownPosiX = event.pageX;
		mouseDownPosiY = event.pageY;
		nodeDownPosiX = shownodeDownPosiX;
		nodeDownPosiY = shownodeDownPosiY;
		thisNo = parseInt($(this).attr("No"));
		tableauUrl = $(this).attr("tableauUrl");
		isMouseDown = true;
	})
	$(document).mousemove(function(event){
		if (isMouseDown)
		{
			var nodeMovePosiX = event.pageX - mouseDownPosiX  +  nodeDownPosiX;
			var nodeMovePosiY = event.pageY - mouseDownPosiY  +  nodeDownPosiY;
			$(".move").css({
							"left":nodeMovePosiX,
							"top":nodeMovePosiY
						});	
			if (event.pageX>simulationLeft && event.pageX<simulationRight && event.pageY>boxTop && event.pageY<simulationBottom)	 //模拟显示区定位
			{
				$(".showbox").removeClass("cur");
				$(".showbox").eq(parseInt((event.pageY - boxTop)/showboxHeight)).addClass("cur");
				$(".tbox").removeClass("cur");
				$(".tbox").eq(parseInt((event.pageY - boxTop)/showboxHeight)).addClass("cur");
			}else{
				$(".showbox").removeClass("cur");
			}
		}	
	}).mouseup(function(event){
		if(isMouseDown){
			isMouseDown = false;	
			$(".move").remove();
			if (event.pageX>simulationLeft && event.pageX<simulationRight && event.pageY>boxTop && event.pageY<simulationBottom)	 //模拟显示区定位
			{
				$(".showbox").eq(parseInt((event.pageY - boxTop)/showboxHeight)).find("img").remove();
				$(".showbox").eq(parseInt((event.pageY - boxTop)/showboxHeight)).append("<img src='"+imgsrc+"' />");
				$(".showbox").removeClass("cur");
				$(".tbox").eq(parseInt((event.pageY - boxTop)/showboxHeight)).html("").removeClass("cur").append("<div class='loading-80'></div>");
				bigFunction(thisNo,parseInt((event.pageY - boxTop)/showboxHeight),tableauUrl);
			}
		}
	}).bind("selectstart",function(){
		return false;
	}).bind("dragstart",function(){
		return false;
	});
	//No 图表序列号
	//tableauUrl tableau引入链接地址
	//position 绘画区域索引定位
	var bigFunction = function(No,position,tableauUrl){
		switch (No){
			case 1 :
					worldMap2D(position);
					break;
			default:
					tableau(position,tableauUrl);
		}	
	}
	/*图表绘画区域*/
	/*2D世界地图*/
	var openNum = true;
	var worldMap2D = function(position){
		var ajaxFunction = function(){
			$.ajax({
				url:"../big/worldMap2D.jsp",
				type:"GET",
				dataType:"text",
				success: function(data){
					$(".loading-80").remove();
					var tmp = $("<div/>")
					$(".tbox").eq(position).append(tmp);
					tmp[0].innerHTML=data
					if(openNum){
						$.getScript("big/ipviking.js",function(){
							console.log("js装载完毕，执行绘画~！")	
						});
						openNum = false;
					}else{
						location.reload();
					}
				},	
				error: function (er) {
					alert("请求超时，请重试~！");	
				}
			})
		}
		ajaxFunction();
	}
	/*tableau链接*/
	var tableau = function(position,tableauUrl){
		$(".loading-80").remove();
		$(".tbox").eq(position).append("<iframe src='"+tableauUrl+"'></iframe>");		
	}
})