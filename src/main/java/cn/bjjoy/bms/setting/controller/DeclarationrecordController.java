package cn.bjjoy.bms.setting.controller;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.setting.persist.model.Declarationrecord;import cn.bjjoy.bms.setting.service.DeclarationrecordService;import cn.bjjoy.bms.setting.service.EquiptypeService;import cn.bjjoy.bms.util.DataUtils;import cn.bjjoy.bms.util.MapUtils;import cn.bjjoy.bms.util.POIEasyExport;import cn.bjjoy.bms.util.PageUtils;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.StringUtils;import cn.bjjoy.bms.util.UserUtils;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@Controller@RequestMapping(value = "declarationrecord" )@CrossOriginpublic class DeclarationrecordController extends BaseController {	@Autowired	private DeclarationrecordService declarationrecordService;		@Autowired	private EquiptypeService equiptypeService;		    @RequestMapping(value = "index" )    public String index( ModelMap modelMap) {        return "/declare/index";    }	    @RequestMapping(value = "add" )    public String add( ModelMap modelMap) {        return "/declare/add";    }		    @RequestMapping(value = "edit" )    public String edit( @RequestParam Map map ,ModelMap modelMap) {    	Declarationrecord declare = declarationrecordService.queryOne((String)map.get("declareId")) ;    	modelMap.addAttribute("declare", declare) ;        return "/declare/edit";    }	    	@Description("-添加")	@RequestMapping(value="save")	@ResponseBody	public Response save(Declarationrecord declarationrecord) throws ControllerException {		declarationrecord.setId(null);		Long t = declarationrecordService.save(declarationrecord);		return Response.success(t);	}		@Description("-删除")	@RequestMapping(value="delete")	@ResponseBody	public Response delete(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		declarationrecordService.deleteOne(id);		return Response.success();	}	@Description("-根据主键查询")	@RequestMapping(value="one")	@ResponseBody	public Response one(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		Declarationrecord declarationrecord = declarationrecordService.queryOne(id);		return Response.success(declarationrecord);	}		@Description("-查询列表")	@RequestMapping(value="list")	@SuppressWarnings({ "rawtypes", "unchecked" })	@ResponseBody	public ResponseResult getNewestData( @RequestParam Map map ,  ModelMap modelMap) throws ControllerException {		Integer rows = Integer.valueOf((String) map.get("rows")) ;		Integer page = Integer.valueOf((String) map.get("page")) ;		map.put("page", (page - 1) * rows );		map.put("rows",rows);		boolean pageOK = PageUtils.isOK(map);		if(!pageOK)			return ResponseResult.error();				map.put("sort", "addTime");		map.put("order", "desc");				List<Map<String, Object>> equips = declarationrecordService.queryMapList(map);		List<Declarationrecord> declares = DataUtils.getDataArray(equips, Declarationrecord.class);				long count = declarationrecordService.queryMapListCount(map);        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("declares",equips);        responseResult.put("count",count);        return ResponseResult.ok(responseResult);	}		@Description("-根据主键修改")	@RequestMapping(value="update")	@ResponseBody	public Response update(@RequestParam Map<String, Object> map) throws ControllerException {		if(MapUtils.isEmpty(map, "id"))			return Response.error("id不能为空");		map.put("MeasuringPoint", map.get("measuringPoint")) ;		declarationrecordService.updateDeclareById(map);		return Response.success();	}		@SuppressWarnings({ "unchecked", "rawtypes" })	@RequestMapping(value="export")	public void export(@RequestParam Map map){		int systemId = UserUtils.getSystemId();		if(systemId != 0){			map.put("systemId", systemId) ;		}		int parentId = equiptypeService.getParentId(map) ;		if(parentId != 1){			List<Integer> ids = equiptypeService.getSubTypeIds(parentId,  (String)map.get("stationName")) ;			map.put("ids", ids);		}		List<Map<String, Object>> declaras = declarationrecordService.queryMapList(map) ;				List<Declarationrecord> declarationrecords = DataUtils.getDataArray(declaras, Declarationrecord.class);				POIEasyExport.exportExcel(declarationrecords,"申报记录","申报记录", Declarationrecord.class, "厚水智能-申报记录.xls",response);	}		}