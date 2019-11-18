package com.nwnu.blockchain.p2p.socket.server;

import com.nwnu.blockchain.p2p.socket.vote.Const;
import org.springframework.stereotype.Component;
import org.tio.core.Tio;
import org.tio.server.ServerGroupContext;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * server启动器
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:40 PM
 * @since 1.0.0
 */
@Component
public class BlockServerStarter {

	@PostConstruct
	public void serverStart() throws IOException {
		ServerAioHandler serverAioHandler = new BlockServerAioHandler();
		ServerAioListener serverAioListener = new BlockServerAioListener();
		ServerGroupContext serverGroupContext = new ServerGroupContext(serverAioHandler, serverAioListener);
		TioServer aioServer = new TioServer(serverGroupContext);
		//本机启动服务
		aioServer.start(null, Const.PORT);
	}
}
