package cn.bjjoy.bms.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import cn.bjjoy.bms.setting.exception.ServiceException;

import com.alibaba.fastjson.JSON;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	
    public static void populate(Object bean, Map<String, ? extends Object> properties) throws IllegalAccessException, InvocationTargetException {

    	ConvertUtils.deregister();
    	
		ConvertUtils.register(new Converter() {
			@SuppressWarnings("unchecked")
			@Override
			public <T> T convert(Class<T> type, Object value) {
				if(value == null)
					return null;
				return (T) DateUtils.toDate(value.toString());
			}
		}, Date.class);
		
		ConvertUtils.register(new Converter() {
			@SuppressWarnings("unchecked")
			@Override
			public <T> T convert(Class<T> type, Object value) {
				return (T) StringUtils.toInteger(value);
			}
		}, Integer.class);

		org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
		
		ConvertUtils.deregister();
	}
    
    public static void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
    	
    	ConvertUtils.deregister();
    	
		ConvertUtils.register(new Converter() {
			@SuppressWarnings("unchecked")
			@Override
			public <T> T convert(Class<T> type, Object value) {
				if(value == null)
					return null;
				return (T) DateUtils.toDate(value.toString());
			}
		}, Date.class);
		
		org.apache.commons.beanutils.BeanUtils.copyProperty(bean, name, value);
		
		ConvertUtils.deregister();
    }

    public static void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
    	
    	ConvertUtils.deregister();
    	
		ConvertUtils.register(new Converter() {
			@SuppressWarnings("unchecked")
			@Override
			public <T> T convert(Class<T> type, Object value) {
				if(value == null)
					return null;

				if(value instanceof Date)
					return (T) value;
				
				return (T) DateUtils.toDate(value.toString());
			}
		}, Date.class);
		
		org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		
		ConvertUtils.deregister();
	}
    
    public static <T> T parseObject(Map<String, ? extends Object> properties, Class<T> clazz) {
    	
    	String json = JSON.toJSONString(properties);
    	
        return JSON.parseObject(json, clazz);
    }
    
    public static <T> T parseObject(Object bean, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(bean), clazz);
    }
    
/*  public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                String K = f.getName();
                Object V = f.get(obj);
                map.put(K, V);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }*/
    
    @SuppressWarnings({"rawtypes", "unchecked"})
	public static Map<String,Object> toMap(Object bean) throws ServiceException {
    	
    	if(bean == null)
    		return null;
    	
    	if(bean instanceof Map){
    		return (Map<String, Object>) bean;
    	}
    		
    	Map<String,Object> returnMap = new HashMap<String,Object>();

        Class type = bean.getClass();
        try {
	        BeanInfo beanInfo = Introspector.getBeanInfo(type);
	
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
	        
	        for (int i = 0; i< propertyDescriptors.length; i++) {
	        	
	            PropertyDescriptor descriptor = propertyDescriptors[i];
	            
	            String key = descriptor.getName();
	          
	            if (key.equals("class"))
	            	continue;

	            if (key.equals("valid"))
	            	continue;

	            Method readMethod = descriptor.getReadMethod();
	            Object result = readMethod.invoke(bean, new Object[0]);
	            
	            if(result == null)
	            	continue;

	            returnMap.put(key,result);
	        }
		} catch (Exception e) {
			throw new ServiceException(e);
		}
        return returnMap;
    }
}
