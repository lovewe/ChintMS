package com.mozi.chintms.inpourManage.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
import com.mozi.chintms.inpourManage.model.WaterInfo;
import com.mozi.chintms.inpourManage.service.WaterService;
import com.mozi.chintms.login.model.LoginUserInfo;

/**
 * 固话宽带充值管理
 * 
 * @author xzh
 * @version 1.0 
 * */
@Controller
@RequestMapping("/waterInpour")
public class WaterController extends BaseController {

	@Autowired
	private WaterService waterService;
	private String sql=null;
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(WaterController.class);
	

	/**
	 * 登录通过后，跳转至主页 main.jsp
	 * @return
	 */
	@RequestMapping("/water")
	public String view1(HttpServletRequest request) {
		WaterInfo info = new WaterInfo();
		String accountNo= request.getParameter("accountNo");
		String amount= request.getParameter("amount");
		String cmdWater= request.getParameter("cmdWater");
		String cmdProvince= request.getParameter("cmdProvince");
		String cmdCity= request.getParameter("cmdCity");
		String cmdPublicService= request.getParameter("cmdPublicService");
		
		request.getSession().setAttribute(accountNo, info);
		request.getSession().getAttribute(accountNo);

		info.setAccountNo(accountNo);
		info.setAmount(amount);
		//info.setAmount(Double.parseDouble(amount.toString()));
		info.setCmdWater(cmdWater);
		info.setCmdProvince(cmdProvince);
		info.setCmdCity(cmdCity);
		info.setCmdPublicService(cmdPublicService);
		String user_id;
		LoginUserInfo user = (LoginUserInfo) request.getSession()
				.getAttribute(Constant.SESSION_LOGINUSER_KEY);
		
		user_id=user.getUser_id();
		request.setAttribute("user_id",user_id);
		request.getAttribute("user_id");
		request.getSession().setAttribute("user_id",user_id);
		
		if(accountNo != null){
			
				return "/inpourManage/disburse";
			
		}
		return "/inpourManage/waterInpour";
	}
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
				return "/inpourManage/waterInpour";
			}
		}
		return "/logout";
	}

	/**
	 * 添加固话宽带充值
	 * @param request	
	 * @param waterBean	充值bean
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	@RequestMapping("/insertWater")
	@ResponseBody
	public Json insertWater(HttpServletRequest request,WaterInfo waterBean) throws ParseException, IOException, SQLException {
		Json json = new Json();
		String errorMsg = null;
		String result = null;
		try{
			 result = waterService.insertWater(waterBean);
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
			//setAmount(String.valueOf(amount));
			//String.valueOf(c);
			HttpBasic http = new HttpBasic();
			Map<String, String> map = new HashMap();
			String strUrl ="http://localhost:8080/ChintMS/transactionManage/transferAccounts.do";
			map.put("accountNo", waterBean.getAccountNo());
			map.put("amount", waterBean.getAmount());
			map.put("cmdWater", waterBean.getCmdWater());
			map.put("cmdProvince", waterBean.getCmdProvince());
			map.put("cmdCity", waterBean.getCmdCity());
			map.put("cmdPublicService", waterBean.getCmdPublicService());
			map.put("personID", waterBean.getUser_id());
			map.put("password", waterBean.getPassword());
			map.put("changeType", waterBean.getChangeType());
			map.put("OrderID", waterBean.getRechargeId());
			map.put("subAccountType", waterBean.getSubAccountType());
			map.put("state", waterBean.getState());
			map.put("remark", waterBean.getRemark());
			map.put("creatTime", waterBean.getCreatTime());
			
			response = http.post(strUrl, map);

			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(),"utf-8");  
				 
				 JSONObject jsonObject = null; 
				
				 jsonObject = JSONObject.fromObject(content); 
				 
				 json.setMsg(String.valueOf(jsonObject.get("msg")));
				 
				    Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.10.100.121:1433","ChintMS","ms678@123");//获得数据库连接
				    Statement statement = conn.createStatement(); //访问数据库
					
				 if((String.valueOf(jsonObject.get("msg"))).equals("null"))
				 {
					  sql="Update  ChintMS.dbo.Recharge  set State ='01' where rechargeId="+"\'"+waterBean.getRechargeId()+"\'"; 
				 }else{
					  sql="Update  ChintMS.dbo.Recharge  set State ='02' where rechargeId="+"\'"+waterBean.getRechargeId()+"\'"; 
				 }
				 
				int resultSet = statement.executeUpdate(sql);//执行SQL语句
			}
		}else{
			json.setMsg(errorMsg);
			json.setSuccess(false);
		}
		return json;
	}
	/**
	 * 查询固话宽带充值
	 * @param request
	 *//*
	@RequestMapping("/selectWaterList")
	@ResponseBody
	public Map<String,Object> selectWaterList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		List<WaterInfo> waterList = waterService.selectWaterList();
		map.put("data",waterList);
		return map;
	}
	
	*//**
	 * 更新固话宽带充值
	 * @param request
	 * @param  waterRechargeBean
	 *//*
	@RequestMapping("/updateWater")
	@ResponseBody
	public Json updateWater(HttpServletRequest request,WaterInfo waterInfo){
		Json json = new Json();
		String errorMsg = null;
		try{
			
			String result = waterService.updateWater(waterInfo);
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
	
	*//**
	 * 删除固话宽带充值
	 * @param request
	 * @param waterRechargeBean
	 *//*
	@RequestMapping("/deleteWater")
	@ResponseBody
	public Json deleteWater(HttpServletRequest request,String rechargeIds){
		Json json = new Json();
		String errorMsg = null;
		String[] rechargeId= null;
		if(rechargeIds != null){
			rechargeId = rechargeIds.split(",");
		}
		try{

			String result =waterService.deleteWater(rechargeId);
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
	*/
	}
		
	


