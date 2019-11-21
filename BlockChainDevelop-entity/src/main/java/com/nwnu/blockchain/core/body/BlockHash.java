package com.nwnu.blockchain.core.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * BlockHash
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:52 AM
 * @since 1.0.0
 */
@Data
@ToString
@AllArgsConstructor
public class BlockHash {
	private String hash;
	private String prevHash;
	private String appId;
}
