package com.nwnu.blockchain.p2p.handler.base;

import com.nwnu.blockchain.core.packet.BlockPacket;
import org.tio.core.ChannelContext;

/**
 * 业务处理器接口
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:45 AM
 * @since 1.0.0
 */
public interface HandlerInterface {
	/**
	 * handler方法在此封装转换
	 *
	 * @param packet         packet
	 * @param channelContext channelContext
	 * @return Object对象
	 * @throws Exception Exception
	 */
	Object handler(BlockPacket packet, ChannelContext channelContext) throws Exception;
}
