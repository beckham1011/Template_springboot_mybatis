package cn.bjjoy.bms.setting.controller;

import java.util.ArrayList;
import java.util.Date;
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
import cn.bjjoy.bms.util.DateUtils;
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
		String dayMonthAgo = DateUtils.formatDate(DateUtils.addDays(new Date(), -31), DateUtils.YYYYMMDDHHMMSS) ;
		String today = DateUtils.formatDate(DateUtils.addDays(new Date(), -1), DateUtils.YYYYMMDDHHMMSS) ;
		modelMap.addAttribute("dayMonthAgo" , dayMonthAgo);
		modelMap.addAttribute("today" , today);
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
    
	
	@RequestMapping(value="chart")
	@ResponseBody
	public ResponseResult getAnalysisChartData(@RequestParam Map<String, Object> map){
		
		List<String> days = DateUtils.getDaysList(DateUtils.toDate(String.valueOf(map.get("startDate"))), DateUtils.toDate(String.valueOf(map.get("endDate")))) ;
		
		List<Map<String, Object>> subtypeList = equiptypeService.queryDirectSubTypes(map) ;
		
		List<Map<String , Object>> datas = equipdataService.getHistoryDataEveryday(map);

//		Map<String , Object> dataDayAddress = equipdataService.getDayDataDayAddressData(datas);
		Map<String , Object> dataAddressDay = equipdataService.getDayDataDayAddressData(datas);
		
		int subTypeNum = subtypeList != null ? subtypeList.size() : 0;
		
		/** 构建二维数组结构
			2019-01-01, 2019-01-02, 2019-01-03, 2019-01-04, 2019-01-05, ....
			10	    10		10	    10		10
			20	    20		20	    20		20
			.
			.
		 */
		Object[] result = new Object[subTypeNum + 1] ;
		List<String> typeNameList = new ArrayList<>();
		result[0] = days;
		for (int typeIndex = 0; typeIndex < subTypeNum; typeIndex ++) {
			List<Double> daysData = new ArrayList<>(days.size()) ;
			String addressCode = (String) subtypeList.get(typeIndex).get("addressCode") ;
			String typeName = (String) subtypeList.get(typeIndex).get("name") ;
			typeNameList.add(typeName) ;
			Double dayData = 0.0;
			for(String day : days){
				String key = addressCode + "_" + day ;
				dayData = (Double) (dataAddressDay.containsKey(key) ? dataAddressDay.get(key) : 0.0);
				daysData.add(dayData) ;
			}
			result[typeIndex + 1 ] = daysData ;
		}
		
		Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("analysisChart",result);
        responseResult.put("typeList", typeNameList) ;
        return ResponseResult.ok(responseResult);
	}
	
    
}
