package com.nwnu.blockchain.rpc.quickstart;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/9 9:10 AM
 * @since 1.0.0
 */
@Slf4j
public class HelloServiceImpl implements HelloService {
	@Override
	public String sayHello(String str) {
		log.info("service receive: {}", str);
		return "hello " + str + " !";
	}
}
