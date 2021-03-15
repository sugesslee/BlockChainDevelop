package com.nwnu.blockchain.p2p.handler.client;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.core.body.BlockHash;
import com.nwnu.blockchain.core.body.RpcNextBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.p2p.handler.base.AbstractBlockHandler;
import com.nwnu.blockchain.p2p.pbft.queue.NextBlockQueue;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;

/**
 * NextBlockResponseHandler
 * 对方根据我们传的hash，给我们返回的next block
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:37 PM
 * @since 1.0.0
 */
@Slf4j
public class NextBlockResponseHandler extends AbstractBlockHandler<RpcNextBlockBody> {
	@Override
	public Class<RpcNextBlockBody> bodyClass() {
		return RpcNextBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcNextBlockBody rpcBlockBody, ChannelContext channelContext) {
//		log.info("收到来自于<{}>的回复，下一个Block hash为：{}", rpcBlockBody.getAppId(), rpcBlockBody.getHash());

		String hash = rpcBlockBody.getHash();
		//如果为null，说明对方根据我们传过去的hash，找不到next block。说明要么已经是最新了，要么对方的block比自己的少
		if (hash == null) {
//			log.info("和<{}>相比，本地已是最新块了", rpcBlockBody.getAppId());
		} else {
			BlockHash blockHash = new BlockHash(hash, rpcBlockBody.getPrevHash(), rpcBlockBody.getAppId());
			//此处进行搜集next block的hash，相同的hash过2f+1时可以确认
			ApplicationContextProvider.getBean(NextBlockQueue.class).push(blockHash);
		}
		return null;
	}
}
