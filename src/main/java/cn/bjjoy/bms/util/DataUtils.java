package cn.bjjoy.bms.util;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by bjjoy on 2017/11/02.
 */
public class DataUtils {
    private static final Logger logger = LoggerFactory.getLogger(DataUtils.class);
    
    /**
     * 转化为List<T>
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> LinkedList<T> getDataArray(Object data, Class<T> clazz) {
        String jsonString = JSONObject.toJSONString(data);
        List<T> linkedList = new LinkedList<T>();
        for(T t : JSONObject.parseArray(jsonString, clazz)){
        	linkedList.add(t);
        }
        return (LinkedList<T>) linkedList;
    }

    /**
     * 转化为T
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getData(Object data, Class<T> clazz) {
        String jsonString = JSONObject.toJSONString(data);
        return JSONObject.parseObject(jsonString, clazz);
    }
}
