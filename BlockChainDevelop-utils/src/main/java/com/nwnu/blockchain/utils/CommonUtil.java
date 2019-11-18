package com.nwnu.blockchain.utils;

import java.util.UUID;

/**
 * CommonUtil
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:47 AM
 * @since 1.0.0
 */
public class CommonUtil {
	public static String generateUuid() {
		return UUID.randomUUID().toString();
	}

	public static Long getNow() {
		return System.currentTimeMillis();
	}

}
