package com.nwnu.blockchain.rpc.zookeeper;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.nwnu.blockchain.rpc.test.EchoService;
import com.nwnu.blockchain.rpc.test.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * ZookeeperBoltClientMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 4:31 PM
 * @since 1.0.0
 */
@Slf4j
public class ZookeeperBoltClientMain {
	public static void main(String[] args) throws InterruptedException {
		RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol(RpcConstants.REGISTRY_PROTOCOL_ZK)
				.setAddress("172.16.85.189:2181");

		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setRegistry(registryConfig)
				.setTimeout(3000);
		HelloService helloService = consumerConfig.refer();

		ConsumerConfig<EchoService> consumerConfig2 = new ConsumerConfig<EchoService>()
				.setInterfaceId(EchoService.class.getName())
				.setRegistry(registryConfig)
				.setTimeout(3000);
		EchoService echoService = consumerConfig2.refer();

		log.warn("started at pid {}", RpcRuntimeContext.PID);

		try {
			while (true) {
				try {
					String s = helloService.sayHello("xxx", 22);
					log.warn("{}", s);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			log.error("", e);
		}

		synchronized (ZookeeperBoltClientMain.class) {
			while (true) {
				ZookeeperBoltClientMain.class.wait();
			}
		}
	}
}
