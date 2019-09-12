package com.nwnu.blockchain.gossip.handler;

/**
 * MessageHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:11 AM
 * @since 1.0.0
 */
public interface MessageHandler {
	void handle(String cluster, String data, String from);
}
