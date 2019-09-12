package com.nwnu.blockchain.gossip.core;

import com.nwnu.blockchain.gossip.enums.MessageType;
import io.vertx.core.json.JsonObject;


/**
 * GossipMessageFactory
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:04 AM
 * @since 1.0.0
 */
public class GossipMessageFactory {
	private static GossipMessageFactory ourInstance = new GossipMessageFactory();
	public static final String KEY_MSG_TYPE = "msgtype";
	public static final String KEY_DATA = "data";
	public static final String KEY_CLUSTER = "cluster";
	public static final String KEY_FROM = "from";

	public static GossipMessageFactory getInstance() {
		return ourInstance;
	}

	private GossipMessageFactory() {
	}

	public JsonObject makeMessage(MessageType type, String data, String cluster, String from) {
		JsonObject bj = new JsonObject();
		bj.put(KEY_MSG_TYPE, type);
		bj.put(KEY_CLUSTER, cluster);
		bj.put(KEY_DATA, data);
		bj.put(KEY_FROM, from);
		return bj;
	}
}
