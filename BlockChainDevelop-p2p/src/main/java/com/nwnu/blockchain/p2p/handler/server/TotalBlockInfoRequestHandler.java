package com.nwnu.blockchain.p2p.handler.server;

import com.nwnu.blockchain.core.body.RpcBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.p2p.base.AbstractBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * 获取全部区块信息的请求，全网广播
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:36 PM
 * @since 1.0.0
 */
@Slf4j
public class TotalBlockInfoRequestHandler extends AbstractBlockHandler<RpcBlockBody> {
	@Override
	public Class<RpcBlockBody> bodyClass() {
		return RpcBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcBlockBody rpcBlockBody, ChannelContext channelContext)
			throws Exception {
		log.info("收到<请求生成Block的回应>消息", Json.toJson(rpcBlockBody));

		//TODO check合法性
		//TODO response

		return null;
	}
}
