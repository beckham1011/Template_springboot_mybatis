package cn.bjjoy.bms.setting.persist.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;
import cn.bjjoy.bms.setting.persist.model.System;

/**
 * 类名称   : SystemDao
 * 类描述   : 
 * 创建人	: system
 * 创建时间 ：2018-09-13 23:02:19
 * @version 1.0
 */
@Mapper
@Repository
public interface SystemDao extends BaseDao<System> {

}