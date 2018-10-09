package cn.bjjoy.bms.setting.controller;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.setting.persist.model.Maintainrecord;import cn.bjjoy.bms.setting.service.MaintainrecordService;import cn.bjjoy.bms.util.DataUtils;import cn.bjjoy.bms.util.MapUtils;import cn.bjjoy.bms.util.PageUtils;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.StringUtils;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@Controller@CrossOriginpublic class MaintainrecordController extends BaseController {	@Autowired	private MaintainrecordService maintainrecordService;	    @RequestMapping(value = "maintainrecord/index" )    public String index( ModelMap modelMap) {        return "/maintanence/index";    }		@Description("-添加")	@RequestMapping(value="maintainrecord/save")	public Response save(Maintainrecord maintainrecord) throws ControllerException {		maintainrecordService.save(maintainrecord);		return Response.success(maintainrecord.getId());	}		@Description("-删除")	@RequestMapping(value="maintainrecord/delete")	public Response delete(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		maintainrecordService.deleteOne(id);		return Response.success();	}	@Description("-根据主键查询")	@RequestMapping(value="maintainrecord/one")	public Response one(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		Maintainrecord maintainrecord = maintainrecordService.queryOne(id);		return Response.success(maintainrecord);	}		@Description("-查询列表")	@RequestMapping(value="maintainrecord/list")	@SuppressWarnings({ "rawtypes", "unchecked" })	@ResponseBody	public ResponseResult getNewestData( @RequestParam Map map ,  ModelMap modelMap) throws ControllerException {				Integer rows = Integer.valueOf((String) map.get("rows")) ;		Integer page = Integer.valueOf((String) map.get("page")) ;		map.put("page", (page - 1) * rows );		map.put("rows",rows);		boolean pageOK = PageUtils.isOK(map);		if(!pageOK)			return ResponseResult.error();				map.put("sort", "addTime");		map.put("order", "desc");				List<Map<String, Object>> maintainrecords = maintainrecordService.queryMapList(map);		List<Maintainrecord> records = DataUtils.getDataArray(maintainrecords, Maintainrecord.class);		long count = maintainrecordService.queryMapListCount(map);        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("records",records);        responseResult.put("count",count);        return ResponseResult.ok(responseResult);	}		@Description("-根据主键修改")	@RequestMapping(value="maintainrecord/update")	public Response update(@RequestParam Map<String, Object> map) throws ControllerException {		if(MapUtils.isEmpty(map, "id"))			return Response.error("id不能为空");		maintainrecordService.updateOne(map);		return Response.success();	}}