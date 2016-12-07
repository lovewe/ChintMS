package com.mozi.chintms.accountManage.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.UUID;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.mozi.chintms.accountManage.dao.AccountDao;
import com.mozi.chintms.accountManage.model.QRCodeInfo;
import com.mozi.chintms.accountManage.model.SubAccountInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqForListInfo;
import com.mozi.chintms.accountManage.model.SubAccountSeqInfo;
import com.mozi.chintms.accountManage.model.TransferBillInfo;
import com.mozi.chintms.accountManage.model.TransferInfo;
import com.mozi.chintms.accountManage.model.UserInfoByPhoneInfo;
import com.mozi.chintms.accountManage.service.AccountService;
import com.mozi.chintms.common.Arith;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.common.MD5Util;
import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.login.model.LoginUserInfo;
import com.mozi.chintms.login.service.UserLoginService;
import com.mozi.chintms.safeManage.controller.MenuManageController;
import com.mozi.chintms.smsManage.model.PhoneInfo;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;

@Service("accountService")
public class AccountServiceImpl implements AccountService  {

	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private UserLoginService loginService;

	private static CacheManager manager = CacheManager.create(); // 初始化缓存

	
	/**
	 * 转账操作 
	 * 账户变更
	 */
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE,rollbackFor = { Exception.class })
	public  Map<Integer,String> transferAccounts(TransferInfo info) {
		// TODO Auto-generated method stub
		Map<Integer, String> megMap = new HashMap<Integer, String>();

		SubAccountInfo payerSubAccountInfo = null;
		SubAccountInfo payeeSubAccountInfo = null;

		double payerAmount = 0.00;
		double amount = 0.00;

		int key = 0;
		int result = 0;

		String strDateTimeNow = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		UUID uuid = UUID.randomUUID();

		try {
			// 获取付款人账户信息
			payerSubAccountInfo = accountDao.getAmountByID(info.getPayerUserID());
			// 获取收款人账户信息
			payeeSubAccountInfo = accountDao.getAmountByID(info.getPayeeUserID());
			
			if(payerSubAccountInfo==null)
			{
				megMap.put(0, "支付账户不存在");
				return megMap;
			}
			if(payeeSubAccountInfo==null)
			{
				megMap.put(0, "收款账户不存在");
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

			// 得到对象产生的ID
			String strGuid = uuid.toString();
			// 转换为大写
			strGuid = strGuid.toUpperCase();
			// System.out.println(strGuid);
			info.setOrderID(strGuid);

			result = outMoney(info, payerSubAccountInfo);
			result += inMoney(info, payeeSubAccountInfo);
            result += accountDao.insertTransferBILL(info);
			
			if (result == 3) {
				megMap.put(1, "转账成功!");
			}
		} catch (Exception e) {
			logger.error("转账异常：", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return megMap;
	}
	
	/**
	 * 
	 * 流水记录
	 * 转出
	 */
	@Override
	public  int outMoney(TransferInfo tinfo,SubAccountInfo sInfo) {
		
		// TODO Auto-generated method stub
		double amount =0.00;
		double payerAmount = 0.00;
		SubAccountSeqInfo subSeqInfo = new SubAccountSeqInfo();
		
		amount = Double.parseDouble(tinfo.getAmount());
		payerAmount =Double.parseDouble(sInfo.getAmount());
		amount = Arith.sub(payerAmount,amount);
		
		subSeqInfo.setCreateTime(tinfo.getCreateTime()); //发生时间
		subSeqInfo.setChangeType("02"); //账户变动类型 转账
		subSeqInfo.setUserID(sInfo.getPersonID());//用户ID
		subSeqInfo.setOrderID(tinfo.getOrderID()); //交易流水
		subSeqInfo.setSeqFlag("1");  //来往方向
		subSeqInfo.setSubAccountID(sInfo.getId()); //子账户ID
		subSeqInfo.setTradeRemark(tinfo.getRemark()); //备注信息
		subSeqInfo.setTradeAmount(tinfo.getAmount()); //发生金额
		subSeqInfo.setPreAmount(sInfo.getAmount());  //发生前账户余额
		subSeqInfo.setAmount(Double.toString(amount));//发生后账户余额
		subSeqInfo.setSubAccountType("01"); //个人账户
	    
		accountDao.insertSubAccountSeq(subSeqInfo);
		return accountDao.updateAccounts(subSeqInfo);
		
		 
	}

	/**
	 * 流水记录
	 * 转入
	 */
	@Override
	public  int inMoney(TransferInfo tinfo,SubAccountInfo sInfo) {
		// TODO Auto-generated method stub
		double amount =0.00;
		double payerAmount = 0.00;
		SubAccountSeqInfo subSeqInfo = new SubAccountSeqInfo();
		
		amount = Double.parseDouble(tinfo.getAmount());
		payerAmount =Double.parseDouble(sInfo.getAmount());
		amount = Arith.add(payerAmount,amount);
		
		subSeqInfo.setCreateTime(tinfo.getCreateTime()); //发生时间
		subSeqInfo.setChangeType("02"); //账户变动类型 转账
		subSeqInfo.setUserID(sInfo.getPersonID());//用户ID
		subSeqInfo.setOrderID(tinfo.getOrderID()); //交易流水
		subSeqInfo.setSeqFlag("2");  //来往方向
		subSeqInfo.setSubAccountID(sInfo.getId()); //子账户ID
		subSeqInfo.setTradeRemark(tinfo.getRemark()); //备注信息
		subSeqInfo.setTradeAmount(tinfo.getAmount()); //发生金额
		subSeqInfo.setPreAmount(sInfo.getAmount());  //发生前账户余额
		subSeqInfo.setAmount(Double.toString(amount));//发生后账户余额
		subSeqInfo.setSubAccountType("01"); //个人账户
	    
		accountDao.insertSubAccountSeq(subSeqInfo);
		return accountDao.updateAccounts(subSeqInfo);
        
	}

	/**
	 * 获取余额
	 */
	@Override
	public double  getAmountByID(String userId) {
		// TODO Auto-generated method stub
		BigDecimal decAmount = null;
		/**
		 * 接收账户信息
		 */
		SubAccountInfo sInfo = new SubAccountInfo();
		sInfo = accountDao.getAmountByID(userId);
		//精度计算
		decAmount = new BigDecimal(Double.valueOf(sInfo.getAmount())); 
		
//		return decAmount.doubleValue();
		return decAmount.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	
	@Override
	public Map<String, List<SubAccountSeqForListInfo>> getSubAccountSeqByUser(SubAccountSeqForListInfo info) {
		// TODO Auto-generated method stub
		List<SubAccountSeqForListInfo>  seqList =null;
		seqList = accountDao.getSubAccountSeqByUser(info);
		
		SubAccountSeqForListInfo dataItem; // 数据库中查询到的每条记录
		Map<String, List<SubAccountSeqForListInfo>> resultMap= new HashMap<String, List<SubAccountSeqForListInfo>>(); // 最终要的结果
		for(int i=0;i<seqList.size();i++){
		    dataItem = seqList.get(i);
		    if(resultMap.containsKey(dataItem.getDays())){
		        resultMap.get(dataItem.getDays()).add(dataItem);
		    }else{
		        List<SubAccountSeqForListInfo> list = new ArrayList<SubAccountSeqForListInfo>();
		        list.add(dataItem);
		        resultMap.put(dataItem.getDays(),list);
		    }
		}
		return resultMap;
	}

	@Override
	public UserInfoByPhoneInfo getUserInfoByPhone(String phoneNum) {
		// TODO Auto-generated method stub
		return accountDao.getUserInfoByPhone(phoneNum);
	}

	@Override
	public int updateAccounts(SubAccountInfo info) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Map<Integer, String> userVerificati(TransferInfo info) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {

			LoginUserInfo user = new LoginUserInfo();
			user.setPassword(info.getPassword());
			user.setUser_id(info.getPayerUserID());

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
	 * 获取缓存
	 */
	@Override
	 public Object GetCache(QRCodeInfo info) {
		
		Object obj = null;
		Cache cache = manager.getCache("qrCodeCache");
		Element e = cache.get(info.getPersonID());
		if(e!=null)
		{
			obj= e.getObjectValue();
		}
		
		return obj;

	}
    /**
     * 添加缓存
     */
	@Override
	public void SetCache(QRCodeInfo info) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
//		map.put(info.getQrCode(), info.getPayoutORincome());
		Cache cache = manager.getCache("qrCodeCache");
		cache.put(new Element(info.getPersonID(), map));
		Element e = cache.get(info.getPersonID());
//		System.out.println(e.getObjectKey());
//		System.out.println(e.getObjectValue());

	}

	/**
	 * 移除缓存
	 */
	@Override
	public void RemoveCache(QRCodeInfo info) {
		// TODO Auto-generated method stub
		Cache cache = manager.getCache("qrCodeCache");
		cache.remove(info.getPersonID());
	}

	@Override
	public List<TransferBillInfo> selectTransferBILL(String userID) {
		// TODO Auto-generated method stub
		List<TransferBillInfo> list = null;
		try {
			list = accountDao.selectTransferBILL(userID);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	@Override
	public String generateQRCode(QRCodeInfo info) {
		// TODO Auto-generated method stub
		
		QRCodeInfo qrInfo =null;
		String strQrCode=null;
		int result = 0;
		qrInfo =accountDao.getQRCode(info);
		
		strQrCode =RandomUtil.randomString();
		info.setQrCode(strQrCode);
		if(qrInfo!=null)
		{
			result = accountDao.updateQRCode(info);
		}else
		{
			result = accountDao.generateQRCode(info);
		}
		
		if(result<=0)
		{
			strQrCode =null;
		}
		return strQrCode;
	}

	@Override
	public QRCodeInfo getQRCode(QRCodeInfo info) {
		// TODO Auto-generated method stub
		return accountDao.getQRCode(info);
	}

	@Override
	public UserInfoByPhoneInfo getUserInfoByQRCode(String qrCode) {
		// TODO Auto-generated method stub
		return accountDao.getUserInfoByQRCode(qrCode);
	}

	@Override
	public int updateQRCodeStatus(QRCodeInfo info) {
		// TODO Auto-generated method stub
		return accountDao.updateQRCodeStatus(info);
	}
	

}
