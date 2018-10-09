package cn.bjjoy.bms.setting.controller;import java.util.ArrayList;import java.util.HashMap;import java.util.LinkedList;import java.util.List;import java.util.Map;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.setting.dto.EquiptypeDto;import cn.bjjoy.bms.setting.exception.ServiceException;import cn.bjjoy.bms.setting.persist.model.Equiptype;import cn.bjjoy.bms.setting.service.EquiptypeService;import cn.bjjoy.bms.tree.Node;import cn.bjjoy.bms.tree.TreeGenerate;import cn.bjjoy.bms.util.DataUtils;import cn.bjjoy.bms.util.MapUtils;import cn.bjjoy.bms.util.PageUtils;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.StringUtils;import com.alibaba.fastjson.JSON;import com.alibaba.fastjson.JSONArray;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@SuppressWarnings("rawtypes")@Controller@CrossOriginpublic class EquiptypeController extends BaseController {	Logger logger = LoggerFactory.getLogger(EquiptypeController.class) ;		@Autowired	private EquiptypeService equiptypeService;		@Description("-添加")	@RequestMapping(value="equiptype/save")	@ResponseBody	public Response save(@RequestParam Map map ) throws ControllerException {		try {			map.remove("id") ;			String jsonString = JSON.toJSONString(map);				Equiptype equiptype = JSON.parseObject(jsonString,Equiptype.class);			int type1 = Integer.valueOf(String.valueOf(map.get("typeSelect1"))) ;			int type2 = Integer.valueOf(String.valueOf(map.get("typeSelect2"))) ;			Integer[] typeAndLayer = equiptypeService.getTypeAndLayer(type1 ,type2, -3) ;			equiptype.setParentId(typeAndLayer[0]);			equiptype.setTypeLayer(String.valueOf(typeAndLayer[1]));			equiptypeService.save(equiptype);		} catch (ServiceException e) {			e.printStackTrace();		} catch (SecurityException e) {			e.printStackTrace();		} catch (IllegalArgumentException e) {			e.printStackTrace();		}		return Response.success("");	}		@Description("-删除")	@RequestMapping(value="equiptype/delete")	@ResponseBody	public Response delete(@RequestParam Map paramMap) throws ControllerException {		String id = String.valueOf(paramMap.get("id")) ;		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		equiptypeService.deleteOne(id);		return Response.success();	}	@Description("-根据主键查询")	@RequestMapping(value="equiptype/one")	public Response one(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		Equiptype equiptype = equiptypeService.queryOne(id);		return Response.success(equiptype);	}			@RequestMapping(value="eqiuptype/add")	public String add() throws ControllerException {    	Map<String ,String> map = new HashMap<>();    	map.put("typeLayer", "1") ;    	map.put("parentId", "1") ;    	map.put("order", "parentId") ;    	map.put("sort", "asc");		List<Map<String, Object>> types = equiptypeService.getSubType(map) ;		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;				return "equiptype/add";	}			@RequestMapping(value="eqiuptype/edit")	public String edit(@RequestParam Map paramMap) throws ControllerException {    	Map<String ,String> map = new HashMap<>();    	map.put("typeLayer", "1") ;    	map.put("parentId", "1") ;    	map.put("order", "parentId") ;    	map.put("sort", "asc");		List<Map<String, Object>> types = equiptypeService.getSubType(map) ;		Equiptype typeToEdit = equiptypeService.queryOne(String.valueOf(paramMap.get("id")));		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;		modelMap.addAttribute("equiptype" , typeToEdit) ;		return "equiptype/edit";	}	    @RequestMapping(value = "equiptype/index" )    public String index( ModelMap modelMap) {        return "equiptype/index";    }			@SuppressWarnings("unchecked")	@Description("-查询列表")	@RequestMapping(value="equiptype/list")	@ResponseBody	public ResponseResult getNewestData(@RequestParam Map map) throws ControllerException {				Integer rows = Integer.valueOf((String) map.get("rows")) ;		Integer page = Integer.valueOf((String) map.get("page")) ;		map.put("page", (page - 1) * rows );		map.put("rows",rows);		boolean pageOK = PageUtils.isOK(map);		if(!pageOK)			return ResponseResult.error();				List<Map<String, Object>> types = equiptypeService.queryMapList(map);		System.out.println(types.size());				List<EquiptypeDto> typeList = DataUtils.getDataArray(types, EquiptypeDto.class);		long count = equiptypeService.queryMapListCount(map);        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("typeList",typeList);        responseResult.put("count",count);        return ResponseResult.ok(responseResult);	}			@Description("-查询列表")	@RequestMapping(value="equiptype/subTypelist")	@ResponseBody	public ResponseResult subTypelist(@RequestParam Map map) throws ControllerException {		List<Map<String, Object>> types = equiptypeService.getSubType(map) ;		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);		if(map.get("parentId") == null || "0".equals(map.get("parentId"))){			EquiptypeDto rootType = new EquiptypeDto();			rootType.setName("智慧抄表云平台");			subTypeList.addFirst(rootType);		}        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("subTypeList" ,subTypeList);        return ResponseResult.ok(responseResult);	}		@Description("-根据主键修改")	@RequestMapping(value="equiptype/update")	public Response update(@RequestParam Map<String, Object> map) throws ControllerException {		if(MapUtils.isEmpty(map, "id"))			return Response.error("id不能为空");		equiptypeService.updateOne(map);		return Response.success();	}		@RequestMapping(value="equiptype/tree")	@ResponseBody	public ResponseResult getTypeTree(){		List<Node> nodes = new ArrayList<>();		List<Equiptype> types = equiptypeService.queryList(new HashMap<>()) ;		Node node = null ;		for(Equiptype type : types){			node = new Node() ;			node.setId(type.getId());			node.setParentId(type.getParentId());			node.setName(type.getName());			nodes.add(node);		}				TreeGenerate treeGen = new TreeGenerate();		JSONArray result = treeGen.generateTree(nodes);        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("typeTree" ,result);        return ResponseResult.ok(responseResult);	}			@RequestMapping(value="equiptype/listnode")	@ResponseBody	public ResponseResult getTypeList(){		List<Node> nodes = new ArrayList<>();		List<Equiptype> types = equiptypeService.queryList(new HashMap<>()) ;		Node node = null ;		for(Equiptype type : types){			node = new Node() ;			node.setId(type.getId());			node.setParentId(type.getParentId());			node.setName(type.getName());			nodes.add(node);		}		        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("nodes" ,nodes);        return ResponseResult.ok(responseResult);	}			}