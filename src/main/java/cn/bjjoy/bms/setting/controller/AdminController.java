package cn.bjjoy.bms.setting.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import cn.bjjoy.bms.setting.dto.EquiptypeDto;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.util.DataUtils;
import cn.bjjoy.bms.util.UserUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger( AdminController.class) ;
	
	@Autowired
	private EquiptypeService equiptypeService;
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}

	/**
	 * 用户登录验证
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			
			subject.getPrincipal();
			
			return redirect("/equipdata/index");
		} catch (AuthenticationException e) {
			logger.info("Login error message..................");
			e.printStackTrace();
		}
		return "admin/login";
	}

	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return redirect("admin/login");
	}

	@RequestMapping(value ={"","/index"}, method = RequestMethod.GET)
	public String index( ModelMap modelMap){
    	Map<String ,String> map = new HashMap<>();
    	map.put("typeLayer", "1") ;
    	map.put("parentId", "1") ;
    	map.put("order", "parentId") ;
    	map.put("sort", "asc");
    	int systemId = UserUtils.getSystemId();
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
	private String redirect(String path) {
		return "redirect:" + path;
	}
}
