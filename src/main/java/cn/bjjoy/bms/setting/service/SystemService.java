package cn.bjjoy.bms.setting.service;

import java.util.HashMap;
import java.util.Map;

public interface SystemService {

	public static Map<Integer,Integer> systemPortMap = new HashMap<>();
	
	public String getSystem(Integer systemId) ;

	public Map<String ,cn.bjjoy.bms.setting.persist.model.System> getSystemList();

	public Integer getPort(Integer systemId);
}
