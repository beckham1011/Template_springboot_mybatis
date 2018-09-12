package cn.bjjoy.bms.setting.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.entity.CamacBusiKeymsgFeedbk;

/**
 * 类名称   : CamacBusiKeymsgFeedbkDao
 * 类描述   : CAMAC_KeyMessage反馈
 * 创建人	: system
 * 创建时间 ：2018-09-12 16:16:56
 * @version 1.0
 */
@Mapper
@Repository
public interface CamacBusiKeymsgFeedbkDao extends BaseDao<CamacBusiKeymsgFeedbk> {

}