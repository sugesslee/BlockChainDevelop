package com.nwnu.blockchain.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.block.BlockBody;
import com.nwnu.blockchain.block.Transaction;
import com.nwnu.blockchain.check.BlockChecker;
import com.nwnu.blockchain.common.exception.TrustSDKException;
import com.nwnu.blockchain.core.bean.BaseData;
import com.nwnu.blockchain.core.bean.ResultCode;
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

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/block_test")
public class BlockTestController {
	private final BlockService blockService;
	private final PacketSender packetSender;
	private final DbBlockManager dbBlockManager;
	private final TransactionService transactionService;
	private final SyncManager syncManager;
	private final MessageManager messageManager;
	private final BlockChecker blockChecker;

	public BlockTestController(BlockService blockService, PacketSender packetSender, DbBlockManager dbBlockManager,
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
	 * 测试生成一个insert:Block，公钥私钥可以通过PairKeyController来生成
	 *
	 *
	 */
	@GetMapping
	public BaseData test() throws Exception {
		// 索引构建测试
		// 128
		long stime = System.currentTimeMillis();
		log.info("开始生成区块时间：{}", System.currentTimeMillis());
		blockService.addBlock(getBlockBodyData(128));
		log.info("128条交易生成索引时长：{} ms.", ( System.currentTimeMillis() - stime));
		log.info("开始生成区块时间：{}", System.currentTimeMillis());
		long s1time = System.currentTimeMillis();
		blockService.addBlock(getBlockBodyData(256));
		log.info("256条交易生成索引时长：{} ms.", ( System.currentTimeMillis() - s1time));

		long s2time = System.currentTimeMillis();
		blockService.addBlock(getBlockBodyData(512));
		log.info("512条交易生成索引时长：{} ms.", ( System.currentTimeMillis() - s2time));

		long s3time = System.currentTimeMillis();
		blockService.addBlock(getBlockBodyData(1024));
		log.info("1024条交易生成索引时长：{} ms.", ( System.currentTimeMillis() - s3time));

		long s4time = System.currentTimeMillis();
		blockService.addBlock(getBlockBodyData(2048));
		log.info("2048条交易生成索引时长：{} ms.", ( System.currentTimeMillis() - s4time));

		long s5time = System.currentTimeMillis();
		blockService.addBlock(getBlockBodyData(4096));
		log.info("4096条交易生成索引时长：{} ms.", ( System.currentTimeMillis() - s5time));

		long s6time = System.currentTimeMillis();
		blockService.addBlock(getBlockBodyData(8192));
		log.info("8192条交易生成索引时长：{} ms.", ( System.currentTimeMillis() - s6time));


		return new BaseData()
				.setCode(ResultCode.SUCCESS)
				.setMessage("success")
				.setData("success");
	}
// "publicKey": "A/yfoakmjR1MHFVn5F3mjCoPWEnJUA9i/8iHXNJfgCGv",
//		 "privateKey": "g4CveIObhPhBPX/fc+iAwKkF67jGd8IKNAeMLP/K+qI="
	public BlockRequestBody getBlockBodyData(int translateDataNum) throws Exception{
		String publicKey = "A/yfoakmjR1MHFVn5F3mjCoPWEnJUA9i/8iHXNJfgCGv";
//		String translateJson = "{\"translateId\":\"11256\", \"translationContent\":\"交易信息\", \"商品信息\", \"商品信息\"}";
		String translateJson = "{\"content\":\"" + "block" + "\"}";
		List<Transaction> transactionList = new ArrayList<>();
		for (int i = 0; i < translateDataNum; i++) {
			TransactionBody transactionBody = new TransactionBody();
			transactionBody.setTable("message");
			transactionBody.setJson(translateJson);
			transactionBody.setPublicKey(publicKey);
			transactionBody.setPrivateKey("g4CveIObhPhBPX/fc+iAwKkF67jGd8IKNAeMLP/K+qI=");
			Transaction transaction = transactionService.build(transactionBody);
			transactionList.add(transaction);
		}

		BlockRequestBody blockRequestBody = new BlockRequestBody();
		blockRequestBody.setPublicKey(publicKey);
		BlockBody blockBody = new BlockBody();
		blockBody.setTransactions(transactionList);
		blockRequestBody.setBlockBody(blockBody);
		return blockRequestBody;
	}
}
