package cn.bjjoy.bms.setting.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.exception.ControllerException;
import cn.bjjoy.bms.setting.dto.AnalysisDto;
import cn.bjjoy.bms.setting.dto.CurrentEquipData;
import cn.bjjoy.bms.setting.dto.EquiptypeDto;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.util.DataUtils;

@CrossOrigin
@Controller
@RequestMapping(value="analysis")
public class AnalysisController {

	@Autowired
	private EquipdataService equipdataService;
	
	@Autowired
	private EquiptypeService equiptypeService;
	
	
    @RequestMapping(value = "/index" )
    public String index( ModelMap modelMap) {
    	Map<String ,String> map = new HashMap<>();
    	map.put("typeLayer", "1") ;
    	map.put("parentId", "1") ;
    	map.put("order", "parentId") ;
    	map.put("sort", "asc");
		List<Map<String, Object>> types = equiptypeService.getSubType(map) ;
		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);
		modelMap.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;
		
        return "/analysis/index";
    }
	

	@Description("-查询列表")
	@RequestMapping(value="list")
	@ResponseBody
	public ResponseResult analysis(@RequestParam Map<String, Object> map) throws ControllerException {
		List<AnalysisDto> analysises = equipdataService.analysisesDto(map);
        Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("analysises",analysises);
        responseResult.put("count",analysises.size());
        
        return ResponseResult.ok(responseResult);
	}
    
    
}
