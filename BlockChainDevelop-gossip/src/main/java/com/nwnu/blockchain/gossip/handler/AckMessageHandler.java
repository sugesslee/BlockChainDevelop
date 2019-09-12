package com.nwnu.blockchain.gossip.handler;

import com.nwnu.blockchain.gossip.core.GossipManager;
import com.nwnu.blockchain.gossip.model.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AckMessageHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:10 AM
 * @since 1.0.0
 */
public class AckMessageHandler implements MessageHandler {
	@Override
	public void handle(String cluster, String data, String from) {
		JsonObject dj = new JsonObject(data);
		AckMessage ackMessage = dj.mapTo(AckMessage.class);

		List<GossipDigest> olders = ackMessage.getOlder();
		Map<GossipMember, HeartbeatState> newers = ackMessage.getNewers();

		//update local state
		if (newers.size() > 0) {
			GossipManager.getInstance().apply2LocalState(newers);
		}

		Map<GossipMember, HeartbeatState> deltaEndpoints = new HashMap<>();
		if (olders != null) {
			for (GossipDigest d : olders) {
				GossipMember member = GossipManager.getInstance().createByDigest(d);
				HeartbeatState hb = GossipManager.getInstance().getEndpointMembers().get(member);
				if (hb != null) {
					deltaEndpoints.put(member, hb);
				}
			}
		}

		if (!deltaEndpoints.isEmpty()) {
			Ack2Message ack2Message = new Ack2Message(deltaEndpoints);
			Buffer ack2Buffer = GossipManager.getInstance().encodeAck2Message(ack2Message);
			if (from != null) {
				String[] host = from.split(":");
				GossipManager.getInstance().getSettings().getMsgService()
						.sendMsg(host[0], Integer.valueOf(host[1]), ack2Buffer);
			}
		}
	}
}
