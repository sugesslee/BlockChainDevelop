package com.nwnu.blockchain.repository.sqlparser;


import com.nwnu.blockchain.block.InstructionBase;

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
	boolean parse(InstructionBase instructionBase);
}
