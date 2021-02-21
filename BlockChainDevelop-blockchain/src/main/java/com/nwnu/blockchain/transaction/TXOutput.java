package com.nwnu.blockchain.transaction;

import com.nwnu.blockchain.utils.Base58Check;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * TXOutput
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/10/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/10/9 9:02 AM
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TXOutput {
	/**
	 * 数值
	 */
	private int value;
	/**
	 * 公钥Hash
	 */
	private byte[] pubKeyHash;

	/**
	 * 创建交易输出
	 *
	 * @param value   value
	 * @param address address
	 * @return TXOutput
	 */
	public static TXOutput newTXOutput(int value, String address) {
		// 反向转化为 byte 数组
		byte[] versionedPayload = Base58Check.base58ToBytes(address);
		byte[] pubKeyHash = Arrays.copyOfRange(versionedPayload, 1, versionedPayload.length);
		return new TXOutput(value, pubKeyHash);
	}

	/**
	 * 检查交易输出是否能够使用指定的公钥
	 *
	 * @param pubKeyHash pubKeyHash
	 * @return boolean
	 */
	public boolean isLockedWithKey(byte[] pubKeyHash) {
		return Arrays.equals(this.getPubKeyHash(), pubKeyHash);
	}
}
