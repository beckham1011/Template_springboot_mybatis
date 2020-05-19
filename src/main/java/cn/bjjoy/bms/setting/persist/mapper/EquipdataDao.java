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

	List<Map<String, Object>> getNewestData(Map map) ;
	
	long  getNewestDataCount(Map map) ;
	
	List<Map<String, Object>> getDataNoPage(Map map) ;
	
	long  getDataNoPageCount(Map map) ;

	List<Map<String,Object>> commonAnalysis(Map map) ;

	List<Map<String, Object>> queryOnlineAndOffLineNums(Map map);

	void updateErrorData(Map<String, Object> equip);

	Map<String, Object> getStationCurrentDataByDataId(String id);

	void updateRealData(Map<String, Object> map);

	Map<String, Object> getNewestDataByAddress(String addressCode);

	void insertDataHistory(Map map) ;
	
	void updateHistoryParentId(@Param("addressCode") String addressCode);
	
	List<Map<String, Object>> getHistoryEveryday(Map map);

	List<Map<String, Object>> getHistorySearch(Map map);
	
	List<Map<String, Object>> getSpecialDayData(Map map);

	List<Map<String, Object>> getSpecialDayData2(@Param("startDate") String startDate,@Param("endDate") String endDate);
	
}