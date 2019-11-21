package com.nwnu.blockchain.p2p.distruptor.base;

import com.nwnu.blockchain.core.packet.BlockPacket;
import org.tio.core.ChannelContext;

import java.io.Serializable;

/**
 * 生产、消费者之间传递消息用的event
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:05 AM
 * @since 1.0.0
 */
public class BaseEvent implements Serializable {
	private BlockPacket blockPacket;
	private ChannelContext channelContext;

	public BaseEvent(BlockPacket blockPacket, ChannelContext channelContext) {
		this.blockPacket = blockPacket;
		this.channelContext = channelContext;
	}

	public BaseEvent(BlockPacket blockPacket) {
		this.blockPacket = blockPacket;
	}

	public BaseEvent() {
	}

	public ChannelContext getChannelContext() {
		return channelContext;
	}

	public void setChannelContext(ChannelContext channelContext) {
		this.channelContext = channelContext;
	}

	public BlockPacket getBlockPacket() {
		return blockPacket;
	}

	public void setBlockPacket(BlockPacket blockPacket) {
		this.blockPacket = blockPacket;
	}
}
