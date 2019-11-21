package com.nwnu.blockchain.packet;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.core.body.RpcSimpleBlockBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.core.packet.PacketBuilder;
import com.nwnu.blockchain.core.packet.PacketType;
import com.nwnu.blockchain.repository.event.ClientRequestEvent;
import com.nwnu.blockchain.repository.manager.DbBlockManager;

/**
 *  构建向别的节点请求next block的builder.带着自己最后一个block的hash
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:27 PM
 * @since 1.0.0
 */
public class NextBlockPacketBuilder {
	public static BlockPacket build() {
		return build(null);
	}

	public static BlockPacket build(String responseId) {
		String hash = ApplicationContextProvider.getBean(DbBlockManager.class).getLastBlockHash();

		RpcSimpleBlockBody rpcBlockBody = new RpcSimpleBlockBody(hash);
		rpcBlockBody.setResponseMsgId(responseId);
		BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.NEXT_BLOCK_INFO_REQUEST).setBody
				(rpcBlockBody).build();
		//发布client请求事件
		ApplicationContextProvider.publishEvent(new ClientRequestEvent(blockPacket));
		return blockPacket;
	}
}
