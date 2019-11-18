package com.nwnu.blockchain.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:04 AM
 * @since 1.0.0
 */
@Slf4j
public class ECDSAAlgorithmTest {

	/**
	 * 测试使用私钥签名，并使用公钥验证签名
	 *
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		String priKey = ECDSAAlgorithm.generatePrivateKey();
		log.info(priKey);
		String pubKey = ECDSAAlgorithm.generatePublicKey(priKey, true);
		String pubKey1 = ECDSAAlgorithm.generatePublicKey(priKey);
		log.info(pubKey);
		log.info(pubKey1);
		String sign = ECDSAAlgorithm.sign(priKey, "abc");
		log.info(sign);
		boolean verify = ECDSAAlgorithm.verify("abc", sign, pubKey);
		log.info("{}", verify);
	}
}