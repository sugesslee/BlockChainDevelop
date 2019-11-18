package com.nwnu.blockchain.p2p.socket.handler.client;

import com.nwnu.blockchain.p2p.socket.base.AbstractBlockHandler;
import com.nwnu.blockchain.p2p.socket.body.RpcBlockBody;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * TotalBlockInfoResponseHandler
 * 对获取所有区块信息请求的回复
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:38 PM
 * @since 1.0.0
 */
@Slf4j
public class TotalBlockInfoResponseHandler extends AbstractBlockHandler<RpcBlockBody> {
	@Override
	public Class<RpcBlockBody> bodyClass() {
		return RpcBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcBlockBody rpcBlockBody, ChannelContext channelContext)
			throws Exception {
		log.info("收到<请求生成Block的回应>消息: {}", Json.toJson(rpcBlockBody));

		//TODO check合法性
		//TODO response

		return null;
	}
}
