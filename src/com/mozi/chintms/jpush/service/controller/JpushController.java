package com.mozi.chintms.jpush.service.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jpush.api.push.model.Platform;

import com.mozi.chintms.jpush.service.JpushService;


/**
 * This Class ONLY FOR MOBILE TEST
 * @author chenjb
 *
 */
@Controller
@RequestMapping("/jpush")
public class JpushController {
	private static final Logger logger = Logger.getLogger(JpushController.class);

	@Autowired
	private JpushService jpushService;
	
	@RequestMapping("/push" )
	@ResponseBody
	public void push(HttpServletRequest request, String count) {
		logger.info("count:" + count);
//		jpushService.push(count, false, Platform.ios());
		ArrayList<String>tempList = new ArrayList<String>();
		tempList.add("60D2288A9FD94DB0");
		jpushService.pushByTag(count, false, Platform.ios(), tempList);
	}
	
	@RequestMapping("/jstest")
	public String mainView(HttpServletRequest request) {
		return "/jstest";
	}
}
