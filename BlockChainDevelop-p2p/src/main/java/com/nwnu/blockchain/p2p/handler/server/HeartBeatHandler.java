package com.nwnu.blockchain.p2p.handler.server;

import com.nwnu.blockchain.core.body.HeartBeatBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.p2p.base.AbstractBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;

/**
 * 客户端心跳包
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:37 PM
 * @since 1.0.0
 */
@Slf4j
@Deprecated
public class HeartBeatHandler extends AbstractBlockHandler<HeartBeatBody> {
	@Override
	public Class<HeartBeatBody> bodyClass() {
		return HeartBeatBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, HeartBeatBody heartBeatBody, ChannelContext channelContext)
			throws Exception {
		log.info("收到<心跳包>消息", heartBeatBody.getText());

		return null;
	}
}
