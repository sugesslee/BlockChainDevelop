package com.nwnu.blockchain.gossip.net;

import io.vertx.core.buffer.Buffer;

/**
 * MsgService
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:20 AM
 * @since 1.0.0
 */
public interface MsgService {
	void listen(String ipAddress, int port);

	void handleMsg(Buffer data);

	void sendMsg(String targetIp, Integer targetPort, Buffer data);

	void unListen();
}
