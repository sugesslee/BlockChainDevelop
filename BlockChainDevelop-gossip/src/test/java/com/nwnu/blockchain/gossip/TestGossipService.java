package com.nwnu.blockchain.gossip;

import com.nwnu.blockchain.gossip.core.GossipService;
import com.nwnu.blockchain.gossip.core.GossipSettings;
import com.nwnu.blockchain.gossip.model.SeedMember;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * TestGossipService
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
public class TestGossipService {
	@Test
	public void startGossip() throws Exception {
		String cluster = "testCluster";
		String ipAddress = "127.0.0.1";
		int port = 5000;
		List<SeedMember> seedNodes = new ArrayList<>();
		SeedMember seed = new SeedMember();
		seed.setCluster(cluster);
		seed.setIpAddress(ipAddress);
		seed.setPort(port);
		seedNodes.add(seed);

		for (int i = 0; i < 1; i++) {
			GossipService gossipService = null;
			try {
				gossipService = new GossipService(cluster, ipAddress, port + i, null, seedNodes, new GossipSettings(),
						(member, state) -> {
							log.info("member: {}, state: {}", member, state);
						});
			} catch (Exception e) {
				e.printStackTrace();
			}

			assert gossipService != null;
			gossipService.start();
		}
	}
}
