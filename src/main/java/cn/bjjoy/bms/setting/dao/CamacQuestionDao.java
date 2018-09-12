package cn.bjjoy.bms.setting.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.entity.CamacQuestion;

/**
 * 类名称   : CamacQuestionDao
 * 类描述   : CAMAC_角色
 * 创建人	: system
 * 创建时间 ：2018-09-12 16:16:57
 * @version 1.0
 */
@Mapper
@Repository
public interface CamacQuestionDao extends BaseDao<CamacQuestion> {

}