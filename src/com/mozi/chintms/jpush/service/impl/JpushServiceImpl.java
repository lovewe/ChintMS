package com.mozi.chintms.jpush.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.mozi.chintms.jpush.service.JpushService;

@Service("JpushService")
public class JpushServiceImpl implements JpushService {

	private static final String masterSecret = "030f30d37f871eb74f068356";
	private static final String appKey = "d439a95bd5d889a3ff575fa8";
	private static final Logger logger = Logger.getLogger(JpushServiceImpl.class);
	
	@Override
	public void push(String message, boolean apnsProduction, Platform platform) {

		ClientConfig config = ClientConfig.getInstance();
		config.setApnsProduction(false);
		config.setMaxRetryTimes(3);
		
		JPushClient jpush = new JPushClient(masterSecret, appKey, null, config);
		PushPayload payload = buildPushObjectForAll(message, apnsProduction, platform);

		try {
			PushResult result = jpush.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
        	logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
        	logger.error("Should review the error, and fix the request", e);
        	logger.info("HTTP Status: " + e.getStatus());
        	logger.info("Error Code: " + e.getErrorCode());
        	logger.info("Error Message: " + e.getErrorMessage());
        }
	}
	
	public void pushByTag(String message, boolean apnsProduction, Platform platform, ArrayList<String> tags){
		ClientConfig config = ClientConfig.getInstance();
		config.setApnsProduction(false);
		config.setMaxRetryTimes(3);
		
		JPushClient jpush = new JPushClient(masterSecret, appKey, null, config);
		PushPayload payload = buildPushObjectForTags(message, apnsProduction, platform, tags);

		try {
			PushResult result = jpush.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
        	logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
        	logger.error("Should review the error, and fix the request", e);
        	logger.info("HTTP Status: " + e.getStatus());
        	logger.info("Error Code: " + e.getErrorCode());
        	logger.info("Error Message: " + e.getErrorMessage());
        }
	}
	
	/**
	 * 构造推送对象
	 * @param message
	 * @param apnsProduction
	 * @return
	 */
	public static PushPayload buildPushObjectForAll(String message, boolean apnsProduction, Platform platform) {

		return  PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
					.addPlatformNotification(IosNotification.newBuilder()
						.setAlert(message)
						.setBadge(1)
						.setSound("happy")
						.addExtra("from", "LargeDataServer").build()).build()
						).setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build()).build();
    }

	public static PushPayload buildPushObjectForTags(String message, boolean apnsProduction, Platform platform, ArrayList<String> tags) {
	
		return  PushPayload.newBuilder().setPlatform(platform).setAudience(Audience.tag(tags))
				.setNotification(Notification.newBuilder()
					.addPlatformNotification(IosNotification.newBuilder()
						.setAlert(message)
						.setBadge(1)
						.setSound("happy")
						.addExtra("from", "LargeDataServer").build()).build()
						).setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build()).build();
	}
}

/**
 * 推送返回错误码
 * @author chenjb
 *
 */
enum ErrorCodeEnum {
    
    //没有错误，发送成功
    NOERROR(0),
 
    //系统内部错误
    SystemError(10),
 
    //不支持GET请求
    NotSupportGetMethod(1001),
 
    //缺少必须参数
    MissingRequiredParameters(1002),
 
    //参数值不合法
    InvalidParameter(1003),
 
    //验证失败
    ValidateFailed(1004),
 
    //消息体太大
    DataTooBig(1005),
 
    //IMEI不合法
    InvalidIMEI(1007),
 
    //appkey不合法
    InvalidAppKey(1008),
 
    //msg_content不合法
    InvalidMsgContent(1010),
 
    //没有满足条件的推送目标
    InvalidPush(1011),
 
    //IOS不支持自定义消息
    CustomMessgaeNotSupportIOS(1012);
 
    private final int value;
    private ErrorCodeEnum(final int value) {
        this.value = value;
    }
 
    public int value() {
        return this.value;
    }
}
