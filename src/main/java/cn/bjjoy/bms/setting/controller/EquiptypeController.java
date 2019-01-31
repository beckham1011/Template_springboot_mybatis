package cn.bjjoy.bms.setting.controller;import java.util.ArrayList;import java.util.HashMap;import java.util.LinkedList;import java.util.List;import java.util.Map;import org.apache.poi.hssf.usermodel.HSSFSheet;import org.apache.poi.hssf.usermodel.HSSFWorkbook;import org.apache.poi.ss.usermodel.Cell;import org.apache.poi.ss.usermodel.Row;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.multipart.MultipartFile;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.setting.dto.EquiptypeDto;import cn.bjjoy.bms.setting.entity.User;import cn.bjjoy.bms.setting.exception.ServiceException;import cn.bjjoy.bms.setting.persist.model.Equiptype;import cn.bjjoy.bms.setting.persist.model.System;import cn.bjjoy.bms.setting.service.EquiptypeService;import cn.bjjoy.bms.setting.service.TenantService;import cn.bjjoy.bms.tree.Node;import cn.bjjoy.bms.tree.TreeGenerate;import cn.bjjoy.bms.tree.b1.BuildTree;import cn.bjjoy.bms.tree.b1.Tree;import cn.bjjoy.bms.util.DataUtils;import cn.bjjoy.bms.util.DateUtils;import cn.bjjoy.bms.util.MapUtils;import cn.bjjoy.bms.util.PageUtils;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.StringUtils;import cn.bjjoy.bms.util.UserUtils;import com.alibaba.fastjson.JSON;import com.alibaba.fastjson.JSONArray;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@SuppressWarnings("rawtypes")@Controller@CrossOriginpublic class EquiptypeController extends BaseController {	Logger logger = LoggerFactory.getLogger(EquiptypeController.class) ;		@Autowired	private EquiptypeService equiptypeService;		@Autowired	TenantService tenantService;		@Description("-添加")	@RequestMapping(value="equiptype/save")	@ResponseBody	public Response save(@RequestParam Map map ) throws ControllerException {		String flag = "" ;		try {			map.remove("id") ;			String jsonString = JSON.toJSONString(map);				Equiptype equiptype = JSON.parseObject(jsonString,Equiptype.class);			Integer[] typeAndLayer = equiptypeService.getTypeAndLayer(map) ;			equiptype.setParentId(typeAndLayer[0]);			equiptype.setTypeLayer(String.valueOf(typeAndLayer[1]));			equiptype.setAddTime(DateUtils.getCurrentDate());			equiptypeService.save(equiptype);			flag = "添加成功" ;		} catch (ServiceException e) {			e.printStackTrace();			flag = "添加失败" ;		} catch (SecurityException e) {			flag = "添加失败" ;			e.printStackTrace();		} catch (IllegalArgumentException e) {			flag = "添加失败" ;			e.printStackTrace();		}		return Response.success(flag);	}		@Description("-删除")	@RequestMapping(value="equiptype/delete")	@ResponseBody	public Response delete(@RequestParam Map paramMap) throws ControllerException {		String id = String.valueOf(paramMap.get("id")) ;		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		equiptypeService.deleteOne(id);		return Response.success();	}	@Description("-根据主键查询")	@RequestMapping(value="equiptype/one")	public Response one(String id) throws ControllerException {		if(StringUtils.isEmpty(id))			return Response.error("id不能为空");		Equiptype equiptype = equiptypeService.queryOne(id);		return Response.success(equiptype);	}			@RequestMapping(value="eqiuptype/add")	public String add() throws ControllerException {    	Map<String ,String> map = new HashMap<>();    	map.put("typeLayer", "1") ;    	map.put("parentId", "1") ;    	map.put("order", "parentId") ;    	map.put("sort", "asc");    	int systemId = equiptypeService.getUserSystemId(UserUtils.getUer().getId()) ;		if(systemId != 0){			map.put("systemId", String.valueOf(systemId)) ;		}		List<Map<String, Object>> types = equiptypeService.queryDirectSubTypes(map);		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;		modelMap.addAttribute("systemList"  , tenantService.getTenantById(systemId)) ;		return "equiptype/add";	}			@RequestMapping(value="eqiuptype/importFile")	public String importFile(@RequestParam Map paramMap) throws ControllerException {		return "equiptype/importFile" ;	}			@RequestMapping(value="equiptype/importFileSubmit" ,method={RequestMethod.POST})	public String importFileSubmit(@RequestParam("file") MultipartFile file) throws ControllerException {		logger.info("get submit file");		try {						HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());			HSSFSheet sheet = workbook.getSheetAt(0) ;			//获得当前sheet的开始行              int firstRowNum  = sheet.getFirstRowNum();              //获得当前sheet的结束行              int lastRowNum = sheet.getLastRowNum();              //循环除了第2行的所有行                          List<Map<String,Object>> types = new ArrayList<>();            Map<String,Object> typeMap = null;            for(int rowNum = firstRowNum+2;rowNum <= lastRowNum;rowNum++){                  //获得当前行                  Row row = sheet.getRow(rowNum);                  if(row == null){                      continue;                  }                  //获得当前行的开始列                  int firstCellNum = row.getFirstCellNum();                                  typeMap = new HashMap<>();                typeMap.put("addressCode", getCellValue(row.getCell(firstCellNum + 1)));                typeMap.put("bengxing", getCellValue(row.getCell(firstCellNum + 2)));                typeMap.put("koujing", getCellValue(row.getCell(firstCellNum + 3)));                typeMap.put("gonglv", getCellValue(row.getCell(firstCellNum + 4)));                typeMap.put("xinhao", getCellValue(row.getCell(firstCellNum + 5)));                typeMap.put("longitude", getCellValue(row.getCell(firstCellNum + 6)));                typeMap.put("latitude", getCellValue(row.getCell(firstCellNum + 7)));                                typeMap.put("zguanliPer", getCellValue(row.getCell(firstCellNum + 8)));                typeMap.put("zguanliPhone", getCellValue(row.getCell(firstCellNum + 9)));                typeMap.put("cguanliPer", getCellValue(row.getCell(firstCellNum + 10)));                typeMap.put("cguanliPhone", getCellValue(row.getCell(firstCellNum + 11)));                typeMap.put("jguanliPer", getCellValue(row.getCell(firstCellNum + 12)));                typeMap.put("jguanliPhone", getCellValue(row.getCell(firstCellNum + 13)));//                types.add(typeMap);                equiptypeService.updateInfoByAddressCode(typeMap);            }            logger.info("TypeSize:"+types.size());		} catch (Exception e) {			e.printStackTrace();		}		return "equiptype/index" ;	}			@RequestMapping(value="equiptype/exportEquips" ,method={RequestMethod.GET})	public String exportEquips(@RequestParam Map map ) throws ControllerException {		logger.info("exportEquips file");		try {//			equiptypeService.importFile(file);		} catch (Exception e) {			e.printStackTrace();		}		return "equiptype/index" ;	}			@RequestMapping(value="eqiuptype/edit")	public String edit(@RequestParam Map paramMap) throws ControllerException {    	Map<String ,String> map = new HashMap<>();    	map.put("typeLayer", "1") ;    	map.put("parentId", "1") ;    	map.put("order", "parentId") ;    	map.put("sort", "asc");    	int systemId = equiptypeService.getUserSystemId(UserUtils.getUer().getId()) ;		if(systemId != 0){			map.put("systemId", String.valueOf(systemId)) ;		}		List<Map<String, Object>> types = equiptypeService.queryDirectSubTypes(map);		Equiptype typeToEdit = equiptypeService.queryOne(String.valueOf(paramMap.get("id")));		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);		modelMap.addAttribute("systemList"  ,equiptypeService.getSystems()) ;		modelMap.addAttribute("equiptype" , typeToEdit) ;		modelMap.addAttribute("subTypeList" , subTypeList) ;		if("4".equals(typeToEdit.getTypeLayer())){			modelMap.addAttribute("parentParentTypes" , equiptypeService.queryOne(String.valueOf(equiptypeService.queryOne(String.valueOf(typeToEdit.getParentId())).getParentId()))) ;			modelMap.addAttribute("parentTypes" , equiptypeService.queryOne(String.valueOf(typeToEdit.getParentId()))) ;		}else if("3".equals(typeToEdit.getTypeLayer())){			modelMap.addAttribute("parentParentTypes" , equiptypeService.queryOne(String.valueOf(typeToEdit.getParentId()))) ;			modelMap.addAttribute("parentTypes" , null) ;		}else if("2".equals(typeToEdit.getTypeLayer())){			modelMap.addAttribute("parentParentTypes" , null) ;			modelMap.addAttribute("parentTypes" , null) ;		}else{			modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;		}		/** 		if("4".equals(typeToEdit.getTypeLayer())){			modelMap.addAttribute("parentParentTypes" , equiptypeService.getSubTypeIds( equiptypeService.queryOne(String.valueOf(typeToEdit.getParentId())).getParentId() , null)) ;			modelMap.addAttribute("parentTypes" , equiptypeService.getSubTypeIds(typeToEdit.getParentId(),null)) ;		}else if("3".equals(typeToEdit.getTypeLayer())){			modelMap.addAttribute("parentParentTypes" , equiptypeService.getSubTypeIds(typeToEdit.getParentId(),null)) ;			modelMap.addAttribute("parentTypes" , null) ;		}else if("2".equals(typeToEdit.getTypeLayer())){			modelMap.addAttribute("parentParentTypes" , null) ;			modelMap.addAttribute("parentTypes" , null) ;		}else{			modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;		}		 */		return "equiptype/edit";	}	    @RequestMapping(value = "equiptype/index" )    public String index(@RequestParam Map paramMap , ModelMap modelMap) {    	modelMap.addAttribute("parentId", UserUtils.getUer().getParentId()) ;        return "equiptype/index";    }			@SuppressWarnings("unchecked")	@Description("-查询列表")	@RequestMapping(value="equiptype/list")	@ResponseBody	public ResponseResult getNewestData(@RequestParam Map map) throws ControllerException {				boolean pageOK = PageUtils.isOK(map);		if(!pageOK)			return ResponseResult.error();				map = equiptypeService.getParamMap(map);		List<Map<String, Object>> types = equiptypeService.getSubType(map);				List<EquiptypeDto> typeList = DataUtils.getDataArray(types, EquiptypeDto.class);		long count = equiptypeService.getSubTypeCount(map);        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("typeList",typeList);        responseResult.put("count",count);        return ResponseResult.ok(responseResult);	}			@Description("-查询列表")	@RequestMapping(value="equiptype/subTypelist")	@ResponseBody	public ResponseResult subTypelist(@RequestParam Map map) throws ControllerException {		int systemId = equiptypeService.getUserSystemId(UserUtils.getUer().getId()) ;		if(systemId != 0){			map.put("systemId", String.valueOf(systemId)) ;		}		List<Map<String, Object>> types = equiptypeService.queryDirectSubTypes(map) ;		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);		if(map.get("parentId") == null || "0".equals(map.get("parentId"))){			EquiptypeDto rootType = new EquiptypeDto();			rootType.setName("智慧抄表云平台");			subTypeList.addFirst(rootType);		}        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("subTypeList" ,subTypeList);        return ResponseResult.ok(responseResult);	}		@Description("-根据主键修改")	@RequestMapping(value="equiptype/update")	@ResponseBody	public Response update(@RequestParam Map<String, Object> map) throws ControllerException {		if(MapUtils.isEmpty(map, "id"))			return Response.error("id不能为空");				int type1 = Integer.valueOf(String.valueOf(map.get("typeSelect1")).replace(",", "")) ;		int type2 = Integer.valueOf(String.valueOf(map.get("typeSelect2")).replace(",", "")) ;		Integer[] typeAndLayer = equiptypeService.getTypeAndLayer(type1 ,type2, -3) ;				map.put("typeLayer", typeAndLayer[1]) ;		map.put("parentId", typeAndLayer[0]);		equiptypeService.updateOne(map);		return Response.success();	}		@RequestMapping(value="equiptype/tree2")	@ResponseBody	public ResponseResult getTypeTree(){		List<Node> nodes = new ArrayList<>();		Map map = new HashMap<>() ;				User user = UserUtils.getUer() ;		List<Integer> ids = equiptypeService.getSubTypeIds2(user.getParentId() ,  (String)map.get("stationName")) ;		map.put("ids", ids);				List<Equiptype> types = equiptypeService.getTypesByIds(map);		Node node = null ;		for(Equiptype type : types){			node = new Node() ;			node.setId(type.getId());			node.setParentId(type.getParentId());			node.setName(type.getName());			node.setTag(type.getTag());			nodes.add(node);		}				TreeGenerate treeGen = new TreeGenerate();		JSONArray result = treeGen.generateTree(nodes);        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("typeTree" ,result);        return ResponseResult.ok(responseResult);	}			@RequestMapping(value="equiptype/listnode")	@ResponseBody	public ResponseResult getTypeList(){		List<Node> nodes = new ArrayList<>();		List<Equiptype> types = equiptypeService.queryList(new HashMap<>()) ;		Node node = null ;		for(Equiptype type : types){			node = new Node() ;			node.setId(type.getId());			node.setParentId(type.getParentId());			node.setName(type.getName());			nodes.add(node);		}		        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("nodes" ,nodes);        return ResponseResult.ok(responseResult);	}			@RequestMapping(value="equiptype/tree")	@ResponseBody	public ResponseResult getTree(){		Map map = new HashMap<>() ;		map.put("parentId", 1) ;		List<Map<String, Object>> subTypes =  equiptypeService.getSubType(map) ;//		List<Equiptype> types = new ArrayList<>() ;		List<Tree<Equiptype>> types = new ArrayList<Tree<Equiptype>>();		for(Map<String, Object> type : subTypes){			Tree<Equiptype> tree = new Tree<>();			tree.setId(String.valueOf(type.get("id")));			tree.setParentId(String.valueOf(type.get("parentId")));			tree.setText(String.valueOf(type.get("name")));			types.add(tree) ;		}				Tree<Equiptype> typeTree = BuildTree.build(types);		Map<String, Object> responseResult = new HashMap<>();        responseResult.put("typeTree" , typeTree);        return ResponseResult.ok(responseResult);	}			@RequestMapping(value="equiptype/system")	@ResponseBody	public ResponseResult getSystem(){				List<System> systems = new ArrayList<>();		System s1 = new System();		s1.setId(0);		s1.setSystem("南京厚水智能监控系统");		systems.add(s1);				System s2 = new System();		s1.setId(1);		s1.setSystem("高港区农业综合水价改革远程监测系统");		systems.add(s2);				System s3 = new System();		s1.setId(2);		s1.setSystem("南京测试");		systems.add(s3);				System s4 = new System();		s1.setId(3);		s1.setSystem("远程监测系统");		systems.add(s4);				System s5 = new System();		s1.setId(4);		s1.setSystem("大丰区农业水价综合改革监测系统");		systems.add(s5);		        Map<String, Object> responseResult = new HashMap<>();        responseResult.put("systems" , systems);        return ResponseResult.ok(responseResult);	}	public static String getCellValue(Cell cell){          String cellValue = "";          if(cell == null){              return cellValue;          }          //把数字当成String来读，避免出现1读成1.0的情况          if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){              cell.setCellType(Cell.CELL_TYPE_STRING);          }          //判断数据的类型          switch (cell.getCellType()){              case Cell.CELL_TYPE_NUMERIC: //数字                  cellValue = String.valueOf(cell.getNumericCellValue());                  break;              case Cell.CELL_TYPE_STRING: //字符串                  cellValue = String.valueOf(cell.getStringCellValue());                  break;              case Cell.CELL_TYPE_BOOLEAN: //Boolean                  cellValue = String.valueOf(cell.getBooleanCellValue());                  break;              case Cell.CELL_TYPE_FORMULA: //公式                  cellValue = String.valueOf(cell.getCellFormula());                  break;              case Cell.CELL_TYPE_BLANK: //空值                   cellValue = "";                  break;              case Cell.CELL_TYPE_ERROR: //故障                  cellValue = "非法字符";                  break;              default:                  cellValue = "未知类型";                  break;          }          return cellValue;      }  		}