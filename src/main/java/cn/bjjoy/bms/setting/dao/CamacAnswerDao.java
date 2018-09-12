package cn.bjjoy.bms.setting.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.entity.CamacAnswer;

/**
 * 类名称   : CamacAnswerDao
 * 类描述   : CAMAC_回答
 * 创建人	: system
 * 创建时间 ：2018-09-12 16:16:56
 * @version 1.0
 */
@Mapper
@Repository
public interface CamacAnswerDao extends BaseDao<CamacAnswer> {

}