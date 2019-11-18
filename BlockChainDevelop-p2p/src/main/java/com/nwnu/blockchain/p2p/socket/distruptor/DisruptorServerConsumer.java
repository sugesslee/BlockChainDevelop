package com.nwnu.blockchain.p2p.socket.distruptor;

import com.nwnu.blockchain.p2p.socket.base.AbstractBlockHandler;
import com.nwnu.blockchain.p2p.socket.distruptor.base.BaseEvent;
import com.nwnu.blockchain.p2p.socket.distruptor.base.MessageConsumer;
import com.nwnu.blockchain.p2p.socket.handler.server.*;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.PacketType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * DisruptorServerConsumer
 * 所有client发来的消息都在这里处理
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:10 AM
 * @since 1.0.0
 */
@Component
public class DisruptorServerConsumer implements MessageConsumer {
	private static Map<Byte, AbstractBlockHandler<?>> handlerMap = new HashMap<>();

	static {
		handlerMap.put(PacketType.GENERATE_COMPLETE_REQUEST, new GenerateCompleteRequestHandler());
		handlerMap.put(PacketType.GENERATE_BLOCK_REQUEST, new GenerateBlockRequestHandler());
		handlerMap.put(PacketType.TOTAL_BLOCK_INFO_REQUEST, new TotalBlockInfoRequestHandler());
		handlerMap.put(PacketType.FETCH_BLOCK_INFO_REQUEST, new FetchBlockRequestHandler());
		handlerMap.put(PacketType.HEART_BEAT, new HeartBeatHandler());
		handlerMap.put(PacketType.NEXT_BLOCK_INFO_REQUEST, new NextBlockRequestHandler());
		handlerMap.put(PacketType.PBFT_VOTE, new PbftVoteHandler());
	}

	@Override
	public void receive(BaseEvent baseEvent) throws Exception {
		BlockPacket blockPacket = baseEvent.getBlockPacket();
		Byte type = blockPacket.getType();
		AbstractBlockHandler<?> handler = handlerMap.get(type);
		if (handler == null) {
			return;
		}
		handler.handler(blockPacket, baseEvent.getChannelContext());
	}
}
