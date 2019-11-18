package com.nwnu.blockchain.p2p.socket.client;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.p2p.core.event.ClientRequestEvent;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.Const;
import org.springframework.stereotype.Component;
import org.tio.client.ClientGroupContext;
import org.tio.core.Tio;

import javax.annotation.Resource;

/**
 * PacketSender
 * 发送消息的工具类
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:44 PM
 * @since 1.0.0
 */
@Component
public class PacketSender {
	@Resource
	private ClientGroupContext clientGroupContext;

	public void sendGroup(BlockPacket blockPacket) {
		//对外发出client请求事件
		ApplicationContextProvider.publishEvent(new ClientRequestEvent(blockPacket));
		//发送到一个group
		Tio.sendToGroup(clientGroupContext, Const.GROUP_NAME, blockPacket);
	}
}
