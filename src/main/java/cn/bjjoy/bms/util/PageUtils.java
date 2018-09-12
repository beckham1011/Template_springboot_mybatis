package cn.bjjoy.bms.util;

import java.util.Map;

import cn.bjjoy.bms.setting.constants.Constants;

public class PageUtils {
	
	public static boolean isOK(Map<String,Object> map){
		
		if(!map.containsKey(Constants.PAGE) || !map.containsKey(Constants.ROWS))
			return false;
		
		if(!RegexUtils.checkInteger(map.get(Constants.PAGE)) || !RegexUtils.checkInteger(map.get(Constants.ROWS))){
			
			map.remove(Constants.PAGE);
			map.remove(Constants.ROWS);
			
			return false;
		}
		return true;
	}
	
	public static Page initPage(Map<String, Object> map,Long total) {
		
		Long pageSize = Long.parseLong(String.valueOf(map.get(Constants.PAGE)));
		Long rows 	  = Long.parseLong(String.valueOf(map.get(Constants.ROWS)));
		
		Page page = new Page(pageSize, rows , total);
		
		if(0 >= pageSize){
			pageSize = 1L;
			page.setPage(1L);
		}

		map.put(Constants.PAGE,page.getRows()*(pageSize-1));
		map.put(Constants.ROWS,rows);
		
		return page;
	}

}
