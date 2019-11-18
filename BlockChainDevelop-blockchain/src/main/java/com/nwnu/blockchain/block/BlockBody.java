package com.nwnu.blockchain.block;

import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 区块body，里面存放交易的数组
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
@ToString
public class BlockBody {
	private List<Instruction> instructions;
}
