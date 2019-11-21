package com.nwnu.blockchain.check;

import com.nwnu.blockchain.block.Block;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:11 PM
 * @since 1.0.0
 */
public interface BlockChecker {
	/**
	 * 比较目标区块和自己本地的区块num大小
	 *
	 * @param block 被比较的区块
	 * @return 本地与目标区块的差值
	 */
	int checkNum(Block block);

	/**
	 * 校验区块内操作的权限是否合法
	 *
	 * @param block block
	 * @return 大于0合法
	 */
	int checkPermission(Block block);

	/**
	 * 校验hash，包括prevHash、内部hash（merkle tree root hash）
	 *
	 * @param block block
	 * @return 大于0合法
	 */
	int checkHash(Block block);

	/**
	 * 校验生成时间
	 *
	 * @param block block
	 * @return block
	 */
	int checkTime(Block block);

	/**
	 * 校验签名
	 *
	 * @param block block
	 * @return block
	 */
	int checkSign(Block block);

	/**
	 * 校验block，包括签名、hash、关联关系
	 *
	 * @param block
	 * @return
	 */
	public String checkBlock(Block block);
}
