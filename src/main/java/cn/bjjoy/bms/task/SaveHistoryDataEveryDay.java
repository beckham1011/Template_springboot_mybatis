package cn.bjjoy.bms.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.util.DateUtils;

import com.google.common.collect.Sets;

@Component
public class SaveHistoryDataEveryDay {

	@Autowired
	EquipdataService dataService ;

	@Autowired
	EquiptypeService typeService ;

	/**
	 *  执行每天历史任务
	 *  VALUES ( #{equiptypeId} , #{ addressCode }, ${areCumulativeHis}, #{addTime} ) 
	 *  设计思路：
	 *  1，取今天有数据的泵站
	 *  2，取昨天有数据的泵站
	 *  3，取全部泵站的code
	 *  4，1 distinct 2 ，留下今天有数据的list，取今天-昨天数据的值，保存，
	 *  5，3 distinct 4 ，值为 0
	 *  
	 */
	@Scheduled(cron = "30 41 23 * * ?")
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void importData(){
		
		Map map = new HashMap() ;
		
		//前天
		String yesterdayBefore = DateUtils.formatDate( DateUtils.addDays(new Date(), -2) ,DateUtils.YYYYMMDD) + DateUtils.HHMMSS000000;
		//昨天
		String yesterday  = DateUtils.formatDate( DateUtils.addDays(new Date(), -1) ,DateUtils.YYYYMMDD) + DateUtils.HHMMSS000000;
		map.put("startDate", yesterdayBefore);
		map.put("endDate", yesterday);
		Map<String , Double> yesterdayEquipDataMap = dataService.getSpecialDayData(map) ;
		//今天
		String today = DateUtils.formatDate( new Date(),DateUtils.YYYYMMDD) + DateUtils.HHMMSS000000;
		map.put("startDate", yesterday);
		map.put("endDate", today);
		Map<String , Double> todayEquipDataMap = dataService.getSpecialDayData(map) ;

		List<String> equipCodeList = typeService.allEquipAddressCodeList(map);
		
		Set<String> todayValueAddressCodes = Sets.difference(todayEquipDataMap.keySet(),yesterdayEquipDataMap.keySet());
		
		double v = 0.0;
		
		for(String addressCode : equipCodeList){
			map.put("addressCode", addressCode);
			map.put("addTime", today);
			if(!todayValueAddressCodes.contains(addressCode)){
				map.put("areCumulativeHis", ((Double)todayEquipDataMap.get(addressCode) - (Double)yesterdayEquipDataMap.get(addressCode)));
			}else{
				map.put("areCumulativeHis", v);
			}
			dataService.insertDataHistory(map) ;
		}
	}
	
	
	public boolean isValidAddressCode(String addressCode){
		if(addressCode.startsWith("hsg") && addressCode.length() == 6)
			return true ;
		return false ;
	}
	
//	
//	@Test
//	public void test(){
////		String yesterday = DateUtils.formatDate(DateUtils.getPrevDay(new Date()) , DateUtils.YYYYMMDDHHMMSS) ; ;
////		System.out.println(yesterday);
//		
//		Map<String, String> m1 = new HashMap<>() ;
//		m1.put("1", "1");
//		m1.put("2", "2");
//		m1.put("3", "3");
//		m1.put("4", "4");
//		m1.put("5", "5");
//		m1.put("6", "6");
//		
//		Map<String, String> m2 = new HashMap<>() ;
//		m2.put("2", "2");
//		m2.put("4", "4");
//		m2.put("7", "7");
//
//		Set<String> set = Sets.difference(m1.keySet(), m2.keySet()) ;
//		
//		System.out.println(set.contains("5"));
//		
//		for(String s : set){
//			System.out.println(s);
//		}
//	}
	
}


