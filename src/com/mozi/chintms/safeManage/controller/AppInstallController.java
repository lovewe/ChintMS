package com.mozi.chintms.safeManage.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.common.BaseController;
import com.mozi.chintms.common.Json;
import com.mozi.chintms.common.utils.ReadProperties;


/**
 * AppInstallController.java
 * 
 * @author wuhanhua
 * @version 1.0 
 * */
@Controller
@RequestMapping("/app")
public class AppInstallController extends BaseController {

	private static final Logger logger = Logger.getLogger(AppInstallController.class);

	@RequestMapping("/iosInstall")
	public void iosInstall(HttpServletRequest request, HttpServletResponse response) {
		logger.info("iosInstall Start。");

		//String contentType = "application/vnd.iphone";
		String contentType = "application/octet-stream";
		String fileName = "data.ipa";
		String key = "IOSInstallFilePath";

		download(request, response, key, contentType, fileName, true);

		logger.info("iosInstall End。");
	}

	@RequestMapping("/iosPlist")
	public void iosPlist(HttpServletRequest request, HttpServletResponse response) {
		logger.info("iosPlist Start。");

		String contentType = "text/xml";
		String fileName = "release.plist";
		String key = "IOSReleasePlist";

		download(request, response, key, contentType, fileName, true);

		logger.info("iosPlist End。");
	}

	/**
	 * 获取IOS版本号
	 * */
	@RequestMapping("/iosVersion")
	@ResponseBody
	public Json iosVersion(HttpServletRequest request, HttpServletResponse response) {
		logger.info("iosVersion Start。");

		//String key = "IOSReleasePlist";
		String key = "iosVersion";
		String filePath = null;
		String version = null;
		Json json = new Json();
		Properties prop = new Properties();
		InputStream ins = ReadProperties.class.getResourceAsStream("/init.properties");
		try {
			prop.load(ins);
			filePath = prop.getProperty(key).trim();
			version = prop.getProperty(key).trim();
		} catch (IOException e) {
			logger.error(e.getMessage());

			return json;
		}

		logger.info("ios version=" + version);
		if (!"".equals(version)) {
			json.setSuccess(true);
			json.setObj(version);
		}

		logger.info("iosVersion End。");
		return json;
	}

	@RequestMapping("/androidInstall")
	public void androidInstall(HttpServletRequest request, HttpServletResponse response) {
		logger.info("androidInstall Start。");

		//String contentType = "application/vnd.android.package-archive";
		String contentType = "application/octet-stream";
		String fileName = "chintData.apk";
		String key = "AndroidInstallFilePath";

		download(request, response, key, contentType, fileName, true);

		logger.info("androidInstall End。");
	}
	
	private void download(HttpServletRequest request, HttpServletResponse response, String key, String contType, String fileName, boolean isAttachment) {
		String filePath = null;

		Properties prop = new Properties();
		InputStream ins = ReadProperties.class.getResourceAsStream("/init.properties");
		try {
			prop.load(ins);
			filePath = prop.getProperty(key).trim();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		//String servletRealPath = request.getSession().getServletContext().getRealPath("/");

		File file = new File(filePath);
		try {
			FileInputStream in = new FileInputStream(file);
			response.setContentType(contType);
			// 如果指定为附件
			if (isAttachment) {
				response.setHeader("CONTENT-DISPOSITION", "attachment;filename=" + fileName);
			} else {				
				response.setHeader("CONTENT-DISPOSITION", "filename=" + fileName);
			}
			ServletOutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte b[] = new byte[1024];
			while (true) {
				int bytes = in.read(b);
				if (bytes == -1) {
					break;
				}
				baos.write(b, 0, bytes);
			}
			in.close();
			b = baos.toByteArray();
			response.setContentLength(b.length);
			out.write(b, 0, b.length);
			out.flush();
			out.close();

		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new AppInstallController().iosVersion(null,null);
	}
}
