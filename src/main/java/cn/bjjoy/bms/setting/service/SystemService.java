package cn.bjjoy.bms.setting.service;

import java.util.HashMap;
import java.util.Map;

public interface SystemService {

	Map<Integer,Integer> systemPortMap = new HashMap<>();

	String getSystem(Integer systemId) ;

	Map<String ,cn.bjjoy.bms.setting.persist.model.System> getSystemList();

	Integer getPort(Integer systemId);
}
