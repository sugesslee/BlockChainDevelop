package com.nwnu.blockchain.p2p.handler.server;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.core.body.RpcBlockBody;
import com.nwnu.blockchain.core.body.RpcSimpleBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.core.packet.PacketBuilder;
import com.nwnu.blockchain.core.packet.PacketType;
import com.nwnu.blockchain.p2p.base.AbstractBlockHandler;
import com.nwnu.blockchain.repository.manager.DbBlockManager;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

/**
 * FetchBlockRequestHandler
 * 请求别人某个区块的信息
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:03 PM
 * @since 1.0.0
 */
@Slf4j
public class FetchBlockRequestHandler extends AbstractBlockHandler<RpcSimpleBlockBody> {
	@Override
	public Class<RpcSimpleBlockBody> bodyClass() {
		return RpcSimpleBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcSimpleBlockBody rpcBlockBody, ChannelContext channelContext) {
		log.info("收到来自于<" + rpcBlockBody.getAppId() + "><请求该Block>消息，block hash为[" + rpcBlockBody.getHash() + "]");
		Block block = ApplicationContextProvider.getBean(DbBlockManager.class).getBlockByHash(rpcBlockBody.getHash());

		BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.FETCH_BLOCK_INFO_RESPONSE).setBody(new
				RpcBlockBody(block)).build();
		Tio.send(channelContext, blockPacket);

		return null;
	}
}
