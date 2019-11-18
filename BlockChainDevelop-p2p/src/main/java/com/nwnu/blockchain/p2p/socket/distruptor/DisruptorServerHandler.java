package com.nwnu.blockchain.p2p.socket.distruptor;

import com.alipay.disruptor.EventHandler;
import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.p2p.socket.distruptor.base.BaseEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * DisruptorServerHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:09 AM
 * @since 1.0.0
 */
@Slf4j
public class DisruptorServerHandler implements EventHandler<BaseEvent> {
	@Override
	public void onEvent(BaseEvent baseEvent, long sequence, boolean endOfBatch) throws Exception {
		try {
			ApplicationContextProvider.getBean(DisruptorServerConsumer.class).receive(baseEvent);
		} catch (Exception e) {
			log.error("Disruptor事件执行异常",e);
		}
	}
}
