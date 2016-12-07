package com.mozi.chintms.common.page;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL工具类
 * 
 * @author wuhanhua
 * */
public class SqlUtil {

	public static Map<Integer, Integer> getQuestionMark(String sql) {
		Map<Integer, Integer> position = new HashMap<Integer, Integer>();
		Integer i = 0;
		while (StringUtils.indexOf(sql, "?") >= 0) {
			position.put(i, StringUtils.indexOf(sql, "?"));
			i++;
			sql = StringUtils.replaceOnce(sql, "?", ".");
		}
		return position;
	}
}
