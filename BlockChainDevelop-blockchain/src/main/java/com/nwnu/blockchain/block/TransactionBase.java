package com.nwnu.blockchain.block;

import lombok.Data;
import lombok.ToString;

/**
 * TransactionBase
 * blockBody内一条指令的基础属性
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:21 PM
 * @since 1.0.0
 */
@Data
@ToString
public class TransactionBase {
	/**
	 * 操作的表名
	 */
	private String table;
	/**
	 * 最终要执行入库的json内容
	 */
	private String oldJson;
	/**
	 * 业务id，sql语句中where需要该Id
	 */
	private String transactionId;

}
