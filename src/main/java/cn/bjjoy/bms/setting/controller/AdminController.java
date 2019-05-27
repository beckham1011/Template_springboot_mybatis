package cn.bjjoy.bms.setting.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bjjoy.bms.setting.dto.EquiptypeDto;
import cn.bjjoy.bms.util.DataUtils;
import cn.bjjoy.bms.util.DeviceUtil;
import cn.bjjoy.bms.util.EncryptUtils;
import cn.bjjoy.bms.util.RedirectUtil;
import cn.bjjoy.bms.util.UserUtils;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractHosznController {

	private static final Logger logger = LogManager.getLogger();
	
	
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request) {
		return "admin/login";
	}

	/**
	 * 用户登录验证
	 */
	@PostMapping(value = "/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, 
				ModelMap model , HttpServletRequest request) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if(DeviceUtil.isApp(request)){
				password = userService.findUserByName(username).getPassword();
			}else{
				password = EncryptUtils.encryptMD5(password);
			}
			
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			subject.getPrincipal();
			//跳转页面
			return RedirectUtil.redirect(getNextPage(request));
		} catch (AuthenticationException e) {
			e.printStackTrace();
			logger.info("Login error message.................." + e);
		}
		return "admin/login";
	}

	@GetMapping(value = "/logout")
	public String logout( HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return RedirectUtil.redirect("/login");
	}

	@GetMapping(value ={"","/index"})
	public String index( ModelMap modelMap){
    	Map<String ,String> map = new HashMap<>();
    	map.put("typeLayer", "1") ;
    	map.put("parentId", String.valueOf(UserUtils.getUer().getParentId())) ;
    	map.put("order", "parentId") ;
    	map.put("sort", "asc");

    	int systemId = equiptypeService.getUserSystemId(UserUtils.getUer().getId()) ;
				
		map.put("systemId", String.valueOf(systemId)) ;
		List<Map<String, Object>> types = equiptypeService.getSubType(map) ;
		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);
		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;
		return "admin/index";
	}

	/**
	 * 带参重定向
	 *
	 * @param path
	 * @return
	 */
	public String getNextPage(HttpServletRequest request){
		String URL = "login";
		if(!DeviceUtil.isApp(request)){
			URL = "/equipdata/index";
		}else{
			URL = "/appmainpage";
		}
		return URL;
	}

}
