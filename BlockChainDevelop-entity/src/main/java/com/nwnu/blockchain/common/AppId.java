package com.nwnu.blockchain.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * AppId
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 9:26 AM
 * @since 1.0.0
 */
@Component
public class AppId {
	/**
	 * 节点的唯一标志
	 */
	@Value("${appId}")
	private String appId;
	/**
	 * 该客户的唯一标志
	 */
	@Value("${name}")
	private String name;

	public static String value;
	
	public static String nameValue;

	@PostConstruct
	public void init() {
		value = appId;
		nameValue = name;
	}
}
