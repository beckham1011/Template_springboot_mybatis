package cn.bjjoy.bms.setting.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.exception.ControllerException;
import cn.bjjoy.bms.setting.dto.AnalysisDto;
import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.util.DateUtils;
import cn.bjjoy.bms.util.UserUtils;

@SuppressWarnings({"rawtypes"})
@CrossOrigin
@Controller
@RequestMapping(value="analysis")
public class AnalysisController  extends AbstractHosznController{
    
    @GetMapping(value = "/index" )
    public String index2( @RequestParam Map paramMap ,ModelMap modelMap) {
    	User user = UserUtils.getUer() ;
		modelMap.addAttribute("parentId", user.getParentId()) ;
		String dayMonthAgo = DateUtils.formatDate(DateUtils.addDays(new Date(), -31), DateUtils.YYYYMMDDHHMMSS) ;
		String today = DateUtils.formatDate(DateUtils.addDays(new Date(), -1), DateUtils.YYYYMMDDHHMMSS) ;
		modelMap.addAttribute("dayMonthAgo" , dayMonthAgo);
		modelMap.addAttribute("today" , today);
        return "/analysis/index";
    }
	
	@Description("-查询列表")
	@GetMapping(value="list")
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

		User user = UserUtils.getUer();
		int parentId =  equiptypeService.getParentId(map , user);
		map.put("map", parentId);
		
		Equiptype equip = equiptypeService.getEquipById(String.valueOf(parentId));
		if(equip.getSystemId() != 0){
			map.put("systemId", equip.getSystemId()) ;
		}
		map.put("typeLayer", equip.getTypeLayer());
		String type = (String)map.get("type");
		String timeFmt = saveService.getDatFormatByType(type);
		
		map.put("timeFmt", timeFmt);
		
		List<String> days = DateUtils.getDaysList(DateUtils.toDate(String.valueOf(map.get("startDate"))), DateUtils.toDate(String.valueOf(map.get("endDate")))) ;
		Set<String> timeFormatList = DateUtils.getTimeTypeList(days , type);
		
		List<Map<String, Object>> subtypeList = equiptypeService.queryDirectSubTypes(map) ;
		
		
		List<Map<String , Object>> datas = equipdataService.getHistoryDataEveryday(map);

		//select  quarter( add_time ) from equipdata_history 季度
		
		//2018_4季度
		//select CONCAT(DATE_FORMAT(add_time , '%Y') ,'_' , quarter( add_time ),'季度') , add_time from equipdata_history
		//where id in ( 44881, 44882 , 44883, 44884 , 44885, 44886 , 44887)

		
//		Map<String , Object> dataDayAddress = equipdataService.getDayDataDayAddressData(datas);
		Map<String , Object> dataAddressDay = equipdataService.getDayDataDayAddressData(datas , type);
		
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
		result[0] = timeFormatList;
		for (int typeIndex = 0; typeIndex < subTypeNum; typeIndex ++) {
			List<Double> daysData = new ArrayList<>(timeFormatList.size()) ;
			Integer id = (Integer) subtypeList.get(typeIndex).get("id") ;
			String typeName = (String) subtypeList.get(typeIndex).get("name") ;
			typeNameList.add(typeName) ;
			Double dayData = 0.0;
			for(String timeType : timeFormatList){
				String key = timeType+ "_" + id  ;
				dayData = (Double) (dataAddressDay.containsKey(key) ? dataAddressDay.get(key) : 0.0);
				daysData.add(dayData) ;
			}
			result[typeIndex + 1] = daysData ;
		}
		
		Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("analysisChart",result);
        responseResult.put("typeList", typeNameList) ;
        return ResponseResult.ok(responseResult);
	}
	
    
}
