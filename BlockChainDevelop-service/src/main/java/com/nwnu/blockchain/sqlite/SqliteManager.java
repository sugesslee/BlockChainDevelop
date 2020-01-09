package com.nwnu.blockchain.sqlite;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.block.Instruction;
import com.nwnu.blockchain.block.InstructionBase;
import com.nwnu.blockchain.block.InstructionReverse;
import com.nwnu.blockchain.core.model.SyncEntity;
import com.nwnu.blockchain.repository.event.DbSyncEvent;
import com.nwnu.blockchain.repository.manager.DbBlockManager;
import com.nwnu.blockchain.repository.manager.SyncManager;
import com.nwnu.blockchain.repository.sqlparser.TransactionParser;
import com.nwnu.blockchain.service.InstructionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 对sqlite数据库的操作（监听新增区块请求，执行对应的sql命令）
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
@Component
@Slf4j
public class SqliteManager {
	@Resource
	private TransactionParser transactionParser;
	@Resource
	private SyncManager syncManager;
	@Resource
	private DbBlockManager dbBlockManager;
	@Resource
	private InstructionService instructionService;

	/**
	 * sqlite同步，监听该事件后，去check当前已经同步到哪个区块了，然后继续执行之后的区块
	 */
	@EventListener(DbSyncEvent.class)
	public void dbSync() {
		log.info("开始执行导入区块到Sqlite操作");
		//查看同步到哪个区块了
		SyncEntity syncEntity = syncManager.findLastOne();

		Block block;
		if (syncEntity == null) {
			//从第一个开始
			block = dbBlockManager.getFirstBlock();
			log.info("正在导入第一个区块，hash为：" + block.getBlockHash());
		} else {
			Block lastBlock = dbBlockManager.getLastBlock();
			//已经同步到最后一块了
			if (lastBlock.getBlockHash().equals(syncEntity.getHash())) {
				log.info("导入完毕");
				return;
			}
			log.info("正在导入区块，hash为：" + lastBlock.getBlockHash());
			String hash = syncEntity.getHash();
			block = dbBlockManager.getNextBlock(dbBlockManager.getBlockByHash(hash));
		}
		execute(block);
		ApplicationContextProvider.publishEvent(new DbSyncEvent(""));
	}

	/**
	 * 根据一个block执行sql
	 * 整个block一个事务
	 *
	 * @param block block
	 */
	@Transactional
	public void execute(Block block) {
		List<Instruction> instructions = block.getBlockBody().getInstructions();
		//InstructionParserImpl类里面执行的是InstructionBase，需要转成InstructionBase
		for (Instruction instruction : instructions) {
			instruction.setOldJson(instruction.getJson());
		}
		doSqlParse(instructions);

		//保存已同步的进度
		SyncEntity syncEntity = new SyncEntity();
		syncEntity.setHash(block.getBlockHash());
		syncManager.save(syncEntity);
	}

	/**
	 * 执行回滚一个block
	 *
	 * @param block block
	 */
	public void rollBack(Block block) {
		List<Instruction> instructions = block.getBlockBody().getInstructions();
		int size = instructions.size();
		//需要对语句集合进行反转，然后执行和execute一样的操作
		List<InstructionReverse> instructionReverses = new ArrayList<>(size);
		for (int i = size - 1; i >= 0; i--) {
			instructionReverses.add(instructionService.buildReverse(instructions.get(i)));
		}
		doSqlParse(instructionReverses);
	}

	private <T extends InstructionBase> void doSqlParse(List<T> instructions) {
		for (InstructionBase instruction : instructions) {
			transactionParser.parse(instruction);
		}
	}

	/**
	 * 测试block的代码是否能正确执行
	 *
	 * @param block block
	 * @throws Exception msg=00001 则说明是正常执行
	 */
	@Transactional(rollbackFor = Exception.class)
	public void tryExecute(Block block) throws Exception {
		execute(block);
		throw new Exception("00001");
	}
}