package com.nwnu.blockchain.rpc.http2;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.server.http.Http2ClearTextServer;
import com.nwnu.blockchain.rpc.protobuf.ProtoService;
import com.nwnu.blockchain.rpc.protobuf.ProtoServiceImpl;
import com.nwnu.blockchain.rpc.test.HelloService;
import com.nwnu.blockchain.rpc.test.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Http2ServerMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/06     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/6 11:08 AM
 * @since 1.0.0
 */
@Slf4j
public class Http2ServerMain {
	public static void main(String[] args) {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("test-http2");

		ServerConfig serverConfig = new ServerConfig()
				.setProtocol("h2c")
				.setPort(12300)
				.setDaemon(false);

		ProviderConfig<ProtoService> providerConfig = new ProviderConfig<ProtoService>()
				.setInterfaceId(ProtoService.class.getName())
				.setApplication(applicationConfig)
				.setRef(new ProtoServiceImpl())
				.setServer(serverConfig);

		providerConfig.export();

		ProviderConfig<HelloService> providerConfig1 = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setApplication(applicationConfig)
				.setRef(new HelloServiceImpl())
				.setServer(serverConfig)
				.setRegister(false);

		providerConfig1.export();

		// http://127.0.0.1:12300/com.alipay.sofa.rpc.test.HelloService/sayHello
		log.error("start at pid {}", RpcRuntimeContext.PID);

		final AtomicInteger cnt = ((ProtoServiceImpl) providerConfig.getRef()).getCounter();
		final ThreadPoolExecutor executor = ((Http2ClearTextServer) serverConfig.getServer()).getBizThreadPool();

		Thread thread = new Thread(new Runnable() {
			private long last = 0;

			@Override
			public void run() {
				while (true) {
					long count = cnt.get();
					long tps = count - last;

					log.error("last 1s invoke:{},, queue:{}", tps, executor.getQueue().size());
					last = count;

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						log.error("{}", e.getMessage());
					}
				}
			}
		}, "Print-tps-THREAD");
		thread.start();
	}
}
