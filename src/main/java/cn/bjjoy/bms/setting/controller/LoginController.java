package cn.bjjoy.bms.setting.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cn.bjjoy.bms.util.DeviceUtil;
import cn.bjjoy.bms.util.RedirectUtil;

@Controller
public class LoginController {
	
	private static final Logger log = LogManager.getLogger();
	
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request) {
		String URL = "app/login/applogin";
		if(!DeviceUtil.isApp(request)){
			URL = "admin/login";
		}
		log.info(" Login url : " + URL);
		return URL;
	}
	
	
	@GetMapping(value = "/appmainpage")
	public String appMainpage(HttpServletRequest request) {
		String URL = "app/login/appmainpage";
		log.info(" main page url : " + URL);
		return URL;
	}

	
	@GetMapping(value = "/logout")
	public String logout( HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return RedirectUtil.redirect("/login");
	}
	
}
