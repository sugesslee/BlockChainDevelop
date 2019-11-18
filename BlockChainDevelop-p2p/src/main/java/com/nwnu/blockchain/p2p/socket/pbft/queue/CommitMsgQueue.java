package com.nwnu.blockchain.p2p.socket.pbft.queue;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.p2p.core.event.AddBlockEvent;
import com.nwnu.blockchain.p2p.socket.vote.VoteMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * CommitMsgQueue
 * Confirm阶段的消息队列
 * 每个节点收到超过2f+1个不同节点（包括自己）的commit消息后，就认为该区块已经达成一致，进入committed状态，并将其持久化到区块链数据库中
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:46 PM
 * @since 1.0.0
 */
@Component
@Slf4j
public class CommitMsgQueue extends AbstractVoteMsgQueue {
	@Resource
	private PreMsgQueue preMsgQueue;

	@Override
	protected void deal(VoteMsg voteMsg, List<VoteMsg> voteMsgs) {
		String hash = voteMsg.getHash();

		//通过校验agree数量，来决定是否在本地生成Block
		long count = voteMsgs.stream().filter(VoteMsg::isAgree).count();
		log.info("已经commit为true的数量为:{}", count);
		if (count >= pbftAgreeSize()) {
			Block block = preMsgQueue.findByHash(hash);
			if (block == null) {
				return;
			}
			//本地落地
			voteStateConcurrentHashMap.put(hash, true);
			ApplicationContextProvider.publishEvent(new AddBlockEvent(block));
		}
	}

	/**
	 * 新区块生成后，clear掉map中number比区块小的所有数据
	 */
	@Order(3)
	@EventListener(AddBlockEvent.class)
	public void blockGenerated(AddBlockEvent addBlockEvent) {
		Block block = (Block) addBlockEvent.getSource();
		clearOldBlockHash(block.getBlockHeader().getNumber());
	}
}
