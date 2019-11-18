package com.nwnu.blockchain.p2p.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * 同步block到sqlite事件
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 3:08 PM
 * @since 1.0.0
 */
public class DbSyncEvent extends ApplicationEvent {
	public DbSyncEvent(Object source) {
		super(source);
	}
}
