package com.nwnu.blockchain.p2p.distruptor.base;

/**
 * MessageProducer
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:07 AM
 * @since 1.0.0
 */
public interface MessageProducer {
	void publish(BaseEvent baseEvent);
}
