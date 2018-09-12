package cn.bjjoy.bms.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Response implements Serializable {

	private static final long serialVersionUID = 5647506285715637308L;
	private static final int SUCCESS = 200;
	private static final int ERROR = 500;
	private static final int BUSY = 666;

	private int status;
	private String msg;
	private Object data;
	
	public Response() {
	}

	public static Response success() {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";
		return res;
	}

	public static Response successId(String id) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", id);
		
		res.data = map;

		return res;
	}

	public static Response success(String msg) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = msg;
		return res;
	}
	
	public static Response success(Object objct) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";
		
		if(objct == null){
			res.data = new LinkedHashMap<>();
		}else{
			res.data = objct;
		}
		return res;
	}

	public static Response success(Map<String, Object> map) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";
		res.data = map;
		return res;
	}
	
	public static Response success(String msg, Map<String, Object> map) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = msg;
		res.data = map;
		return res;
	}
	
	public static Response success(Collection<?> list) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("rows", list);
		
		res.data = map;
		return res;
	}
	public static Response success(Page page, Collection<?> list) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (page != null) {
			map.put("totalPage", page.getTotalPage());
			map.put("total", page.getTotal());
			map.put("page", page.getPage());

			if (list != null) {
				map.put("count", list.size());
			}
		}
		map.put("rows", list);
		
		res.data = map;
		return res;
	}
	
	public static Response success(List<Map<String, Object>> list, Page page) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (page != null) {
			map.put("totalPage", page.getTotalPage());
			map.put("total", page.getTotal());
			map.put("page", page.getPage());

			if (list != null) {
				map.put("count", list.size());
			}
		}
		map.put("rows", list);
		
		res.data = map;
		return res;
	}

	public static Response data(Map<String, Object> data) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";
		
		res.data = data;
		return res;
	}
	
	public static Response data(Object data) {
		Response res = new Response();
		res.status = SUCCESS;
		res.msg = "成功";
		
		res.data = data;
		return res;
	}

	public static Response busy() {
		Response res = new Response();
		res.status = BUSY;
		res.msg = "服务器繁忙请稍后重试";
		
		return res;
	}

	public static Response error(String msg) {
		Response res = new Response();
		res.status = ERROR;
		res.msg = (msg == null || msg.isEmpty()) ? "Request failed." : msg;
		
		return res;
	}

	public static Response debug(String msg) {
		Response res = new Response();
		res.status = ERROR;
		res.msg = (msg == null || msg.isEmpty()) ? "Request failed." : msg;
		
		return res;
	}

	public static Response error(int code, String msg) {
		Response res = new Response();
		res.status = code;
		res.msg = msg;
		
		return res;
	}
	
	public static Response error401(String msg) {
		Response res = new Response();
		res.status = 401;
		res.msg = msg;
		return res;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int code) {
		this.status = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String message) {
		this.msg = message;
	}

	public Object getData() {
		if(data == null){
			data = new LinkedHashMap<>();
		}
		return data;
	}
}
