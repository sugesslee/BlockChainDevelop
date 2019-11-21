package com.nwnu.blockchain.repository.db;

/**
 * key-value型DB数据库操作接口
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:30 PM
 * @since 1.0.0
 */
public interface DbStore {
	/**
	 * 数据库key value
	 *
	 * @param key   key
	 * @param value value
	 */
	void put(String key, String value);

	/**
	 * get By Key
	 *
	 * @param key key
	 * @return value
	 */
	String get(String key);

	/**
	 * remove by key
	 *
	 * @param key key
	 */
	void remove(String key);
}
