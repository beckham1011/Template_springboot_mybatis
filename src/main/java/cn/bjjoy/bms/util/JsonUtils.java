package cn.bjjoy.bms.util;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.ObjectUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("rawtypes")
public class JsonUtils {

    public static final String STATUS = "status";
    public static final String STATUS_STR = "执行结果";
    
    public static final String MSG = "msg";
    public static final String ERROR_MSG = "errorMsg";
    public static final int ERROR_CODE = 500;
    public static final String ERROR_STR = "失败";
    
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_STR = "成功";
    
    public static final String DATA = "data";
    
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_STR = "描述";
    
    /**
     * @Title outJsonString
     * @Description 按照字符串方式输出json
     * @date 2017年8月21日 下午12:09:18
     * @auther guoyongfeng
     * @param str
     * @param response
     * @return void
     */
    public static void outJsonString(String str, HttpServletResponse response) {
        try {
            response.setContentType("text/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title outJsonStringHtml
     * @Description 按照html方式输出json
     * @date 2017年8月21日 下午12:09:30
     * @auther guoyongfeng
     * @param str
     * @param response
     * @return void
     */
    public static void outJsonStringHtml(String str, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title outJsonStringText
     * @Description 按照页面打印方式输出json
     * @date 2017年8月21日 下午12:09:38
     * @auther guoyongfeng
     * @param str
     * @param response
     * @return void
     */
    public static void outJsonStringText(String str, HttpServletResponse response) {
        try {
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject toJSON(String str) {
        if (StringUtils.isEmpty(str)) {
            return new JSONObject(true);
        }
        return (JSONObject) JSONObject.parse(str);
    }
    
    public static JSONObject toJSON(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return new JSONObject(true);
        }
        return (JSONObject) JSONObject.toJSON(obj);
    }
    
    /**
     * @Title toStr
     * @Description json 转换成string
     * @date 2017年8月21日 下午12:10:01
     * @auther guoyongfeng
     * @param json
     * @return String
     */
    public static String toStr(JSONObject json) {
        if (null == json || json.isEmpty()) {
            json = new JSONObject(true);
        }
        return json.toString();
    }

    /**
     * @Title toJSONArray
     * @Description 集合转换成json
     * @date 2017年8月21日 下午12:10:11
     * @auther guoyongfeng
     * @param list
     * @return JSONArray
     */
    public static JSONArray toJSONArray(List list) {
        if (CollectionUtils.isEmpty(list)) {
            list = new JSONArray();
        }
        return (JSONArray) JSONArray.toJSON(list);
    }
    
    /**
     * @Title toJSONArray
     * @Description 字符串型结构转换成json
     * @date 2018年5月28日 07:39:28
     * @auther guoyongfeng
     * @param list
     * @return JSONArray
     */
    public static JSONArray toJSONArray(String str) {
        JSONArray jsonArray;
        if (StringUtils.isEmpty(str)) {
            jsonArray = new JSONArray();
        }
        
        try {
            jsonArray = JSONArray.parseArray(str);
        } catch (Exception e) {
            jsonArray = new JSONArray();
        }
        return jsonArray;
    }
    
    public static boolean isEmpty(JSONObject json) {
        
        if (null == json || json.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public static boolean isNotEmpty(JSONObject json) {
        return !isEmpty(json);
    }
    
    public static boolean isEmpty(JSONArray jsonArr) {
        
        if (null == jsonArr || jsonArr.isEmpty() || jsonArr.size() == 0) {
            return true;
        }
        return false;
    }
    
    public static boolean isNotEmpty(JSONArray jsonArr) {
        return !isEmpty(jsonArr);
    }
    
    /**
     * @Title setAppSuccess
     * @Description 设置成功
     * @date 2017年8月21日 下午12:09:45
     * @auther guoyongfeng
     * @return
     * @return JSONObject
     */
    public static JSONObject setAppSuccess() {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, SUCCESS_CODE);
        json.put(MSG, StringUtils.EMPTY);
        return json;
    }
    
    /**
     * @Title setAppError
     * @Description APP 失败方法
     * @date 2017年8月21日 下午12:09:52
     * @auther guoyongfeng
     * @return
     * @return JSONObject
     */
    public static JSONObject setAppError() {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, ERROR_CODE);
        return json;
    }
    
    public static JSONObject setSuccess() {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, 1);
        json.put(ERROR_MSG, StringUtils.EMPTY);
        json.put(DATA, StringUtils.EMPTY);
        return json;
    }

    public static JSONObject setSuccess(String msg) {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, 1);
        json.put(ERROR_MSG, StringUtils.isEmpty(msg) ? StringUtils.EMPTY : msg);
        json.put(DATA, StringUtils.EMPTY);
        return json;
    }
    
    public static JSONObject setSuccess(String msg, Object data) {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, 1);
        json.put(ERROR_MSG, StringUtils.isEmpty(msg) ? StringUtils.EMPTY : msg);
        json.put(DATA, ObjectUtils.isEmpty(data) ? StringUtils.EMPTY : data);
        return json;
    }
    
    public static JSONObject setError() {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, 0);
        json.put(ERROR_MSG, StringUtils.EMPTY);
        json.put(DATA, StringUtils.EMPTY);
        return json;
    }

    public static JSONObject setError(String msg) {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, 0);
        json.put(ERROR_MSG, msg);
        json.put(DATA, StringUtils.EMPTY);
        return json;
    }
    
    public static JSONObject setError(String msg, Object data) {
        JSONObject json = new JSONObject(true);
        json.put(STATUS, 0);
        json.put(ERROR_MSG, StringUtils.isEmpty(msg) ? StringUtils.EMPTY : msg);
        json.put(DATA, ObjectUtils.isEmpty(data) ? StringUtils.EMPTY : data);
        return json;
    }
}