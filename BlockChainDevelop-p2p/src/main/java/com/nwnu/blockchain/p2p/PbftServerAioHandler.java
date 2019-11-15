package com.nwnu.blockchain.p2p;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nwnu.blockchain.p2p.BlockPacket;
import com.nwnu.blockchain.p2p.VoteEnum;
import com.nwnu.blockchain.p2p.VoteInfo;
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
		// 提醒：buffer的开始位置并不一定是0，应用需要从buffer.position()开始读取数据
		// 收到的数据组不了业务包，则返回null以告诉框架数据不够
		if (readableLength < BlockPacket.HEADER_LENGTH) {
			return null;
		}

		// 读取消息体的长度
		int bodyLength = buffer.getInt();

		// 数据不正确，则抛出AioDecodeException异常
		if (bodyLength < 0) {
			throw new AioDecodeException(
					"bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
		}

		// 计算本次需要的数据长度
		int neededLength = BlockPacket.HEADER_LENGTH + bodyLength;
		// 收到的数据是否足够组包
		int isDataEnough = readableLength - neededLength;
		// 不够消息体长度(剩下的buffer组不了消息体)
		if (isDataEnough < 0) {
			return null;
		} else // 组包成功
		{
			BlockPacket imPacket = new BlockPacket();
			if (bodyLength > 0) {
				byte[] dst = new byte[bodyLength];
				buffer.get(dst);
				imPacket.setBody(dst);
			}
			return imPacket;
		}
	}

	/**
	 * 编码：把业务消息包编码为可以发送的ByteBuffer 总的消息结构：消息头 + 消息体 消息头结构： 4个字节，存储消息体的长度 消息体结构： 对象的json串的byte[]
	 */
	@Override
	public ByteBuffer encode(Packet packet, GroupContext groupContext,
							 ChannelContext channelContext) {
		BlockPacket helloPacket = (BlockPacket) packet;
		byte[] body = helloPacket.getBody();
		int bodyLen = 0;
		if (body != null) {
			bodyLen = body.length;
		}

		// byte buffer的总长度是 = 消息头的长度 + 消息体的长度
		int allLen = BlockPacket.HEADER_LENGTH + bodyLen;
		// 创建一个新的byte buffer
		ByteBuffer buffer = ByteBuffer.allocate(allLen);
		// 设置字节序
		buffer.order(groupContext.getByteOrder());

		// 写入消息头----消息头的内容就是消息体的长度
		buffer.putInt(bodyLen);

		// 写入消息体
		if (body != null) {
			buffer.put(body);
		}
		return buffer;
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
			log.info("好未来服务端收到消息：" + str);

			// 如果收到的是非JSON数据则说明不是pbft阶段
			if (!str.startsWith("{")) {
				BlockPacket resPacket = new BlockPacket();
				resPacket.setBody(("好未来服务端收到了你的消息，你的消息是:" + str).getBytes(BlockPacket.CHARSET));
				Tio.send(channelContext, resPacket);
				return;
			}

			// //如果收到的是JSON数据则说明是pbft阶段
			// 如果是json化数据，则进入到了pbft投票阶段
			JSONObject json = JSON.parseObject(str);
			if (!json.containsKey("code")) {
				log.info("好未来服务端收到非JSON化数据");
			}

			int code = json.getIntValue("code");
			if (code == VoteEnum.PRE_PREPARE.getCode()) {
				// 校验hash
				VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
				if (!voteInfo.getHash().equals(SimpleMerkleTree.getTreeNodeHash(voteInfo.getList()))) {
					log.info("收到好未来客户端错误的JSON化数据");
					return;
				}

				// 校验成功，发送下一个状态的数据
				VoteInfo vi = createVoteInfo(VoteEnum.PREPARE);
				BlockPacket resppacket = new BlockPacket();
				resppacket.setBody(JSON.toJSONString(vi).getBytes(BlockPacket.CHARSET));
				Tio.send(channelContext, resppacket);
				log.info("好未来服务端发送到客户端pbft消息：" + JSON.toJSONString(vi));
				return;
			}

			if (code == VoteEnum.COMMIT.getCode()) {
				// 校验hash
				VoteInfo voteInfo = JSON.parseObject(str, VoteInfo.class);
				if (!voteInfo.getHash().equals(SimpleMerkleTree.getTreeNodeHash(voteInfo.getList()))) {
					log.info("收到好未来北京服务端错误的JSON化数据");
					return;
				}

				// 校验成功，检验节点确认个数是否有效
				if (getConnectedNodeCount() >= getLeastNodeCount()) {
					BlockPacket resPacket = new BlockPacket();
					resPacket.setBody("好未来北京客户端开始区块入库啦".getBytes(BlockPacket.CHARSET));
					Tio.send(channelContext, resPacket);
					log.info("好未来北京客户端开始区块入库啦");
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
