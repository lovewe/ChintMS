package com.mozi.chintms.basicDataManage.controller;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.basicDataManage.model.RegionInfo;
import com.mozi.chintms.basicDataManage.service.BasicDataService;
import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.safeManage.model.Permission;
import com.mozi.chintms.safeManage.service.MenuService;

/**
 * 基础数据
 * 
 * @author wx
 * @version 1.0 
 * */
@Controller
@RequestMapping("/basicDataManage")
public class BasicDataController extends BaseController {

	@Autowired
	private BasicDataService basicDataService;
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(BasicDataController.class);
	

	/**
	 * 登录通过后，跳转至主页 main.jsp
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		if(userName != null){
			String user = (String)request.getSession().getAttribute(userName);
			if("true".equals(user)){
				return "/safeManage/menuManage";
			}
		}
		return "/logout";
	}

	/**
	 * 获取地域信息
	 */
	@RequestMapping("/getRegion")
	@ResponseBody
	public List<IdentityHashMap<String,Object>> getRegion(HttpServletRequest request) {

		return basicDataService.getRegion();

	}
}
