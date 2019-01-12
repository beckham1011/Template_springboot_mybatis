package cn.bjjoy.bms.setting.controller;

import java.util.HashMap;
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
import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.util.UserUtils;

@CrossOrigin
@Controller
@RequestMapping(value="analysis")
public class AnalysisController {

	@Autowired
	private EquipdataService equipdataService;
	
	@Autowired
	private EquiptypeService equiptypeService;
	
    @RequestMapping(value = "/index" )
    public String index( @RequestParam Map paramMap ,ModelMap modelMap) {
		modelMap.addAttribute("parentId", paramMap.containsKey("parentId") ? paramMap.get("parentId") : "1") ;
        return "/analysis/index";
    }
    
    
    @RequestMapping(value = "/index2" )
    public String index2( @RequestParam Map paramMap ,ModelMap modelMap) {
		modelMap.addAttribute("parentId", paramMap.containsKey("parentId") ? paramMap.get("parentId") : "1") ;
        return "/analysis/index2";
    }
	
	@Description("-查询列表")
	@RequestMapping(value="list")
	@ResponseBody
	public ResponseResult analysis(@RequestParam Map<String, Object> map) throws ControllerException {
		User user = UserUtils.getUer() ;
		int systemId = equiptypeService.getUserSystemId(user.getId()) ;
		if(systemId != 0){
			map.put("systemId", 1) ;
		}
		List<Integer> ids = equiptypeService.getSubTypeIds2(user.getParentId() ,  (String)map.get("stationName")) ;
		map.put("ids", ids);
		List<AnalysisDto> analysises = equipdataService.analysisesDto(map);
        Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("analysises",analysises);
        responseResult.put("count",analysises.size());
        
        return ResponseResult.ok(responseResult);
	}
    
    
}
