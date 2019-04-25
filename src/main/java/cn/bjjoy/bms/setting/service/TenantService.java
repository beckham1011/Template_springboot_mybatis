package cn.bjjoy.bms.setting.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface TenantService {

	public List<Map<String,Object>> getTenants(Map map);
	
	public List<Map<String,Object>> getTenantById(int id);
	
	public int getCount(Map map) ;
	
	public PageInfo<cn.bjjoy.bms.setting.persist.model.System> getPage(Integer pageNumber, Integer pageSize);
	
	public void deleteSystem(Map map);
	
	public int updateSystem(Map map);

	public void add(Map map);
	
	
}
