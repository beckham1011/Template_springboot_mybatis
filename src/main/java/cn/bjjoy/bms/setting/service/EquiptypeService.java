package cn.bjjoy.bms.setting.service;import java.util.LinkedList;import java.util.List;import java.util.Map;import cn.bjjoy.bms.setting.dto.EquiptypeDto;import cn.bjjoy.bms.setting.persist.model.Equiptype;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */public interface EquiptypeService extends BaseService<Equiptype> {	public List<Map<String,Object>> getListNoPage(Map map );		public List<Map<String,Object>> getSubType(Map map) ;	public List<Integer> getSubTypeIds(int parentId ,String stationName);	public List<Integer> getSubTypeIds(LinkedList<EquiptypeDto> subTypeList);		public int getParentId(Map map);		public void updateByAddressCode(Map map);		public String getAddressCodeByIP(String ip);	public String getIPByAddressCode(String addressCode);	public Integer[] getTypeAndLayer(int type1, int type2, int type3);	}