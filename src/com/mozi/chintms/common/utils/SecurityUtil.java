package com.mozi.chintms.common.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.mozi.chintms.common.SessionInfo;
import com.mozi.chintms.login.model.Module;

/**
 * 用于前台页面判断是否有权限的工具类
 * 
 * @author wuhanhua
 * 
 */
public class SecurityUtil   {
	private HttpSession session;

	public SecurityUtil(HttpSession session) {
		this.session = session;
	}

	/**
	 * 判断当前用户是否可以访问某资源
	 * 
	 * @param url
	 *            资源地址
	 * @return
	 */
	public boolean havePermission(String url) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
		List<Module> moduleList = sessionInfo.getModuleList();
		for (Module resource : moduleList) {
			if (StringUtils.equals(resource.getLinkURL(), url)) {// 如果有相同的，则代表当前用户可以访问这个资源
				return true;
			}
		}
		return false;
	}

}
