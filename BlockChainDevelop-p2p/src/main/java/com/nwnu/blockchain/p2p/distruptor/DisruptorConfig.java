package com.nwnu.blockchain.p2p.distruptor;

import com.alipay.disruptor.BlockingWaitStrategy;
import com.alipay.disruptor.dsl.Disruptor;
import com.alipay.disruptor.dsl.ProducerType;
import com.nwnu.blockchain.p2p.distruptor.base.BaseEvent;
import com.nwnu.blockchain.p2p.distruptor.base.BaseEventFactory;
import com.nwnu.blockchain.p2p.distruptor.base.MessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * DisruptorConfig
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:08 AM
 * @since 1.0.0
 */
@Configuration
public class DisruptorConfig {
	private Disruptor<BaseEvent> disruptor() {
		ThreadFactory producerFactory = Executors.defaultThreadFactory();
		BaseEventFactory eventFactory = new BaseEventFactory();
		int bufferSize = 1024;
		Disruptor<BaseEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, producerFactory,
				ProducerType.SINGLE, new BlockingWaitStrategy());
		//两个消费者，任何消息都会同时被两个消费者消费，消费者会根据type来判断哪个是该自己处理的
		disruptor.handleEventsWith(new DisruptorServerHandler(), new DisruptorClientHandler());

		disruptor.start();

		return disruptor;
	}

	@Bean
	public MessageProducer messageProducer() {
		return new DisruptorProducer(disruptor());
	}
}
