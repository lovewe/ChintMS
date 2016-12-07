package com.mozi.chintms.transactionManage.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.mozi.chintms.accountManage.dao.AccountDao;
import com.mozi.chintms.accountManage.model.SubAccountInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqInfo;
import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.common.Arith;
import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.common.MD5Util;
import com.mozi.chintms.login.model.LoginUserInfo;
import com.mozi.chintms.login.service.UserLoginService;
import com.mozi.chintms.transactionManage.controller.TransactionManageController;
import com.mozi.chintms.transactionManage.dao.TransactionDao;
import com.mozi.chintms.transactionManage.model.TransactionInfo;
import com.mozi.chintms.transactionManage.service.TransactionService;


@Service("transactionService")
public class TransactionServiceImpl extends BaseService implements TransactionService{	
	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private UserLoginService loginService;
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE,rollbackFor = { Exception.class })
	public Map<Integer, String> transferAccounts(TransactionInfo info) {
		// TODO Auto-generated method stub
		Map<Integer, String> megMap = new HashMap<Integer, String>();

		SubAccountInfo payerSubAccountInfo = null;

		double payerAmount = 0.00;
		double amount = 0.00;

		int key = 0;
		int result = 0;

		String strDateTimeNow = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式


		try {
			// 获取账户信息
			payerSubAccountInfo = transactionDao.getAmountByID(info.getPersonID());
			
			if(payerSubAccountInfo==null)
			{
				megMap.put(0, "支付账户不存在");
				return megMap;
			}
		
			// 获取账户余额
			payerAmount = Double.parseDouble(payerSubAccountInfo.getAmount());
			amount = Double.parseDouble(info.getAmount());

			megMap = userVerificati(info);
			Set set = megMap.keySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				key = (int) iter.next();
			}

			if (key == 0) {
				return megMap;
			} else {
				megMap = new HashMap<Integer, String>();
			}

			if (payerAmount < amount) {
				megMap.put(0, "账户余额不足");
				return megMap;
			}

			strDateTimeNow = df.format(new Date());// 获取当前系统时间
			info.setCreateTime(strDateTimeNow);
			info.setOrderID(info.getOrderID());

			result = outMoney(info, payerSubAccountInfo);
			
			if (result == 2) {
				megMap.put(1, "转账成功!");
			}
		} catch (Exception e) {
			logger.error("转账异常：", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return megMap;
	}

	@Override
	public Map<Integer, String> userVerificati(TransactionInfo info) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {

			LoginUserInfo user = new LoginUserInfo();
			user.setPassword(info.getPassword());
			user.setUser_id(info.getPersonID());

			if (user.getUser_id() == null || user.getUser_id().length() <= 0) {
				map.put(0, "用户名为空,请重新输入");
				return map;
			}

			if (user.getPassword() == null || user.getPassword().length() <= 0) {
				map.put(0, "用户名密码为空,请重新输入");
				return map;
			}

			user = loginService.userLogin(user);

			if (user == null) {
				map.put(0, "该用户不存在，请检查用户名");
			} else {
				// 对输入的密码进行MD5加密
				info.setPassword(MD5Util.md5Encode(info.getPassword()));
				// 密码相同,表示通过
				if (info.getPassword().equals(user.getPassword())) {
					map.put(1, "验证通过");
				} else {
					map.put(0, "输入的密码有误，请重新输入");
				}
			}
		} catch (Exception e) {
			logger.error("用户登录时异常：", e);
		}
		return map;
	}
	
	/**
	 * 
	 * 流水记录
	 * 转出
	 */
	@Override
	public  int outMoney(TransactionInfo tinfo,SubAccountInfo sInfo) {
		
		// TODO Auto-generated method stub
		double amount =0.00;
		double payerAmount = 0.00;
		SubAccountSeqInfo subSeqInfo = new SubAccountSeqInfo();
		
		amount = Double.parseDouble(tinfo.getAmount());
		payerAmount =Double.parseDouble(sInfo.getAmount());
		amount = Arith.sub(payerAmount,amount);
		
		subSeqInfo.setCreateTime(tinfo.getCreateTime()); //发生时间
		subSeqInfo.setChangeType(tinfo.getChangeType()); //账户变动类型 
		subSeqInfo.setUserID(sInfo.getPersonID());//用户ID
		subSeqInfo.setOrderID(tinfo.getOrderID()); //交易流水
		subSeqInfo.setSeqFlag("1");  //来往方向
		subSeqInfo.setSubAccountID(sInfo.getId()); //子账户ID
		subSeqInfo.setTradeRemark(tinfo.getRemark()); //备注信息
		subSeqInfo.setTradeAmount(tinfo.getAmount()); //发生金额
		subSeqInfo.setPreAmount(sInfo.getAmount());  //发生前账户余额
		subSeqInfo.setAmount(Double.toString(amount));//发生后账户余额
		subSeqInfo.setSubAccountType("01"); //个人账户
	    
		transactionDao.insertSubAccountSeq(subSeqInfo);
		return transactionDao.updateAccounts(subSeqInfo);
		
		 
	}
}
