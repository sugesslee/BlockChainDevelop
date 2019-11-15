package com.nwnu.blockchain.block;


import lombok.Data;

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
public class CoinInfo {
	// 币地址
	private String address;
	// 币地址对应的余额
	private double balance;
}
