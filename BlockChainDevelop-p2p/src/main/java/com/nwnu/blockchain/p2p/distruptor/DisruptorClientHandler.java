package com.nwnu.blockchain.p2p.distruptor;

import com.alipay.disruptor.EventHandler;
import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.p2p.distruptor.base.BaseEvent;

/**
 * DisruptorClientHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:18 AM
 * @since 1.0.0
 */
public class DisruptorClientHandler implements EventHandler<BaseEvent> {
	@Override
	public void onEvent(BaseEvent baseEvent, long sequence, boolean endOfBatch) throws Exception {
		ApplicationContextProvider.getBean(DisruptorClientConsumer.class).receive(baseEvent);
	}
}
