package com.nwnu.blockchain.p2p.handler.server;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.core.body.RpcNextBlockBody;
import com.nwnu.blockchain.core.body.RpcSimpleBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.core.packet.PacketBuilder;
import com.nwnu.blockchain.core.packet.PacketType;
import com.nwnu.blockchain.p2p.base.AbstractBlockHandler;
import com.nwnu.blockchain.repository.manager.DbBlockManager;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.json.Json;

/**
 * NextBlockRequestHandler
 * 获取某个区块下一块的请求，发起方带着自己的lastBlock hash，接收方则将自己的区块中，在传来的hash后面的那块返回回去。<p>
 * 如A传来了3，而我本地有5个区块，则返回区块4。
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:33 PM
 * @since 1.0.0
 */
@Slf4j
public class NextBlockRequestHandler extends AbstractBlockHandler<RpcSimpleBlockBody> {
	@Override
	public Class<RpcSimpleBlockBody> bodyClass() {
		return RpcSimpleBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcSimpleBlockBody rpcBlockBody, ChannelContext channelContext) {
		log.info("收到来自于<" + rpcBlockBody.getAppId() + ">的<请求下一Block>消息，请求者的block hash为：" + Json.toJson
				(rpcBlockBody.getHash()));
		//传来的Block，如果为null，说明发起方连一个Block都没有
		String hash = rpcBlockBody.getHash();

		//查询自己的next block hash，返回给对方，让对方搜集2f+1后确定哪个是对的
		Block nextBlock = ApplicationContextProvider.getBean(DbBlockManager.class).getNextBlockByHash(hash);
		String nextHash = null;
		if (nextBlock != null) {
			nextHash = nextBlock.getBlockHash();
		}
		RpcNextBlockBody respBody = new RpcNextBlockBody(nextHash, hash);
		respBody.setResponseMsgId(rpcBlockBody.getMessageId());
		BlockPacket blockPacket = new PacketBuilder<RpcNextBlockBody>().setType(PacketType
				.NEXT_BLOCK_INFO_RESPONSE).setBody(respBody).build();
		Tio.send(channelContext, blockPacket);
		log.info("回复给<" + rpcBlockBody.getAppId() + ">，我的nextBlock是" + respBody.toString());

		return null;
	}
}
