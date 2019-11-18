package com.nwnu.blockchain.p2p.socket.server;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.p2p.socket.base.AbstractAioHandler;
import com.nwnu.blockchain.p2p.socket.distruptor.base.BaseEvent;
import com.nwnu.blockchain.p2p.socket.distruptor.base.MessageProducer;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

/**
 * server端处理所有client请求的入口
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
public class BlockServerAioHandler extends AbstractAioHandler implements ServerAioHandler {


	/**
	 * 自己是server，此处接收到客户端来的消息。这里是入口
	 */
	@Override
	public void handler(Packet packet, ChannelContext channelContext) {
		BlockPacket blockPacket = (BlockPacket) packet;

		//使用Disruptor来publish消息。所有收到的消息都进入Disruptor，同BlockClientAioHandler
		ApplicationContextProvider.getBean(MessageProducer.class).publish(new BaseEvent(blockPacket, channelContext));
	}
}
