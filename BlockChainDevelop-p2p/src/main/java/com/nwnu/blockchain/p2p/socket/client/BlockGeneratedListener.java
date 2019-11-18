package com.nwnu.blockchain.p2p.socket.client;

import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.p2p.socket.body.RpcSimpleBlockBody;
import com.nwnu.blockchain.p2p.core.event.AddBlockEvent;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.PacketBuilder;
import com.nwnu.blockchain.p2p.socket.vote.PacketType;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 本地新生成区块后，需要通知所有group内的节点
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
@Component
public class BlockGeneratedListener {
	@Resource
	private PacketSender packetSender;

	@Order(2)
	@EventListener(AddBlockEvent.class)
	public void blockGenerated(AddBlockEvent addBlockEvent) {
		Block block = (Block) addBlockEvent.getSource();
		BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.GENERATE_COMPLETE_REQUEST).setBody(new
                RpcSimpleBlockBody(block.getBlockHash())).build();

		//广播给其他人做验证
		packetSender.sendGroup(blockPacket);
	}
}
