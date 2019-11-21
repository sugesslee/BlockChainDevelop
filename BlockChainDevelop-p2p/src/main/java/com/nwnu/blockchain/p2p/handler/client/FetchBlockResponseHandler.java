package com.nwnu.blockchain.p2p.handler.client;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.check.CheckerManager;
import com.nwnu.blockchain.core.body.RpcBlockBody;
import com.nwnu.blockchain.core.body.RpcCheckBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.p2p.base.AbstractBlockHandler;
import com.nwnu.blockchain.p2p.pbft.queue.NextBlockQueue;
import com.nwnu.blockchain.packet.NextBlockPacketBuilder;
import com.nwnu.blockchain.packet.PacketSender;
import com.nwnu.blockchain.repository.event.AddBlockEvent;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * FetchBlockResponseHandler
 * 对方根据我们传的hash，给我们返回的block
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:35 PM
 * @since 1.0.0
 */
@Slf4j
public class FetchBlockResponseHandler extends AbstractBlockHandler<RpcBlockBody> {
	@Override
	public Class<RpcBlockBody> bodyClass() {
		return RpcBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcBlockBody rpcBlockBody, ChannelContext channelContext) {
		log.info("收到来自于<" + rpcBlockBody.getAppId() + ">的回复，Block为：" + Json.toJson(rpcBlockBody));

		Block block = rpcBlockBody.getBlock();
		//如果为null，说明对方也没有该Block
		if (block == null) {
			log.info("对方也没有该Block");
		} else {
			//此处校验传过来的block的合法性，如果合法，则更新到本地，作为next区块
			if (ApplicationContextProvider.getBean(NextBlockQueue.class).pop(block.getBlockHash()) == null) return null;

			CheckerManager checkerManager = ApplicationContextProvider.getBean(CheckerManager.class);
			RpcCheckBlockBody rpcCheckBlockBody = checkerManager.check(block);
			//校验通过，则存入本地DB，保存新区块
			if (rpcCheckBlockBody.getCode() == 0) {
				ApplicationContextProvider.publishEvent(new AddBlockEvent(block));
				//继续请求下一块
				BlockPacket blockPacket = NextBlockPacketBuilder.build();
				ApplicationContextProvider.getBean(PacketSender.class).sendGroup(blockPacket);
			}
		}

		return null;
	}
}
