package com.nwnu.blockchain.p2p.socket.pbft.listener;

import com.nwnu.blockchain.p2p.socket.body.VoteBody;
import com.nwnu.blockchain.p2p.socket.client.PacketSender;
import com.nwnu.blockchain.p2p.socket.pbft.event.MsgPrepareEvent;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.PacketBuilder;
import com.nwnu.blockchain.p2p.socket.vote.PacketType;
import com.nwnu.blockchain.p2p.socket.vote.VoteMsg;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * PrepareEventListener
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:40 PM
 * @since 1.0.0
 */
@Component
public class PrepareEventListener {
	@Resource
	private PacketSender packetSender;

	/**
	 * block已经开始进入Prepare状态
	 *
	 * @param msgPrepareEvent msgIsPrepareEvent
	 */
	@EventListener
	public void msgIsPrepare(MsgPrepareEvent msgPrepareEvent) {
		VoteMsg voteMsg = (VoteMsg) msgPrepareEvent.getSource();

		//群发消息，通知别的节点，我已对该Block Prepare
		BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.PBFT_VOTE).setBody(new
				VoteBody(voteMsg)).build();

		//广播给所有人我已Prepare
		packetSender.sendGroup(blockPacket);
	}
}
