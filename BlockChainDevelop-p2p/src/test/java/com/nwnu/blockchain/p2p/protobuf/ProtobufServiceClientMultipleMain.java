package com.nwnu.blockchain.p2p.protobuf;

import com.alipay.remoting.NamedThreadFactory;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ProtobufServiceClientMultipleMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 4:06 PM
 * @since 1.0.0
 */
@Slf4j
public class ProtobufServiceClientMultipleMain {
	public static void main(String[] args) {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("test-client");

		ConsumerConfig<ProtoService> consumerConfig = new ConsumerConfig<ProtoService>()
				.setInterfaceId(ProtoService.class.getName())
				.setProtocol("bolt")
				.setApplication(applicationConfig)
				.setDirectUrl("bolt://127.0.0.1:12200")
				.setSerialization("protobuf")
				.setConnectTimeout(1000);

		final ProtoService helloService = consumerConfig.refer();

		log.warn("start at pid {}", RpcRuntimeContext.PID);

		final int threads = 50;

		final AtomicLong cnt = new AtomicLong(0);
		final ThreadPoolExecutor service1 = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(), new NamedThreadFactory("client-"));
		for (int i = 0; i < threads; i++) {
			service1.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							EchoRequest request = EchoRequest.newBuilder().setGroup(Group.A).setName("xxx")
									.build();
							EchoResponse response = helloService.echoObj(request);
							cnt.incrementAndGet();
						} catch (Exception e) {
							log.error(e.getMessage());
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

					log.warn("last 1s invoke:{}, queue: {}", tps, service1.getQueue().size());
					last = count;

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Print-tps-Thread");
		thread.start();
	}
}
