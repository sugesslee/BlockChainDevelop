package com.nwnu.blockchain.p2p.rest;

import java.io.Serializable;

/**
 * example object
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 10:18 AM
 * @since 1.0.0
 */
public class ExampleObj implements Serializable {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public ExampleObj setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ExampleObj setName(String name) {
		this.name = name;
		return this;
	}
}
