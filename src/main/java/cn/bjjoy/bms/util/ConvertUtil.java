package cn.bjjoy.bms.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ConvertUtil {


	// map -> object
	public static Object map2Obj(Map<String, Object> map , Class clas){
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
	    return JSON.parseObject(itemJSONObj.toString(), clas);
	}

	
}
