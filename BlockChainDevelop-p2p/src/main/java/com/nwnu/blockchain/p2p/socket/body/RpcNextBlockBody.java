package com.nwnu.blockchain.p2p.socket.body;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 请求next block时用的包装类
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:58 AM
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RpcNextBlockBody extends BaseBody {
	/**
	 * blockHash
	 */
	private String hash;
	/**
	 * 上一个hash
	 */
	private String prevHash;

	public RpcNextBlockBody() {
		super();
	}

	public RpcNextBlockBody(String hash, String prevHash) {
		super();
		this.hash = hash;
		this.prevHash = prevHash;
	}
}
