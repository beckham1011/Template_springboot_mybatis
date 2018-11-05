package cn.bjjoy.bms.setting.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.bjjoy.bms.setting.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	static Map<Integer,String> systemMap = new HashMap<>();
	
	static{
		systemMap.put(0, "南京厚水智能监控系统") ;
		systemMap.put(1, "高港区农业综合水价改革远程监测系统") ;
		systemMap.put(2, "南京测试") ;
		systemMap.put(3, "远程监测系统") ;
		systemMap.put(4, "大丰区农业水价综合改革监测系统") ;
	}
	
	@Override
	public String getSystem(Integer systemId) {
		return systemMap.get(systemId);
	}

}

