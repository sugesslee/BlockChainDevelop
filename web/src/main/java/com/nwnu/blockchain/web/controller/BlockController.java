package com.nwnu.blockchain.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.block.BlockBody;
import com.nwnu.blockchain.block.Transaction;
import com.nwnu.blockchain.check.BlockChecker;
import com.nwnu.blockchain.common.exception.TrustSDKException;
import com.nwnu.blockchain.core.bean.BaseData;
import com.nwnu.blockchain.core.bean.ResultGenerator;
import com.nwnu.blockchain.core.body.RpcBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.core.packet.PacketBuilder;
import com.nwnu.blockchain.core.packet.PacketType;
import com.nwnu.blockchain.core.requestbody.BlockRequestBody;
import com.nwnu.blockchain.core.requestbody.TransactionBody;
import com.nwnu.blockchain.packet.PacketSender;
import com.nwnu.blockchain.repository.event.DbSyncEvent;
import com.nwnu.blockchain.repository.manager.DbBlockManager;
import com.nwnu.blockchain.repository.manager.MessageManager;
import com.nwnu.blockchain.repository.manager.SyncManager;
import com.nwnu.blockchain.service.BlockService;
import com.nwnu.blockchain.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * BlockController
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:01 PM
 * @since 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("/block")
public class BlockController {
	private final BlockService blockService;
	private final PacketSender packetSender;
	private final DbBlockManager dbBlockManager;
	private final TransactionService transactionService;
	private final SyncManager syncManager;
	private final MessageManager messageManager;
	private final BlockChecker blockChecker;

	public BlockController(BlockService blockService, PacketSender packetSender, DbBlockManager dbBlockManager,
						   TransactionService transactionService, SyncManager syncManager,
						   MessageManager messageManager, BlockChecker blockChecker) {
		this.blockService = blockService;
		this.packetSender = packetSender;
		this.dbBlockManager = dbBlockManager;
		this.transactionService = transactionService;
		this.syncManager = syncManager;
		this.messageManager = messageManager;
		this.blockChecker = blockChecker;
	}

	/**
	 * 添加一个block，需要先在TransactionController构建1-N个Transaction，然后调用该接口生成Block
	 *
	 * @param blockRequestBody 交易的集合
	 * @return 结果
	 */
	@PostMapping
	public BaseData add(@RequestBody BlockRequestBody blockRequestBody) throws TrustSDKException {
		String msg = blockService.check(blockRequestBody);
		if (msg != null) {
			return ResultGenerator.genFailResult(msg);
		}
		return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
	}

	/**
	 * 测试生成一个insert:Block，公钥私钥可以通过PairKeyController来生成
	 *
	 * @param content sql内容
	 */
	@GetMapping
	public BaseData test(String content) throws Exception {
		TransactionBody transactionBody = new TransactionBody();
		transactionBody.setTable("message");
		transactionBody.setJson("{\"content\":\"" + content + "\"}");
		transactionBody.setPublicKey("A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54");
		transactionBody.setPrivateKey("yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=");
		log.info("build transaction");
		Transaction transaction = transactionService.build(transactionBody);

		BlockRequestBody blockRequestBody = new BlockRequestBody();
		blockRequestBody.setPublicKey(transactionBody.getPublicKey());
		BlockBody blockBody = new BlockBody();
		blockBody.setTransactions(CollectionUtil.newArrayList(transaction));

		blockRequestBody.setBlockBody(blockBody);
		log.info("add block");

		return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
	}

	/**
	 * 查询已落地的sqlite里的所有数据
	 */
	@GetMapping("sqlite")
	public BaseData sqlite() {
		return ResultGenerator.genSuccessResult(messageManager.findAll());
	}

	/**
	 * 查询已落地的sqlite里content字段
	 */
	@GetMapping("sqlite/content")
	public BaseData content() {
		return ResultGenerator.genSuccessResult(messageManager.findAllContent());
	}

	/**
	 * 获取最后一个block的信息
	 */
	@GetMapping("db")
	public BaseData getRockDB() {
		return ResultGenerator.genSuccessResult(dbBlockManager.getLastBlock());
	}

	/**
	 * 手工执行区块内sql落地到sqlite操作
	 *
	 * @param pageable 分页
	 * @return 已同步到哪块了的信息
	 */
	@GetMapping("sync")
	public BaseData sync(@PageableDefault Pageable pageable) {
		ApplicationContextProvider.publishEvent(new DbSyncEvent(""));
		return ResultGenerator.genSuccessResult(syncManager.findAll(pageable));
	}

	/**
	 * 全量检测区块是否正常
	 *
	 * @return null - 通过
	 * hash - 第一个异常hash
	 */
	@GetMapping("checkb")
	public BaseData checkAllBlock() {

		Block block = dbBlockManager.getFirstBlock();

		String hash = null;
		while (block != null && hash == null) {
			hash = blockChecker.checkBlock(block);
			block = dbBlockManager.getNextBlock(block);
		}
		return ResultGenerator.genSuccessResult(hash);
	}

	@GetMapping("/next")
	public BaseData nextBlock() {
		Block block = dbBlockManager.getFirstBlock();
		BlockPacket packet = new PacketBuilder<RpcBlockBody>()
				.setType(PacketType.NEXT_BLOCK_INFO_REQUEST)
				.setBody(new RpcBlockBody(block)).build();
		packetSender.sendGroup(packet);
		return null;
	}
}
