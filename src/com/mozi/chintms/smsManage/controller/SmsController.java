package com.mozi.chintms.smsManage.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.safeManage.model.SafeUserInfo;
import com.mozi.chintms.safeManage.service.UserService;
import com.mozi.chintms.smsManage.model.PhoneInfo;
import com.mozi.chintms.smsManage.service.SmsService;


/**
 * 角色管理
 * 
 * @author wx
 * @version 1.0 
 * */
@Controller
@RequestMapping("/smsManage")
public class SmsController extends BaseController {

	@Autowired
	private SmsService smsService;
	@Autowired
	private UserService userService;
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(SmsController.class);
	
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
	 * 查询用户
	 * @param request
	 */
	@RequestMapping("/getSmsMessage")
	@ResponseBody
	public Json getSmsMessage(HttpServletRequest request,PhoneInfo info){
		String errorMsg = null;
		Json json = new Json();
		
		try {
			smsService.SetPhoneCache(info);
		} catch (Exception e) {
			errorMsg = "短信发送异常";
		}
		if (errorMsg == null) {
			json.setMsg("短信发送成功!");
			json.setObj("200");
			json.setSuccess(true);
		} else {
			json.setMsg(errorMsg);
			json.setObj("-201");
			json.setSuccess(false);
		}
		return json;
	}
	
	
}
