package com.nwnu.blockchain.block;

import lombok.Data;
import lombok.ToString;

/**
 * InstructionBase
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
public class InstructionBase {
	/**
	 * 指令的操作，增删改（1，-1，2）
	 */
	private byte operation;
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
	private String instructionId;

}
