package com.nwnu.blockchain.p2p.socket.pbft.listener;

import com.nwnu.blockchain.p2p.socket.body.VoteBody;
import com.nwnu.blockchain.p2p.socket.client.PacketSender;
import com.nwnu.blockchain.p2p.socket.pbft.event.MsgCommitEvent;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.PacketBuilder;
import com.nwnu.blockchain.p2p.socket.vote.PacketType;
import com.nwnu.blockchain.p2p.socket.vote.VoteMsg;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * CommitEventListener
 * 监听block可以commit消息
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:41 PM
 * @since 1.0.0
 */
@Component
public class CommitEventListener {
	@Resource
	private PacketSender packetSender;

	/**
	 * block已经开始进入commit状态，广播消息
	 *
	 * @param msgCommitEvent
	 *         msgCommitEvent
	 */
	@EventListener
	public void msgIsCommit(MsgCommitEvent msgCommitEvent) {
		VoteMsg voteMsg = (VoteMsg) msgCommitEvent.getSource();

		//群发消息，通知所有节点，我已对该Block Prepare
		BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.PBFT_VOTE).setBody(new
				VoteBody(voteMsg)).build();

		//广播给所有人我已commit
		packetSender.sendGroup(blockPacket);
	}
}
