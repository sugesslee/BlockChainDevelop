package com.nwnu.blockchain.gossip.net.udp;

import com.nwnu.blockchain.gossip.core.GossipManager;
import com.nwnu.blockchain.gossip.core.GossipMessageFactory;
import com.nwnu.blockchain.gossip.enums.MessageType;
import com.nwnu.blockchain.gossip.handler.*;
import com.nwnu.blockchain.gossip.net.MsgService;
import lombok.extern.slf4j.Slf4j;
import io.netty.util.internal.StringUtil;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.json.JsonObject;

/**
 * UDPMsgService
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:19 AM
 * @since 1.0.0
 */
@Slf4j
public class UDPMsgService implements MsgService {
	private DatagramSocket socket;

	@Override
	public void listen(String ipAddress, int port) {
		socket = Vertx.vertx().createDatagramSocket(new DatagramSocketOptions());
		socket.listen(port, ipAddress, asyncResult -> {
			if (asyncResult.succeeded()) {
				socket.handler(packet -> handleMsg(packet.data()));
			} else {
				log.error("Listen failed: {}", asyncResult.cause().getMessage());
			}
		});
	}

	@Override
	public void handleMsg(Buffer data) {
		JsonObject j = data.toJsonObject();
		String msgType = j.getString(GossipMessageFactory.KEY_MSG_TYPE);
		String _data = j.getString(GossipMessageFactory.KEY_DATA);
		String cluster = j.getString(GossipMessageFactory.KEY_CLUSTER);
		String from = j.getString(GossipMessageFactory.KEY_FROM);
		if (StringUtil.isNullOrEmpty(cluster) || !GossipManager.getInstance().getCluster().equals(cluster)) {
			log.error("This message shouldn't exist my world! {}", data.toString());
			return;
		}
		MessageHandler handler = null;
		MessageType type = MessageType.valueOf(msgType);
		if (type == MessageType.SYNC_MESSAGE) {
			handler = new SyncMessageHandler();
		} else if (type == MessageType.ACK_MESSAGE) {
			handler = new AckMessageHandler();
		} else if (type == MessageType.ACK2_MESSAGE) {
			handler = new Ack2MessageHandler();
		} else if (type == MessageType.SHUTDOWN) {
			handler = new ShutdownMessageHandler();
		} else {
			log.error("Not supported message type");
		}
		if (handler != null) {
			handler.handle(cluster, _data, from);
		}
	}

	@Override
	public void sendMsg(String targetIp, Integer targetPort, Buffer data) {
		if (targetIp != null && targetPort != null && data != null) {
			socket.send(data, targetPort, targetIp, asyncResult -> {
			});
		}
	}

	@Override
	public void unListen() {
		if (socket != null) {
			socket.close(asyncResult -> {
				if (asyncResult.succeeded()) {
					log.info("Socket was close!");
				} else {
					log.error("Close socket an error has occurred. {}" + asyncResult.cause().getMessage());
				}
			});
		}
	}
}
