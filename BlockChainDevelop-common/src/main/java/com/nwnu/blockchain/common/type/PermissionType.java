package com.nwnu.blockchain.common.type;

/**
 * 对表的权限
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 9:32 AM
 * @since 1.0.0
 */
public interface PermissionType {
	/**
	 * 表的创建者
	 */
	byte OWNER = 1;
	/**
	 * 所有权限
	 */
	byte ALL = 2;
	byte ADD = 3;
	byte UPDATE = 4;
	byte DELETE = 5;
	/**
	 * 不可见
	 */
	byte NONE = -1;
}
