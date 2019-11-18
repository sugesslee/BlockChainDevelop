package com.nwnu.blockchain.p2p.socket.pbft.event;

import com.nwnu.blockchain.p2p.socket.vote.VoteMsg;
import org.springframework.context.ApplicationEvent;

/**
 * MsgCommitEvent
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:40 PM
 * @since 1.0.0
 */
public class MsgCommitEvent extends ApplicationEvent {
	public MsgCommitEvent(VoteMsg source) {
		super(source);
	}
}
