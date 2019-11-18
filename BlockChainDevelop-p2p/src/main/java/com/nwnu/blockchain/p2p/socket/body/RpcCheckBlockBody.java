package com.nwnu.blockchain.p2p.socket.body;

import com.nwnu.blockchain.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 校验block是否合法，同意、拒绝区块生成请求
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:57 AM
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RpcCheckBlockBody extends RpcBlockBody {
	/**
	 * 0是正常同意，-1区块number错误，-2没有权限，-3hash错误，-4时间错误，-10不合法的next block
	 */
	private int code;
	/**
	 * 附带的message
	 */
	private String message;

	public RpcCheckBlockBody() {
	}

	public RpcCheckBlockBody(int code, String message) {
		this(code, message, null);
	}

	public RpcCheckBlockBody(int code, String message, Block block) {
		super(block);
		this.code = code;
		this.message = message;

	}
}