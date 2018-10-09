package cn.bjjoy.bms.setting.persist.mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
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

	public LinkedList<Map<String, Object>> querySubTypes(Map map);
	
	public LinkedList<Map<String, Object>> getSubTypes(Map map) ;

	public void updateByAddressCode(Map map);

	public String getAddressCodeByIp(String ip);
	
	public String getIPByAddressCode(String addressCode);
	
	
}