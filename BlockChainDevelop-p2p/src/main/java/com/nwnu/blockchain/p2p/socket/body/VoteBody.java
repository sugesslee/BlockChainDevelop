package com.nwnu.blockchain.p2p.socket.body;

import com.nwnu.blockchain.p2p.socket.vote.VoteMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * VoteBody
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:00 AM
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class VoteBody extends BaseBody {
	private VoteMsg voteMsg;

	public VoteBody() {
		super();
	}

	public VoteBody(VoteMsg voteMsg) {
		super();
		this.voteMsg = voteMsg;
	}
}
