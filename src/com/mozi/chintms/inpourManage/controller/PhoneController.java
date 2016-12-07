package com.mozi.chintms.inpourManage.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.HttpBasic;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.common.utils.Constant;
import com.mozi.chintms.inpourManage.dao.PhoneDao;
import com.mozi.chintms.inpourManage.model.PhoneInfo;
import com.mozi.chintms.inpourManage.service.PhoneService;
import com.mozi.chintms.login.model.LoginUserInfo;

/**
 * 充值管理
 * 
 * @author xzh
 * @version 1.0 
 * */
@Controller
@RequestMapping("/phoneInpour")
public class PhoneController extends BaseController {

	@Autowired
	private PhoneService phoneService;


	private String sql=null;

	
	/**
	 * 日志器F
	 */
	
	private static final Logger logger = Logger.getLogger(PhoneController.class);
	
	
	
	

	/**
	 * 登录通过后，跳转至主页 main.jsp
	 * @return
	 */
	@RequestMapping("/phone")
	public String view1(HttpServletRequest request) {
		PhoneInfo info = new PhoneInfo();
		String phoneNo= request.getParameter("phoneNo");
		String amount= request.getParameter("amount");
		String price= request.getParameter("price");
		request.setAttribute("phoneNo", phoneNo);
		info.setPhoneNo(phoneNo);
		info.setAmount(amount);
		info.setPrice(price);
		//info.setAmount(Double.parseDouble(amount.toString()));
		//info.setPrice(Double.parseDouble(price.toString()));
		request.getSession().setAttribute(phoneNo, info);
		
		String user_id;
		LoginUserInfo user = (LoginUserInfo) request.getSession()
				.getAttribute(Constant.SESSION_LOGINUSER_KEY);
		user_id=user.getUser_id();
		request.setAttribute("user_id",user_id);
		request.getAttribute("user_id");
		request.getSession().setAttribute("user_id",user_id);
		
		
		if(phoneNo != null){
			
				return "/inpourManage/payment";
		}
		return "/inpourManage/phoneInpour";
	}
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		
		String userName = request.getParameter("userName");
		if (userName != null) {
			LoginUserInfo user = (LoginUserInfo) request.getSession()
					.getAttribute(Constant.SESSION_LOGINUSER_KEY);
			if (user != null) {
				return "/inpourManage/phoneInpour";
			}
		}
		return "/logout";
	}
	/**
	 * 添加手机充值
	 * @param request	
	 * @param phoneBean	充值bean
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	@RequestMapping("/insertPhone")
	@ResponseBody
	public Json insertPhone(HttpServletRequest request,PhoneInfo phoneBean) throws ParseException, IOException, SQLException {
		Json json = new Json();
		String errorMsg = null;
		String result = null;
		//request.setAttribute(arg0, arg1);
		try{
		 result = phoneService.insertPhone(phoneBean);
			if("false".equals(result)){
				errorMsg = "添加失败，请重试。";
			}
		}catch(Exception e){
			logger.error("添加充值异常:",e);
			errorMsg = "添加充值异常:"+e.getMessage();
		}
		if(errorMsg == null){
			json.setObj(result);
			json.setMsg("添加成功。");
			json.setSuccess(true);
			
			CloseableHttpResponse response = null;
			HttpBasic http = new HttpBasic();
			Map<String, String> map = new HashMap();
			String strUrl ="http://localhost:8080/ChintMS/transactionManage/transferAccounts.do";
			map.put("phoneNo", phoneBean.getPhoneNo());
			map.put("amount", phoneBean.getPrice());
			map.put("personID", phoneBean.getUser_id());
			map.put("password", phoneBean.getPassword());
			map.put("changeType", phoneBean.getChangeType());
			map.put("OrderID", phoneBean.getRechargeId());
			map.put("subAccountType", phoneBean.getSubAccountType());
			map.put("state", phoneBean.getState());
			map.put("remark", phoneBean.getRemark());
			map.put("creatTime", phoneBean.getCreatTime());
			response = http.post(strUrl, map);
			if (response.getStatusLine().getStatusCode() == 200) {
				 String content = EntityUtils.toString(response.getEntity(),"utf-8");  
				 JSONObject jsonObject = null; 
				 jsonObject = JSONObject.fromObject(content); 
				// System.out.println(jsonObject);
				
				 json.setMsg(String.valueOf(jsonObject.get("msg")));
				 
				    Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.10.100.121:1433","ChintMS","ms678@123");//获得数据库连接
					 Statement statement = conn.createStatement(); //访问数据库
					
				 if((String.valueOf(jsonObject.get("msg"))).equals("null"))
				 {
					  sql="Update  ChintMS.dbo.Recharge  set State ='01' where rechargeId="+"\'"+phoneBean.getRechargeId()+"\'"; 
				 }else{
					  sql="Update  ChintMS.dbo.Recharge  set State ='02' where rechargeId="+"\'"+phoneBean.getRechargeId()+"\'"; 
				 }
				 
				int resultSet = statement.executeUpdate(sql);//执行SQL语句
		}
		}else{
			//json.setObj();
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}
		return json;
	}
	/**
	 * 更新充值
	 * @param request
	 * @param  phoneBean
	 */
/*	@RequestMapping("/updatePhone")
	@ResponseBody
	public Json updatePhone(HttpServletRequest request,PhoneInfo phoneInfo){
		Json json = new Json();
		String errorMsg = null;
		try{
			
			String result = phoneService.updatePhone1(phoneInfo.getRechargeId());
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
	}*/
	
}

