package cn.bjjoy.bms.setting.persist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;
import cn.bjjoy.bms.setting.persist.model.Equipdata;

/**
 * 类名称   : EquipdataDao
 * 类描述   : 
 * 创建人	: system
 * 创建时间 ：2018-09-13 23:02:18
 * @version 1.0
 */
@Mapper
@Repository
public interface EquipdataDao extends BaseDao<Equipdata> {

	public List<Map<String, Object>> getNewestData(Map map) ;
	
	public long  getNewestDataCount(Map map) ;
	
	public List<Map<String, Object>> getDataNoPage(Map map) ;
	
	public long  getDataNoPageCount(Map map) ;

	public List<Map<String,Object>> commonAnalysis(Map map) ;

	public List<Map<String, Object>> queryOnlineAndOffLineNums(Map map);

	public void updateErrorData(Map<String, Object> equip);
	
	
}