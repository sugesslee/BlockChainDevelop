package com.nwnu.blockchain.p2p.socket.body;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * RpcSimpleBlockBody
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:59 AM
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RpcSimpleBlockBody extends BaseBody {
	/**
	 * blockHash
	 */
	private String hash;

	public RpcSimpleBlockBody() {
		super();
	}

	public RpcSimpleBlockBody(String hash) {
		super();
		this.hash = hash;
	}
}
