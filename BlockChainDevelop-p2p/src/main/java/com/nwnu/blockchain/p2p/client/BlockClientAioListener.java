package com.nwnu.blockchain.p2p.client;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.repository.event.NodesConnectedEvent;
import lombok.extern.slf4j.Slf4j;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;

/**
 * BlockClientAioListener
 * client端对各个server连接的情况回调。</p>
 * 当某个server的心跳超时（2min）时，Aio会从group里remove掉该连接，需要在重新connect后重新加入group
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:59 PM
 * @since 1.0.0
 */
@Slf4j
public class BlockClientAioListener implements ClientAioListener {
	@Override
	public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
//        if (isConnected) {
//            logger.info("连接成功：server地址为-" + channelContext.getServerNode());
//            Aio.bindGroup(channelContext, Const.GROUP_NAME);
//        } else {
//            logger.info("连接失败：server地址为-" + channelContext.getServerNode());
//        }
		ApplicationContextProvider.publishEvent(new NodesConnectedEvent(channelContext));
	}

	@Override
	public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String s, boolean b) {
		log.info("连接关闭：server地址为-" + channelContext.getServerNode());
		Tio.unbindGroup(channelContext);
	}

	@Override
	public void onAfterDecoded(ChannelContext channelContext, Packet packet, int i) throws Exception {

	}

	@Override
	public void onAfterReceivedBytes(ChannelContext channelContext, int i) throws Exception {

	}

	@Override
	public void onAfterSent(ChannelContext channelContext, Packet packet, boolean b) throws Exception {

	}

	@Override
	public void onAfterHandled(ChannelContext channelContext, Packet packet, long l) throws Exception {

	}
}
