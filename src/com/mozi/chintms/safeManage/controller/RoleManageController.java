package com.mozi.chintms.safeManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.common.utils.Constant;
import com.mozi.chintms.login.model.RoleInfo;
import com.mozi.chintms.login.model.LoginUserInfo;
import com.mozi.chintms.safeManage.model.TreeDto;
import com.mozi.chintms.safeManage.service.RoleService;

/**
 * 角色管理
 * 
 * @author zlm
 * @version 1.0 
 * */
@Controller
@RequestMapping("/roleManage")
public class RoleManageController extends BaseController {

	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(RoleManageController.class);
	
	@Autowired
	private RoleService roleService;
	

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
				return "/safeManage/roleManage";
			}
		}
		return "/logout";
	}

	/**
	 * 登录方法 login.do
	 * @param request	
	 * @param inputUser	前台用户beann
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Json logining(HttpServletRequest request, LoginUserInfo inputUser) throws Exception {
		return null;
	}
	
	/**
	 * 获得权限
	 * @param request	
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoles")
	@ResponseBody
	public Map<String,Object> getRoles(HttpServletRequest request) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<RoleInfo> list = roleService.getRoles();
		map.put("data", list);
		return map;
	}
	
	/**
	 * 根据用户ID获得权限
	 * @param request	
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoleByUserID")
	@ResponseBody
	public List<RoleInfo> getRoleByUserID(HttpServletRequest request,String userId) throws Exception {
		return roleService.getRolesByUserId(userId);
	}
	
	/**
	 * 添加权限
	 * @param request	
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertRole")
	@ResponseBody
	public int insertRole(HttpServletRequest request,RoleInfo role,String menuIds) throws Exception {
		return roleService.insertRole(role,menuIds);
	}
	
	/**
	 * 修改权限
	 * @param request	
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public int updateRole(HttpServletRequest request,RoleInfo role,String menuIds) throws Exception {
		return roleService.updateRole(role,menuIds);
	}
	
	/**
	 * 删除权限
	 * @param request	
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public int deleteRole(HttpServletRequest request,String roleIds) throws Exception {
		return roleService.deleteRole(roleIds);
	}
	
	
	/**
	 * 获得角色转为tree类型
	 * @param request	
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoleTree")
	@ResponseBody
	public List<TreeDto>getRoleTree(HttpServletRequest request) throws Exception {
		
		List<RoleInfo> list = roleService.getRoles();
		List<TreeDto> treeList = new ArrayList<TreeDto>();
		int i=0;
		for(RoleInfo role:list){
			TreeDto dto = new TreeDto();
			dto.setId(i++);
			dto.setName(role.getRoleName());
			dto.setTitle(role.getRoleId());
			treeList.add(dto);
		}
		return treeList;
	}

}
