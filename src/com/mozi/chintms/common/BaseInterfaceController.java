package com.mozi.chintms.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.common.exception.BusiException;


/**
 * 接口调用专用baseController
 * @author gwb
 *
 */
public class BaseInterfaceController {

	protected final Logger logger_sys = LoggerFactory.getLogger("sys_info");
	protected final Logger logger_error = LoggerFactory.getLogger("error_stack");

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public void handleException(Exception ex, HttpServletRequest request,HttpServletResponse response) {
		logger_error.error(ex.getMessage());
		String json=ex.getMessage();
		// 未登录
		//String msg = null;
		if (ex instanceof BusiException) {
			writJson(json,response);
			
		} 
	}


	public void writJson(String json,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
