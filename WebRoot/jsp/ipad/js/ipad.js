$(function(){
	$("#paidShow div").click(function(){
		var attr = $(this).attr("val");
		$.ajax({
			url:basePath+'/show/setParam.do',
			data:{"param":attr},
			type:"POST",
			dataType:"json",
			success: function(data){
				
			},
			error:function(){
				
			}
		});
	});
})