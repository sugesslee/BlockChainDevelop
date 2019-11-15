package com.nwnu.blockchain.block;

import lombok.Data;

import java.util.List;


/**
 * block
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/14     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/14 3:54 PM
 * @since 1.0.0
 */
@Data
public class DealInfo {
	// 交易发起人
	private int fromUserId;
	// 交易发起人用于转账的CoinInfo列表
	private List<CoinInfo> fromCoinList;
	// 交易收款人
	private int toUserId;
	// 交易费用
	private double dealCost;
	// 打包成区块的费用
	private double blockCost;
}
