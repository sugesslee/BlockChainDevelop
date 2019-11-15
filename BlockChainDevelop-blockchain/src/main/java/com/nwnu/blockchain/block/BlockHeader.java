package com.nwnu.blockchain.block;

import lombok.Data;

import java.util.List;


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
public class BlockHeader {
	/**
	 * 版本号
	 */
	private int version;
	/**
	 * 上一区块的hash
	 */
	private String hashPreviousBlock;
	/**
	 * merkle tree根节点hash
	 */
	private String hashMerkleRoot;
	/**
	 * 生成该区块的公钥
	 */
	private String publicKey;
	/**
	 * 区块的序号
	 */
	private int number;
	/**
	 * 时间戳
	 */
	private long timeStamp;
	/**
	 * 32位随机数
	 */
	private long nonce;
	/**
	 * 该区块里每条交易信息的hash集合，按顺序来的，通过该hash集合能算出根节点hash
	 */
	private List<String> hashList;
}
