package com.mozi.chintms.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mozi.chintms.common.utils.DateUtil;

/**
 * service父类<br>
 * 1、放置公用的方法 2、日志记录对象
 * 
 * @author wuhanhua
 */
public class BaseService {

	protected final Logger logger_sys = LoggerFactory.getLogger("sys_info");
	protected final Logger logger_err = LoggerFactory.getLogger("err_info");

	/**
	 * 获取当前时间，格式为"yyyyMMddHHmmss"
	 * 
	 * @return
	 */
	protected String getNowTime() {
		return DateUtil.getDateStr(new Date(), "yyyyMMddHHmmss");
	}
}
