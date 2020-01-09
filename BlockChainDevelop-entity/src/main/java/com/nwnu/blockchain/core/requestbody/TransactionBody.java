package com.nwnu.blockchain.core.requestbody;

import lombok.Data;
import lombok.ToString;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:39 PM
 * @since 1.0.0
 */
@Data
@ToString
public class TransactionBody {
	/**
	 * 操作的表名
	 */
	private String table;
	/**
	 * 具体内容
	 */
	private String json;
	/**
	 * 原始内容
	 */
	private String oldJson;
	/**
	 * 业务id
	 */
	private String transactionId;
	/**
	 * 私钥
	 */
	private String privateKey;
	/**
	 * 公钥
	 */
	private String publicKey;
}
