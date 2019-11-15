package com.nwnu.blockchain.p2p.quickstart;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;

/**
 * QuickStartServer
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 9:16 AM
 * @since 1.0.0
 */
public class QuickStartServer {
	public static void main(String[] args) {
		ServerConfig serverConfig = new ServerConfig()
				.setProtocol("bolt")//通信协议
				.setPort(12200)
				.setDaemon(false);//非守护线程

		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())//指定接口
				.setRef(new HelloServiceImpl())//指定实现
				.setServer(serverConfig);//指定服务端

		// 发布服务
		providerConfig.export();
	}
}
