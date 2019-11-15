package com.nwnu.blockchain.p2p.test;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 4:54 PM
 * @since 1.0.0
 */
@Slf4j
public class HelloServiceImpl implements HelloService {

	private int sleep;

	private String result;

	public HelloServiceImpl() {

	}

	public HelloServiceImpl(String result) {
		this.result = result;
	}

	public HelloServiceImpl(int sleep) {
		this.sleep = sleep;
	}


	@Override
	public String sayHello(String name, int age) {
		log.info("name:" + name + ", age:" + age);
		if (sleep > 0) {
			try {
				Thread.sleep(sleep);
			} catch (Exception ignore) {
			}
		}
		return result != null ? result : "hello " + name + " from server! age: " + age;
	}
}
