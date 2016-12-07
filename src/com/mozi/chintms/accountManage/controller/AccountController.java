package com.mozi.chintms.accountManage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.accountManage.model.QRCodeInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqForListInfo;
import com.mozi.chintms.accountManage.model.TransferBillInfo;
import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.accountManage.model.UserInfoByPhoneInfo;
import com.mozi.chintms.accountManage.service.AccountService;
import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.common.utils.Constant;
import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.login.model.LoginUserInfo;

/**
 * 角交易管理
 * 
 * @author wx
 * */

@Controller
@RequestMapping("/accountManage")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;
	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(AccountController.class);
	

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
				return "/accountManage/recordManage";
			}
		}
		return "/logout";
	}

	/**
	 * 根据用户ID查询流水记录
	 * 全部 第一次
	 * @param request
	 */
	@RequestMapping("/getAllSubAccountSeqByUser")
	@ResponseBody
	public Json queryAllUserSubAccountList(HttpServletRequest request,SubAccountSeqForListInfo info){
		Json json = new Json();
		Map<String,Object> mapObject = new HashMap<String,Object>();

		String strPersonID = null;
		
		strPersonID = info.getPersonID();
		if(strPersonID == null || strPersonID.length() <= 0)
		{
			json.setMsg("用户ID不能为空");
			json.setSuccess(false);
			return json;
		}
	
		mapObject.put("data", accountService.getSubAccountSeqByUser(info));
		mapObject.put("amount", accountService.getAmountByID(strPersonID));
		
		json.setMsg("用户查询成功");
		json.setObj(mapObject);
		json.setSuccess(true);
		return json;
	}
	
	/**
	 * 根据用户ID查询流水记录
	 * 下拉刷新
	 * @param request
	 */
	@RequestMapping("/getSubAccountSeqByUser")
	@ResponseBody
	public Json queryUserSubAccountList(HttpServletRequest request,SubAccountSeqForListInfo info){
		Json json = new Json();
		Map<String,Object> mapObject = new HashMap<String,Object>();
		List<Object> list = new ArrayList<Object>();

		list.add(accountService.getSubAccountSeqByUser(info));		
		mapObject.put("data", list);
		
		json.setMsg("用户查询成功");
		json.setObj(mapObject);
		json.setSuccess(true);
		return json;
	}
	
	/**
	 * 根据用户ID查询流水记录
	 * 下拉刷新
	 * @param request
	 */
	@SuppressWarnings("null")
	@RequestMapping("/getUserByPhone")
	@ResponseBody
	public Json getUserInfoByPhone(HttpServletRequest request,String  phoneNum){
		Json json = new Json();
		UserInfoByPhoneInfo info = new UserInfoByPhoneInfo();
		
		
		if(phoneNum==null||phoneNum.length()<=0)
		{
			json.setMsg("请输入手机号码");
			json.setObj(null);
			json.setSuccess(false);
			return json;
		}
		
		info =accountService.getUserInfoByPhone(phoneNum);
		
		if(info ==null)
		{
			json.setMsg("用户不存在");
			json.setObj(null);
			json.setSuccess(false);
			
		}else{

			json.setMsg("用户查询成功");
			json.setObj(info);
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping("/transferAccounts")
	@ResponseBody
	public Json transferAccounts(HttpServletRequest request,TransferInfo  info){
		int key = 0;
		int result = 0;
		String msg = null;
		Json json = new Json();
		Map<Integer,String> map = new HashMap<Integer,String>();
		QRCodeInfo qrInfo = new QRCodeInfo();
		String strQRCode = request.getParameter("qrCode");
		json.setSuccess(false);
		
		if(info.getPayeeUserID().equals(info.getPayerUserID()))
		{
			msg = "对方账户相同,请核对后重试";
			json.setMsg(msg);
			json.setSuccess(false);
			return json;
		}
		
		map = accountService.transferAccounts(info);
		Set set = map.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			key = (int) iter.next();
			msg=map.get(key);
			//System.out.println(map.get(key));
		}
		
		if(key==1)
		{
			qrInfo.setQrCode(strQRCode);
			result = accountService.updateQRCodeStatus(qrInfo);
			if(result>0)
			{
				json.setSuccess(true);
			}
		}
		
		json.setMsg(msg);
		json.setObj(key);

		return json;
	}
	
	/*
	 * 
	 */
	@RequestMapping("/generateQRCode")
	@ResponseBody
	public Json generateQRCode(HttpServletRequest request,QRCodeInfo info){
		Json json = new Json();
		String strQrCode=null;

		info.setIgnoreValidate(info.getIgnoreValidate()==null?"0":info.getIgnoreValidate());
		
		strQrCode = accountService.generateQRCode(info);

		if(strQrCode!=null&&strQrCode.length()>0)
		{
			json.setSuccess(true);
			json.setMsg("生成成功");
			json.setObj(strQrCode);
		}else
		{
			json.setSuccess(false);
			json.setMsg("处理异常,请稍后再试!");
		}
		
		return json;
	}
	
	@RequestMapping("/validateTransferTime")
	@ResponseBody
	public Json validateTransferTime(HttpServletRequest request,QRCodeInfo info){
		Json json = new Json();		
		QRCodeInfo qrInfo = null;
		String strDateTimeNow = null;
		UserInfoByPhoneInfo userInfo = new UserInfoByPhoneInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		strDateTimeNow = df.format(new Date());// 获取当前系统时间
		
		qrInfo = accountService.getQRCode(info);
		
		if (qrInfo != null) {			
			if (qrInfo.getIgnoreValidate().equals("0")) {//安全码是否过期 0:过期,1:不过期
				try {
					if (qrInfo.getStatus().equals("1")) {//安全码是否使用  0:未使用 1 已使用 
						json.setMsg("安全码已使用");
						json.setSuccess(false);
						return json;
					}
					c1.setTime(df.parse(strDateTimeNow));
					c2.setTime(df.parse(qrInfo.getGenerateTime()));
					c2.add(Calendar.MINUTE, 10);

					int result = c1.compareTo(c2);
					if (result > 0) {
						json.setMsg("安全码已过期");
						json.setSuccess(false);
						return json;
					} else {
						if (info.getQrCode().equals(qrInfo.getQrCode())) {
							userInfo = accountService.getUserInfoByQRCode(info
									.getQrCode());
							json.setObj(userInfo);
							json.setMsg("安全码验证成功");
							json.setSuccess(true);
							return json;
						} else {
							json.setMsg("验证失败");
							json.setSuccess(false);
							return json;
						}
					}
				} catch (java.text.ParseException e) {
					logger.info("格式不正确"+e);
				}catch (Exception e) {
					logger.info(e);
				}
			} else {
				if (info.getQrCode().equals(qrInfo.getQrCode())) {
					userInfo =accountService.getUserInfoByQRCode(info.getQrCode());
					json.setObj(userInfo);
					json.setMsg("安全码验证成功");
					json.setSuccess(true);
					return json;
				} else {
					json.setMsg("验证失败");
					json.setSuccess(false);
					return json;
				}
			}
			
		}else
		{
			json.setMsg("验证失败");
			json.setSuccess(false);
		}

		return json;
	}
	
	@RequestMapping("/selectTransferBILL")
	@ResponseBody
	public Map<String,Object> selectTransferBILL(HttpServletRequest request){
		String userID = null;
		LoginUserInfo user =null;
		user =(LoginUserInfo) request.getSession().getAttribute(Constant.SESSION_LOGINUSER_KEY);
		
		if(user!=null)
		{
			userID=user.getUser_id();
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<TransferBillInfo> list = accountService.selectTransferBILL(userID);
		map.put("data", list);
		return map;
	} 
	/**
	 * 生成6位随机验证码
	 * 
	 * @return
	 */
	public static String createRandomVcode() {
		// 验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}

}
