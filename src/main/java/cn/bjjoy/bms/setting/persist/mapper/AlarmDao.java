package cn.bjjoy.bms.setting.persist.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;
import cn.bjjoy.bms.setting.persist.model.Alarm;


/**
 * 类名称   : AlarmDao
 * 类描述   : 
 * 创建人	: system
 * 创建时间 ：2018-09-13 23:02:17
 * @version 1.0
 */
@Mapper
@Repository
public interface AlarmDao extends BaseDao<Alarm> {

}