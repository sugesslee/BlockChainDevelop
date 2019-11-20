package com.nwnu.blockchain.p2p.core.requestbody;

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
public class InstructionBody {
	/**
	 * 指令的操作，增删改
	 */
	private byte operation;
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
	private String instructionId;
	/**
	 * 私钥
	 */
	private String privateKey;
	/**
	 * 公钥
	 */
	private String publicKey;
}
