package com.mozi.chintms.common.exception;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常信息常量类
 * 
 * @author wuhanhua
 * 
 */
public class Exceptions {

	// 通用（100）====================================================================
	@ExpInfo(key = "0", value = "运行正确。")
	public static final int SUCCESS = 0;
	@ExpInfo(key = "100", value = "系统错误，请联系管理员")
	public static final int ERROR_SYS = 100;
	@ExpInfo(key = "101", value = "数据库异常，请联系管理员")
	public static final int ERROR_DB = 101;
	@ExpInfo(key = "102", value = "导出excel失败，请联系管理员")
	public static final int ERROR_EXPORT = 102;
	@ExpInfo(key = "103", value = "不允许操作，请先登录")
	public static final int ERROR_NOT_LOGIN = 103;
	@ExpInfo(key = "104", value = "您无权操作，请联系管理员")
	public static final int ERROR_NO_RIGHT = 104;
	@ExpInfo(key = "105", value = "文件格式不正确，请联系管理员")
	public static final int ERROR_FILE_FORMAT = 105;
	@ExpInfo(key = "106", value = "文件太大，不能超过{0}")
	public static final int ERROR_FILE_TOOBIG = 106;
	@ExpInfo(key = "107", value = "最多只能上传{0}条数据，请重新选择上传文件")
	public static final int ERROR_FILE_MAXNUM = 107;
	@ExpInfo(key = "1000", value = "{0}")
	public static final int ERROR_USER_DEFINE = 1000;

	// 客户管理（30000）====================================================================
	@ExpInfo(key = "30001", value = "无此客户")
	public static final int ERROR_CUSTOMER_NOTFOUND = 30001;

	/** 异常map */
	private Map<String, String> exps = new HashMap<String, String>();

	/** 单例实现 */
	private static Exceptions ce = null;

	private Exceptions() {
	}

	public synchronized static Exceptions getInstance() {
		if (ce == null)
			ce = new Exceptions();
		// 支持热部署
		ce.exps.clear();
		/** 构造异常map */
		for (Field f : Exceptions.class.getDeclaredFields()) {
			ExpInfo expInfo = f.getAnnotation(ExpInfo.class);
			if (expInfo == null) {
				continue;
			}
			ce.exps.put(expInfo.key(), expInfo.value());
		}
		return ce;
	}

	/**
	 * 获得异常信息
	 * 
	 * @param key
	 * @return
	 */
	public String getExpInfo(String key) {
		return exps.get(key);
	}

	/**
	 * 打印测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Exceptions.getInstance().exps.toString().replace("{", "").replace("}", "").replaceAll(", ", "\r\n"));
	}
}
