package com.nwnu.blockchain.repository.sqlparser;


import com.nwnu.blockchain.block.TransactionBase;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:39 PM
 * @since 1.0.0
 */
public interface TransactionParser {
	/**
	 * @param transactionBase 交易
	 * @return boolean
	 */
	boolean parse(TransactionBase transactionBase);
}
