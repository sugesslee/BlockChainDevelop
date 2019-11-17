package com.nwnu.blockchain.web.controller;

import com.nwnu.blockchain.p2p.client.PbftClientStarter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/14     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/14 3:39 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

	private final PbftClientStarter pbftClientStarter;

	public HelloController(PbftClientStarter pbftClientStarter) {
		this.pbftClientStarter = pbftClientStarter;
	}

	@RequestMapping("/test")
	public String Hello(int index){
		return "hello block chain" + index;
	}

	@RequestMapping("/send")
	public void SendMessage() throws Exception {
		pbftClientStarter.sendMessage();
	}
}
