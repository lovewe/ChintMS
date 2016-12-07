package com.mozi.chintms.smsManage.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.safeManage.dao.MenuDao;
import com.mozi.chintms.smsManage.model.PhoneInfo;
import com.mozi.chintms.smsManage.service.SmsService;

/**
 * 短信验证
 * 
 * @author wx
 *
 */
@Service("SmsService")
public class SmsServiceImpl extends BaseService implements SmsService {

	@Autowired
	private MenuDao menuDao;

	private static CacheManager manager = CacheManager.create(); // 初始化缓存

	@Override
	public List<PhoneInfo> SelectUserByPhone(PhoneInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 短信发送方法
	 * 
	 * @param phones
	 * @param content
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String sendMsg(String phones, String content) {
		// 短信接口URL提交地址
		String url = "短信接口URL提交地址";

		Map<String, String> params = new HashMap<String, String>();

		params.put("test", "用户账号");
		params.put("123456", "用户密码");

		// 手机号码，多个号码使用英文逗号进行分割
		params.put("hm", phones);
		// 将短信内容进行URLEncoder编码
		params.put("nr", URLEncoder.encode(content));

		return null;
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

	/**
	 * 缓存 获取手机验证码用以作为回执验证 测试用
	 */
	@Override
	public String GetPhoneCache(PhoneInfo info) {
		Cache test = manager.getCache("myCache");
		Element e = test.get(info.getPhoneNum());

		return e.getObjectValue().toString();

	}

	/**
	 * 缓存 保存手机验证码用以作为回执验证
	 */
	@Override
	public void SetPhoneCache(PhoneInfo info) {
		// TODO Auto-generated method stub
		// 暂时写死
		info.setvCode("123456");
	
//		manager.addCache("myCache");
		Cache test = manager.getCache("myCache");
		test.put(new Element(info.getPhoneNum(), info.getvCode()));
		// manager.shutdown();

		Element e = test.get(info.getPhoneNum());

		System.out.println(e.getObjectKey());
		System.out.println(e.getObjectValue());	
	}
}
