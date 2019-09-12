package com.nwnu.blockchain.gossip;

import com.nwnu.blockchain.gossip.enums.GossipState;
import com.nwnu.blockchain.gossip.model.GossipMember;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * TestGossipMember
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:32 AM
 * @since 1.0.0
 */
@Slf4j
public class TestGossipMember {
	private GossipMember member = new GossipMember();
	private String j;

	@Before
	public void init() {
		member.setId("id");
		member.setState(GossipState.DOWN);
		member.setCluster("cluster");
		member.setIpAddress("ip");
		member.setPort(111);
	}

	@Test
	public void encode() {
		j = JsonObject.mapFrom(member).encode();
		log.info("j: {}", j);
	}

	@Test
	public void decode() {
		j = JsonObject.mapFrom(member).encode();
		GossipMember member1 = (new JsonObject(j)).mapTo(GossipMember.class);
		log.info("member1.getState: {}", member1.getState());
	}
}
