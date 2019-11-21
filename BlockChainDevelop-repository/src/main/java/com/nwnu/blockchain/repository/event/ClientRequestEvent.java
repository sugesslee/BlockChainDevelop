package com.nwnu.blockchain.repository.event;

import com.nwnu.blockchain.core.packet.BlockPacket;
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
