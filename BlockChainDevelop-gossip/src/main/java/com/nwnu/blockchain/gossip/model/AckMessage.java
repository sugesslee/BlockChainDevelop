package com.nwnu.blockchain.gossip.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nwnu.blockchain.gossip.core.CustomDeserializer;
import com.nwnu.blockchain.gossip.core.CustomSerializer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * AckMessage
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:14 AM
 * @since 1.0.0
 */
public class AckMessage implements Serializable {
	private List<GossipDigest> older;

	@JsonSerialize(keyUsing = CustomSerializer.class)
	@JsonDeserialize(keyUsing = CustomDeserializer.class)
	private Map<GossipMember, HeartbeatState> newers;

	public AckMessage() {
	}

	public AckMessage(List<GossipDigest> olders, Map<GossipMember, HeartbeatState> newers) {
		this.older = olders;
		this.newers = newers;
	}

	public List<GossipDigest> getOlder() {
		return older;
	}

	public void setOlder(List<GossipDigest> older) {
		this.older = older;
	}

	public Map<GossipMember, HeartbeatState> getNewers() {
		return newers;
	}

	public void setNewers(Map<GossipMember, HeartbeatState> newers) {
		this.newers = newers;
	}

	@Override
	public String toString() {
		return "AckMessage{" +
				"olders=" + older +
				", newers=" + newers +
				'}';
	}

}
