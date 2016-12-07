//添加cookie
function addCookie(name,value,expiresPath,expiresDay){ 
//	var cookieString=name+"="+escape(value); 
	//判断是否设置过期时间 
//	if(expiresDay>0){ 
//		var date=new Date(); 
//		date.setTime(date.getTime+expiresDay*24*3600*1000); 
//		cookieString=cookieString+"; expires="+date.toGMTString(); 
//	} 
//	document.cookie=cookieString; 
	$.cookie(name,escape(value),{path:expiresPath,expires:expiresDay})
}

//根据cookie名获取
function getCookie(name){ 
//	var strCookie=document.cookie; 
//	var arrCookie=strCookie.split("; "); 
//	for(var i=0;i<arrCookie.length;i++){ 
//	var arr=arrCookie[i].split("="); 
//	if(arr[0]==name)return arr[1]; 
//	} 
//	return ""; 
	return $.cookie(name);
}
//根据cookie名进行删除操作
function deleteCookie(name){ 
//	var date=new Date(); 
//	date.setTime(date.getTime()-10000); 
//	document.cookie=name+"=v; expires="+date.toGMTString(); 
	$.cookie(name,null)
}