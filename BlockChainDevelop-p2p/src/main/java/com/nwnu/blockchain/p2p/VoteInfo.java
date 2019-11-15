package com.nwnu.blockchain.p2p;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * VoteInfo
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 10:49 AM
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteInfo {
	// 投票状态码
	private int code;
	// 待写入区块的内容
	private List<String> list;
	// 待写入区块的内容的Merkle root hash值
	private String hash;
}
