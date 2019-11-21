package com.nwnu.blockchain.p2p.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;

/**
 * ClientContextConfig
 * 配置ClientGroupContext
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:58 PM
 * @since 1.0.0
 */
@Configuration
public class ClientContextConfig {
	/**
	 * 构建客户端连接的context
	 *
	 * @return ClientGroupContext
	 */
	@Bean
	public ClientGroupContext clientGroupContext() {
		//handler, 包括编码、解码、消息处理
		ClientAioHandler clientAioHandler = new BlockClientAioHandler();
		//事件监听器，可以为null，但建议自己实现该接口
		ClientAioListener clientAioListener = new BlockClientAioListener();
		//断链后自动连接的，不想自动连接请设为null
		ReconnConf reconnConf = new ReconnConf(5000L, 20);
		ClientGroupContext clientGroupContext = new ClientGroupContext(clientAioHandler, clientAioListener,
				reconnConf);

		//clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
		return clientGroupContext;
	}
}
