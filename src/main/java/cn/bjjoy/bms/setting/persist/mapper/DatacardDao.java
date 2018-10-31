package cn.bjjoy.bms.setting.persist.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;
import cn.bjjoy.bms.setting.persist.model.Datacard;

/**
 * 类名称   : DatacardDao
 * 类描述   : 
 * 创建人	: system
 * 创建时间 ：2018-09-13 23:02:18
 * @version 1.0
 */
@Mapper
@Repository
public interface DatacardDao extends BaseDao<Datacard> {

	public void updateDatacardById(Map map);
	
}