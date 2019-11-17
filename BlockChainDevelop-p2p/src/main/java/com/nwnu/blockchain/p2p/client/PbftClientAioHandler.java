package com.nwnu.blockchain.p2p.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nwnu.blockchain.p2p.vote.BlockPacket;
import com.nwnu.blockchain.p2p.vote.VoteEnum;
import com.nwnu.blockchain.p2p.vote.VoteInfo;
import com.nwnu.blockchain.p2p.message.MsgDecode;
import com.nwnu.blockchain.p2p.message.MsgEncode;
import com.nwnu.blockchain.tree.SimpleMerkleTree;
import lombok.extern.slf4j.Slf4j;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.Tio;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * PbftClientAioHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 11:17 AM
 * @since 1.0.0
 */
@Slf4j
public class PbftClientAioHandler implements ClientAioHandler {
	private static BlockPacket heartbeatPacket = new BlockPacket();


	/**
	 * 此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
	 */
	@Override
	public Packet heartbeatPacket() {
		return null;
//		return heartbeatPacket;
	}

	/**
	 * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包 总的消息结构：消息头 + 消息体 消息头结构： 4个字节，存储消息体的长度 消息体结构： 对象的json串的byte[]
	 */
	@Override
	public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength,
						 ChannelContext channelContext)
			throws AioDecodeException {
		MsgDecode msgDecode = new MsgDecode();
		return msgDecode.decode(buffer, limit, position, readableLength, channelContext);
	}


	/**
	 * 编码：把业务消息包编码为可以发送的ByteBuffer 总的消息结构：消息头 + 消息体 消息头结构： 4个字节，存储消息体的长度 消息体结构： 对象的json串的byte[]
	 */
	@Override
	public ByteBuffer encode(Packet packet, GroupContext groupContext,
							 ChannelContext channelContext) {
		MsgEncode msgEncode = new MsgEncode();
		return msgEncode.encode(packet, groupContext, channelContext);
	}

	/**
	 * 处理消息
	 */
	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		BlockPacket helloPacket = (BlockPacket) packet;
		byte[] body = helloPacket.getBody();
		if (body != null) {
			String str = new String(body, BlockPacket.CHARSET);
			log.info("client get message:" + str);

			// 收到入库的消息则不再发送
			if ("server start block to block chain".equals(str)) {
				log.info("pbft end, start block to block chain");
				//TODO start block to block chain
				return;
			}

			// 发送pbft投票信息
			// 如果收到的不是JSON话数据，说明是在双方建立连接的过程中。目前连接以及建立完毕，发起投票
			if (!str.startsWith("{")) {
				VoteInfo vi = createVoteInfo(VoteEnum.PRE_PREPARE);
				BlockPacket bp = new BlockPacket();
				bp.setBody(JSON.toJSONString(vi).getBytes(BlockPacket.CHARSET));
				Tio.send(channelContext, bp);
				log.info("client to server pbft message：" + JSON.toJSONString(vi));
				return;
			}

			// 如果是json化数据，则进入到了pbft投票阶段
			JSONObject json = JSON.parseObject(str);
			if (!json.containsKey("code")) {
				log.info("client get not json data.");
			}

			int code = json.getIntValue("code");
			if (code == VoteEnum.PREPARE.getCode()) {
				// 校验hash
				VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
				if (!voteInfo.getHash().equals(SimpleMerkleTree.getTreeNodeHash(voteInfo.getList()))) {
					log.info("client get error json data.");
					return;
				}

				// 校验成功，发送下一个状态的数据
				VoteInfo vi = createVoteInfo(VoteEnum.COMMIT);
				BlockPacket bp = new BlockPacket();
				bp.setBody(JSON.toJSONString(vi).getBytes(BlockPacket.CHARSET));
				Tio.send(channelContext, bp);

				log.info("server to client message:" + JSON.toJSONString(vi));
			}
		}

		return;
	}

	// 根据VoteEnum构建对应状态的VoteInfo
	private VoteInfo createVoteInfo(VoteEnum ve) {
		VoteInfo vi = new VoteInfo();
		vi.setCode(ve.getCode());

		List<String> list = new ArrayList<>();
		list.add("AI");
		list.add("BlockChain");
		vi.setList(list);
		vi.setHash(SimpleMerkleTree.getTreeNodeHash(list));

		return vi;
	}
}
