package com.nwnu.blockchain.gossip.enums;

/**
 * GossipState
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
public enum GossipState {
	UP("up"), DOWN("down"), JOIN("join");

	private final String state;

	GossipState(String state) {
		this.state = state;
	}
}
