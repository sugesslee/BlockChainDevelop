package com.nwnu.blockchain.block;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 区块body内一条交易
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
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Transaction extends TransactionBase {
	/**
	 * 内容
	 */
	private String json;
	/**
	 * 时间戳
	 */
	private Long timeStamp;
	/**
	 * 操作人的公钥
	 */
	private String publicKey;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 该操作的hash
	 */
	private String hash;
}
