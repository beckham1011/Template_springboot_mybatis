package cn.bjjoy.bms.setting.service;import java.util.Map;import cn.bjjoy.bms.setting.persist.model.Declarationrecord;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@SuppressWarnings("rawtypes")public interface DeclarationrecordService extends BaseService<Declarationrecord> {	public void updateDeclareById(Map map);	}