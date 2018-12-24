package cn.bjjoy.bms.setting.controller;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.setting.persist.model.Datacard;import cn.bjjoy.bms.setting.service.DatacardService;import cn.bjjoy.bms.setting.service.EquiptypeService;import cn.bjjoy.bms.util.DataUtils;import cn.bjjoy.bms.util.MapUtils;import cn.bjjoy.bms.util.POIEasyExport;import cn.bjjoy.bms.util.PageUtils;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.StringUtils;import cn.bjjoy.bms.util.UserUtils;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@CrossOrigin@Controllerpublic class DatacardController extends BaseController {	@Autowired	private DatacardService datacardService;		@Autowired	private EquiptypeService equiptypeService;	    @RequestMapping(value = "datacard/index" )    public String index( ModelMap modelMap) {        return "/datacard/index";    }		@Description("-添加页面")	@RequestMapping(value="datacard/add")	public String add(ModelMap modelMap) throws ControllerException {		modelMap.put("costStandards", datacardService.getCostStandard()) ;		return "datacard/add";	}		@Description("-添加页面")	@RequestMapping(value="datacard/edit")	public String edit( @RequestParam Map map ,  ModelMap modelMap) throws ControllerException {		List<Map<String, Object>> datacards = datacardService.queryMapList(map);		Datacard datacard = datacards.size() > 0 ? DataUtils.getDataArray(datacards, Datacard.class).get(0) : null ;		modelMap.put("datacard", datacard) ;		modelMap.put("costStandards", datacardService.getCostStandard()) ;		return "datacard/edit";	}    		@Description("-添加")	@RequestMapping(value="datacard/save")	@ResponseBody	public Response save(Datacard datacard) throws ControllerException {		datacard.setId(null);		datacardService.save(datacard);		return Response.success(datacard.getId());	}		@Description("-删除")	@RequestMapping(value="datacard/delete")	@ResponseBody	public Response delete(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		datacardService.deleteOne(id);		return Response.success();	}	@Description("-根据主键查询")	@RequestMapping(value="datacard/one")	@ResponseBody	public Response one(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		Datacard datacard = datacardService.queryOne(id);		return Response.success(datacard);	}		@Description("-查询列表")	@SuppressWarnings({ "rawtypes", "unchecked" })	@RequestMapping(value="datacard/list")	@ResponseBody	public ResponseResult getNewestData( @RequestParam Map map ,  ModelMap modelMap) throws ControllerException {				Integer rows = Integer.valueOf((String) map.get("rows")) ;		Integer page = Integer.valueOf((String) map.get("page")) ;		map.put("page", (page - 1) * rows );		map.put("rows",rows);		boolean pageOK = PageUtils.isOK(map);		if(!pageOK)			return ResponseResult.error();				map.put("sort", "addTime");		map.put("order", "asc");				List<Map<String, Object>> equips = datacardService.queryMapList(map);		List<Datacard> datacards = DataUtils.getDataArray(equips, Datacard.class);		datacards = datacardService.convertStandardAndStatus(datacards) ;				long count = datacardService.queryMapListCount(map);        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("datacards",datacards);        responseResult.put("count",count);                return ResponseResult.ok(responseResult);	}		@Description("-根据主键修改")	@RequestMapping(value="datacard/update")	@ResponseBody	public Response update(@RequestParam Map<String, Object> map) throws ControllerException {		if(MapUtils.isEmpty(map, "id"))			return Response.error("id不能为空");		datacardService.updateById(map);		return Response.success();	}			@SuppressWarnings({ "unchecked", "rawtypes" })	@RequestMapping(value="datacard/export")	public void export(@RequestParam Map map){				int systemId = map.containsKey("systemId") 				? Integer.valueOf(String.valueOf(map.get("systemId"))) 				: UserUtils.getSystemId();		if(systemId != 0){			map.put("systemId", systemId) ;		}		int parentId = equiptypeService.getParentId(map) ;		if(parentId != 1){			List<Integer> ids = equiptypeService.getSubTypeIds(parentId,  (String)map.get("stationName")) ;			map.put("ids", ids);		}		List<Map<String, Object>> datacards = datacardService.queryMapList(map) ;		List<Datacard> equipList = DataUtils.getDataArray(datacards, Datacard.class);				POIEasyExport.exportExcel(equipList,"数据卡维护","数据卡列表", Datacard.class,"厚水智能-数据卡列表.xls",response);			}		}