package com.nwnu.blockchain.gossip.event;

import com.nwnu.blockchain.gossip.enums.GossipState;
import com.nwnu.blockchain.gossip.model.GossipMember;

/**
 * GossipListener
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:09 AM
 * @since 1.0.0
 */
public interface GossipListener {
	void gossipEvent(GossipMember member, GossipState state);
}
