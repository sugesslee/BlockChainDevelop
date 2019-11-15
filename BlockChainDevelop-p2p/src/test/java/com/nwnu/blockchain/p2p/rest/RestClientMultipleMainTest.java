package com.nwnu.blockchain.p2p.rest;

import com.alipay.sofa.rpc.common.struct.NamedThreadFactory;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * RestClientMultipleMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 10:59 AM
 * @since 1.0.0
 */
@Slf4j
public class RestClientMultipleMainTest {
	public static void main(String[] args) {

		ApplicationConfig application = new ApplicationConfig().setAppName("test-client");

		ConsumerConfig<RestService> consumerConfig = new ConsumerConfig<RestService>()
				.setApplication(application)
				.setInterfaceId(RestService.class.getName())
				.setProtocol("rest")
				.setBootstrap("rest")
				.setDirectUrl("rest://127.0.0.1:8888")
				.setTimeout(3000);
		final RestService helloService = consumerConfig.refer();

		log.warn("started at pid {}", RpcRuntimeContext.PID);

		final int threads = 50;
		final AtomicLong cnt = new AtomicLong(0);
		final ThreadPoolExecutor service1 = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(), new NamedThreadFactory("client-"));// 无队列
		for (int i = 0; i < threads; i++) {
			service1.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							String s = helloService.get("1234567890");
							cnt.incrementAndGet();
						} catch (Exception e) {
							log.error("", e);
						}
					}
				}
			});
		}

		Thread thread = new Thread(new Runnable() {
			private long last = 0;

			@Override
			public void run() {
				while (true) {
					long count = cnt.get();
					long tps = count - last;
					log.error("last 1s invoke: {}, queue: {}", tps, service1.getQueue().size());
					last = count;

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
		}, "Print-tps-THREAD");
		thread.start();
	}
}
