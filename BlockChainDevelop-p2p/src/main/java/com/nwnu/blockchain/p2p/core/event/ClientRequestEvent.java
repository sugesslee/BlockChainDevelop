package com.nwnu.blockchain.p2p.core.event;

import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import org.springframework.context.ApplicationEvent;

/**
 * ClientRequestEvent
 * 客户端对外发请求时会触发该Event
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:56 PM
 * @since 1.0.0
 */
public class ClientRequestEvent extends ApplicationEvent {
	public ClientRequestEvent(BlockPacket blockPacket) {
		super(blockPacket);
	}
}
