package cn.bjjoy.bms.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.bjjoy.bms.setting.entity.User;

public class UserUtils {
	
	private static Map<String , Integer> userSystemIdMap ;
	static{
		userSystemIdMap = new HashMap<>() ;
		userSystemIdMap.put("admin", 0) ;
		userSystemIdMap.put("superadmin", 0) ;
		userSystemIdMap.put("liuyang", 1) ;
		userSystemIdMap.put("hs1188", 1) ;
		userSystemIdMap.put("DFSLJ01", 1) ;
		userSystemIdMap.put("DFSL", 4) ;
		userSystemIdMap.put("史糖", 0) ;
	}
	
	public static User getTokerUser(){
		Subject subject = SecurityUtils.getSubject();
		return (User)subject.getPrincipal();
	}
	
	public static int getSystemId(){
		User user = getTokerUser() ;
		return userSystemIdMap.get(user.getLoginName()) ;
	}

	public static int getSystemId(String loginName){
		return userSystemIdMap.get(loginName) ;
	}

	
	public static int getTopTypeId(){
		User user = getTokerUser() ;
		return userSystemIdMap.get(user.getLoginName()) ;
	}
	
	public static String getUername(){
		User user = getTokerUser() ;
		return user.getName();
	}
}
