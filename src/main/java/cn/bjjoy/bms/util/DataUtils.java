package cn.bjjoy.bms.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        try {
			for(T t : JSONObject.parseArray(jsonString, clazz)){
				linkedList.add(t);
			}
		} catch (Exception e) {
			logger.error("Parse DataMap Error", e);
			e.printStackTrace();
		}
        return (LinkedList<T>) linkedList;
    }

    public static <T> T convertData(Object data , Class<T> clazz){
    	String jsonString = JSONObject.toJSONString(data);
    	T t = (T) JSONObject.parseObject(jsonString, clazz) ;
    	return t ;
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
    
    
    public static String formattedOutputDecimal(double decimal){
        DecimalFormat df=new DecimalFormat("######0.00");  
        String result=df.format(decimal);  
        return result;
    }
	
    public static String formattedDecimalToPercentage(double decimal){
    	//获取格式化对象
    	NumberFormat nt = NumberFormat.getPercentInstance();
    	//设置百分数精确度2即保留两位小数
    	nt.setMinimumFractionDigits(2);
    	return nt.format(decimal);
    }
    
    public static String formattedDecimalToPercentage(Long val1 , Long val2 ){
    	double val = 0.0;
    	try {
			val = val1 / (double)(val1 + val2) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return formattedDecimalToPercentage(val) ;
    }
    
    
}
