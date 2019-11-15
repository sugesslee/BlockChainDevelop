package com.nwnu.blockchain.p2p.protobuf;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.server.bolt.BoltServer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProtobufServiceServerMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 3:30 PM
 * @since 1.0.0
 */
@Slf4j
public class ProtobufServiceServerMain {
	public static void main(String[] args) {
		ServerConfig serverConfig = new ServerConfig()
				.setProtocol("bolt")
				.setPort(12200)
				.setDaemon(false);

		ProviderConfig<ProtoService> protoServiceProviderConfig = new ProviderConfig<ProtoService>()
				.setInterfaceId(ProtoService.class.getName())
				.setRef(new ProtoServiceImpl())
				.setServer(serverConfig);

		protoServiceProviderConfig.export();

		log.warn("started at pid {}", RpcRuntimeContext.PID);

		final AtomicInteger cnt = ((ProtoServiceImpl) protoServiceProviderConfig.getRef()).getCounter();
		final ThreadPoolExecutor executor = ((BoltServer) serverConfig.getServer()).getBizThreadPool();

		Thread thread = new Thread(new Runnable() {
			private long last = 0;

			@Override
			public void run() {
				while (true) {
					long count = cnt.get();
					long tps = count - last;
					log.warn("last 1s invoke: {}, queue: {}", tps, executor.getQueue().size());
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
