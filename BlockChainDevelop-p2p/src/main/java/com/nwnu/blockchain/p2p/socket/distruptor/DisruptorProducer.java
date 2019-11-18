package com.nwnu.blockchain.p2p.socket.distruptor;

import com.alipay.disruptor.RingBuffer;
import com.alipay.disruptor.dsl.Disruptor;
import com.nwnu.blockchain.p2p.socket.distruptor.base.BaseEvent;
import com.nwnu.blockchain.p2p.socket.distruptor.base.MessageProducer;

/**
 * DisruptorProducer
 * 所有客户端、server端发来的消息，都进入这里，然后publish出去，供消费者消费
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:17 AM
 * @since 1.0.0
 */
public class DisruptorProducer implements MessageProducer {
	private Disruptor<BaseEvent> disruptor;

	public DisruptorProducer(Disruptor<BaseEvent> disruptor) {
		this.disruptor = disruptor;
	}

	@Override
	public void publish(BaseEvent baseEvent) {
		RingBuffer<BaseEvent> ringBuffer = disruptor.getRingBuffer();
		long sequence = ringBuffer.next();
		try {
			// Get the entry in the Disruptor
			BaseEvent event = ringBuffer.get(sequence);
			// for the sequence   // Fill with data
			event.setBlockPacket(baseEvent.getBlockPacket());
			event.setChannelContext(baseEvent.getChannelContext());
		} finally {
			ringBuffer.publish(sequence);
		}
	}
}
