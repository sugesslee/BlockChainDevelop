package com.nwnu.blockchain.p2p;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nwnu.blockchain.p2p.vote.BlockPacket;
import com.nwnu.blockchain.p2p.vote.VoteEnum;
import com.nwnu.blockchain.p2p.vote.VoteInfo;
import com.nwnu.blockchain.p2p.message.MsgDecode;
import com.nwnu.blockchain.p2p.message.MsgEncode;
import com.nwnu.blockchain.tree.SimpleMerkleTree;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.Tio;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * PbftServerAioHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 10:44 AM
 * @since 1.0.0
 */
@Slf4j
public class PbftServerAioHandler implements ServerAioHandler {
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
			log.info("server get message：" + str);

			// 如果收到的是非JSON数据则说明不是pbft阶段
			if (!str.startsWith("{")) {
				BlockPacket resPacket = new BlockPacket();
				resPacket.setBody(("server get client message, your message is:" + str).getBytes(BlockPacket.CHARSET));
				Tio.send(channelContext, resPacket);
				return;
			}

			// //如果收到的是JSON数据则说明是pbft阶段
			// 如果是json化数据，则进入到了pbft投票阶段
			JSONObject json = JSON.parseObject(str);
			if (!json.containsKey("code")) {
				log.info("server get not json data.");
			}

			int code = json.getIntValue("code");
			if (code == VoteEnum.PRE_PREPARE.getCode()) {
				// 校验hash
				VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
				if (!voteInfo.getHash().equals(SimpleMerkleTree.getTreeNodeHash(voteInfo.getList()))) {
					log.info("server get error json data");
					return;
				}

				// 校验成功，发送下一个状态的数据
				VoteInfo vi = createVoteInfo(VoteEnum.PREPARE);
				BlockPacket respPacket = new BlockPacket();
				respPacket.setBody(JSON.toJSONString(vi).getBytes(BlockPacket.CHARSET));
				Tio.send(channelContext, respPacket);
				log.info("server to client message:" + JSON.toJSONString(vi));
				return;
			}

			if (code == VoteEnum.COMMIT.getCode()) {
				// 校验hash
				VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
				if (!voteInfo.getHash().equals(SimpleMerkleTree.getTreeNodeHash(voteInfo.getList()))) {
					log.info("get server error json data.");
					return;
				}

				// 校验成功，检验节点确认个数是否有效
				if (getConnectedNodeCount() >= getLeastNodeCount()) {
					BlockPacket resPacket = new BlockPacket();
					resPacket.setBody("server start block to block chain".getBytes(BlockPacket.CHARSET));
					Tio.send(channelContext, resPacket);
					log.info("server start block to block chain");
					//TODO start block to block chain
				}
			}
		}
		return;
	}

	// 已经在连接的节点的个数
	private int getConnectedNodeCount() {
		// 本机测试时，写死为1.实际开发部署多个节点时，按实际情况返回
		return 1;
	}

	// pbft消息节点最少确认个数计算
	private int getLeastNodeCount() {
		// 本机测试时，写死为1.实际开发部署多个节点时，pbft算法中拜占庭节点数量f，总节点数3f+1
		return 1;
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
