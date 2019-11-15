package com.nwnu.blockchain.p2p.quickstart;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 9:12 AM
 * @since 1.0.0
 */

@Slf4j
public class QuickStartClient {
	public static void main(String[] args) {
		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setProtocol("bolt")
				.setDirectUrl("bolt://127.0.0.1:12200")
				.setTimeout(1000);

		HelloService helloService = consumerConfig.refer();

		while (true) {
			try {
				log.info(helloService.sayHello("world"));

				Thread.sleep(1000);
			} catch (Exception e) {
				log.info("{}", e.getMessage());
			}
		}
	}
}
