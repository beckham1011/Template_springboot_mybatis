package cn.bjjoy.bms.setting.persist.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.dao.BaseDao;
import cn.bjjoy.bms.setting.persist.model.Declarationrecord;

/**
 * 类名称   : DeclarationrecordDao
 * 类描述   : 
 * 创建人	: system
 * 创建时间 ：2018-09-13 23:02:18
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
@Mapper
@Repository
public interface DeclarationrecordDao extends BaseDao<Declarationrecord> {

	void updateDeclareById(Map map);

}