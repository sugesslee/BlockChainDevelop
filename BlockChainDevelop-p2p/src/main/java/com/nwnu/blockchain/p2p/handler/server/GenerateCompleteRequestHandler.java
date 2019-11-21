package com.nwnu.blockchain.p2p.handler.server;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.core.body.RpcSimpleBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.p2p.base.AbstractBlockHandler;
import com.nwnu.blockchain.packet.NextBlockPacketBuilder;
import com.nwnu.blockchain.packet.PacketSender;
import com.nwnu.blockchain.repository.manager.DbBlockManager;
import com.nwnu.blockchain.timer.TimerManager;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;

/**
 * 已生成了新区块的全网广播
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:32 PM
 * @since 1.0.0
 */

@Slf4j
public class GenerateCompleteRequestHandler extends AbstractBlockHandler<RpcSimpleBlockBody> {
	@Override
	public Class<RpcSimpleBlockBody> bodyClass() {
		return RpcSimpleBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcSimpleBlockBody rpcBlockBody, ChannelContext channelContext) {
		log.info("收到来自于<" + rpcBlockBody.getAppId() + "><生成了新的Block>消息，block hash为[" + rpcBlockBody.getHash() +
				"]");

		//延迟2秒校验一下本地是否有该区块，如果没有，则发请求去获取新Block
		//延迟的目的是可能刚好自己也马上就要生成同样的Block了，就可以省一次请求
		TimerManager.schedule(() -> {
			Block block = ApplicationContextProvider.getBean(DbBlockManager.class).getBlockByHash(rpcBlockBody
					.getHash());
			//本地有了
			if (block == null) {
				log.info("开始去获取别人的新区块");
				//在这里发请求，去获取group别人的新区块
				BlockPacket nextBlockPacket = NextBlockPacketBuilder.build();
				ApplicationContextProvider.getBean(PacketSender.class).sendGroup(nextBlockPacket);
			}
			return null;
		},2000);

		return null;
	}
}
