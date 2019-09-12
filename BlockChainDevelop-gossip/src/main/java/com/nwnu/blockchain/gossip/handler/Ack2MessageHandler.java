package com.nwnu.blockchain.gossip.handler;

import com.nwnu.blockchain.gossip.core.GossipManager;
import com.nwnu.blockchain.gossip.model.Ack2Message;
import com.nwnu.blockchain.gossip.model.GossipMember;
import com.nwnu.blockchain.gossip.model.HeartbeatState;
import io.vertx.core.json.JsonObject;

import java.util.Map;

/**
 * Ack2MessageHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:09 AM
 * @since 1.0.0
 */
public class Ack2MessageHandler implements MessageHandler {
	@Override
	public void handle(String cluster, String data, String from) {
		JsonObject dj = new JsonObject(data);
		Ack2Message ack2Message = dj.mapTo(Ack2Message.class);

		Map<GossipMember, HeartbeatState> deltaEndpoints = ack2Message.getEndpoints();
		GossipManager.getInstance().apply2LocalState(deltaEndpoints);
	}
}
