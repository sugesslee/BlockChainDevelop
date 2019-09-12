package com.nwnu.blockchain.gossip.handler;

import com.nwnu.blockchain.gossip.core.GossipManager;
import com.nwnu.blockchain.gossip.model.GossipMember;
import io.vertx.core.json.JsonObject;

/**
 * ShutdownMessageHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:11 AM
 * @since 1.0.0
 */
public class ShutdownMessageHandler implements MessageHandler {
	@Override
	public void handle(String cluster, String data, String from) {
		JsonObject dj = new JsonObject(data);
		GossipMember whoShutdown = dj.mapTo(GossipMember.class);
		if (whoShutdown != null) {
			GossipManager.getInstance().down(whoShutdown);
		}
	}
}
