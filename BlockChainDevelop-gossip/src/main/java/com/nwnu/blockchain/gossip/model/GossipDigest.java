package com.nwnu.blockchain.gossip.model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:15 AM
 * @since 1.0.0
 */
public class GossipDigest implements Serializable, Comparable<GossipDigest> {
	private InetSocketAddress endpoint;
	private long heartbeatTime;
	private long version;
	private String id;

	@Override
	public int compareTo(GossipDigest o) {
		if (heartbeatTime != o.heartbeatTime) {
			return (int) (heartbeatTime - o.heartbeatTime);
		}
		return (int) (version - o.version);
	}

	public GossipDigest() {
	}

	public GossipDigest(GossipMember endpoint, long heartbeatTime, long version) throws UnknownHostException {
		this.endpoint = new InetSocketAddress(InetAddress.getByName(endpoint.getIpAddress()), endpoint.getPort());
		this.heartbeatTime = heartbeatTime;
		this.version = version;
		this.id = endpoint.getId();
	}

	public InetSocketAddress getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(InetSocketAddress endpoint) {
		this.endpoint = endpoint;
	}

	public long getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(long heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GossipDigest{" +
				"endpoint=" + endpoint.toString() +
				", heartbeatTime=" + heartbeatTime +
				", version=" + version +
				'}';
	}
}
