package com.nwnu.blockchain.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:48 AM
 * @since 1.0.0
 */
@Slf4j
public class GetLocalIpUtilTest {

	@Test
	public void getLocalIp() {
		log.info("Local IP: {}", GetLocalIpUtil.getLocalIp());
	}
}