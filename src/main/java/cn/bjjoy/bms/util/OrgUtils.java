package cn.bjjoy.bms.util;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

@Component
public class OrgUtils {

	@Autowired
	public EquiptypeService equipService;

	@Autowired
	public static EquiptypeService staticEquipService;
	
	@PostConstruct
	public void init() {
		staticEquipService = equipService;
	}
	
	private static Map<Integer , Integer> parentOrgMap = new ConcurrentHashMap<>();
	
	private static Map<Integer , String> orgNameMap = new ConcurrentHashMap<>();
	
	public static boolean existOrgId(Integer orgId) {
		return parentOrgMap.containsKey(orgId) && orgNameMap.containsKey(orgId);
	}
	
	public static Integer getParentOrgId(Integer orgId) {
		if(existOrgId(orgId)) {
			return parentOrgMap.get(orgId);
		} else {
			Equiptype parentOrg = staticEquipService.getEquipById(String.valueOf(orgId));
			parentOrgMap.put(orgId, parentOrg.getParentId());
			orgNameMap.put(orgId, parentOrg.getName());
			return parentOrg.getParentId();
		}
	}
	
	public static String getOrgName(Integer orgId) {
		if(existOrgId(orgId)) {
			return orgNameMap.get(orgId);
		} else {
			Equiptype parentOrg = staticEquipService.getEquipById(String.valueOf(orgId));
			parentOrgMap.put(orgId, parentOrg.getParentId());
			orgNameMap.put(orgId, parentOrg.getName());
			return parentOrg.getName();
		}
	}
	
}
