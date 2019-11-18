package com.nwnu.blockchain.core.bean;

import lombok.Data;
import lombok.ToString;

/**
 * 权限，主要存储各member对表的权限信息，如不可见、只能ADD，可以UPDATE、DELETE等等组合
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:31 AM
 * @since 1.0.0
 */
@Data
@ToString
public class Permission {
	/**
	 * 哪张表
	 */
	private String tableName;
	/**
	 * 操作权限，见PermissionType类
	 */
	private byte permissionType;
	/**
	 * 公钥（账户的概念，能具体到某个member，为*则代表所有节点，不具体指定某个）
	 */
	private String publicKey;
	/**
	 * 该权限是归属于哪个group的。节点只需要获取自己group的权限信息，不需要知道别的group的
	 */
	private String groupId;
}
