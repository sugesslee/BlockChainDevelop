package com.nwnu.blockchain.p2p.pbft.queue;

import com.nwnu.blockchain.core.vote.VoteMsg;
import com.nwnu.blockchain.p2p.client.ClientStarter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * BaseMsgQueue
 * 各节点互传的投票消息存储队列基类
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:14 PM
 * @since 1.0.0
 */
@Component
public abstract class BaseMsgQueue {
	@Resource
	private ClientStarter clientStarter;

	public int pbftSize() {
		return clientStarter.pbftSize();
	}

	public int pbftAgreeSize() {
		return clientStarter.pbftAgreeCount();
	}

	/**
	 * 来了新消息
	 *
	 * @param voteMsg voteMsg
	 */
	protected abstract void push(VoteMsg voteMsg);
}
