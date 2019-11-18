package com.nwnu.blockchain.p2p.socket.vote;

import lombok.Data;
import lombok.ToString;

/**
 * pbft算法传输prepare和commit消息的载体
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:53 AM
 * @since 1.0.0
 */
@Data
@ToString
public class VoteMsg {
	/**
	 * 当前投票状态（Prepare，commit）
	 */
	private byte voteType;
	/**
	 * 区块的hash
	 */
	private String hash;
	/**
	 * 区块的number
	 */
	private int number;
	/**
	 * 是哪个节点传来的
	 */
	private String appId;
	/**
	 * 是否同意
	 */
	private boolean agree;
}
