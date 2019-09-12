package com.nwnu.blockchain.gossip.model;

import java.io.Serializable;

/**
 * SeedMember
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:18 AM
 * @since 1.0.0
 */
public class SeedMember implements Serializable {
	private String cluster;
	private String ipAddress;
	private Integer port;
	private String id;

	public SeedMember(String cluster, String ipAddress, Integer port, String id) {
		this.cluster = cluster;
		this.ipAddress = ipAddress;
		this.port = port;
		this.id = id;
	}

	public SeedMember() {

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
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String eigenvalue() {
		return getCluster().concat(":").concat(getIpAddress()).concat(":").concat(getPort().toString());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SeedMember that = (SeedMember) o;

		if (!cluster.equals(that.cluster)) return false;
		if (!ipAddress.equals(that.ipAddress)) return false;
		return port.equals(that.port);
	}

	@Override
	public int hashCode() {
		int result = cluster.hashCode();
		result = 31 * result + ipAddress.hashCode();
		result = 31 * result + port.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "SeedMember{" +
				"cluster='" + cluster + '\'' +
				", ipAddress='" + ipAddress + '\'' +
				", port=" + port +
				", id='" + id + '\'' +
				'}';
	}
}
