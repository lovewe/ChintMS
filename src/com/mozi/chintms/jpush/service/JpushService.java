package com.mozi.chintms.jpush.service;

import java.util.ArrayList;

import cn.jpush.api.push.model.Platform;

public interface JpushService {
	
	/**
	 *  推送消息接口
	 * @param message 消息
	 * @param apnsProduction true正式推送 false开发测试推送
	 * @param platForm 目标平台 ios|android|all
	 */
	public void push(String message, boolean apnsProduction, Platform platForm);
	
	/**
	 * 按组（角色）推送消息
	 * @param message 消息
	 * @param apnsProduction 是否正式环境
	 * @param platForm 目标平台 ios|android|all
	 * @param tags 角色数组
	 */
	public void pushByTag(String message, boolean apnsProduction, Platform platform, ArrayList<String> tags);
	
}
