package com.mozi.chintms.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mozi.chintms.common.BaseDomain;

public class BeanUtil {

	/**
	 * 反射，根据当前传入对象实例，属性名，返回执行后的值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object getProperty(Object obj, String fieldName) {
		if (obj == null || fieldName == null) {
			return null;
		}
		String m = fieldName.substring(0, 1).toUpperCase();
		m = "get" + m + fieldName.substring(1, fieldName.length());
		try {
			return obj.getClass().getMethod(m, new Class[0]).invoke(obj);
		} catch (Exception e) {
			m = fieldName.substring(0, 1).toUpperCase();
			m = "is" + m + fieldName.substring(1, fieldName.length());
			try {
				return obj.getClass().getMethod(m, new Class[0]).invoke(obj);
			} catch (Exception e1) {
				try {
					Field field= getDeclaredFieldByName(obj.getClass(), fieldName);
					field.setAccessible(true);
					return field.get(obj);
				} catch (Exception e2) {
					return null;
				}
			} 
		}
	}
	
	/**
	 * 反射，根据当前传入对象实例获取所有basedomain子类对象属性
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static List<Object> getBdObjProperties(Object obj) {
		if (obj == null) {
			return null;
		}
		List<Object> obList = new ArrayList<Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field f : fields) {
			if(!f.getType().isPrimitive() && BaseDomain.class.isAssignableFrom((Class)f.getType())){
				try {
					f.setAccessible(true);
					if(f.get(obj) != null){
						obList.add(f.get(obj));
					}
				} catch (Exception e) {
				}
			}
		}
		return obList.size() > 0 ? obList : null;
	}
	
	/**
	 * 反射，根据传入的注解，返回属性的值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object getProperty(Object obj, Class ann) {
		if (obj == null || ann == null) {
			return null;
		}
		String fieldName = null;
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field f : fields) {
			if(f.getAnnotation(ann) != null){
				fieldName = f.getName();
				break;
			}
		}
		
		String m = fieldName.substring(0, 1).toUpperCase();
		m = "get" + m + fieldName.substring(1, fieldName.length());
		try {
			return obj.getClass().getMethod(m, new Class[0]).invoke(obj);
		} catch (Exception e) {
			m = fieldName.substring(0, 1).toUpperCase();
			m = "is" + m + fieldName.substring(1, fieldName.length());
			try {
				return obj.getClass().getMethod(m, new Class[0]).invoke(obj);
			} catch (Exception e1) {
			} 
		}
		return null;
	}

	/**
	 * 反射，根据当前传入对象实例，属性名，设置值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param val
	 */
	public static void setProperty(Object obj, String fieldName, Object val) {
		if (obj == null || fieldName == null) {
			return;
		}
		String m = fieldName.substring(0, 1).toUpperCase();
		m = "set" + m + fieldName.substring(1, fieldName.length());
		try {
			Method[] methods = getAllMethods(obj.getClass());
			for (Method method : methods) {
				if (m.equals(method.getName())) {
					method.invoke(obj, val);
					break;
				}
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 递归查找所有的属性，包括父类的属性
	 * 
	 * @param object
	 * @return
	 */
	public static Field[] getAllDeclaredFields(Class clazz) {

		if (clazz != null && clazz != Object.class) {
			Field[] fields = clazz.getDeclaredFields();

			Field[] resFields = fields;

			Field[] fields_ = getAllDeclaredFields(clazz.getSuperclass());

			if (fields_ != null) {
				resFields = new Field[fields.length + fields_.length];
				System.arraycopy(fields, 0, resFields, 0, fields.length);
				System.arraycopy(fields_, 0, resFields, fields.length, fields_.length);
			}
			return resFields;
		}
		return null;
	}
	
	/**
	 * 查找属性，包括父类的属性
	 * 
	 * @param object
	 * @return
	 */
	public static Field getDeclaredFieldByName(Class clazz, String fieldName) {
		for(Field f : getAllDeclaredFields(clazz)){
			if(f.getName().equals(fieldName))
				return f;
		}
		return null;
	}
	
	/**
	 * 根据方法名获取方法
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Method getMethod(Class clazz, String name){
		for(Method m : getAllMethods(clazz)){
			if(m.getName().equals(name)){
				return m;
			}
		}
		return null;
	}
	
	/**
	 * 递归获取所有的方法
	 * @param clazz
	 * @return
	 */
	public static Method[] getAllMethods(Class clazz){
		if (clazz != null && clazz != Object.class) {
			Method[] methods = clazz.getDeclaredMethods();

			Method[] resMethods = methods;

			Method[] methods_ = getAllMethods(clazz.getSuperclass());

			if (methods_ != null) {
				resMethods = new Method[methods.length + methods_.length];
				System.arraycopy(methods, 0, resMethods, 0, methods.length);
				System.arraycopy(methods_, 0, resMethods, methods.length, methods_.length);
			}

			return resMethods;
		}
		return null;
	}
	
	/**
	 * 反射，根据传入的注解，返回属性的名称集合
	 * 
	 * @param obj
	 * @param ann
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> getPropertyNames(Object obj, Class ann) {
		if (obj == null || ann == null) {
			return null;
		}
		Set<String> fieldNames = new HashSet<String>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field f : fields) {
			if(f.getAnnotation(ann) != null){
				fieldNames.add(f.getName());
			}
		}
		return fieldNames;
	}
}
