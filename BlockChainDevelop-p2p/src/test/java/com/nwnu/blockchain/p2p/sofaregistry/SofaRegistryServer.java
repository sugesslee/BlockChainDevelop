package com.nwnu.blockchain.p2p.sofaregistry;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.nwnu.blockchain.p2p.quickstart.HelloService;
import com.nwnu.blockchain.p2p.quickstart.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * SofaRegistryServer
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 9:44 AM
 * @since 1.0.0
 */
@Slf4j
public class SofaRegistryServer {
	public static void main(String[] args) {
		RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol(RpcConstants.REGISTRY_PROTOCOL_SOFA)
				.setAddress("127.0.0.1:9603");

		ServerConfig serverConfig = new ServerConfig()
				.setProtocol("bolt")
				.setPort(12201)
				.setDaemon(false);

		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setRegistry(registryConfig)
				.setInterfaceId(HelloService.class.getName())
				.setRef(new HelloServiceImpl())
				.setServer(serverConfig);

		providerConfig.export();
		log.warn("start at pid {}", RpcRuntimeContext.PID);
	}
}
