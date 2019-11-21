package com.nwnu.blockchain.core.requestbody;


import com.nwnu.blockchain.block.BlockBody;

/**
 * 生成Block时传参
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:39 PM
 * @since 1.0.0
 */
public class BlockRequestBody {
	private String publicKey;
	private BlockBody blockBody;

	@Override
	public String toString() {
		return "BlockRequestBody{" +
				"publicKey='" + publicKey + '\'' +
				", blockBody=" + blockBody +
				'}';
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public BlockBody getBlockBody() {
		return blockBody;
	}

	public void setBlockBody(BlockBody blockBody) {
		this.blockBody = blockBody;
	}
}
