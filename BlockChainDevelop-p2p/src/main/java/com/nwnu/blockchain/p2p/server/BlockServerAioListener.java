package com.nwnu.blockchain.p2p.server;

import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;
import org.tio.utils.json.Json;

/**
 * 连接状态的监听器
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
@Slf4j
public class BlockServerAioListener implements ServerAioListener {

	@Override
	public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) {
		log.info("onAfterConnected channelContext:{}, isConnected:{}, isReconnect:{}", channelContext, isConnected,
				isReconnect);

		//连接后，需要把连接会话对象设置给channelContext
		//channelContext.setAttribute(new ShowcaseSessionContext());
	}

	@Override
	public void onAfterDecoded(ChannelContext channelContext, Packet packet, int i) throws Exception {

	}

	@Override
	public void onAfterReceivedBytes(ChannelContext channelContext, int i) throws Exception {
		log.info("onAfterReceived channelContext:{}, packet:{}, packetSize:{}", channelContext, i, i);
	}


	@Override
	public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) {
		log.info("onAfterSent channelContext:{}, packet:{}, isSentSuccess:{}", channelContext, Json.toJson(packet),
				isSentSuccess);
	}

	@Override
	public void onAfterHandled(ChannelContext channelContext, Packet packet, long l) throws Exception {

	}

	@Override
	public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) {
	}
}
