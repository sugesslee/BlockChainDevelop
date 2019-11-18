package com.nwnu.blockchain.p2p.socket.body;

import com.nwnu.blockchain.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * body里是一个block信息
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:56 AM
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RpcBlockBody extends BaseBody {
	/**
	 * blockJson
	 */
	private Block block;

	public RpcBlockBody() {
		super();
	}

	public RpcBlockBody(Block block) {
		super();
		this.block = block;
	}
}
