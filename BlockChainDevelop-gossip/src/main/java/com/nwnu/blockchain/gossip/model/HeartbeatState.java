package com.nwnu.blockchain.gossip.model;

import com.nwnu.blockchain.gossip.core.VersionHelper;

/**
 * HeartbeatState
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:16 AM
 * @since 1.0.0
 */
public class HeartbeatState {
	private long heartbeatTime;
	private long version;

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

	public HeartbeatState() {
		this.heartbeatTime = System.currentTimeMillis();
		this.version = VersionHelper.getInstance().nextVersion();
	}

	public long updateVersion() {
		setHeartbeatTime(System.currentTimeMillis());
		this.version = VersionHelper.getInstance().nextVersion();
		return version;
	}

	@Override
	public String toString() {
		return "HeartbeatState{" +
				"heartbeatTime=" + heartbeatTime +
				", version=" + version +
				'}';
	}
}
