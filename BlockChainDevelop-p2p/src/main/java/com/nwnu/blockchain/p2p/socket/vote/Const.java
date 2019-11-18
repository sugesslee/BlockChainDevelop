package com.nwnu.blockchain.p2p.socket.vote;

/**
 * Const
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 10:47 AM
 * @since 1.0.0
 */
public class Const {
	/**
	 * 服务器地址
	 */
	public static final String SERVER = "127.0.0.1";

	/**
	 * 监听端口
	 */
	public static final int PORT = 6789;
	/**
	 * 服务器分组名
	 */
	public static final String GROUP_NAME = "block_group";
	/**
	 * 心跳超时时间
	 */
	public static final int TIMEOUT = 5000;

	public static final String CHARSET = "utf-8";
}
