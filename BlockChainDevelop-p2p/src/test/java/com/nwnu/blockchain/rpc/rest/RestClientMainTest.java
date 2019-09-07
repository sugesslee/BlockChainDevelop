package com.nwnu.blockchain.rpc.rest;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.nwnu.blockchain.rpc.rest.ExampleObj;
import com.nwnu.blockchain.rpc.rest.RestService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * rest test
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 10:15 AM
 * @since 1.0.0
 */
@Slf4j
public class RestClientMainTest {
	public static void main(String[] args) throws InterruptedException {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("test-api");
		ConsumerConfig<RestService> consumerConfig = new ConsumerConfig<RestService>()
				.setApplication(applicationConfig)
				.setInterfaceId(RestService.class.getName())
				.setProtocol("rest")
				.setBootstrap("rest")
				.setDirectUrl("rest://127.0.0.1:8888")
//				.setRegister(false)
				.setTimeout(300);
		RestService helloService = consumerConfig.refer();

		log.warn("start at pid {}", RpcRuntimeContext.PID);

		while (true) {

			try {
				String s = helloService.add(22, "xxx");
				log.info("add {}", s);
				s = helloService.query(22);
				log.info("get {}", s);
				List<ExampleObj> es = new ArrayList<ExampleObj>();
				es.add(new ExampleObj().setName("xxx").setId(1));
				es.add(new ExampleObj().setName("yyy").setId(2));
				List<ExampleObj> rs = helloService.objects(es);
				log.info("rs {}", rs.size());

				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
