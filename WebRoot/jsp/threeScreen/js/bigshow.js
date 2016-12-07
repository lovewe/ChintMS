$(function(){
	var ajaxFunction = function(){
		$.ajax({
			//url:"http://10.121.2.51:83/WebGetContent.aspx",
			url:basePath+"/show/getContent.do",
			type:"get",
			dataType:"json",
			//dataType:"jsonp",
			//jsonp:"callback",
			success:function(data){
				setTimeout(ajaxFunction,5000);
				switch(data)
				{
					case "Group":		//集团公司大屏版
						$("iframe").remove();
						for (var i=107;i<119;i++)
						{	
							$("body").append("<iframe src= '"+basePath+"/tableau/report.do?t=report"+i+"&userName=screenUser'></iframe>")
						}
					break;
					case "Stock":		//股份公司大屏版
						$("iframe").remove();
						for (var i=120;i<132;i++)
						{	
							$("body").append("<iframe src= '"+basePath+"/tableau/report.do?t=report"+i+"&userName=screenUser'></iframe>")
						}
					break;
				}
			},
			error:function (er) {
			   
			}
		})
	}
	ajaxFunction();	
}) 
