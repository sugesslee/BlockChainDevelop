package com.nwnu.blockchain.p2p.pbft.queue;

import cn.hutool.core.bean.BeanUtil;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.common.AppId;
import com.nwnu.blockchain.core.vote.VoteMsg;
import com.nwnu.blockchain.core.vote.VotePreMsg;
import com.nwnu.blockchain.core.vote.VoteType;
import com.nwnu.blockchain.p2p.pbft.event.MsgPrepareEvent;
import com.nwnu.blockchain.repository.event.AddBlockEvent;
import com.nwnu.blockchain.sqlite.SqliteManager;
import com.nwnu.blockchain.timer.TimerManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * pre prepare消息的存储，但凡收到请求生成Block的信息，都在这里处理
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:39 PM
 * @since 1.0.0
 */
@Component
@Slf4j
public class PreMsgQueue extends BaseMsgQueue {
	@Resource
	private SqliteManager sqliteManager;
	@Resource
	private PrepareMsgQueue prepareMsgQueue;
	@Resource
	private ApplicationEventPublisher eventPublisher;

	private ConcurrentHashMap<String, VotePreMsg> blockConcurrentHashMap = new ConcurrentHashMap<>();

	@Override
	protected void push(VoteMsg voteMsg) {
		//该队列里的是votePreMsg
		VotePreMsg votePreMsg = (VotePreMsg) voteMsg;
		String hash = votePreMsg.getHash();
		//避免收到重复消息
		if (blockConcurrentHashMap.get(hash) != null) {
			return;
		}
		//但凡是能进到该push方法的，都是通过基本校验的，但在并发情况下可能会相同number的block都进到投票队列中
		//需要对新进来的Vote信息的number进行校验，如果在比prepare阶段靠后的阶段中，已经出现了认证OK的同number的vote，则拒绝进入该队列
		if (prepareMsgQueue.otherConfirm(hash, voteMsg.getNumber())) {
			log.info("拒绝进入Prepare阶段，hash为:{}", hash);
			return;
		}
		// 检测脚本是否正常
		try {
			sqliteManager.tryExecute(votePreMsg.getBlock());
		} catch (Exception e) {
			if (!"00001".equals(e.getMessage())) {
				// 执行异常
				return;
			} else {
				log.info("指令预校验执行成功！");
			}
		}

		//存入Pre集合中
		log.info("blockConcurrentHashMap.put(hash: {}, votePreMsg: {})", hash, votePreMsg);
		blockConcurrentHashMap.put(hash, votePreMsg);

		//加入Prepare行列，推送给所有人
		VoteMsg prepareMsg = new VoteMsg();
		BeanUtil.copyProperties(voteMsg, prepareMsg);
		prepareMsg.setVoteType(VoteType.PREPARE);
		prepareMsg.setAppId(AppId.value);
		log.info("加入Prepare行列，推送给所有人, prepareMsg: {}", prepareMsg);
		eventPublisher.publishEvent(new MsgPrepareEvent(prepareMsg));
	}

	/**
	 * 根据hash，得到内存中的Block信息
	 *
	 * @param hash hash
	 * @return Block
	 */
	public Block findByHash(String hash) {
		VotePreMsg votePreMsg = blockConcurrentHashMap.get(hash);
		if (votePreMsg != null) {
			return votePreMsg.getBlock();
		}
		return null;
	}

	/**
	 * 新区块生成后，clear掉map中number比区块小的所有数据
	 */
	@Order(3)
	@EventListener(AddBlockEvent.class)
	public void blockGenerated(AddBlockEvent addBlockEvent) {
		Block block = (Block) addBlockEvent.getSource();
		int number = block.getBlockHeader().getNumber();
		TimerManager.schedule(() -> {
			for (String key : blockConcurrentHashMap.keySet()) {
				if (blockConcurrentHashMap.get(key).getNumber() <= number) {
					blockConcurrentHashMap.remove(key);
				}
			}
			return null;
		}, 2000);
	}
}
