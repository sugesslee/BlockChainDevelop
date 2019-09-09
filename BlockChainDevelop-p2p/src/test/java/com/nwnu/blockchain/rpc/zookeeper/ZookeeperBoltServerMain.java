package com.nwnu.blockchain.rpc.zookeeper;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.nwnu.blockchain.rpc.test.EchoService;
import com.nwnu.blockchain.rpc.test.EchoServiceImpl;
import com.nwnu.blockchain.rpc.test.HelloService;
import com.nwnu.blockchain.rpc.test.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * ZookeeperBoltServerMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 5:10 PM
 * @since 1.0.0
 */
@Slf4j
public class ZookeeperBoltServerMain {
	public static void main(String[] args) {
		RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol(RpcConstants.REGISTRY_PROTOCOL_ZK)
				.setAddress("172.16.85.189:2181");

		ServerConfig serverConfig = new ServerConfig()
				.setPort(22101)
				.setDaemon(false);

		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setRef(new HelloServiceImpl("result from 22101"))
				.setServer(serverConfig)
				.setRegistry(registryConfig);

		ProviderConfig<EchoService> providerConfig2 = new ProviderConfig<EchoService>()
				.setInterfaceId(EchoService.class.getName())
				.setRef(new EchoServiceImpl())
				.setServer(serverConfig)
				.setRegistry(registryConfig);

		providerConfig.export();
		providerConfig2.export();

		log.warn("started at pid {}", RpcRuntimeContext.PID);
	}
}
