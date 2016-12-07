package com.mozi.chintms.transactionManage.controller;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.common.utils.Constant;
import com.mozi.chintms.safeManage.service.MenuService;
import com.mozi.chintms.transactionManage.model.TransactionInfo;
import com.mozi.chintms.transactionManage.service.TransactionService;

/**
 * 交易管理
 * 
 * @author wx
 * */
@Controller
@RequestMapping("/transactionManage")
public class TransactionManageController extends BaseController {

	@Autowired
	private TransactionService transactionService;
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(TransactionManageController.class);
	

	/**
	 * 登录通过后，跳转至主页 main.jsp
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		if(userName != null){
			String user = (String)request.getSession().getAttribute(Constant.SESSION_LOGINUSER_KEY);
			if("true".equals(user)){
				return "/main";
			}
		}
		return "/logout";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/transferAccounts")
	@ResponseBody
	public Json transferAccounts(HttpServletRequest request,TransactionInfo  info){
		int key = 0;
		String msg = null;
		Json json = new Json();
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		map = transactionService.transferAccounts(info);
		Set set = map.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			key = (int) iter.next();
			msg=map.get(key);
			//System.out.println(map.get(key));
		}
		if(key==1)
		{
			json.setSuccess(true);
		}else
		{
			json.setSuccess(false);
		}
		
		json.setMsg(msg);
		json.setObj(key);

		return json;
	}
	
	private String TransactionVerificati(TransactionInfo info)
	{
		String strMsg=null;
		
		String strOrderID = null;
		strOrderID = info.getOrderID();
		if(strOrderID==null||strOrderID.length()<=0)
		{
			return "订单号不能为空";
		}
		
		String strChangeType = null;
		strChangeType = info.getChangeType();
		if(strChangeType==null||strChangeType.length()<=0)
		{
			return "交易类型不能为空";
		}
		
		return strMsg;
	}
	
}
