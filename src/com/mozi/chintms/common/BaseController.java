package com.mozi.chintms.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mozi.chintms.common.exception.BusiException;
import com.mozi.chintms.common.exception.ExceptionMsg;
import com.mozi.chintms.common.exception.Exceptions;

/**
 * 基础控制器类<br>
 * 
 * @author wuhanhua
 * @version 1.0
 */
public class BaseController {

	protected final Logger logger_sys = LoggerFactory.getLogger("sys_info");
	protected final Logger logger_error = LoggerFactory.getLogger("error_stack");

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ExceptionMsg handleException(Exception ex, HttpServletRequest request) {
		logger_error.error(ex.getMessage());
		// 未登录
		//String msg = null;
		if (ex instanceof BusiException) {
			return new ExceptionMsg((BusiException)ex);
		} else {
			BusiException exception = new BusiException(Exceptions.ERROR_SYS);
			return new ExceptionMsg(exception);
		}
	}

	/**
	 * 输出excel文件
	 * 
	 * @param response
	 * @param fileName
	 * @param data
	 * @throws BusiException
	 */
	public void print(HttpServletResponse response, String fileName, byte[] data) throws BusiException {
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName + ".xls").getBytes(), "ISO8859-1"));
			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			throw new BusiException(Exceptions.ERROR_SYS);
		}
	}
}
