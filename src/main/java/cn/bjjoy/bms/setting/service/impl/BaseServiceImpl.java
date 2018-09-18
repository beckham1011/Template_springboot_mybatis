package cn.bjjoy.bms.setting.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.exception.ServiceException;
import cn.bjjoy.bms.setting.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger("service");

	@Override
	public long save(T t) throws ServiceException {
		return getBaseDao().save(t);
	}

	@Override
	public long deleteOne(String id) throws ServiceException {
		return getBaseDao().deleteOne(id);
	}

	@Override
	public long delete(Map<String, Object> map) throws ServiceException {
		return getBaseDao().delete(map);
	}

	@Override
	public long updateOne(Map<String, Object> map) throws ServiceException {
		return getBaseDao().updateOne(map);
	}

	@Override
	public long update(Map<String, Object> map) throws ServiceException {
		return getBaseDao().update(map);
	}

	@Override
	public T queryOne(String id) throws ServiceException {
		return getBaseDao().queryOne(id);
	}

	@Override
	public List<T> queryList(Map<String, Object> map) throws ServiceException {
		map.put("isDel", Constants.N);
		return getBaseDao().queryList(map);
	}

	@Override
	public long queryListCount(Map<String, Object> map) throws ServiceException {
		map.put("isDel", Constants.N);
		return getBaseDao().queryListCount(map);
	}

	@Override
	public List<Map<String, Object>> queryMapList(Map<String, Object> map) throws ServiceException {
		map.put("isDel", Constants.N);
		map.put("order", "desc");
		map.put("isDel", Constants.N);
		return getBaseDao().queryMapList(map);
	}
	
	@Override
	public long queryMapListCount(Map<String, Object> map) throws ServiceException {
		map.put("isDel", Constants.N);
		return getBaseDao().queryMapListCount(map);
	}
}