package cn.bjjoy.bms.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.setting.service.EquiptypeService;

@Component
public class UserUtils {
	
	@Autowired
	private EquiptypeService equiptypeService;
	
	public static User getTokerUser(){
		Subject subject = SecurityUtils.getSubject();
		return (User)subject.getPrincipal();
	}
	
	public static User getUer(){
		return getTokerUser() ;
	}
	
	public static String getUername(){
		User user = getTokerUser() ;
		return user.getName();
	}
	
	public static Integer getUserId(){
		User user = getTokerUser() ;
		return user.getId();
	}
	
}
