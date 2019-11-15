package com.nwnu.blockchain.p2p.sofaregistry;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.nwnu.blockchain.p2p.quickstart.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 9:43 AM
 * @since 1.0.0
 */
@Slf4j
public class SofaRegistryClient {
	public static void main(String[] args) {
		RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol(RpcConstants.REGISTRY_PROTOCOL_SOFA)
				.setAddress("127.0.0.1:9603");

		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setRegistry(registryConfig)
				.setProtocol("bolt")
				.setConnectTimeout(10 * 1000);

		HelloService helloService = consumerConfig.refer();

		log.warn("start at pid {}", RpcRuntimeContext.PID);

		try {
			while (true) {
				try {
					System.out.println(helloService.sayHello("world"));
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
	}
}

