package com.nwnu.blockchain.gossip.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nwnu.blockchain.gossip.core.CustomDeserializer;
import com.nwnu.blockchain.gossip.core.CustomSerializer;

import java.io.Serializable;
import java.util.Map;

/**
 * Ack2Message
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:13 AM
 * @since 1.0.0
 */
public class Ack2Message implements Serializable {
	@JsonSerialize(keyUsing = CustomSerializer.class)
	@JsonDeserialize(keyUsing = CustomDeserializer.class)
	private Map<GossipMember, HeartbeatState> endpoints;

	public Ack2Message() {
	}

	public Ack2Message(Map<GossipMember, HeartbeatState> endpoints) {

		this.endpoints = endpoints;
	}

	@Override
	public String toString() {
		return "GossipDigestAck2Message{" +
				"endpoints=" + endpoints +
				'}';
	}

	public Map<GossipMember, HeartbeatState> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(Map<GossipMember, HeartbeatState> endpoints) {
		this.endpoints = endpoints;
	}
}
