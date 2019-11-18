package com.nwnu.blockchain.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 9:42 AM
 * @since 1.0.0
 */
@Slf4j
public class DESAlgorithmTest {

	@Test
	public void toKey() throws Exception {
		log.info("DES key: {}", DESAlgorithm
				.toKey(new byte[]{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
	}

	@Test
	public void encrypt() throws Exception {
		log.info("en: {}", DESAlgorithm.encrypt(new byte[]{1},
				new byte[]{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
	}

	@Test
	public void decrypt() {
	}
}