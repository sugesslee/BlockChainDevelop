package com.nwnu.blockchain.packet;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.common.constant.ServerConst;
import com.nwnu.blockchain.core.packet.BlockPacket;
import com.nwnu.blockchain.repository.event.ClientRequestEvent;
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
		Tio.sendToGroup(clientGroupContext, ServerConst.GROUP_NAME, blockPacket);
	}
}
