package cn.bjjoy.bms.setting.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("rawtypes")
public interface TenantService {

	List<Map<String,Object>> getTenants(Map map);
	
	List<Map<String,Object>> getTenantsById(int id);
	
	int getCount(Map map) ;
	
	PageInfo<cn.bjjoy.bms.setting.persist.model.System> getPage(Integer pageNumber, Integer pageSize);
	
	void deleteSystem(Map map);
	
	int updateSystem(Map map);

	void add(Map map);
	
	
}
