package cn.bjjoy.bms.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.util.DateUtils;

@Component
public class SaveHistoryDataEveryDay {

	private static final Logger log = LogManager.getLogger();
	
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
	@Scheduled(cron = "30 41 12 * * ?")
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void importData(){
		
		Map map = new HashMap() ;
		
		//前天
		String yesterdayBefore = DateUtils.formatDate( DateUtils.addDays(new Date(), -2) ,DateUtils.YYYYMMDD) + DateUtils.HHMMSS000000;
		//昨天
		String yesterday  = DateUtils.formatDate( DateUtils.addDays(new Date(), -1) ,DateUtils.YYYYMMDD) + DateUtils.HHMMSS000000;
		Map<String , Double> yesterdayEquipDataMap = dataService.getSpecialDayData2(yesterdayBefore , yesterday) ;
		
		//今天
		String today = DateUtils.formatDate( new Date(),DateUtils.YYYYMMDD) + DateUtils.HHMMSS000000;
		Map<String , Double> todayEquipDataMap = dataService.getSpecialDayData2(yesterday,today) ;

		List<String> equipCodeList = typeService.allEquipAddressCodeList(map);
		
		final double defaultValue = 0.0;
		
		for(String addressCode : equipCodeList){
			map.put("addressCode", addressCode);
			map.put("addTime", yesterday.substring(0, 10));
			if(todayEquipDataMap.containsKey(addressCode.trim()) && yesterdayEquipDataMap.containsKey(addressCode.trim())){
				double netCollect = (Double)todayEquipDataMap.get(addressCode) - (Double)yesterdayEquipDataMap.get(addressCode);
//				log.info("addressCode: " + addressCode + ", netCollect" + netCollect);
				map.put("areCumulativeHis", netCollect);
			}else{
				map.put("areCumulativeHis", defaultValue);
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


