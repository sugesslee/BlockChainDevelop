package com.nwnu.blockchain.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 11:10 AM
 * @since 1.0.0
 */
@Slf4j
public class CryptoUtilTest {

	@Test
	public void getSHA256() {
		String result = CryptoUtil.getSHA256("123");
		Assert.assertEquals("加密失败", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", result);
	}
}