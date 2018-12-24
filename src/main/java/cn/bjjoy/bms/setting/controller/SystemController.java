package cn.bjjoy.bms.setting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.setting.service.TenantService;

@CrossOrigin
@Controller
@RequestMapping("system")
public class SystemController {

	
	@Autowired
	TenantService tenantService;
	
	/**
	 * 角色管理初始化页面
	 * @return
	 */
	@RequestMapping(value = "/index" , method = RequestMethod.GET)
	public String index() {
		return "/system/index";
	}

    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseResult getList(@RequestParam Map map ,  ModelMap modelMap){
        Integer rows = Integer.valueOf((String) map.get("rows")) ;
		Integer page = Integer.valueOf((String) map.get("page")) ;
		map.put("page", (page - 1) * rows );
		map.put("rows",rows);

		List<Map<String,Object>> tenants = tenantService.getTenants(map);
		int count = tenantService.getCount(map);
		
		Map<String, Object> responseResult = new HashMap<>();
		responseResult.put("tenants",tenants);
		responseResult.put("count",count);
		
        return ResponseResult.ok(responseResult);
    }

    /**
     * 到创建角色页面
     * @return
     */
    @RequestMapping(value = "/add" )
    public String toAdd() {
        return "/system/add";
    }

    
    
}
