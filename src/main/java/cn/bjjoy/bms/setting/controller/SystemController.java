package cn.bjjoy.bms.setting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.exception.ControllerException;
import cn.bjjoy.bms.setting.exception.ServiceException;
import cn.bjjoy.bms.util.Response;

@SuppressWarnings({"rawtypes","unchecked"})
@CrossOrigin
@Controller
@RequestMapping("system")
public class SystemController extends AbstractHosznController{

	private static final Logger logger = LogManager.getLogger();

	/**
	 * 角色管理初始化页面
	 * @return
	 */
	@GetMapping("index")
	public String index() {
		return "/system/index";
	}

    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @GetMapping("getList")
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
		logger.info("get system list success");
        return ResponseResult.ok(responseResult);
    }

    /**
     * 到创建角色页面
     * @return
     */
    @RequestMapping("add" )
    public String toAdd() {
        return "/system/add";
    }

	@Description("-添加")
	@PostMapping(value="add")
	@ResponseBody
	public Response add(@RequestParam Map map ) throws ControllerException {
		String flag = "" ;
		try {
			tenantService.add(map);
			flag = "添加成功" ;
		} catch (ServiceException e) {
			flag = "添加失败" ;
		} catch (SecurityException e) {
			flag = "添加失败" ;
		} catch (IllegalArgumentException e) {
			flag = "添加失败" ;
		}
		return Response.success(flag);
	}
	

	@Description("-编辑")
	@PutMapping(value="update")
	@ResponseBody
	public Response update(@RequestParam Map map ) throws ControllerException {
		String flag = "" ;
		try {
			tenantService.updateSystem(map);
			flag = "编辑成功" ;
		} catch (ServiceException e) {
			flag = "编辑失败" ;
		} catch (SecurityException e) {
			flag = "编辑失败" ;
		} catch (IllegalArgumentException e) {
			flag = "编辑失败" ;
		}
		return Response.success(flag);
	}

    /**
     * 到创建角色页面
     * @return
     */
    @GetMapping("edit")
    public String toEdit(@RequestParam Map map ,  ModelMap modelMap) {
    	List<Map<String, Object>> tenart = null;
    	if(!Objects.isNull(map.get("id"))){
    		tenart = tenantService.getTenantsById(Integer.valueOf(map.get("id").toString()));
    	}
    	modelMap.put("currentSystem", tenart.size() > 0 ? tenart.get(0) : null);
        return "/system/edit";
    }
    
    
    /**
     * 删除 添加的系统
     * @return
     */
    @DeleteMapping("delete" )
    @ResponseBody
    public ResponseResult toDel(@RequestParam Map map ,  ModelMap modelMap) {
    	tenantService.deleteSystem(map);
        return ResponseResult.ok(map.get("id"));
    }
    
    
}
