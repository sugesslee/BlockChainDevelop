package com.nwnu.blockchain.p2p;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tio.server.ServerGroupContext;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import javax.annotation.PostConstruct;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/14     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/14 4:19 PM
 * @since 1.0.0
 */
@Component
@Slf4j
public class PbftServerStart {
	// handler, 包括编码、解码、消息处理
	public static ServerAioHandler aioHandler = new PbftServerAioHandler();

	// 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	public static ServerAioListener aioListener = null;

	// 一组连接共用的上下文对象
	public static ServerGroupContext serverGroupContext =
			new ServerGroupContext("hello-tio-server", aioHandler, aioListener);

	// tioServer对象
	public static TioServer tioServer = new TioServer(serverGroupContext);

	// 有时候需要绑定ip，不需要则null
	public static String serverIp = Const.SERVER;//Const.SERVER;

	// 监听的端口
	public static int serverPort = Const.PORT;

	@PostConstruct
	@Order(1)
	public void start() {
		try {
			log.info("好未来服务端即将启动");

			serverGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
			tioServer.start(serverIp, serverPort);

			log.info("好未来服务端启动完毕");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
