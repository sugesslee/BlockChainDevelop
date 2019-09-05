package com.nwnu.blockchain.rpc;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * RestServerTest
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 10:31 AM
 * @since 1.0.0
 */
@Slf4j
public class RestServerTest {
	//	@Test
	public static void main(String[] args) {
		ApplicationConfig application = new ApplicationConfig().setAppName("test-server");

        /*
         访问地址：
         POST http://127.0.0.1:8888/rest/hello/code/name
         GET http://127.0.0.1:8888/rest/hello/code
         PUT http://127.0.0.1:8888/rest/hello/code/name
         DELETE http://127.0.0.1:8888/rest/hello/code
         GET http://127.0.0.1:8888/rest/get/1234567890
         POST http://127.0.0.1:8888/rest/post/1234567890 bodydddddd
         */

		ServerConfig serverConfig = new ServerConfig()
				.setProtocol("rest")
				.setPort(8888)
				.setDaemon(false);

		ProviderConfig<RestService> providerConfig = new ProviderConfig<RestService>()
				.setInterfaceId(RestService.class.getName())
				.setApplication(application)
				.setRef(new RestServiceImpl())
				.setBootstrap("rest")
				.setServer(serverConfig)
				.setRegister(false);

		providerConfig.export();

		log.info("started at pid {}", RpcRuntimeContext.PID);
	}
}
