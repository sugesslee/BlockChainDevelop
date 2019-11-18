package com.nwnu.blockchain.p2p.socket.client;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.p2p.socket.base.AbstractAioHandler;
import com.nwnu.blockchain.p2p.socket.distruptor.base.BaseEvent;
import com.nwnu.blockchain.p2p.socket.distruptor.base.MessageProducer;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

/**
 * BlockClientAioHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:02 AM
 * @since 1.0.0
 */
public class BlockClientAioHandler extends AbstractAioHandler implements ClientAioHandler {
	@Override
	public BlockPacket heartbeatPacket() {
		//心跳包的内容就是隔一段时间向别的节点获取一次下一步区块（带着自己的最新Block获取别人的next Block）
		//return NextBlockPacketBuilder.build();
		return null;
	}

	/**
	 * server端返回的响应会先进到该方法，将消息全丢到Disruptor中
	 */
	@Override
	public void handler(Packet packet, ChannelContext channelContext) {
		BlockPacket blockPacket = (BlockPacket) packet;

		//使用Disruptor来publish消息。所有收到的消息都进入Disruptor，同BlockServerAioHandler
		ApplicationContextProvider.getBean(MessageProducer.class).publish(new BaseEvent(blockPacket, channelContext));
	}
}
