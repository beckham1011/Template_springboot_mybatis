package cn.bjjoy.bms.setting.persist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;

@Mapper
@Repository
@SuppressWarnings("rawtypes")
public interface TenantDao extends BaseDao<cn.bjjoy.bms.setting.persist.model.Tenant> {

	public List<Map<String, Object>> getTenantList(Map map);

	public int getTenantsCount(Map map);

	@Insert("insert into edm.system(system.system) values(#{system})")
	public void addTenant(Map map);

	@Update("update edm.system set system.system = #{system} where id = #{id}")
	public int updateTenant(Map map);

	@Delete("delete from edm.system where id = #{id}")
	public int deleteTenant(String id);

}
