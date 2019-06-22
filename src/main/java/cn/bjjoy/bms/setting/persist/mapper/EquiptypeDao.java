package cn.bjjoy.bms.setting.persist.mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;
import cn.bjjoy.bms.setting.exception.DaoException;
import cn.bjjoy.bms.setting.persist.model.Equiptype;

/**
 * 类名称   : EquiptypeDao
 * 类描述   : 
 * 创建人	: system
 * 创建时间 ：2018-09-13 23:02:18
 * @version 1.0
 */
@Mapper
@Repository
@SuppressWarnings("rawtypes")
public interface EquiptypeDao extends BaseDao<Equiptype> {

	public List<Map<String,Object>> queryMapListNoPage( Map map) throws DaoException ;

	public Equiptype getEquipByAddressCode(@Param("addressCode") String addressCode);
	
	public Equiptype getEquipByName(@Param("name") String name);
	
	public LinkedList<Map<String, Object>> querySubTypes(Map map);
	
	public LinkedList<Map<String, Object>> getTypesByIds(Map map) ;

	public LinkedList<Integer> getSubTypeIds(Map map) ;

	public LinkedList<Integer> getSubOrgIds(Map map) ;
	
	public void updateByAddressCode(Map map);
	
	public void updateInfoByAddressCode(Map map);

	public String getAddressCodeByIp(String ip);
	
	public String getIPByAddressCode(String addressCode);
	
	public List<String> getAddressCodeNull() ;

	public int getSubTypeCount(Map map);

	public List<Map<String, Object>> queryDirectSubTypes(Map map);

	public List<Map<String, Object>> exportTypeList(Map map) ;

	public int getUserSystemIdByUserId(Map map);
	
	public Equiptype getEquipById(@Param("id") int id);

	public Map<Integer, String> getTypeById(String id);

	public List<String> allEquipAddressCodeList(Map map) ;
	
	public List<Equiptype> testResultMap();

	public List<Map<String, Object>> getEquipsByParentId(@Param("parentId") int parentId);

	public List<Integer> getEquipIdsByParentId(@Param("parentId") int parentId);
	
	public void updateOnlineStatus(@Param("addressCode") String addressCode, @Param("onlineFlag") int onlineFlag);
	
}