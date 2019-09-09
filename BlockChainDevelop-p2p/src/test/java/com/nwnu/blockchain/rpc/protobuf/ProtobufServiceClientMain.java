package com.nwnu.blockchain.rpc.protobuf;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * ProtobufServiceClientMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 3:40 PM
 * @since 1.0.0
 */
@Slf4j
public class ProtobufServiceClientMain {
	public static void main(String[] args) {
		ConsumerConfig<ProtoService> consumerConfig = new ConsumerConfig<ProtoService>()
				.setInterfaceId(ProtoService.class.getName())
				.setProtocol("bolt")
				.setDirectUrl("bolt://127.0.0.1:12200") // 指定直连地址
				.setSerialization("protobuf") //指定序列化协议，默认为hessian
				.setConnectTimeout(10 * 1000);
		ProtoService helloService = consumerConfig.refer();

		while (true) {
			try {
				EchoRequest request = EchoRequest.newBuilder().setName("xxx").setGroup(Group.A).build();
				EchoResponse response = helloService.echoObj(request);

				log.info("{}, {}", response.getCode(), response.getMessage());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
