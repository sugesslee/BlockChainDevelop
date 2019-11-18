package com.nwnu.blockchain.p2p.socket.vote;

import com.nwnu.blockchain.block.Block;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:53 AM
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VotePreMsg extends VoteMsg {
	private Block block;
}
