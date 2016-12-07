package com.mozi.chintms.login.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.common.MD5Util;
import com.mozi.chintms.common.utils.Constant;
import com.mozi.chintms.common.utils.Md5Util;
import com.mozi.chintms.login.model.LoginUserInfo;
import com.mozi.chintms.login.model.PhoneLoginUserInfo;
import com.mozi.chintms.login.service.UserLoginService;
import com.mozi.chintms.safeManage.model.Permission;
import com.mozi.chintms.safeManage.model.SafeUserInfo;
import com.mozi.chintms.safeManage.service.MenuService;

/**
 * 用户登录
 * 
 * @author wx
 * @version 1.0
 * */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserLoginService loginService;

	@RequestMapping("/content")
	public String content(HttpServletRequest request, Model model,
			HttpSession session) {
		return "/content";
	}

	/**
	 * 登录通过后，跳转至主页 main.jsp
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public String mainView(HttpServletRequest request) {

		String userName = request.getParameter("userName");
		LoginUserInfo user =null;
		user =(LoginUserInfo) request.getSession().getAttribute(Constant.SESSION_LOGINUSER_KEY);
		
		if (user != null) {
			String htmlStr = this.getMenuList(user.getUser_name());
			request.setAttribute("menuStr", htmlStr);
			request.setAttribute("userName", user.getUser_name());
			return "/main";
		} else {
//			String htmlStr = this.getMenuList("screenUser");
//			request.setAttribute("menuStr", htmlStr);
//			request.setAttribute("userName", userName);
//			return "/main";
			return "/logout";
		}
		// return "/logout";
	}

	/**
	 * 登录方法 login.do
	 * 
	 * @param request
	 * @param inputUser
	 *            前台用户bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Json logining(HttpServletRequest request, LoginUserInfo inputUser) throws Exception {
		
		String loginIp = request.getRemoteAddr() + ':' + request.getRemotePort();
		Json json = new Json();
		logger.info("开始登录");
		String errorMsg = null;
		LoginUserInfo user = null;
		
		try {
			// 非Cookie登录方式，进行用户名密码验证			
			user = loginService.userLogin(inputUser);
			
			if(user==null)
			{
				inputUser.setPhone(inputUser.getUser_name());
				inputUser.setUser_name(null);
				user = loginService.userLogin(inputUser);
			}

			if (user == null) {
				errorMsg = "该用户不存在，请检查用户名";
			} else {
				// 对输入的密码进行MD5加密
				inputUser.setPassword(MD5Util.md5Encode(inputUser.getPassword()));
				// 密码相同,表示通过
				if (inputUser.getPassword().equals(user.getPassword())) {
					// 将用户信息保存session
					request.getSession().setAttribute(Constant.SESSION_LOGINUSER_KEY, user);
					
				} else {
					errorMsg = "输入的密码有误，请重新输入";
				}
			}

		} catch (Exception e) {
			logger.error("用户登录时异常：", e);
			errorMsg = "用户登录失败，请联系管理员。";
		}
		
		// 用户名密码验证成功
		if (errorMsg == null) {
			String msg = "用户("+inputUser.getUser_name()+")在"+loginIp+"终端登陆系统成功。";
			logger.info(msg);
			
			String strToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			json.setMsg(user.getUser_name()==null?user.getPhone():user.getUser_name());
			json.setObj(user.getUser_id());
			json.setSuccess(true);
			return json;
		} else {
			//用户登录失败
			logger.error(errorMsg);
			json.setSuccess(false);
			json.setMsg(errorMsg);
			return json;
		}
	}
	
	@RequestMapping("/phoneLogin")
	@ResponseBody
	public Json phoneLogining(HttpServletRequest request, PhoneLoginUserInfo inputUser) throws Exception {
		
		String loginIp = request.getRemoteAddr() + ':' + request.getRemotePort();
		Json json = new Json();
		logger.info("开始登录");
		String errorMsg = null;
		
		Enumeration em = request.getParameterNames();
		 while (em.hasMoreElements()) {
		    String name = (String) em.nextElement();
		    System.out.println(name);
		    String value = request.getParameter(name);
		    System.out.println(value);
		}
		
		
		try {
			
			LoginUserInfo user = new LoginUserInfo();
			user.setPhone(inputUser.getUsername());
			user.setPassword(inputUser.getPassword());
			

			if(user.getPhone()== null || user.getPhone().length() <= 0)
			{
				json.setMsg("用户名为空,请重新输入");
				json.setSuccess(true);	
				return json;
			}
			
			if(user.getPassword()== null || user.getPassword().length() <= 0)
			{
				json.setMsg("用户名密码为空,请重新输入");
				json.setSuccess(true);	
				return json;
			}
			
			user = loginService.userLogin(user);

			if (user == null) {
				errorMsg = "该用户不存在，请检查用户名";
			} else {
				// 对输入的密码进行MD5加密
				inputUser.setPassword(MD5Util.md5Encode(inputUser.getPassword()));
				// 密码相同,表示通过
				if (inputUser.getPassword().equals(user.getPassword())) {
					json.setMsg(user.getUser_name());
					json.setObj(user.getUser_id());
					json.setSuccess(true);	
				} else {
					errorMsg = "输入的密码有误，请重新输入";
					logger.error(errorMsg);
					json.setSuccess(false);
					json.setMsg(errorMsg);
				}
			}

		} catch (Exception e) {
			logger.error("用户登录时异常：", e);
			errorMsg = "用户登录失败，请联系管理员。";
		}
		
		return json;
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model)
			throws Exception {
		String userName = request.getParameter("userName");
		if (userName != null) {
			Object userFlag =  request.getSession().getAttribute(userName);;
			if (userFlag != null) {
				request.getSession().removeAttribute(userName);
				logger.info("终端登出系统成功。");
			}
			// Logout时不自动Cookie登录。
			model.addAttribute("isNeedLogin", false);
		}
		return "/login/login";
	}

	/**
	 * 取得用户权限
	 * 
	 */
	public String getMenuList(String userName) {
		StringBuffer htmlStr = new StringBuffer();
		List<Permission> permList = menuService.getMenulistByUser(userName,
				null);
		htmlStr = menuService.getMenuHtmlStr(permList);
		return htmlStr.toString();
	}

	/**
	 * 模拟登录方法
	 * 
	 * @param request
	 * @param inputUser
	 *            前台用户bean
	 * @return
	 */
	@RequestMapping("/simulationLogin")
	@ResponseBody
	public Json simulationLogin(HttpServletRequest request, LoginUserInfo inputUser) {
		logger.info("模拟登录开始。");
		Json json = new Json();
		String errorMsg = null;
		try {
			LoginUserInfo user = loginService.userLogin(inputUser);

			if (user == null) {
				errorMsg = "该用户不存在，请检查用户名";
			} else {
				// 密码相同,表示通过
				if (inputUser.getPassword().equals(user.getPassword())) {
					// 将用户信息保存session
					request.getSession().setAttribute(inputUser.getUser_name(),
							"true");
				} else {
					errorMsg = "输入的密码有误，请重新输入";
				}
			}
		} catch (Exception e) {
			logger.error("用户登录时异常：", e);
			errorMsg = "用户登录失败，请联系管理员。";
		}

		// 用户名密码验证成功
		if (errorMsg == null) {
			String loginIp = request.getRemoteAddr() + ':'
					+ request.getRemotePort();
			logger.info("用户(" + inputUser.getUser_name() + ")在" + loginIp
					+ "终端登陆系统成功。");
			json.setSuccess(true);
		} else {
			// 用户登录失败
			logger.error(errorMsg);
			json.setSuccess(false);
			json.setMsg(errorMsg);
		}

		logger.info("模拟登录结束。");
		return json;
	}
}
