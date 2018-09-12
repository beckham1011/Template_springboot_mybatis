package cn.bjjoy.bms.setting.dao;

import java.util.List;
import java.util.Map;

import cn.bjjoy.bms.setting.exception.DaoException;

public interface BaseDao<T> extends GeneralDao {
	/**
	 * 插入数据
	 * @param t
	 * @return
	 */
	long save(T t) throws DaoException;
	/**
	 * 根据主键删除
	 * @param map
	 * @return
	 */
	long deleteOne(String id) throws DaoException;
	/**
	 * 根据条件删除
	 * @param map
	 * @return
	 */
	long delete(Map<String,Object> map) throws DaoException;
	/**
	 * 通过主键修改
	 * @param map
	 * @return
	 */
	long updateOne(Map<String,Object> map) throws DaoException;
	/**
	 * 通过条件修改
	 * @param map
	 * @return
	 */
	long update(Map<String,Object> map) throws DaoException;
	/**
	 * 查询一条结果
	 * @param map
	 * @return
	 */
	T queryOne (String id) throws DaoException;
	/**
	 * 查询集合
	 * @param map
	 * @return
	 */
	List<T> queryList(Map<String,Object> map) throws DaoException;
	/**
	 * 总条数
	 * @param map
	 * @return
	 */
	long queryListCount(Map<String,Object> map) throws DaoException;
	/**
	 * 查询集合map
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> queryMapList(Map<String,Object> map) throws DaoException;
	/**
	 * 查询集合map 总条数
	 * @param map
	 * @return
	 */
	long queryMapListCount(Map<String,Object> map) throws DaoException;
	/**
	 * 查询一条信息
	 * @param param
	 * @return
	 * @throws DaoException
	 */
	Map<String,Object> queryOneMap(String id) throws DaoException;
}
