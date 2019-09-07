package com.nwnu.blockchain.rpc.test;

import lombok.Data;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 5:04 PM
 * @since 1.0.0
 */
public class EchoServiceImpl implements EchoService {
	@Override
	public String echoStr(String arg) {
		return arg;
	}
}
