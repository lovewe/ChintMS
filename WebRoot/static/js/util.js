var util = util || {};

/**
 * 去字符串空格
 * 
 * @author 孙宇
 */
util.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, '');
};
util.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
};
util.rtrim = function(str) {
	return str.replace(/(\s*$)/g, '');
};

/**
 * 判断开始字符是否是XX
 * 
 * @author 孙宇
 */
util.startWith = function(source, str) {
	var reg = new RegExp("^" + str);
	return reg.test(source);
};
/**
 * 判断结束字符是否是XX
 * 
 * @author 孙宇
 */
util.endWith = function(source, str) {
	var reg = new RegExp(str + "$");
	return reg.test(source);
};

/**
 * iframe自适应高度
 * 
 * @author 孙宇
 * 
 * @param iframe
 */
util.autoIframeHeight = function(iframe) {
	iframe.style.height = iframe.contentWindow.document.body.scrollHeight + "px";
};

/**
 * 设置iframe高度
 * 
 * @author 孙宇
 * 
 * @param iframe
 */
util.setIframeHeight = function(iframe, height) {
	iframe.height = height;
};