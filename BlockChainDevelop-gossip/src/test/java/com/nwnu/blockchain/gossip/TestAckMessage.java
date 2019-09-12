package com.nwnu.blockchain.gossip;

import com.nwnu.blockchain.gossip.enums.GossipState;
import com.nwnu.blockchain.gossip.model.AckMessage;
import com.nwnu.blockchain.gossip.model.GossipDigest;
import com.nwnu.blockchain.gossip.model.GossipMember;
import com.nwnu.blockchain.gossip.model.HeartbeatState;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TestAckMessage
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:29 AM
 * @since 1.0.0
 */
@Slf4j
public class TestAckMessage {
	AckMessage ackMessage = new AckMessage();


	public void init() {
		List<GossipDigest> olders = new ArrayList<>();
		Map<GossipMember, HeartbeatState> newers = new HashMap<>();

		GossipDigest digest = new GossipDigest();
		digest.setEndpoint(new InetSocketAddress(123));
		digest.setHeartbeatTime(123445);
		digest.setVersion(1);
		olders.add(digest);

		GossipMember member = new GossipMember();
		member.setCluster("cluster");
		member.setPort(33);
		member.setIpAddress("ip");
		member.setId("id");
		member.setState(GossipState.DOWN);

		HeartbeatState state = new HeartbeatState();
		state.setHeartbeatTime(123);
		state.setVersion(1);

		newers.put(member, state);

		ackMessage.setOlder(olders);
		ackMessage.setNewers(newers);
	}

	@Test
	public void encode() {
		init();
		String j = JsonObject.mapFrom(ackMessage).encode();
		log.info("j: {}", j);
	}

	@Test
	public void decode() {
		init();
		String j = JsonObject.mapFrom(ackMessage).encode();
		AckMessage member1 = (new JsonObject(j)).mapTo(AckMessage.class);
		log.info("newer: {}", member1.getNewers());
	}
}
