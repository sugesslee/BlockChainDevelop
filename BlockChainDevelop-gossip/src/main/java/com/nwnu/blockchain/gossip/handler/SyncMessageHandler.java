package com.nwnu.blockchain.gossip.handler;

import com.nwnu.blockchain.gossip.core.GossipManager;
import com.nwnu.blockchain.gossip.core.Serializer;
import com.nwnu.blockchain.gossip.model.AckMessage;
import com.nwnu.blockchain.gossip.model.GossipDigest;
import com.nwnu.blockchain.gossip.model.GossipMember;
import com.nwnu.blockchain.gossip.model.HeartbeatState;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SyncMessageHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:12 AM
 * @since 1.0.0
 */
@Slf4j
public class SyncMessageHandler implements MessageHandler {
	@Override
	public void handle(String cluster, String data, String from) {
		if (data != null) {
			try {
				JsonArray array = new JsonArray(data);
				List<GossipDigest> olders = new ArrayList<>();
				Map<GossipMember, HeartbeatState> newers = new HashMap<>();
				List<GossipMember> gMemberList = new ArrayList<>();
				for (Object e : array) {
					GossipDigest g = Serializer.getInstance()
							.decode(Buffer.buffer().appendString(e.toString()), GossipDigest.class);
					GossipMember member = new GossipMember();
					member.setCluster(cluster);
					member.setIpAddress(g.getEndpoint().getAddress().getHostAddress());
					member.setPort(g.getEndpoint().getPort());
					member.setId(g.getId());
					gMemberList.add(member);

					compareDigest(g, member, cluster, olders, newers);
				}
				// I have, you don't have
				Map<GossipMember, HeartbeatState> endpoints = GossipManager.getInstance().getEndpointMembers();
				Set<GossipMember> epKeys = endpoints.keySet();
				for (GossipMember m : epKeys) {
					if (!gMemberList.contains(m)) {
						newers.put(m, endpoints.get(m));
					}
					if (m.equals(GossipManager.getInstance().getSelf())) {
						newers.put(m, endpoints.get(m));
					}
				}
				AckMessage ackMessage = new AckMessage(olders, newers);
				Buffer ackBuffer = GossipManager.getInstance().encodeAckMessage(ackMessage);
				if (from != null) {
					String[] host = from.split(":");
					GossipManager.getInstance().getSettings().getMsgService()
							.sendMsg(host[0], Integer.valueOf(host[1]), ackBuffer);
				}
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			}
		}
	}

	private void compareDigest(GossipDigest g, GossipMember member, String cluster, List<GossipDigest> olders,
							   Map<GossipMember, HeartbeatState> newers) {

		try {
			HeartbeatState hb = GossipManager.getInstance().getEndpointMembers().get(member);
			long remoteHeartbeatTime = g.getHeartbeatTime();
			long remoteVersion = g.getVersion();
			if (hb != null) {
				long localHeartbeatTime = hb.getHeartbeatTime();
				long localVersion = hb.getVersion();

				if (remoteHeartbeatTime > localHeartbeatTime) {
					olders.add(g);
				} else if (remoteHeartbeatTime < localHeartbeatTime) {
					newers.put(member, hb);
				} else if (remoteHeartbeatTime == localHeartbeatTime) {
					if (remoteVersion > localVersion) {
						olders.add(g);
					} else if (remoteVersion < localVersion) {
						newers.put(member, hb);
					}
				}
			} else {
				olders.add(g);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
