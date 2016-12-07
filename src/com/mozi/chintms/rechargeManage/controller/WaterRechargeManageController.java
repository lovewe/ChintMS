package com.mozi.chintms.rechargeManage.controller;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.mozi.chintms.rechargeManage.model.WaterRecharge;
import com.mozi.chintms.rechargeManage.service.WaterRechargeService;


/**
 * 固话宽带充值管理
 * 
 * @author xzh
 * @version 1.0 
 * */
@Controller
@RequestMapping("/waterRechargeManage")
public class WaterRechargeManageController extends BaseController {

	@Autowired
	private WaterRechargeService waterRechargeService;
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(WaterRechargeManageController.class);
	

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
				return "/rechargeManage/waterRechargeManage";
			}
		}
		return "/logout";
	}

	/**
	 * 添加固话宽带充值
	 * @param request	
	 * @param waterRechargeBean	充值bean
	 */
	@RequestMapping("/insertWaterRecharge")
	@ResponseBody
	public Json insertWaterRecharge(HttpServletRequest request,WaterRecharge waterRechargeBean) {
		Json json = new Json();
		String errorMsg = null;
		try{
			String result = waterRechargeService.insertWaterRecharge(waterRechargeBean);
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
	 * 查询固话宽带充值
	 * @param request
	 */
	@RequestMapping("/selectWaterRechargeList")
	@ResponseBody
	public Map<String,Object> selectWaterRechargeList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		List<WaterRecharge> waterRechargeList = waterRechargeService.selectWaterRechargeList();
		map.put("data",waterRechargeList);
		return map;
	}
	
	/**
	 * 更新固话宽带充值
	 * @param request
	 * @param  waterRechargeBean
	 */
	@RequestMapping("/updateWaterRecharge")
	@ResponseBody
	public Json updateWaterRecharge(HttpServletRequest request,WaterRecharge waterRechargeBean){
		Json json = new Json();
		String errorMsg = null;
		try{
			
			String result = waterRechargeService.updateWaterRecharge(waterRechargeBean);
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
	 * 删除固话宽带充值
	 * @param request
	 * @param waterRechargeBean
	 */
	@RequestMapping("/deleteWaterRecharge")
	@ResponseBody
	public Json deleteWaterRecharge(HttpServletRequest request,String rechargeIds){
		Json json = new Json();
		String errorMsg = null;
		String[] rechargeId= null;
		if(rechargeIds != null){
			rechargeId = rechargeIds.split(",");
		}
		try{

			String result =waterRechargeService.deleteWaterRecharge(rechargeId);
			if("false".equals(result)){
				errorMsg = "删除失败，请重试。";
			}	}catch(Exception e){
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
		
	


