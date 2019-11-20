package com.nwnu.blockchain.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.block.BlockBody;
import com.nwnu.blockchain.block.Instruction;
import com.nwnu.blockchain.block.Operation;
import com.nwnu.blockchain.common.exception.TrustSDKException;
import com.nwnu.blockchain.core.bean.BaseData;
import com.nwnu.blockchain.core.bean.ResultGenerator;
import com.nwnu.blockchain.p2p.check.BlockChecker;
import com.nwnu.blockchain.p2p.core.event.DbSyncEvent;
import com.nwnu.blockchain.p2p.core.manager.DbBlockManager;
import com.nwnu.blockchain.p2p.core.manager.MessageManager;
import com.nwnu.blockchain.p2p.core.manager.SyncManager;
import com.nwnu.blockchain.p2p.core.requestbody.BlockRequestBody;
import com.nwnu.blockchain.p2p.core.requestbody.InstructionBody;
import com.nwnu.blockchain.p2p.core.service.BlockService;
import com.nwnu.blockchain.p2p.core.service.InstructionService;
import com.nwnu.blockchain.p2p.socket.body.RpcBlockBody;
import com.nwnu.blockchain.p2p.socket.client.PacketSender;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.PacketBuilder;
import com.nwnu.blockchain.p2p.socket.vote.PacketType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
	private final InstructionService instructionService;
	private final SyncManager syncManager;
	private final MessageManager messageManager;
	private final BlockChecker blockChecker;

	public BlockController(BlockService blockService, PacketSender packetSender, DbBlockManager dbBlockManager,
						   InstructionService instructionService, SyncManager syncManager,
						   MessageManager messageManager, BlockChecker blockChecker) {
		this.blockService = blockService;
		this.packetSender = packetSender;
		this.dbBlockManager = dbBlockManager;
		this.instructionService = instructionService;
		this.syncManager = syncManager;
		this.messageManager = messageManager;
		this.blockChecker = blockChecker;
	}

	/**
	 * 添加一个block，需要先在InstructionController构建1-N个instruction指令，然后调用该接口生成Block
	 *
	 * @param blockRequestBody 指令的集合
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
		InstructionBody instructionBody = new InstructionBody();
		instructionBody.setOperation(Operation.ADD);
		instructionBody.setTable("message");
		instructionBody.setJson("{\"content\":\"" + content + "\"}");
		instructionBody.setPublicKey("A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54");
		instructionBody.setPrivateKey("yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=");
		log.info("build instruction");
		Instruction instruction = instructionService.build(instructionBody);

		BlockRequestBody blockRequestBody = new BlockRequestBody();
		blockRequestBody.setPublicKey(instructionBody.getPublicKey());
		BlockBody blockBody = new BlockBody();
		blockBody.setInstructions(CollectionUtil.newArrayList(instruction));

		blockRequestBody.setBlockBody(blockBody);
		log.info("add block");

		return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
	}

	/**
	 * 测试生成一个update:Block，公钥私钥可以通过PairKeyController来生成
	 *
	 * @param id      更新的主键
	 * @param content sql内容
	 */
	@GetMapping("testUpdate")
	public BaseData testUpdate(String id, String content) throws Exception {
		if (StringUtils.isBlank(id)) ResultGenerator.genSuccessResult("主键不可为空");
		InstructionBody instructionBody = new InstructionBody();
		instructionBody.setOperation(Operation.UPDATE);
		instructionBody.setTable("message");
		instructionBody.setInstructionId(id);
		instructionBody.setJson("{\"content\":\"" + content + "\"}");
		instructionBody.setPublicKey("A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54");
		instructionBody.setPrivateKey("yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=");
		Instruction instruction = instructionService.build(instructionBody);

		BlockRequestBody blockRequestBody = new BlockRequestBody();
		blockRequestBody.setPublicKey(instructionBody.getPublicKey());
		BlockBody blockBody = new BlockBody();
		blockBody.setInstructions(CollectionUtil.newArrayList(instruction));

		blockRequestBody.setBlockBody(blockBody);

		return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
	}

	/**
	 * 测试生成一个delete:Block，公钥私钥可以通过PairKeyController来生成
	 *
	 * @param id 待删除记录的主键
	 *           sql内容
	 */
	@GetMapping("testDel")
	public BaseData testDel(String id) throws Exception {
		if (StringUtils.isBlank(id)) ResultGenerator.genSuccessResult("主键不可为空");
		InstructionBody instructionBody = new InstructionBody();
		instructionBody.setOperation(Operation.DELETE);
		instructionBody.setTable("message");
		instructionBody.setInstructionId(id);
		instructionBody.setPublicKey("A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54");
		instructionBody.setPrivateKey("yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=");
		Instruction instruction = instructionService.build(instructionBody);

		BlockRequestBody blockRequestBody = new BlockRequestBody();
		blockRequestBody.setPublicKey(instructionBody.getPublicKey());
		BlockBody blockBody = new BlockBody();
		blockBody.setInstructions(CollectionUtil.newArrayList(instruction));

		blockRequestBody.setBlockBody(blockBody);

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
