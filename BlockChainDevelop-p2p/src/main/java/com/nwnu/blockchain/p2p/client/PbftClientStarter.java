package com.nwnu.blockchain.p2p.client;

import com.nwnu.blockchain.p2p.BlockPacket;
import com.nwnu.blockchain.p2p.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

import javax.annotation.PostConstruct;

/**
 * PbftClientStarter
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 11:22 AM
 * @since 1.0.0
 */
@Component
@Slf4j
//@Lazy
@Service
public class PbftClientStarter {
	// 服务器节点
	private Node serverNode;

	// handler, 包括编码、解码、消息处理
	private ClientAioHandler tioClientHandler;

	// 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	private ClientAioListener aioListener = null;

	// 断链后自动连接的，不想自动连接请设为null
	private ReconnConf reconnConf = new ReconnConf(5000L);

	// 一组连接共用的上下文对象
	private ClientGroupContext clientGroupContext;

	private TioClient tioClient = null;
	private ClientChannelContext clientChannelContext = null;

	/**
	 * 启动程序入口
	 */
	@PostConstruct
	@Order(2)
	public void start() {
		try {
			log.info("好未来客户端即将启动");

			Thread.sleep(10*1000);

			//初始化
			serverNode = new Node(Const.SERVER, Const.PORT);
			tioClientHandler = new PbftClientAioHandler();
			clientGroupContext = new ClientGroupContext(tioClientHandler, aioListener, reconnConf);

			clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
			tioClient = new TioClient(clientGroupContext);
			clientChannelContext = tioClient.connect(serverNode);

			// 连上后，发条消息测试
			sendMessage();

			log.info("好未来客户端启动完毕");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void sendMessage() throws Exception {
		BlockPacket packet = new BlockPacket();
		packet.setBody("tal say hello world to blockchain!".getBytes(BlockPacket.CHARSET));
		Tio.send(clientChannelContext, packet);
	}
}
