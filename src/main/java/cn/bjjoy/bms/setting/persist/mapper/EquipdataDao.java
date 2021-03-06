package cn.bjjoy.bms.setting.persist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
@SuppressWarnings("rawtypes")
public interface EquipdataDao extends BaseDao<Equipdata> {

	public List<Map<String, Object>> getNewestData(Map map) ;
	
	public long  getNewestDataCount(Map map) ;
	
	public List<Map<String, Object>> getDataNoPage(Map map) ;
	
	public long  getDataNoPageCount(Map map) ;

	public List<Map<String,Object>> commonAnalysis(Map map) ;

	public List<Map<String, Object>> queryOnlineAndOffLineNums(Map map);

	public void updateErrorData(Map<String, Object> equip);

	public Map<String, Object> getStationCurrentDataByDataId(String id);

	public void updateRealData(Map<String, Object> map);

	public Map<String, Object> getNewestDataByAddress(String addressCode);

	public void insertDataHistory(Map map) ;
	
	public void updateHistoryParentId(@Param("addressCode") String addressCode);
	
	public List<Map<String, Object>> getHistoryEveryday(Map map);

	public List<Map<String, Object>> getHistorySearch(Map map);
	
	public List<Map<String, Object>> getSpecialDayData(Map map);

	public List<Map<String, Object>> getSpecialDayData2(@Param("startDate") String startDate,@Param("endDate") String endDate);
	
}