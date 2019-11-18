package com.nwnu.blockchain.p2p.socket.pbft.event;

import com.nwnu.blockchain.p2p.socket.vote.VoteMsg;
import org.springframework.context.ApplicationEvent;

/**
 * MsgPrepareEvent
 * 消息已被验证，进入到Prepare集合中
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
public class MsgPrepareEvent extends ApplicationEvent {
	public MsgPrepareEvent(VoteMsg source) {
		super(source);
	}
}
