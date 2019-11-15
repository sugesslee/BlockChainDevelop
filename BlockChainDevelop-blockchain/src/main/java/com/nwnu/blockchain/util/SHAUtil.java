package com.nwnu.blockchain.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 11:10 AM
 * @since 1.0.0
 */
public class SHAUtil {
	/***
	 * 利用Apache commons-codec的工具类实现SHA-256加密
	 *
	 * @param originalStr 加密前的报文
	 * @return String 加密后的报文
	 */
	static String getSHA256BasedMD(String originalStr) {
		MessageDigest messageDigest;
		String encdeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash = messageDigest.digest(originalStr.getBytes(StandardCharsets.UTF_8));
			encdeStr = Hex.encodeHexString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encdeStr;
	}

	/***
	 * 利用Hutool的工具类实现SHA-256加密
	 *
	 * @param originalStr 加密前的报文
	 * @return String 加密后的报文
	 */
	public static String sha256BasedHuTool(String originalStr) {
		return DigestUtil.sha256Hex(originalStr);
	}

}
