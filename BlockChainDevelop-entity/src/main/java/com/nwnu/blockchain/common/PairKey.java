package com.nwnu.blockchain.common;

import lombok.Data;

/**
 * PairKey
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:26 AM
 * @since 1.0.0
 */
@Data
public class PairKey {
	private String publicKey;
	private String privateKey;
}
