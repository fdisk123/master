package project.master.dbmaamger.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import project.master.dbmaamger.HibernateReplicationMode;

public interface BaseDao<entity, ID extends Serializable> extends HibernateReplicationMode {

	entity get(ID o) throws Throwable;

	/***
	 * 
	 * @param page
	 *            -1为所有记录
	 * @param size
	 *            -1为所有记录
	 * @return
	 * @throws Throwable
	 */
	List<entity> getList(int page, int size) throws Throwable;

	/***
	 * 
	 * @param params
	 * @param page
	 *            -1为所有记录
	 * @param size
	 *            -1为所有记录
	 * @return
	 * @throws Throwable
	 */
	List<entity> getList(Map<String, Object> params, int page, int size) throws Throwable;

	/***
	 * 适合做DTO
	 * 
	 * @param queryName
	 *            (名字根据 实体类package+语句名)
	 * @param params
	 *            参数
	 * @param isFormat
	 *            是否需要格式化(freemarkFormat="true")
	 * @param page
	 * @param size
	 * @return
	 * @throws Throwable
	 */
	// List<Object[]> getList(String queryName, Map<String, Object> params, boolean isHql, boolean isFormat, int page, int size) throws Throwable;
	<T> List<T> getList(String queryName, Map<String, Object> params, boolean isFormat, int page, int size) throws Throwable;

	List<entity> getListEntity(String queryName, Map<String, Object> params, boolean isFormat, int page, int size) throws Throwable;

	/***
	 * 适合做DTO
	 * 
	 * @param queryName
	 *            (自定义输入)
	 * @param params
	 *            参数
	 * @param isFormat
	 *            是否需要格式化(freemarkFormat="true")
	 * @param page
	 * @param size
	 * @return
	 * @throws Throwable
	 */
	<T> List<T> getListCustomQueryName(String queryName, Map<String, Object> params, boolean isFromat, int page, int size) throws Throwable;

	List<entity> getListEntityCustomQueryName(String queryName, Map<String, Object> params, boolean isFromat, int page, int size) throws Throwable;

	// List<Object[]> getListCustomQueryName(String queryName, Map<String, Object> params, boolean isHql, boolean isFromat, int page, int size) throws Throwable;

	entity save(entity o) throws Throwable;

	void saveOrUpeate(entity o) throws Throwable;

	void update(entity o) throws Throwable;

	void delete(entity o) throws Throwable;

	int executeUpdate(String queryName, Map<String, Object> params, boolean isHql, boolean isFromat) throws Throwable;

	int executeUpdateCustomQueryName(String queryName, Map<String, Object> params, boolean isHql, boolean isFromat) throws Throwable;

	/***
	 * 单一返回值
	 * <p>
	 * 适合做: select count(1)from x
	 * 
	 * @param queryName
	 * @param isHql
	 * @param isFormat
	 * @param params
	 * @return
	 * @throws Throwable
	 */
	// Object uniqueResult(String queryName, boolean isHql, boolean isFormat, Map<String, Object> params) throws Throwable;
	Object uniqueResult(String queryName, boolean isFormat, Map<String, Object> params) throws Throwable;

	Object uniqueResultCustomQueryName(String queryName, boolean isFormat, Map<String, Object> params) throws Throwable;

	// List<entity> getListByHql(String hql, Object params) throws Throwable;

}
