package com.nwnu.blockchain.gossip.enums;

/**
 * MessageType
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:17 AM
 * @since 1.0.0
 */
public enum MessageType {
	SYNC_MESSAGE("sync_message"), ACK_MESSAGE("ack_message"), ACK2_MESSAGE("ack2_message"), SHUTDOWN("shutdown");

	private final String type;

	MessageType(String type) {
		this.type = type;
	}
}
