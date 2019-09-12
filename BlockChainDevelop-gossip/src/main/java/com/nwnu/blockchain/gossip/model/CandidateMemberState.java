package com.nwnu.blockchain.gossip.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CandidateMemberState
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:26 AM
 * @since 1.0.0
 */
public class CandidateMemberState implements Serializable {
	private long heartbeatTime;
	private AtomicInteger downingCount;

	public CandidateMemberState(long heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
		this.downingCount = new AtomicInteger(0);
	}

	public void updateCount() {
		this.downingCount.incrementAndGet();
	}

	public long getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(long heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}

	public AtomicInteger getDowningCount() {
		return downingCount;
	}

	public void setDowningCount(AtomicInteger downingCount) {
		this.downingCount = downingCount;
	}

	@Override
	public String toString() {
		return "CandidateMemberState{" +
				"heartbeatTime=" + heartbeatTime +
				", downingCount=" + downingCount.get() +
				'}';
	}
}
