package com.nwnu.blockchain.rpc.http2;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.nwnu.blockchain.rpc.protobuf.ProtoService;
import com.nwnu.blockchain.rpc.protobuf.ProtoServiceImpl;
import lombok.extern.slf4j.Slf4j;

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
				.setPort(123000)
				.setDaemon(false);

		ProviderConfig<ProtoService> protoServiceProviderConfig = new ProviderConfig<ProtoService>()
				.setInterfaceId(ProtoService.class.getName())
				.setApplication(applicationConfig)
				.setRef(new ProtoServiceImpl())
				.setServer(serverConfig);
	}
}
