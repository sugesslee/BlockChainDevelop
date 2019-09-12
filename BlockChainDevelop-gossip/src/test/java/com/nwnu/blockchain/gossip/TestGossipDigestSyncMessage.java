package com.nwnu.blockchain.gossip;

import com.nwnu.blockchain.gossip.core.Serializer;
import com.nwnu.blockchain.gossip.enums.GossipState;
import com.nwnu.blockchain.gossip.model.GossipDigest;
import com.nwnu.blockchain.gossip.model.GossipMember;
import com.nwnu.blockchain.gossip.model.SyncMessage;
import io.vertx.core.buffer.Buffer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * TestGossipDigestSyncMessage
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:31 AM
 * @since 1.0.0
 */
@Slf4j
public class TestGossipDigestSyncMessage {
	@Test
	public void encodeAndDecode() throws UnknownHostException {
		String c = "test.cluster";
		List<GossipDigest> digestList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			GossipMember endpoint = new GossipMember();
			endpoint.setId("id" + 1);
			endpoint.setPort(i);
			endpoint.setIpAddress("127.0.0.1");
			endpoint.setCluster(c);
			endpoint.setState(GossipState.JOIN);
			long heartbeatTime = 1000 + i;
			GossipDigest digest = new GossipDigest(endpoint, heartbeatTime, i);
			digestList.add(digest);
		}
		SyncMessage message = new SyncMessage(c, digestList);
		Buffer buffer = Serializer.getInstance().encode(message);
		log.info("encode：{}", buffer.toString());

		SyncMessage message1 = Serializer.getInstance().decode(buffer, SyncMessage.class);
		log.info("decode: {}", message1.getDigestList());

		log.info("buffer.length: {}", buffer.length());
	}
}
