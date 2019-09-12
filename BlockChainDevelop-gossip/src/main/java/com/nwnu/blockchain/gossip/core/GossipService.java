package com.nwnu.blockchain.gossip.core;


import com.nwnu.blockchain.gossip.event.GossipListener;
import com.nwnu.blockchain.gossip.model.SeedMember;
import io.netty.util.internal.StringUtil;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * GossipService
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:06 AM
 * @since 1.0.0
 */
@Slf4j
public class GossipService {

	public GossipService(String cluster, String ipAddress, Integer port, String id, List<SeedMember> seedMembers,
						 GossipSettings settings, GossipListener listener) throws Exception {
		checkParams(cluster, ipAddress, port, seedMembers);
		if (StringUtil.isNullOrEmpty(id)) {
			id = ipAddress.concat(":").concat(String.valueOf(port));
		}
		GossipManager.getInstance().init(cluster, ipAddress, port, id, seedMembers, settings, listener);
	}

	public GossipManager getGossipManager() {
		return GossipManager.getInstance();
	}

	public void start() {
		if (getGossipManager().isWorking()) {
			log.info("gossip already working");
			return;
		}
		GossipManager.getInstance().start();
	}

	public void shutdown() {
		if (getGossipManager().isWorking()) {
			GossipManager.getInstance().shutdown();
		}
	}

	private void checkParams(String cluster, String ipAddress, Integer port, List<SeedMember> seedMembers)
			throws Exception {
		String f = "[%s] is required!";
		String who = null;
		if (StringUtil.isNullOrEmpty(cluster)) {
			who = "cluster";
		} else if (StringUtil.isNullOrEmpty(ipAddress)) {
			who = "ip";
		} else if (StringUtil.isNullOrEmpty(String.valueOf(port))) {
			who = "port";
		} else if (seedMembers == null || seedMembers.isEmpty()) {
			who = "seed member";
		}
		if (who != null) {
			throw new IllegalArgumentException(String.format(f, who));
		}
	}
}
