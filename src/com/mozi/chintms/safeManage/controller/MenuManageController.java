package com.mozi.chintms.safeManage.controller;

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
import com.mozi.chintms.safeManage.model.Permission;
import com.mozi.chintms.safeManage.model.TreeBean;
import com.mozi.chintms.safeManage.service.MenuService;

/**
 * 角色管理
 * 
 * @author zlm
 * @version 1.0 
 * */
@Controller
@RequestMapping("/menuManage")
public class MenuManageController extends BaseController {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(MenuManageController.class);
	

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
				return "/safeManage/menuManage";
			}
		}
		return "/logout";
		
	}


	/**
	 * 添加菜单
	 * @param request	
	 * @param permBean	菜单bean
	 */
	@RequestMapping("/addMenu")
	@ResponseBody
	public Json addMenu(HttpServletRequest request, Permission permBean) {
		Json json = new Json();
		String errorMsg = null;
		try {
			String result = menuService.addMenu(permBean);
		} catch (Exception e) {
			logger.error("添加菜单异常:", e);
			errorMsg = "添加菜单异常，请联系管理员。";
		}
		if (errorMsg == null) {
			json.setMsg("添加成功。");
			json.setSuccess(true);
		} else {
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}
		return json;
	}

	
	/**
	 * 查询菜单
	 * @param request
	 */
	@RequestMapping("/queryMenuList")
	@ResponseBody
	public Map<String,Object> queryMenuList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Permission> permList = menuService.getMenuList();
		map.put("data", permList);
		return map;
	}
	
	/**
	 * 更新菜单
	 * @param request
	 * @param permBean
	 */
	@RequestMapping("/updateMenu")
	@ResponseBody
	public Json updateMenu(HttpServletRequest request,Permission permBean){
		Json json = new Json();
		String errorMsg = null;
		try{
			
			String result = menuService.updateMenu(permBean);
			if("false".equals(result)){
				errorMsg = "修改失败，请重试。";
			}
		}catch(Exception e){
			logger.error("修改菜单异常:",e);
			errorMsg = "修改菜单异常，请联系管理员。";
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
	 * 删除菜单
	 * @param request
	 * @param permBean
	 */
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public Json deleteMenu(HttpServletRequest request,String[] permId){
		Json json = new Json();
		String errorMsg = null;
		String permIds = request.getParameter("permId");
		if(permIds != null){
			permId = permIds.split(",");
		}
		List<Permission> permList2 = new ArrayList<Permission>();
		try{

			String result = menuService.deleteMenu(permId);
			if("false".equals(result)){
				errorMsg = "删除失败，请重试。";
			}else{
				errorMsg = "成功";
			}

		}catch(Exception e){
			logger.error(e);
			logger.error("删除菜单异常:",e);
			errorMsg = "删除菜单异常，请联系管理员。";
		}
		
		json.setMsg(errorMsg);
		json.setSuccess(false);
		
		return json;
	}
	
	/**
	 * 获得角色转为tree类型
	 * @param request	
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public List<TreeBean> getMenuTree(HttpServletRequest request) throws Exception {
		return menuService.getMenu2();
	}
	
	/**
	 * 通过角色Id获得对应的菜单权限
	 * @param request
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMenuByroleId")
	@ResponseBody
	public List<Permission> getMenuByroleId(HttpServletRequest request,String roleId) throws Exception {
		List<Permission> list = null;
		list = menuService.getMenusByRoleId(roleId);
		return list;
	}
	
	/**
	 * 通过用户名获得对应的菜单权限
	 * @param request
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMenuByUserName")
	@ResponseBody
	public List<Permission> getMenuByUserName(HttpServletRequest request,String userName) {
		List<Permission> permList = menuService.getMenulistByUser(userName,null);
		for(int i=0;i<permList.size();i++){
			Permission perm = permList.get(i);
			//如果文件路径为空，则排除
			
		}
		return permList;
	}
}
