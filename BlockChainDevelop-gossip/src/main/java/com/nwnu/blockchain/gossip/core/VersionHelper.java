package com.nwnu.blockchain.gossip.core;

import java.util.concurrent.atomic.AtomicLong;

/**
 * VersionHelper
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:09 AM
 * @since 1.0.0
 */
public class VersionHelper {
	private static AtomicLong v = new AtomicLong(0);
	private static VersionHelper ourInstance = new VersionHelper();

	public static VersionHelper getInstance() {
		return ourInstance;
	}

	private VersionHelper() {
	}

	public long nextVersion() {
		return v.incrementAndGet();
	}
}
