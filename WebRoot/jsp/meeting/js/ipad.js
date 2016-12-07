$(function(){
	function rem(){
		if (window.innerWidth){
			winWidth = window.innerWidth;  
		}else if ((document.body) && (document.body.clientWidth)){
			winWidth = document.body.clientWidth;
		}  
		document.getElementsByTagName("html")[0].style.fontSize = winWidth * 0.029296875 +"px";
	}
	rem();	
	$(window).resize(function(){
		rem();	
	});
	/*拖动*/
	var downPosiX = 0;
	var downPosiY = 0;
	var nodePosiX = 0;
	var nodePosiY = 0;
	var imgSrc = "";
	var nodeText = "";
	var urlText = "";
	$("#listcanvas").on("touchstart",".listbox",function(e){
		downPosiX = e.originalEvent.touches[0].pageX;
		downPosiY = e.originalEvent.touches[0].pageY;
		nodePosiX = $(this).offset().left;
		nodePosiY = $(this).offset().top;
		imgSrc = $(this).children("img").attr("src");
		nodeText = $(this).children("span").text();
		urlText = $(this).attr("url");
		$("#moveclone").children("img").attr("src",imgSrc);
		$("#moveclone").children("span").text(nodeText);
		$("#moveclone").show().css({
			"top": nodePosiY,
			"left":	nodePosiX
		});
	});
	document.getElementsByTagName("body")[0].addEventListener("touchmove", function(e){
		var touch = e.touches[0];
		var movex = touch.pageX;
		var movey = touch.pageY;
		if(Math.abs(movex - downPosiX)<15 || Math.abs(movey - downPosiY)>15 ){
			$("#moveclone").css({
				"top":movey - downPosiY + nodePosiY,
				"left":movex - downPosiX + nodePosiX
			});
		}else{
			$("#moveclone").hide();
		}
	})
	document.getElementsByTagName("body")[0].addEventListener("touchend", function(e){
		$("#moveclone").hide();
		var touch = e.changedTouches[0];
		var endPosiX = touch.pageX;
		var endPosiY = touch.pageY;
		var framePosiX = $("#showframe").offset().left;
		var framePosiY = $("#showframe").offset().top;
		var frameWidth = $("#showframe").width();
		var frameHeight= $("#showframe").height();
		var boxWidth = 	$(".showbox:first").width();
		var boxHeight = $(".showbox:first").height();
		//判断区域定位
		if(endPosiX > framePosiX && endPosiX < (framePosiX + frameWidth) && endPosiY > framePosiY && endPosiY < (framePosiY + frameHeight))
		{
			$(".showbox").eq(parseInt((endPosiX - framePosiX)/boxWidth) + frameWidth / boxWidth * parseInt((endPosiY - framePosiY)/boxHeight)).children(".moveclone").remove();
			$(".showbox").eq(parseInt((endPosiX - framePosiX)/boxWidth) + frameWidth / boxWidth * parseInt((endPosiY - framePosiY)/boxHeight)).append("<div class='moveclone'>"+
											"<img src='"+imgSrc+"' alt=''>"+
											"<span>"+nodeText+"</span>"+
										"</div>");
			$.ajax({
				url:basePath+"/show/setReport.do",
				type:"POST",
				data:{"index":parseInt((endPosiX - framePosiX)/boxWidth) + frameWidth / boxWidth * parseInt((endPosiY - framePosiY)/boxHeight),"tableauUrl":urlText},
				dataType:"json",
				success: function(data){},
				error: function(er){}
			})
		}
	})
	/*切换滑动*/
	$(".larrow").click(function(){
		var nodeleft = parseInt($("#listcanvas").css("margin-left"));
		var distance = ($("#listcanvas").children(".listbox").width() + parseInt($("#listcanvas").children(".listbox").css("margin-left")))*4;
		if(nodeleft<0){
			$("#listcanvas").animate({
				"margin-left":nodeleft + distance
			},220);	
		}
	});
	$(".rarrow").click(function(){
		var nodeleft = parseInt($("#listcanvas").css("margin-left"));
		var distance = ($("#listcanvas").children(".listbox").width() + parseInt($("#listcanvas").children(".listbox").css("margin-left")))*4;
		if(0 - nodeleft < ($("#listcanvas").children(".listbox").length - 4)*($("#listcanvas").children(".listbox").width() + parseInt($("#listcanvas").children(".listbox").css("margin-left")))){
			$("#listcanvas").animate({
				"margin-left":nodeleft - distance
			},220);	
		}
	});
	$.ajax({
		url:basePath+"/show/showReportList.do",
		type:"POST",
		data:{"userName":"screenUser"},
		dataType:"json",
		success: function(data){
			for(var i=0;i<data.obj.length;i++){
				$("#listcanvas").append("<div class='listbox' url='"+basePath+data.obj[i].url+"'>"+
											"<img src='"+basePath+'/'+data.obj[i].imgFilePath+"' alt=''>"+
											"<span>"+data.obj[i].titleName+"</span>"+
										"</div>");
			}
		},
		error: function (er) {
			
		}
	})
	/*服务器存储数据刷新*/
	function refresh(){
		$.ajax({
			url:basePath+"/show/clearReports.do",
			type:"POST",
			dataType:"json",
			success: function(data){},
			error: function(er){}
		})
	}
	$(".refresh").click(function(){
		refresh();
		window.location.reload();
	});
})