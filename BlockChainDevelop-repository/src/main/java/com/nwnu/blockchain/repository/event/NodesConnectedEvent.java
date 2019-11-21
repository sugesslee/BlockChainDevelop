package com.nwnu.blockchain.repository.event;

import org.springframework.context.ApplicationEvent;
import org.tio.core.ChannelContext;

/**
 * NodesConnectedEvent
 * 节点连接完成时会触发该Event节点连接完成时会触发该Event
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:01 PM
 * @since 1.0.0
 */
public class NodesConnectedEvent extends ApplicationEvent {
	private static final long serialVersionUID = 526755692642414178L;

	public NodesConnectedEvent(ChannelContext channelContext) {
		super(channelContext);
	}

	public ChannelContext getSource() {
		return (ChannelContext) source;
	}
}
