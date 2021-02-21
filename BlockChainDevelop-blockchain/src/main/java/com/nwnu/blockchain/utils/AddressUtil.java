package com.nwnu.blockchain.utils;

import org.apache.commons.codec.digest.DigestUtils;
//import org.bouncycastle.crypto.digests.RIPEMD160Digest;


import java.util.Arrays;

/**
 * AddressUtil
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/10/09     red        -
 * </pre>
 * 地址工具类
 *
 * @author red
 * @version 1.0.0 2019/10/9 8:58 AM
 * @since 1.0.0
 */
public class AddressUtil {

	/**
	 * 双重Hash
	 *
	 * @param data data
	 * @return byte hash
	 */
	public static byte[] doubleHash(byte[] data) {
		return DigestUtils.sha256(DigestUtils.sha256(data));
	}

	/**
	 * 计算公钥的 RIPEMD160 Hash值
	 *
	 * @param pubKey 公钥
	 * @return ipeMD160Hash(sha256 ( pubkey))
	 */
	public static byte[] ripeMD160Hash(byte[] pubKey) {
		//1. 先对公钥做 sha256 处理
//		byte[] shaHashedKey = DigestUtils.sha256(pubKey);
//		RIPEMD160Digest ripemd160 = new RIPEMD160Digest();
//		ripemd160.update(shaHashedKey, 0, shaHashedKey.length);
//		byte[] output = new byte[ripemd160.getDigestSize()];
//		ripemd160.doFinal(output, 0);
//		return output;
		return null;
	}

	/**
	 * 生成公钥的校验码
	 *
	 * @param payload payload
	 * @return byte
	 */
	public static byte[] checksum(byte[] payload) {
		return Arrays.copyOfRange(doubleHash(payload), 0, 4);
	}
}
