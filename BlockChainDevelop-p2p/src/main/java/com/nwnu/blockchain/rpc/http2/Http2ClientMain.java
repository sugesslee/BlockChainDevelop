package com.nwnu.blockchain.rpc.http2;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.nwnu.blockchain.rpc.protobuf.EchoRequest;
import com.nwnu.blockchain.rpc.protobuf.EchoResponse;
import com.nwnu.blockchain.rpc.protobuf.Group;
import com.nwnu.blockchain.rpc.protobuf.ProtoService;
import lombok.extern.slf4j.Slf4j;

/**
 * http2 client main
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/08     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/8 8:32 AM
 * @since 1.0.0
 */
@Slf4j
public class Http2ClientMain {
	public static void main(String[] args) throws InterruptedException {
		ApplicationConfig application = new ApplicationConfig().setAppName("test-http2");

		ConsumerConfig<ProtoService> consumerConfig = new ConsumerConfig<ProtoService>()
				.setApplication(application)
				.setInterfaceId(ProtoService.class.getName())
				.setProtocol("h2c")
				.setDirectUrl("h2c://127.0.0.1:12300")
				.setSerialization("protobuf")
				.setRegister(false)
				.setTimeout(3000);
		ProtoService helloService = consumerConfig.refer();

		log.warn("started at pid: {}", RpcRuntimeContext.PID);

		while (true) {
			try {
				EchoRequest request = EchoRequest.newBuilder().setGroup(Group.A).setName("XXX").build();
				EchoResponse response = helloService.echoObj(request);

				log.warn("{}", response);
			} catch (Exception e) {
				log.error("{}", e.getMessage());
			}
			Thread.sleep(2000);
		}
	}
}
