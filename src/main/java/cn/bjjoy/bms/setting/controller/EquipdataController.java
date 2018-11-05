package cn.bjjoy.bms.setting.controller;import java.util.HashMap;import java.util.LinkedList;import java.util.List;import java.util.Map;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import com.alibaba.fastjson.JSON;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.setting.dto.CurrentEquipData;import cn.bjjoy.bms.setting.dto.EquiptypeDto;import cn.bjjoy.bms.setting.dto.OnlineOfflineNums;import cn.bjjoy.bms.setting.persist.model.Equipdata;import cn.bjjoy.bms.setting.service.EquipdataService;import cn.bjjoy.bms.setting.service.EquiptypeService;import cn.bjjoy.bms.setting.service.SystemService;import cn.bjjoy.bms.util.DataUtils;import cn.bjjoy.bms.util.DateUtils;import cn.bjjoy.bms.util.MapUtils;import cn.bjjoy.bms.util.PageUtils;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.StringUtils;import cn.bjjoy.bms.util.UserUtils;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@Controller@CrossOrigin@SuppressWarnings({ "unchecked", "rawtypes" })@RequestMapping(value="equipdata")public class EquipdataController extends BaseController {	Logger logger = LoggerFactory.getLogger(EquipdataController.class) ;		@Autowired	SystemService systemService ;		@Autowired	private EquipdataService equipdataService;		@Autowired	private EquiptypeService equiptypeService;		@Description("-添加")	@RequestMapping(value="save")	public Response save(Equipdata equipdata) throws ControllerException {		equipdataService.save(equipdata);		return Response.success(equipdata.getId());	}		@Description("-删除")	@RequestMapping(value="delete")	public Response delete(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		equipdataService.deleteOne(id);		return Response.success();	}	@Description("-根据主键查询")	@RequestMapping(value="one")	public Response one(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		Equipdata equipdata = equipdataService.queryOne(id);		return Response.success(equipdata);	}	    @RequestMapping(value = "/index" )    public String index( @RequestParam Map paramMap ,ModelMap modelMap) {    	Map<String ,Object> map = new HashMap<>();    	map.put("typeLayer", "1") ;    	map.put("parentId", paramMap.containsKey("parentId") ? paramMap.containsKey("parentId") : "1") ;    	map.put("order", "parentId") ;    	map.put("sort", "asc");    	int systemId = UserUtils.getSystemId();		if(systemId != 0){			map.put("systemId", String.valueOf(systemId)) ;		}				List<Map<String, Object>> types = equiptypeService.getSubType(map) ;		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;		modelMap.addAttribute("parentId", paramMap.containsKey("parentId") ? paramMap.get("parentId") : "1") ;        return "/equipdata/index";    }    	@RequestMapping(value = "/map" )    public String map( ModelMap modelMap) {    	Map map = new HashMap<>();		int parentId = equiptypeService.getParentId(map) ;		if(parentId != 1){			List<Integer> ids = equiptypeService.getSubTypeIds(parentId,  (String)map.get("stationName")) ;			map.put("ids", ids);		}				List<Map<String, Object>> equips = equipdataService.queryOne(map) ;		List<CurrentEquipData> equipDataList = DataUtils.getDataArray(equips, CurrentEquipData.class);		equipDataList = equipdataService.setWifi(equipDataList);		String stringListJson = JSON.toJSONString(equipDataList);		modelMap.addAttribute("equipDataList", stringListJson);        return "/equipdata/map";    }        @RequestMapping(value = "/countMsg" )    @ResponseBody    public ResponseResult getCoungMsg(){    	Map<String,Object> map = new HashMap<>();		try {	    	map.put("typeLayer", "1") ;	    	map.put("parentId", "1") ;	    	map.put("order", "parentId") ;	    	map.put("sort", "asc");	    		    	int systemId = UserUtils.getSystemId();			if(systemId != 0){				map.put("systemId", String.valueOf(systemId)) ;			}			List<Map<String, Object>> types = equiptypeService.getSubType(map) ;			LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);			map.put("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;				int parentId = equiptypeService.getParentId(map) ;			List<Integer> ids = equiptypeService.getSubTypeIds(parentId,  (String)map.get("stationName")) ;			map.put("ids", ids);			List<OnlineOfflineNums> onlineOfflineNumsList = equipdataService.queryOnlineAndOffLineNums(map) ;			map.put("allNums" , onlineOfflineNumsList.get(0).getNum() + onlineOfflineNumsList.get(1).getNum()) ;			map.put("onLineNums" , onlineOfflineNumsList.get(0).getNum()) ;			map.put("offLineNums" , onlineOfflineNumsList.get(1).getNum()) ;			map.put("onLineNumsRate" ,DataUtils.formattedDecimalToPercentage(onlineOfflineNumsList.get(0).getNum() , onlineOfflineNumsList.get(1).getNum())) ;						map.put("currentDay" , DateUtils.getCurrentDateZZ() + " " + DateUtils.getCurrentDayOfWeekZZ()) ;			map.put("username" , UserUtils.getUername()) ;			map.put("systemInfo" , systemService.getSystem(systemId)) ;		} catch (Exception e) {			e.printStackTrace();			return ResponseResult.error();		}		return ResponseResult.ok(map);    }    	@Description("-查找最新数据")	@RequestMapping(value="newest")	@ResponseBody	public ResponseResult getNewestData(@RequestParam Map map ,  ModelMap modelMap) throws ControllerException {		Map<String, Object> responseResult = new HashMap<>();		List<CurrentEquipData> equipList = null ;		try {						equipList = getEquipList(map) ;						long count = equipdataService.getCurrentAccount(map);			responseResult.put("equipList",equipList);			responseResult.put("count",count);						modelMap.addAttribute("subTypeList2", equiptypeService.getSubType(map)) ;		} catch (NumberFormatException e) {			logger.error(e.getMessage(), e);		}                return ResponseResult.ok(responseResult);	}		public List<CurrentEquipData> getEquipList(Map map){		List<CurrentEquipData> equipList = null ;		try{			Integer rows = Integer.valueOf((String) map.get("rows")) ;			Integer page = Integer.valueOf((String) map.get("page")) ;			map.put("page", (page - 1) * rows );			map.put("rows",rows);						int systemId = UserUtils.getSystemId();			if(systemId != 0){				map.put("systemId", systemId) ;			}			int parentId = equiptypeService.getParentId(map);			if(parentId != 1){				List<Integer> ids = equiptypeService.getSubTypeIds(parentId,  (String)map.get("stationName")) ;				map.put("ids", ids);			}			List<Map<String, Object>> equips = equipdataService.queryOne(map) ;			equipList = DataUtils.getDataArray(equips, CurrentEquipData.class);						equipList = equipdataService.setWifi(equipList);		}catch(Exception e){			logger.debug("", e);		}		return equipList;	}		@Description("-查询列表")	@RequestMapping(value="list")	@ResponseBody	public ResponseResult list(@RequestParam Map<String, Object> map) throws ControllerException {		Integer rows = Integer.valueOf((String) map.get("rows")) ;		Integer page = Integer.valueOf((String) map.get("page")) ;		map.put("page", (page - 1) * rows );		map.put("rows",rows);		List<Map<String, Object>> equips = equipdataService.getHistoryNoPage(map);		List<CurrentEquipData> equipList = DataUtils.getDataArray(equips, CurrentEquipData.class);		long count = equipdataService.getHistoryAccountNoPage();        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("equipList",equipList);        responseResult.put("count",count);		return ResponseResult.ok(responseResult);	}		@Description("-根据主键修改")	@RequestMapping(value="update")	public Response update(@RequestParam Map<String, Object> map) throws ControllerException {		if(MapUtils.isEmpty(map, "id"))			return Response.error("id不能为空");		equipdataService.updateOne(map);		return Response.success();	}		@Description("-查询列表")	@RequestMapping(value="analysis")	@ResponseBody	public ResponseResult analysis(@RequestParam Map<String, Object> map) throws ControllerException {		Integer rows = Integer.valueOf((String) map.get("rows")) ;		Integer page = Integer.valueOf((String) map.get("page")) ;		map.put("page", (page - 1) * rows );		map.put("rows",rows);		List<Map<String, Object>> equips = equipdataService.getHistoryNoPage(map);		List<CurrentEquipData> equipList = DataUtils.getDataArray(equips, CurrentEquipData.class);		long count = equipdataService.getHistoryAccountNoPage();        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("equipList",equipList);        responseResult.put("count",count);		return ResponseResult.ok(responseResult);	}		}