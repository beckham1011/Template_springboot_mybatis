package cn.bjjoy.bms;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.persist.model.Alarm;
import cn.bjjoy.bms.util.SpringSocketUtil;

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
//		
//		String jsonString = JSONObject.toJSONString(alarm);
//		
//		System.out.println(jsonString);
//		Alarm alarm2  = JSON.parseObject(jsonString, Alarm.class);
//		System.out.println(alarm2.getId() + " , " + alarm.getAlarm());
//		
//		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(alarm));
//		System.out.println(itemJSONObj);
		
		Map<String, Object> r =  object2Map(alarm);
		System.out.println(r.get("id"));
	}
	
	//不包含id
	private static Map<String, Object> object2Map(Object object){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Entry<String,Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map=new HashMap<String,Object>();
        for (Entry<String, Object> entry : entrySet) {
        	if(entry.getKey().equals("id"))
        		continue ;
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
	
	
}
