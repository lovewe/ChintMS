package com.mozi.chintms.rechargeManage.controller;

import java.util.ArrayList;
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
import com.mozi.chintms.common.utils.Constant;
import com.mozi.chintms.common.utils.FileOperateUtil;
import com.mozi.chintms.login.model.LoginUserInfo;
import com.mozi.chintms.rechargeManage.model.PhoneRecharge;
import com.mozi.chintms.rechargeManage.service.PhoneRechargeService;

/**
 * 充值管理
 * 
 * @author xzh
 * @version 1.0 
 * */
@Controller
@RequestMapping("/phoneRechargeManage")
public class PhoneRechargeManageController extends BaseController {

	@Autowired
	private PhoneRechargeService phoneRechargeService;
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(PhoneRechargeManageController.class);
	

	/**
	 * 登录通过后，跳转至主页 main.jsp
	 * @return
	 */
	
			@RequestMapping("/view")
			public String view(HttpServletRequest request) {
				
				String userName = request.getParameter("userName");
				if (userName != null) {
					LoginUserInfo user = (LoginUserInfo) request.getSession()
							.getAttribute(Constant.SESSION_LOGINUSER_KEY);
					if (user != null) {
						return "/rechargeManage/phoneRechargeManage";
					}
				}
				return "/logout";
			}
		
	
	/**
	 * 添加手机充值
	 * @param request	
	 * @param stateBean	充值bean
	 */
	@RequestMapping("/insertPhoneRecharge")
	@ResponseBody
	public Json insertPhoneRecharge(HttpServletRequest request,PhoneRecharge phoneRechargeBean) {
		Json json = new Json();
		String errorMsg = null;
		try{
			String result = phoneRechargeService.insertPhoneRecharge(phoneRechargeBean);
			if("false".equals(result)){
				errorMsg = "添加失败，请重试。";
			}
		}catch(Exception e){
			logger.error("添加充值异常:",e);
			errorMsg = "添加充值异常:"+e.getMessage();
		}
		if(errorMsg == null){
			json.setMsg("添加成功。");
			json.setSuccess(true);
		}else{
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}
		return json;
	}

	
	/**
	 * 查询充值
	 * @param request
	 */
	@RequestMapping("/selectPhoneRechargeList")
	@ResponseBody
	public Map<String,Object> selectPhoneRechargeList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		List<PhoneRecharge> phoneRechargeList = phoneRechargeService.selectPhoneRechargeList();
		map.put("data",phoneRechargeList);
		return map;
	}
	
	/**
	 * 更新充值
	 * @param request
	 * @param  phoneRechargeBean
	 */
	@RequestMapping("/updatePhoneRecharge")
	@ResponseBody
	public Json updatePhoneRecharge(HttpServletRequest request,PhoneRecharge phoneRechargeBean){
		Json json = new Json();
		String errorMsg = null;
		try{
			
			String result = phoneRechargeService.updatePhoneRecharge(phoneRechargeBean);
			if("false".equals(result)){
				errorMsg = "修改失败，请重试。";
			}
		}catch(Exception e){
			logger.error("修改充值异常:",e);
			errorMsg = "修改充值异常，请联系管理员。";
		}
		
		if(errorMsg == null){
			json.setMsg("修改成功。");
			json.setSuccess(true);
		}else{
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}
		
		return json;
	}
	
	/**
	 * 删除手机充值
	 * @param request
	 * @param phoneRechargeBean
	 */
	@RequestMapping("/deletePhoneRecharge")
	@ResponseBody
	public Json deletePhoneRecharge(HttpServletRequest request,String rechargeIds){
		Json json = new Json();
		String errorMsg = null;
		String[] rechargeId= null;
		if(rechargeIds != null){
			rechargeId = rechargeIds.split(",");
		}
		try{

			String result =phoneRechargeService.deletePhoneRecharge(rechargeId);
			if("false".equals(result)){
				errorMsg = "删除失败，请重试。";
			}
		}catch(Exception e){
			logger.error(e);
			logger.error("删除手机充值异常:",e);
			errorMsg = "删除手机充值异常，请联系管理员。";
		}
		
		if(errorMsg == null){
			json.setMsg("删除成功。");
			json.setSuccess(true);
		}else{
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}
		
		return json;
	}
	
}
