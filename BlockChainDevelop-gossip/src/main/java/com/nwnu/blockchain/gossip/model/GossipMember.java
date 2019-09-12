package com.nwnu.blockchain.gossip.model;

import com.nwnu.blockchain.gossip.enums.GossipState;

import java.io.Serializable;

/**
 * GossipMember
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
public class GossipMember implements Serializable {
	private String cluster;
	private String ipAddress;
	private Integer port;
	private String id;
	private GossipState state;

	public GossipMember() {
	}

	public GossipMember(String cluster, String ipAddress, Integer port, String id, GossipState state) {
		this.cluster = cluster;
		this.ipAddress = ipAddress;
		this.port = port;
		this.id = id;
		this.state = state;
	}

	public GossipState getState() {
		return state;
	}

	public void setState(GossipState state) {
		this.state = state;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getId() {
		if (id == null) {
			setId(ipAndPort());
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GossipMember{" +
				"cluster='" + cluster + '\'' +
				", ipAddress='" + ipAddress + '\'' +
				", port=" + port +
				", id='" + id + '\'' +
				", state=" + state +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GossipMember member = (GossipMember) o;

		if (!cluster.equals(member.cluster)) return false;
		if (!ipAddress.equals(member.ipAddress)) return false;
		return port.equals(member.port);
	}

	@Override
	public int hashCode() {
		int result = cluster.hashCode();
		result = 31 * result + ipAddress.hashCode();
		result = 31 * result + port.hashCode();
		return result;
	}

	public String ipAndPort() {
		return ipAddress.concat(":").concat(String.valueOf(port));
	}

	public String eigenvalue() {
		return getCluster().concat(":").concat(getIpAddress()).concat(":").concat(getPort().toString());
	}
}
