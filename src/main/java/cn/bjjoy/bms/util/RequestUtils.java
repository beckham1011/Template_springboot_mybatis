package cn.bjjoy.bms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.bjjoy.bms.exception.TokenException;
import cn.bjjoy.bms.setting.constants.Constants;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RequestUtils {

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	public static HttpSession getSession() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	public static String getParameter(HttpServletRequest request, String name) {
		
		String value = request.getParameter(name);
		
		if(value == null || "".equals(value))
			return null;

		return value.trim();
	}

	public static List<String> getParameterValues(HttpServletRequest request, String name) {
		
		String[] values = request.getParameterValues(name);
		
		if(values == null)
			return new ArrayList<>();

		return Arrays.asList(values);
	}

	public static List<String> getParameterToList(HttpServletRequest request, String name) {
		
		String value = request.getParameter(name);
		
		if(value == null || "".equals(value))
			return new ArrayList<>();

		return Arrays.asList(value.split(","));
	}

	public <T> T getModel(HttpServletRequest request, String name, Class<T> clazz) {

		String obj = request.getParameter(name);

		if(obj == null || "".equals(obj))
			return null;

		return JSON.parseObject(obj.toString(), clazz);
	}
	
	public List<String> getStringList(HttpServletRequest request, String name) {

		String obj = request.getParameter(name);

		if (obj != null && !"".equals(obj)) {
			return Arrays.asList(obj.toString().trim().split(","));
		}

		return null;
	}
	
	public static String requestString(HttpServletRequest request) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();

		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String str = sb.toString();

		// 防止用get传递参数
		if (str.equals("")) {
			if (request.getQueryString() != null) {
				str = request.getRequestURL() + "?" + request.getQueryString();
			} else {
				str = request.getRequestURL().toString();
			}
		}
		return str;
	}

	public static String rootPath() {
		//return getRequest().getRealPath("/");
		return PropertiesHelper.getProperty("base.upload.path");
	}
	
	public static String userid() throws TokenException {
		
		String authorization = getRequest().getHeader("authorization");
		
		if(authorization == null || "".equals(authorization)) {
			throw new TokenException("请登录");
		}
		
		String userId = JSONObject.parseObject(authorization).getString("userId");
		
		if(userId == null || "".equals(userId)) {
			throw new TokenException("请登录");
		}
		return userId;
	}
	
	public static String studentId() throws TokenException {
		
		String authorization = getRequest().getHeader("authorization");
		
		if(authorization == null || "".equals(authorization)) {
			throw new TokenException("请选择默认关心学生");
		}
		
		String studentId = JSONObject.parseObject(authorization).getString("studentId");
		
		if(studentId == null || "".equals(studentId)) {
			throw new TokenException("请选择默认关心学生");
		}
		
		return studentId;
	}
	
	public static String userType() throws TokenException {
		
		String authorization = getRequest().getHeader("authorization");
		
		if(authorization == null || "".equals(authorization)) {
			throw new TokenException("请登录");
		}
		
		String userType = JSONObject.parseObject(authorization).getString("userType");
		
		if(userType == null || "".equals(userType)) {
			throw new TokenException("请登录");
		}
		
		return userType;
	}
	
	public static UserToken appToken() throws TokenException {

		Object obj = getRequest().getSession().getAttribute(Constants.USER_TOKEN);
		
		if(obj == null) {
			throw new TokenException("请登录");
		}

		return (UserToken) obj;
	}
	
	public static UserToken webToken() throws TokenException {

		Object obj = getRequest().getSession().getAttribute(Constants.USER_TOKEN);
		
		if(obj == null) {
			throw new TokenException("请登录");
		}

		return (UserToken) obj;
	}

	/*    
	public HashMap<String, Object> getParamsToMap() {
    Map<String, String[]> parameterMap = getRequest().getParameterMap();
    HashMap<String, Object> map = new HashMap<String, Object>();
    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
        String key = entry.getKey().toString();
        String[] value = (String[]) parameterMap.get(key);
        if (value[0].equals("")) {
            map.put(key, null);
        } else {
            map.put(key, value[0]);
        }
    }
    return map;
	}*/
	
	/*
	public static UserToken token() {
		
		if(getRequest() == null)
			throw new TokenException("请登录");
		
		Object obj = getRequest().getSession().getAttribute(Constants.USER_TOKEN);
		
		if(obj == null)
			throw new TokenException("请登录");
		
		UserToken user = (UserToken) obj;
		
		return user;
	}
	
	public static void setToken(UserToken token) {
	
		if(getRequest() == null)
			throw new TokenException("请求异常");
		
		token.setTokenId(getRequest().getSession().getId());

		getRequest().getSession().setAttribute(Constants.USER_TOKEN, token);
	}*/
}
