package com.nwnu.blockchain.p2p.http2;

import com.alipay.remoting.NamedThreadFactory;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.nwnu.blockchain.p2p.protobuf.EchoRequest;
import com.nwnu.blockchain.p2p.protobuf.EchoResponse;
import com.nwnu.blockchain.p2p.protobuf.Group;
import com.nwnu.blockchain.p2p.protobuf.ProtoService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Http2ClientMultipleMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/08     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/8 8:50 AM
 * @since 1.0.0
 */
@Slf4j
public class Http2ClientMultipleMain {
	public static void main(String[] args) {
		ApplicationConfig application = new ApplicationConfig().setAppName("test-http2");

		ConsumerConfig<ProtoService> consumerConfig = new ConsumerConfig<ProtoService>()
				.setApplication(application)
				.setInterfaceId(ProtoService.class.getName())
				.setProtocol("h2c")
				.setDirectUrl("h2c://127.0.0.1:12300")
				.setSerialization("protobuf")
				.setTimeout(3000);

		final ProtoService protoService = consumerConfig.refer();

		log.warn("started at pid:{}", RpcRuntimeContext.PID);

		final int threads = 50;
		final AtomicLong cnt = new AtomicLong(0);
		final ThreadPoolExecutor service1 = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS,
				new SynchronousQueue<>(), new NamedThreadFactory("client-"));

		for (int i = 0; i < threads; i++) {
			service1.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							EchoRequest request = EchoRequest.newBuilder().setGroup(Group.A).setName("121")
									.build();
							EchoResponse response = protoService.echoObj(request);

							cnt.incrementAndGet();
						} catch (Exception e) {
							log.error("{}", e.getMessage());
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
