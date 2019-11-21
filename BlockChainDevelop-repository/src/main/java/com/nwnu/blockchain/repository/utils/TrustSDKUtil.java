package com.nwnu.blockchain.repository.utils;

import com.nwnu.blockchain.algorithm.ECDSAAlgorithm;
import com.nwnu.blockchain.common.PairKey;
import com.nwnu.blockchain.common.enums.ResponseCode;
import com.nwnu.blockchain.common.exception.TrustSDKException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * TrustSDK
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:23 AM
 * @since 1.0.0
 */
public class TrustSDKUtil {
	/**
	 * generatePairKey:产生一对公私钥, 并返回. <br/>
	 *
	 * @return 返回公私钥对
	 * @throws TrustSDKException TrustSDKException
	 */
	public static PairKey generatePairKey() throws TrustSDKException {
		return generatePairKey(false);
	}

	/**
	 * generatePairKey:生成私钥公钥对. <br/>
	 *
	 * @param encodePubKey 是否压缩
	 * @return PairKey
	 * @throws TrustSDKException TrustSDKException
	 */
	public static PairKey generatePairKey(boolean encodePubKey) throws TrustSDKException {
		try {
			PairKey pair = new PairKey();
			String privateKey = ECDSAAlgorithm.generatePrivateKey();
			String pubKey = ECDSAAlgorithm.generatePublicKey(privateKey.trim(), encodePubKey);
			pair.setPrivateKey(privateKey);
			pair.setPublicKey(pubKey);
			return pair;
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.ECDSA_ENCRYPT_ERROR.getCode(),
					ResponseCode.ECDSA_ENCRYPT_ERROR.getDesc(), e);
		}
	}

	/**
	 * checkPairKey:验证一对公私钥是否匹配. <br/>
	 *
	 * @param prvKey 输入 存放私钥 长度必须为PRIVATE_KEY_DIGEST_LENGTH
	 * @param pubKey 输入 存放公钥 长度必须为PUBKEY_DIGEST_LENGTH
	 * @return true 公私钥匹配  false 公私钥不匹配
	 * @throws TrustSDKException TrustSDKException
	 */
	public static boolean checkPairKey(String prvKey, String pubKey) throws TrustSDKException {
		if (StringUtils.isEmpty(prvKey) || StringUtils.isEmpty(pubKey)) {
			throw new TrustSDKException(ResponseCode.INVALID_PARAM_ERROR.getCode(),
					ResponseCode.INVALID_PARAM_ERROR.getDesc());
		}
		try {
			String correctPubKey = ECDSAAlgorithm.generatePublicKey(prvKey.trim(), true);
			return pubKey.trim().equals(correctPubKey);
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.ECDSA_ENCRYPT_ERROR.getCode(),
					ResponseCode.ECDSA_ENCRYPT_ERROR.getDesc(), e);
		}
	}

	/**
	 * generatePubkeyByPrivateKey: 通过私钥计算相应公钥. <br/>
	 *
	 * @param privateKey 私钥字符串
	 * @param encode     是否压缩公钥
	 * @return 返回公钥字符串
	 * @throws TrustSDKException TrustSDKException
	 */
	public static String generatePubkeyByPrivateKey(String privateKey, boolean encode) throws TrustSDKException {
		if (StringUtils.isEmpty(privateKey)) {
			throw new TrustSDKException(ResponseCode.INVALID_PARAM_ERROR.getCode(),
					ResponseCode.INVALID_PARAM_ERROR.getDesc());
		}
		try {
			return ECDSAAlgorithm.generatePublicKey(privateKey, encode);
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.ECDSA_ENCRYPT_ERROR.getCode(),
					ResponseCode.ECDSA_ENCRYPT_ERROR.getDesc(), e);
		}
	}

	/**
	 * generatePubkeyByPrivateKey: 通过私钥计算相应公钥. <br/>
	 *
	 * @param privateKey 私钥字符串
	 * @return 返回公钥字符串
	 * @throws TrustSDKException TrustSDKException
	 */
	public static String generatePubkeyByPrivateKey(String privateKey) throws TrustSDKException {
		return generatePubkeyByPrivateKey(privateKey, false);
	}

	public static String decodePubkey(String encodePubKey) throws TrustSDKException {
		if (StringUtils.isEmpty(encodePubKey)) {
			throw new TrustSDKException(ResponseCode.INVALID_PARAM_ERROR.getCode(),
					ResponseCode.INVALID_PARAM_ERROR.getDesc());
		}
		try {
			return ECDSAAlgorithm.decodePublicKey(encodePubKey);
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.ECDSA_ENCRYPT_ERROR.getCode(),
					ResponseCode.ECDSA_ENCRYPT_ERROR.getDesc(), e);
		}
	}

	/**
	 * generateAddrByPubkey:通过公钥获取对应地址. <br/>
	 *
	 * @param pubKey 公钥字符串
	 * @return address
	 * @throws TrustSDKException TrustSDKException
	 */
	public static String generateAddrByPubkey(String pubKey) throws TrustSDKException {
		if (StringUtils.isEmpty(pubKey)) {
			throw new TrustSDKException(ResponseCode.INVALID_PARAM_ERROR.getCode(),
					ResponseCode.INVALID_PARAM_ERROR.getDesc());
		}
		try {
			return ECDSAAlgorithm.getAddress(Base64.decodeBase64(pubKey));
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.ECDSA_ENCRYPT_ERROR.getCode(),
					ResponseCode.ECDSA_ENCRYPT_ERROR.getDesc(), e);
		}
	}

	/**
	 * generateAddrByPrivateKey:通过私钥计算相应地址. <br/>
	 *
	 * @param privateKey 私钥字符串
	 * @return Address
	 * @throws TrustSDKException TrustSDKException
	 */
	public static String generateAddrByPrivateKey(String privateKey) throws TrustSDKException {
		if (StringUtils.isEmpty(privateKey)) {
			throw new TrustSDKException(ResponseCode.INVALID_PARAM_ERROR.getCode(),
					ResponseCode.INVALID_PARAM_ERROR.getDesc());
		}
		try {
			String pubKey = ECDSAAlgorithm.generatePublicKey(privateKey);
			return generateAddrByPubkey(pubKey);
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.ECDSA_ENCRYPT_ERROR.getCode(),
					ResponseCode.ECDSA_ENCRYPT_ERROR.getDesc(), e);
		}
	}

	/**
	 * signString:为字符串进行签名, 并返回签名. <br/>
	 *
	 * @param privateKey 私钥字符串
	 * @param data       需要签名的字符数组
	 * @return 返回签名字符串
	 * @throws TrustSDKException TrustSDKException
	 */
	public static String signString(String privateKey, byte[] data) throws TrustSDKException {
		if (StringUtils.isEmpty(privateKey)) {
			throw new TrustSDKException(ResponseCode.INVALID_PARAM_ERROR.getCode(),
					ResponseCode.INVALID_PARAM_ERROR.getDesc());
		}
		try {
			return ECDSAAlgorithm.sign(privateKey, data);
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.SIGN_ERROR.getCode(), ResponseCode.SIGN_ERROR.getDesc(), e);
		}
	}

	public static String signString(String privateKey, String data) throws TrustSDKException,
			UnsupportedEncodingException {
		return signString(privateKey, data.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * verifyString:验证一个签名是否有效. <br/>
	 *
	 * @param pubKey    公钥字符串
	 * @param srcString 源字符串
	 * @param sign      签名字符串
	 * @return 返回验证是否通过 true:验证成功 false:验证失败
	 * @throws TrustSDKException TrustSDKException
	 */
	public static boolean verifyString(String pubKey, String srcString, String sign) throws TrustSDKException {
		if (StringUtils.isEmpty(pubKey) || StringUtils.isEmpty(srcString) || StringUtils.isEmpty(sign)) {
			throw new TrustSDKException(ResponseCode.INVALID_PARAM_ERROR.getCode(),
					ResponseCode.INVALID_PARAM_ERROR.getDesc());
		}
		try {
			return ECDSAAlgorithm.verify(srcString, sign, pubKey);
		} catch (Exception e) {
			throw new TrustSDKException(ResponseCode.ECDSA_ENCRYPT_ERROR.getCode(),
					ResponseCode.ECDSA_ENCRYPT_ERROR.getDesc(), e);
		}
	}
}
