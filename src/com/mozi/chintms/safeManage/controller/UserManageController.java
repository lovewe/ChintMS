package com.mozi.chintms.safeManage.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import com.mozi.chintms.safeManage.model.Permission;
import com.mozi.chintms.safeManage.model.SafeUserInfo;
import com.mozi.chintms.safeManage.service.UserService;
import com.mozi.chintms.smsManage.model.PhoneInfo;
import com.mozi.chintms.smsManage.service.SmsService;
import com.mozi.chintms.smsManage.service.impl.SmsServiceImpl;

/**
 * 用户管理
 * 
 * @author zlm
 * @version 1.0 
 * */
@Controller
@RequestMapping("/userManage")
public class UserManageController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SmsService smsService;
	
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(UserManageController.class);
	

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
				return "/safeManage/userManage";
			}
		}
		return "/logout";
		
	}

	/**
	 * 添加用户
	 * @param request	
	 * @param userBean	用户bean
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public Json addUser(HttpServletRequest request,SafeUserInfo userBean) {
		Json json = new Json();
		String errorMsg = null;
		try{
			String result = userService.addUser(userBean);
			if("false".equals(result)){
				errorMsg = "添加失败，请重试。";
			}
		}catch(Exception e){
			logger.error("添加用户异常:",e);
			errorMsg = "添加用户异常:"+e.getMessage();
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
	 * 注册手机用户 
	 * @param request
	 */
	@RequestMapping("/addPhoneUser")
	@ResponseBody
	public Json addPhoneUser(HttpServletRequest request,PhoneInfo info){
		String errorMsg = null;
		Json json = new Json();
		String strVCode =null;
		String result = null;
		SafeUserInfo safuUserInfo = new SafeUserInfo();
		safuUserInfo.setPhoneNum(info.getPhoneNum());
		safuUserInfo.setPassword(info.getPassword());
		safuUserInfo.setUserName(info.getUserName());
		
		try {
			
			SafeUserInfo safeInfo = userService.selectUserByID(safuUserInfo);
			
			if(safeInfo!=null)
			{
				json.setMsg("手机号码已存在!");
				json.setSuccess(false);
				return json;
			}
			
//			strVCode = smsService.GetPhoneCache(info);
			strVCode ="123456";
			
			if(strVCode == null || strVCode.length() <= 0)
			{
				errorMsg = "验证码已过期";
			}else
			{
				if(info.getvCode().equals(strVCode))
				{
				 result =userService.addUser(safuUserInfo);
				}else
				{
					errorMsg = "验证码错误";
				}
			}

		} catch (Exception e) {
			errorMsg = "注册失败"+e.getMessage();
		}
		if (errorMsg == null) {
			json.setMsg("注册成功!");
			json.setSuccess(true);
			json.setObj(safuUserInfo.getPersonID());
		} else {
			json.setMsg("注册失败!");
			json.setObj(errorMsg);
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 根据用户ID查询用户
	 * @param request
	 */
	@RequestMapping("/selectUserByID")
	@ResponseBody
	public Map<String,Object> queryUserList(HttpServletRequest request,SafeUserInfo info){
		Map<String,Object> map = new HashMap<String,Object>();
		SafeUserInfo safeInfo = userService.selectPhoneUserByID(info);
		map.put("data", safeInfo);
		return map;
	}
	
	@RequestMapping("/selectPhoneByID")
	@ResponseBody
	public Json queryPhoneList(HttpServletRequest request,SafeUserInfo info){
		Json json = new Json();
		String errorMsg = null;
        Map<String,String> map = new HashMap<String,String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
		try {
			SafeUserInfo safeInfo = userService.selectPhoneUserByID(info);
			
			if(safeInfo==null)
			{
				json.setMsg("没有找到对应信息。");
				json.setObj(null);
				json.setSuccess(false);
				return json;
			}
			
			map.put("nickname", safeInfo.getNickname());
			map.put("personID", safeInfo.getPersonID());
			map.put("provinceName", safeInfo.getProvinceName());
			map.put("cityName", safeInfo.getCityName());
			map.put("phoneNum", safeInfo.getPhoneNum());
			map.put("gender", safeInfo.getGender());
			map.put("fullname", safeInfo.getFullname());
			map.put("fileName", safeInfo.getImgFilePath());
			
			if(safeInfo.getBirthday()!=null&&safeInfo.getBirthday().length()>0)
			{
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(safeInfo.getBirthday()); 
				map.put("birthday", format.format(date));
			}else
			{
				map.put("birthday", null);
			}

		} catch (Exception e) {
			logger.error("用户查询异常:", e);
			errorMsg = "用户查询异常:" + e;
		}

		if (errorMsg == null) {
			json.setMsg("用户查询成功。");
			json.setObj(map);
			json.setSuccess(true);
		} else {
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}

		return json;
	}
	
	
	/**
	 * 查询用户
	 * @param request
	 */
	@RequestMapping("/queryUserList")
	@ResponseBody
	public Map<String,Object> queryUserList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		List<SafeUserInfo> userList = userService.getUserList();
		map.put("data", userList);
		return map;
	}
	
	/**
	 * 更新用户
	 * @param request
	 * @param permBean
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public Json updateUser(HttpServletRequest request,SafeUserInfo UserBean){
		Json json = new Json();
		String errorMsg = null;
		String result  = null;
		
		try {

			result = userService.updateUser(UserBean);

		} catch (Exception e) {
			logger.error("修改用户异常:", e);
			errorMsg = "修改用户异常:" + e;

		}

		if (errorMsg == null) {
			json.setMsg("修改成功。");
			json.setSuccess(true);
		} else {
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}

		return json;
	}
	
	/**
	 * 更新用户
	 * @param request
	 * @param permBean
	 * @throws IOException 
	 */
	@RequestMapping("/userHead")
	@ResponseBody
	public Json updateUserHeadPic(HttpServletRequest request,SafeUserInfo UserBean) {
		Json json = new Json();
		String errorMsg = null;
		String filePath=null;
		SafeUserInfo safeInfo = null;
		try{
			init(request);
			//上传文件
			filePath = FileOperateUtil.upload(request);
			UserBean.setImgFilePath(filePath);
			
			if(filePath!=null){
				UserBean.setImgFilePath(filePath);
				//更新前先查询原文件路径
				safeInfo = userService.selectUserByID(UserBean);
			}
			//更新文件路径
			String result = userService.updateUser(UserBean);
			if("false".equals(result)){
				errorMsg = "修改失败，请重试。";
				if(filePath != null){
					FileOperateUtil.deleteFile(filePath);
				}
			}else{
				//更新成功,则删除原文件
				if(safeInfo != null && safeInfo.getImgFilePath() != null){
					FileOperateUtil.deleteFile(safeInfo.getImgFilePath());
				}
			}
		}catch(Exception e){
			logger.error("修改头像异常:",e);
			errorMsg = "修改头像异常，请联系管理员。";
			if(filePath != null){
				FileOperateUtil.deleteFile(filePath);
			}
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
	 * 删除用户
	 * @param request
	 * @param userId
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Json deleteUser(HttpServletRequest request,String userIds){
		Json json = new Json();
		String errorMsg = null;
		String[] userId  = null;
		
		if(userIds != null){
			userId = userIds.split(",");
		}
		try{
			String result = userService.deleteUser(userId);
			if("false".equals(result)){
				errorMsg = "删除失败，请重试。";
			}
		}catch(Exception e){
			logger.error(e);
			logger.error("删除用户异常:",e);
			errorMsg = "删除用户异常:"+e;
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
	
	/**
	 * 修改密码
	 * @param request
	 * @param userId
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Json updatePwd(HttpServletRequest request,SafeUserInfo UserBean){
		Json json = new Json();
		String errorMsg = null;
		
		try{
			String result = userService.updatePwd(UserBean);
			if("false".equals(result)){
				errorMsg = "修改密码失败，请确认原密码是否正确。";
			}
		}catch(Exception e){
			logger.error(e);
			logger.error("修改密码异常:",e);
			errorMsg = "修改密码异常:"+e;
		}
		
		if(errorMsg == null){
			json.setMsg("密码修改完成,请重新登录");
			request.getSession().removeAttribute(UserBean.getUserName());
			json.setSuccess(true);
		}else{
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}
		return json;
	}
	/**
	 * 设置文件保存路径
	 * @param request
	 */
	private void init(HttpServletRequest request) {
		if(FileOperateUtil.FILEDIR == null){
			FileOperateUtil.FILEDIR = request.getSession().getServletContext().getRealPath("/");
		}
	}
}
