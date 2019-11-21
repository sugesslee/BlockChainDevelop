package com.nwnu.blockchain.repository.manager;

import cn.hutool.core.util.StrUtil;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.common.constant.Constants;
import com.nwnu.blockchain.repository.db.DbStore;
import com.nwnu.blockchain.utils.FastJsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * DbBlockManager
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:29 PM
 * @since 1.0.0
 */
@Service
public class DbBlockManager {
	@Resource
	private DbStore dbStore;

	/**
	 * 查找第一个区块
	 *
	 * @return 第一个Block
	 */
	public Block getFirstBlock() {
		String firstBlockHash = dbStore.get(Constants.KEY_FIRST_BLOCK);
		if (StrUtil.isEmpty(firstBlockHash)) {
			return null;
		}
		return getBlockByHash(firstBlockHash);
	}

	/**
	 * 获取最后一个区块信息
	 *
	 * @return 最后一个区块
	 */
	public Block getLastBlock() {
		String lastBlockHash = dbStore.get(Constants.KEY_LAST_BLOCK);
		if (StrUtil.isEmpty(lastBlockHash)) {
			return null;
		}
		return getBlockByHash(lastBlockHash);
	}

	/**
	 * 获取最后一个区块的hash
	 *
	 * @return hash
	 */
	public String getLastBlockHash() {
		Block block = getLastBlock();
		if (block != null) {
			return block.getBlockHash();
		}
		return null;
	}

	/**
	 * 获取最后一个block的number
	 * @return number
	 */
	public int getLastBlockNumber() {
		Block block = getLastBlock();
		if (block != null) {
			return block.getBlockHeader().getNumber();
		}
		return 0;
	}

	/**
	 * 获取某一个block的下一个Block
	 *
	 * @param block
	 *         block
	 * @return block
	 */
	public Block getNextBlock(Block block) {
		if (block == null) {
			return getFirstBlock();
		}
		String nextHash = dbStore.get(Constants.KEY_BLOCK_NEXT_PREFIX + block.getBlockHash());
		if (nextHash == null) {
			return null;
		}
		return getBlockByHash(nextHash);
	}

	public Block getNextBlockByHash(String hash) {
		if (hash == null) {
			return getFirstBlock();
		}
		String nextHash = dbStore.get(Constants.KEY_BLOCK_NEXT_PREFIX + hash);
		if (nextHash == null) {
			return null;
		}
		return getBlockByHash(nextHash);
	}

	public Block getBlockByHash(String hash) {
		String blockJson = dbStore.get(hash);
		return FastJsonUtil.toBean(blockJson, Block.class);
	}
}
