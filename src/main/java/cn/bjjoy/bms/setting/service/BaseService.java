package cn.bjjoy.bms.setting.service;

import java.util.List;
import java.util.Map;

import cn.bjjoy.bms.setting.dao.BaseDao;
import cn.bjjoy.bms.setting.exception.ServiceException;

public interface BaseService<T> {
	
	public BaseDao<T> getBaseDao() throws ServiceException;

	/**
	 * 插入数据
	 * @param t
	 * @return
	 */
	long save(T t) throws ServiceException;
	/**
	 * 根据主键删除
	 * @param map
	 * @return
	 */
	long deleteOne(String id) throws ServiceException;
	/**
	 * 根据条件删除
	 * @param map
	 * @return
	 */
	long delete(Map<String,Object> map) throws ServiceException;
	/**
	 * 通过主键修改
	 * @param map
	 * @return
	 */
	long updateOne(Map<String,Object> map) throws ServiceException;
	/**
	 * 通过条件修改
	 * @param map
	 * @return
	 */
	long update(Map<String,Object> map) throws ServiceException;
	/**
	 * 查询一条结果
	 * @param map
	 * @return
	 */
	T queryOne (String id) throws ServiceException;
	/**
	 * 查询集合
	 * @param map
	 * @return
	 */
	List<T> queryList(Map<String,Object> map) throws ServiceException;
	/**
	 * 总条数
	 * @param map
	 * @return
	 */
	long queryListCount(Map<String,Object> map) throws ServiceException;
	/**
	 * 查询集合map
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> queryMapList(Map<String,Object> map) throws ServiceException;
	/**
	 * 查询集合map 总条数
	 * @param map
	 * @return
	 */
	long queryMapListCount(Map<String,Object> map) throws ServiceException;

}
