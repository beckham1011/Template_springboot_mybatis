package cn.bjjoy.bms.setting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import cn.bjjoy.bms.setting.persist.mapper.TenantDao;
import cn.bjjoy.bms.setting.persist.model.System;
import cn.bjjoy.bms.setting.service.TenantService;

@Service
@Transactional
@SuppressWarnings({"rawtypes","unchecked"})
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDao tenantDao ;
	
	public List<Map<String,Object>> getTenants(Map map){
		return tenantDao.getTenantList(map) ;
	}

	public List<Map<String,Object>> getTenantsById(int id){
		Map map = new HashMap();
		if(id != 11)
			map.put("id", id) ;
		return tenantDao.getTenantList(map) ;
	}

	
	public int getCount(Map map){
		return tenantDao.getTenantsCount(map);
	}
	
	@Override
	public PageInfo<System> getPage(Integer pageNumber, Integer pageSize) {
		return null;
	}

	@Override
	public void deleteSystem(Map map) {
		tenantDao.deleteTenant(String.valueOf(map.get("id")));
	}

	@Override
	public int updateSystem(Map map) {
		return tenantDao.updateTenant(map);
	}


	@Override
	public void add(Map map) {
		tenantDao.addTenant(map);
	}
	
}
