package com.nwnu.blockchain.block;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.Data;

/**
 * block
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/14     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/14 3:54 PM
 * @since 1.0.0
 */
@Data
public class Block {
	/**
	 * 区块头
	 */
	private BlockHeader blockHeader;
	/**
	 * 区块body
	 */
	private BlockBody blockBody;
	/**
	 * 该区块的hash
	 */
	private String blockHash;

	/**
	 * 根据该区块所有属性计算sha256
	 *
	 * @return sha256hex
	 */
	public String getBlockHash() {
		return DigestUtil.sha256Hex(blockHeader.toString() + blockBody.toString());
	}
}
