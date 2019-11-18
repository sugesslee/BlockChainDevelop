package com.nwnu.blockchain.p2p.socket.distruptor;

import cn.hutool.core.util.StrUtil;
import com.nwnu.blockchain.common.AppId;
import com.nwnu.blockchain.p2p.socket.body.BaseBody;
import com.nwnu.blockchain.p2p.socket.base.AbstractBlockHandler;
import com.nwnu.blockchain.p2p.socket.distruptor.base.BaseEvent;
import com.nwnu.blockchain.p2p.socket.distruptor.base.MessageConsumer;
import com.nwnu.blockchain.p2p.socket.handler.client.FetchBlockResponseHandler;
import com.nwnu.blockchain.p2p.socket.handler.client.NextBlockResponseHandler;
import com.nwnu.blockchain.p2p.socket.handler.client.TotalBlockInfoResponseHandler;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tio.utils.json.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * DisruptorClientConsumer
 * 所有server发来的消息都在这里处理
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:19 AM
 * @since 1.0.0
 */
@Component
public class DisruptorClientConsumer implements MessageConsumer {
	private static Map<Byte, AbstractBlockHandler<?>> handlerMap = new HashMap<>();
	private Logger logger = LoggerFactory.getLogger(getClass());

	static {
		handlerMap.put(PacketType.TOTAL_BLOCK_INFO_RESPONSE, new TotalBlockInfoResponseHandler());
		handlerMap.put(PacketType.NEXT_BLOCK_INFO_RESPONSE, new NextBlockResponseHandler());
		handlerMap.put(PacketType.FETCH_BLOCK_INFO_RESPONSE, new FetchBlockResponseHandler());
	}

	@Override
	public void receive(BaseEvent baseEvent) throws Exception {
		BlockPacket blockPacket = baseEvent.getBlockPacket();
		Byte type = blockPacket.getType();
		AbstractBlockHandler<?> blockHandler = handlerMap.get(type);
		if (blockHandler == null) {
			return;
		}

		//消费消息
		BaseBody baseBody = Json.toBean(new String(blockPacket.getBody()), BaseBody.class);
		//logger.info("收到来自于<" + baseBody.getAppId() + ">针对msg<" + baseBody.getResponseMsgId() + ">的回应");

		String appId = baseBody.getAppId();
		if (StrUtil.equals(AppId.value, appId)) {
			//是本机
			//return;
		}

		blockHandler.handler(blockPacket, baseEvent.getChannelContext());
	}
}
