package com.nwnu.blockchain.algorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AESAlgorithm
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 9:35 AM
 * @since 1.0.0
 */
public class AESAlgorithm {
	/**
	 * aesEncode:aes 加密. <br/>
	 *
	 * @param key  秘钥
	 * @param data 明文
	 */
	public static byte[] aesEncode(byte[] key, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(data);
	}

	/**
	 * aesDecode: aes 解密. <br/>
	 *
	 * @param key           key
	 * @param encryptedText encryptedText
	 * @return encryptedText
	 * @throws Exception Exception
	 */
	public static byte[] aesDecode(byte[] key, byte[] encryptedText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(encryptedText);
	}
}
