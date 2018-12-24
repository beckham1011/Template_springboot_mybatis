package cn.bjjoy.bms.setting.persist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;

@Mapper
@Repository
public interface TenantDao  extends BaseDao<cn.bjjoy.bms.setting.persist.model.System>{

	public List<Map<String,Object>> getTenantList(Map map) ;
	
	public int getTenantsCount(Map map) ;
	
	public void insertTenant(Map map) ;
	public void updateTenant(Map map) ;

	public void deleteTenant(String id) ;

}
