package com.nwnu.blockchain.transaction;

import com.nwnu.blockchain.utils.AddressUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * TXInput
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/10/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/10/9 8:53 AM
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TXInput {

	/**
	 * 交易Id的hash值
	 */
	private byte[] txId;
	/**
	 * 交易输出索引
	 */
	private int txOutputIndex;
	/**
	 * 签名
	 */
	private byte[] signature;
	/**
	 * 公钥
	 */
	private byte[] pubKey;

	/**
	 * 检查公钥hash是否用于交易输入
	 *
	 * @param pubKeyHash pubKeyHash
	 * @return boolean
	 */
	public boolean usesKey(byte[] pubKeyHash) {
		byte[] lockingHash = AddressUtil.ripeMD160Hash(this.getPubKey());
		return Arrays.equals(lockingHash, pubKeyHash);
	}
}
