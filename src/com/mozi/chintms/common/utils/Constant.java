package com.mozi.chintms.common.utils;

/**
 * Constant.java是系统的常量接口。
 * 
 * @version 1.0
 * @author wuhanhua
 * 
 */
public interface Constant {

	/**
	 * 一天24小时的毫秒数
	 */
	public final static long DATE_24_TIME_MILLISECOND = 24 * 60 * 60 * 1000;

	/**
	 * 在控制器中保存当前登陆信息的session对象属性键
	 */
	public static final String CURRENT_SESSION_USER_KEY = "currentSessionUser";
	/**
	 * 在session中保存登录用户信息
	 */
	public static final String SESSION_LOGINUSER_KEY ="LOGINUSER_KEY";
	/**
	 * 是
	 */
	public static final String YES_CN = "是";

	/**
	 * 否
	 */
	public static final String NO_CN = "否";

	/**
	 * 是
	 */
	public static final char YES = 'Y';

	/**
	 * 否
	 */
	public static final char NO = 'N';

	/**
	 * 默认分隔符：,
	 */
	public static final String DEFAULT_SEPARATOR = ",";

	/**
	 * 分隔符：#
	 */
	public static final String SEPARATOR_POUND = "#";

	/**
	 * 文件路径分隔符：/
	 */
	public static final char SEPARATOR_PATH = '/';

	/**
	 * OK字符串
	 */
	public static final String OK = "ok";
	
	/**
	 * 用户类型 ：管理员
	 */
	public static final byte USER_TYPE_ADMIN = 1;
}
