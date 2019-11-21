package com.nwnu.blockchain.p2p.pbft.queue;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.core.vote.VoteMsg;
import com.nwnu.blockchain.core.vote.VoteType;
import org.springframework.stereotype.Component;

/**
 * MsgQueueManager
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:12 PM
 * @since 1.0.0
 */
@Component
public class MsgQueueManager {
	public void pushMsg(VoteMsg voteMsg) {
		BaseMsgQueue baseMsgQueue = null;
		switch (voteMsg.getVoteType()) {
			case VoteType.PRE_PREPARE:
				baseMsgQueue = ApplicationContextProvider.getBean(PreMsgQueue.class);
				break;
			case VoteType.PREPARE:
				baseMsgQueue = ApplicationContextProvider.getBean(PrepareMsgQueue.class);
				break;
			case VoteType.COMMIT:
				baseMsgQueue = ApplicationContextProvider.getBean(CommitMsgQueue.class);
				break;
			default:
				break;
		}
		if (baseMsgQueue != null) {
			baseMsgQueue.push(voteMsg);
		}
	}
}
