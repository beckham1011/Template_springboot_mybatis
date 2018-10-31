package cn.bjjoy.bms.setting.service.impl;import java.util.ArrayList;import java.util.HashMap;import java.util.LinkedList;import java.util.List;import java.util.Map;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import cn.bjjoy.bms.setting.dao.BaseDao;import cn.bjjoy.bms.setting.dto.EquiptypeDto;import cn.bjjoy.bms.setting.persist.mapper.EquiptypeDao;import cn.bjjoy.bms.setting.persist.model.Equiptype;import cn.bjjoy.bms.setting.persist.model.System;import cn.bjjoy.bms.setting.service.EquiptypeService;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@Service@Transactional@SuppressWarnings({ "rawtypes", "unchecked" })public class EquiptypeServiceImpl extends BaseServiceImpl<Equiptype> implements EquiptypeService {		Logger logger = LoggerFactory.getLogger(EquiptypeServiceImpl.class) ;		@Autowired	private EquiptypeDao equiptypeDao;	@Override	public BaseDao<Equiptype> getBaseDao() {		return equiptypeDao;	}		public List<Map<String,Object>> getListNoPage(Map map ){		return equiptypeDao.queryMapListNoPage(map) ;	}	@Override	public List<Map<String, Object>> getSubType(Map map) {		return equiptypeDao.querySubTypes(map) ;	}		public List<Integer> getSubTypeIds(int parentId, String stationName){		List<Integer> result = new ArrayList<Integer>();		Map map = new HashMap<String,Object>();		map.put("parentId", parentId) ;				if(stationName != null && !"".equalsIgnoreCase(stationName))			map.put("stationName", stationName) ;				List<Map<String, Object>> equipTypes = equiptypeDao.getSubTypes(map) ;		logger.info("parentId" + parentId) ;		for(Map equipMap : equipTypes){			result.add((Integer) equipMap.get("id")) ;		}		return result;	}			public List<Integer> getSubTypeIds(LinkedList<EquiptypeDto> subTypeList){		List<Integer> ids = new ArrayList<>();		for(EquiptypeDto type : subTypeList){			ids.add(type.getId());		}		return ids ;	}		public int getParentId(Map map){		int parentId3 = Integer.valueOf(map.containsKey("parentId3") ? (String)map.get("parentId3") : "-3");		int parentId2 = Integer.valueOf(map.containsKey("parentId2") ? (String)map.get("parentId2") : "-2");		int parentId1 = Integer.valueOf(map.containsKey("parentId1") ? (String)map.get("parentId1") : "-1");		//初始情况都未选择，从第一父节点选值		if( parentId3 == -3 && parentId2 == -2 && parentId1 == -1 ){			return 1;		}else if( parentId3 == -3 && parentId2 == -2 && parentId1 > 0 ){			return parentId1;		}else if( parentId3 == -3 && parentId2 > 0 ){			return parentId2;		}else if( parentId3 > 0 ){			return parentId3;		}		return 1 ;	}	@Override	public void updateByAddressCode(Map map) {		equiptypeDao.updateByAddressCode(map);	}			public String getAddressCodeByIP(String ip){		return equiptypeDao.getAddressCodeByIp(ip) ;	}		public String getIPByAddressCode(String addressCode){		return equiptypeDao.getIPByAddressCode(addressCode) ;	}	public Integer[] getTypeAndLayer(int type1, int type2 ,int type3){		Integer[] values = new Integer[2] ;		if(type1 < 0){			values[0] = 1 ;			values[1] = 2 ;		}else if(type2 < 0){			values[0] = type1 ;			values[1] = 3 ;		}else if(type2 < 0){			values[0] = type2 ;			values[1] = 4 ;		}		return values ;	}	//判断当前属于哪层的数据	public Integer[] getTypeAndLayer(Equiptype type){		Integer[] types = new Integer[2];		if("2".equals(type.getTypeLayer())){					}else if("3".equals(type.getTypeLayer())){			types[0] = type.getParentId() ;		}else if("4".equals(type.getTypeLayer())){			types[0] = equiptypeDao.queryOne(String.valueOf( type.getParentId())).getParentId() ;			types[1] = type.getParentId() ;		}		return types ;	}		public List<System> getSystems(){				List<System> systems = new ArrayList<>();		System s1 = new System();		s1.setId(0);		s1.setSystem("南京厚水智能监控系统");		systems.add(s1);				System s2 = new System();		s2.setId(1);		s2.setSystem("高港区农业综合水价改革远程监测系统");		systems.add(s2);				System s3 = new System();		s3.setId(2);		s3.setSystem("南京测试");		systems.add(s3);				System s4 = new System();		s4.setId(3);		s4.setSystem("远程监测系统");		systems.add(s4);				System s5 = new System();		s5.setId(4);		s5.setSystem("大丰区农业水价综合改革监测系统");		systems.add(s5);		        return systems ;	}		public List<String> getAddressCodeNull() {		return equiptypeDao.getAddressCodeNull() ;	}}