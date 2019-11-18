package com.nwnu.blockchain.block;

/**
 * Operation
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 3:14 PM
 * @since 1.0.0
 */
public interface Operation {
	byte ADD = 1;
	byte DELETE = -1;
	byte UPDATE = 2;
}
