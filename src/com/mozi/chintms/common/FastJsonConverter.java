package com.mozi.chintms.common;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mozi.chintms.common.utils.BeanUtil;

/**
 * fastjson自定义输出，增加过滤器
 * 
 * @author wuhanhua
 * 
 */
public class FastJsonConverter extends FastJsonHttpMessageConverter {

	private SerializerFeature[] features;

	@Override
	protected void writeInternal(final Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		OutputStream out = outputMessage.getBody();

		// 反射获取客户端版本号
		Object servletResponse = BeanUtil.getProperty(outputMessage, "servletResponse");
		Object response = BeanUtil.getProperty(servletResponse, "response");
		HttpServletRequest request = (HttpServletRequest) BeanUtil.getProperty(response, "request");
		final String clientVer = request.getHeader("clientVer") == null ? request.getParameter("clientVer") : request.getHeader("clientVer");

		byte[] bytes = JSON.toJSONString(obj, new PropertyPreFilter() {
			public boolean apply(JSONSerializer serializer, Object source, String name) {
				if (source == null) {
					return true;
				}

				// 用@JSONField过滤属性
				try {
					Set<String> includes = BeanUtil.getPropertyNames(source, JSONField.class);
					if (includes.size() == 0) {
						return true;
					}
					Field field = BeanUtil.getDeclaredFieldByName(source.getClass(), name);
					JSONField ann = field.getAnnotation(JSONField.class);
					if (ann == null) {
						return false;
					} else {
						if (clientVer != null && ann.name().equals("")) {
							return false;
						} else {
							return true;
						}
					}
				} catch (Exception e) {
					if (clientVer != null)
						return false;
					else
						return true;
				}

			}

		}, features).getBytes("UTF-8");

		out.write(bytes);
	}

	public SerializerFeature[] getFeatures() {
		return features;
	}

	public void setFeatures(SerializerFeature... features) {
		this.features = features;
	}
}
