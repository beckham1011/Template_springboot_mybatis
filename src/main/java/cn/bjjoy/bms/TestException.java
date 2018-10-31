package cn.bjjoy.bms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.bjjoy.bms.setting.persist.model.Alarm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestException {
	
//	public static void main(String[] args) {
//		boolean flag = false ;
//		int i = 0 ;
//		while(i<5) {
//			if(!flag){
//				i ++ ;
//				System.out.println("Success Execute Count..." + i);
//			}else{
//				i = 5;
//				System.out.println("Fail...");
//			}
//		}
//	}
	
	
//	public static void main(String[] args) {
////		String source = "68 73 67 30 37 37" ;
////		StringBuffer addressCode = new StringBuffer(300) ;
////		for(String str : source.split(" ")){
////			Integer x = Integer.parseInt(str,16);
////			addressCode.append(SpringSocketUtil.byteAsciiToChar( x ));
////		}
////		System.out.println(addressCode.toString());
//		String source = "01 04 2C 3F 00 00 00 3F 00 00 00 00 00 00 00 45 3A 3F 00 00 00 3F 3F 00 00 00 00 00 00 00 05 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 3F 46";
//		String[] values = SpringSocketUtil.parse8082SocketData( source );
////		
//		System.out.println(values[0] + " , " +values[1] + " , " +values[2] + " , " +values[3]);
//		System.out.println(values[0] + " , " + new BigDecimal(values[1]) + " , " + new BigDecimal(values[2]) + " , " + new BigDecimal(values[3]));
////		String source = "11 14 2C 3F 11 11 11 3F 11 11 11 11 11 11 11 45 1D 3F 11 11 11 3F 54 11 11 11 11 11 11 11 3F 11 11 11 11 11 15 11 11 11 11 11 11 11 11 11 11 3F 6D" ;
////		String  subStr = source.substring(9, 9 + 11) ;
////		System.out.println(subStr);
//		
//	}

	
	public static void main(String[] args) {
		Alarm alarm = new Alarm() ;
		alarm.setId(1);
		alarm.setAlarm("alarm001");
		String jsonString = JSONObject.toJSONString(alarm);
		System.out.println(jsonString);
		//json转实体
		Alarm alarm2  = (Alarm) json2Object(jsonString, Alarm.class);
		System.out.println("Alarm2:"+alarm2.getId() + " , " + alarm.getAlarm());
//		
//		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(alarm));
//		System.out.println(itemJSONObj);
//		
//		System.out.println(Runtime.getRuntime().availableProcessors());
//		System.out.println(Runtime.getRuntime().freeMemory() / (1024 * 1024L));
//		System.out.println(Runtime.getRuntime().maxMemory() / (1024 * 1024L));
//		System.out.println(Runtime.getRuntime().totalMemory() / (1024 * 1024L));
//		
//		Map<String, Object> r =  object2Map(alarm);
//		System.out.println(r.get("id"));
//		
//		JSONObject jsonObj = object2JSON(alarm) ;
//		System.out.println(jsonObj.get("id"));
		
		
		Map<String ,Object> map = object2Map( alarm ) ;
		Alarm alarm3 = (Alarm) map2Obj(map, Alarm.class);
		System.out.println("Alarm3:" + alarm3.getId() + " , " + alarm3.getAlarm());
		
		List<Alarm> lists=new ArrayList<>();
		lists.add(alarm);
		lists.add(alarm2);
		
		JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(lists)); 
		System.out.println("toJSONString:"+jsonArray.toJSONString());
		System.out.println("toString:"+jsonArray.toString());
		

//		List<Alarm> channelItemList  = JSONObject.parseArray(jsonArray , Alarm.class);  /** itemJson: JsonArray  ChannelItem ： 对象bean类**/
		System.out.println("--------JSON string to List Bean--------------");
		List<Alarm> alarms = JSON.parseArray(jsonArray.toJSONString(), Alarm.class);
		for(Alarm a : alarms){
			System.out.println(object2JSON(a).toJSONString());
		}
	}
	
	//Object转JSON
	private static JSONObject object2JSON(Object object){
		JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
		return jsonObject;
	}
	
	//JSON转object
	private static Object json2Object(String jsonObject , Class objClass){
		Object obj = JSON.parseObject(jsonObject, objClass);
		return obj;
	}
	
	
	//Object转map
	private static Map<String, Object> object2Map(Object object){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Entry<String,Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map=new HashMap<String,Object>();
        for (Entry<String, Object> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
	
	// map -> object
	private static Object map2Obj(Map<String, Object> map , Class clas){
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        return JSON.parseObject(itemJSONObj.toString(), clas);
    }
	
	//map -> json
	public JSONObject mapToJson(Map<String, Object> map){
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map)); /** itemMap 为 Map<String, String>**/
		return itemJSONObj ;
	}
	
	
}
