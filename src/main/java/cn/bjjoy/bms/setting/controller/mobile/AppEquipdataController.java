package cn.bjjoy.bms.setting.controller.mobile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bjjoy.bms.exception.ControllerException;
import cn.bjjoy.bms.setting.controller.AbstractHosznController;
import cn.bjjoy.bms.setting.dto.CurrentEquipData;
import cn.bjjoy.bms.setting.dto.EquiptypeDto;
import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.util.DataUtils;
import cn.bjjoy.bms.util.DateUtils;
import cn.bjjoy.bms.util.UserUtils;

import com.alibaba.fastjson.JSON;

@SuppressWarnings({"rawtypes" ,"unchecked"})
@Controller
@CrossOrigin
@RequestMapping("app/equipdata")
public class AppEquipdataController extends AbstractHosznController{

	private static final Logger log = LogManager.getLogger();
	
    @RequestMapping(value = "/index" )
    public String index( @RequestParam Map paramMap ,ModelMap modelMap) {
    	User user = UserUtils.getTokerUser() ;
    	Map<String ,Object> map = new HashMap<>();
    	map.put("typeLayer", "1") ;
    	map.put("parentId",  String.valueOf(user.getParentId())) ;
    	map.put("order", "parentId") ;
    	map.put("sort", "asc");
		int systemId = equiptypeService.getUserSystemId(user.getId()) ;
		if(systemId != 0){
			map.put("systemId", String.valueOf(systemId)) ;
		}
		
		List<Map<String, Object>> types = equiptypeService.queryDirectSubTypes(map) ;
		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);
		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;
		modelMap.addAttribute("parentId",  String.valueOf(user.getParentId())) ;
		
		log.info("get equipdata list ,parent id : " + user.getParentId());
		
        return "app/equipdata/index";
    }
    
	
	@Description("-根据主键修改")
	@GetMapping(value="edit/{addressCode}/{dataId}")
	public String edit(@PathVariable String addressCode,@PathVariable Integer dataId ,  ModelMap modelMap) throws ControllerException {
		Map<String ,Object> data = equipdataService.queryStationCurrentData(String.valueOf(dataId)) ;
		if(data == null){
			data = new HashMap<>();
			data.put("dataId", 0);
			data.put("addresscode", addressCode) ;
			data.put("flowrate", 0);
			data.put("netcumulative", 0);
			data.put("online", 0);
			data.put("addTime", DateUtils.getCurrentDate());
		}
		modelMap.put("data", data) ;
		return "app/equipdata/edit";
	}
    

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

    @RequestMapping(value = "/map2" )
    public String map2(@RequestParam Map paramMap, ModelMap modelMap) {
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
		
        return "app/equipdata/map2";
    }
}
