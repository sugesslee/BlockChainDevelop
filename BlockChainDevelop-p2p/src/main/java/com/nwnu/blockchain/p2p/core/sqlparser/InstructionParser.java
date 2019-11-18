package com.nwnu.blockchain.p2p.core.sqlparser;


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
public interface InstructionParser {
	boolean parse(InstructionBase instructionBase);
}
