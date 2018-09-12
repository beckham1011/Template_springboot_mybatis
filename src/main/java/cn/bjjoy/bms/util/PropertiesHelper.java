/*
 * Pactera Technology International Ltd.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package cn.bjjoy.bms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: PC-BHEC</p>
 * Name: 读取配置文件类
 * Description: 
 * Create Date: 2014年12月18日 下午10:18:06
 * File: com.cpbhec.helper.PropertiesHelper.java 
 * @author Sky.liu
 * @version 1.0
 */
public class PropertiesHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHelper.class);

	private static Properties props = new Properties();

	static {
		InputStream inputStream = PropertiesHelper.class.getClassLoader()
				.getResourceAsStream("application.properties");
		try {
			props.load(inputStream);
		} catch (IOException e) {
			LOGGER.error("Could not found file application.properties");
			throw new RuntimeException("Could not found file application.properties");
		}
	}

	/**
	 * Name: 读取配置文件配置参数值
	 * Description: 
	 * @author Sky.liu
	 * @date 2014年12月18日 下午10:19:03
	 * @param key 键
	 * @return 值
	 */
	public static final String getProperty(String key) {

		return props.getProperty(key);
	}
	public static void main(String[] args) {
		System.out.println(getProperty("USER_NAME"));
	}
}