package cn.bjjoy.bms.setting.mobile.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import cn.bjjoy.bms.setting.dto.CurrentEquipData;
import cn.bjjoy.bms.setting.dto.EquiptypeDto;
import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.setting.service.SystemService;
import cn.bjjoy.bms.setting.service.TenantService;
import cn.bjjoy.bms.util.DataUtils;
import cn.bjjoy.bms.util.UserUtils;

@Controller
@RequestMapping("app/equipdata")
public class AppEquipdataController {

	private static final Logger log = LogManager.getLogger();
	
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd") ;
	
	@Autowired
	SystemService systemService ;
	
	@Autowired
	private TenantService tenantService ;
	
	@Autowired
	private EquipdataService equipdataService;
	
	@Autowired
	private EquiptypeService equiptypeService;
	
    @RequestMapping(value = "/index" )
    public String index( @RequestParam Map paramMap ,ModelMap modelMap) {
    	User user = UserUtils.getTokerUser() ;
    	Map<String ,Object> map = new HashMap<>();
    	map.put("typeLayer", "1") ;
    	map.put("parentId", user.getParentId()) ;
    	map.put("order", "parentId") ;
    	map.put("sort", "asc");
		int systemId = equiptypeService.getUserSystemId(user.getId()) ;
		if(systemId != 0){
			map.put("systemId", String.valueOf(systemId)) ;
		}
		
		List<Map<String, Object>> types = equiptypeService.queryDirectSubTypes(map) ;
		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);
		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;
		modelMap.addAttribute("parentId", user.getParentId()) ;
		
		log.info("get equipdata list ,parent id : " + user.getParentId());
		
        return "app/equipdata/index";
    }
	
    @SuppressWarnings({"rawtypes","unchecked"})
    @RequestMapping(value = "/map" )
    public String map(@RequestParam Map paramMap ,ModelMap modelMap) {
    	Map map = new HashMap<>();
		int parentId = equiptypeService.getParentId(map) ;
		if(parentId != 1){
			List<Integer> ids = equiptypeService.getSubTypeIds2(parentId,  (String)map.get("stationName")) ;
			map.put("ids", ids);
		}
		
		List<Map<String, Object>> equips = equipdataService.queryOne(map) ;
		List<CurrentEquipData> equipDataList = DataUtils.getDataArray(equips, CurrentEquipData.class);
		equipDataList = equipdataService.setWifi(equipDataList , null);
		String stringListJson = JSON.toJSONString(equipDataList);
		modelMap.addAttribute("equipDataList", stringListJson);
		
        return "app/equipdata/map";
    }
	
	
	
}
