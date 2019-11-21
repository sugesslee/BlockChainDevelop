package com.nwnu.blockchain.p2p.base;

import com.nwnu.blockchain.core.packet.BlockPacket;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.AioHandler;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * AbstractAioHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:40 AM
 * @since 1.0.0
 */
public abstract class AbstractAioHandler implements AioHandler {
	/**
	 * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
	 * 消息头：type + bodyLength
	 * 消息体：byte[]
	 */
	@Override
	public BlockPacket decode(ByteBuffer buffer, int limit, int position, int readableLength,
							  ChannelContext channelContext) throws AioDecodeException {
		readableLength = buffer.limit() - buffer.position();
		if (readableLength < BlockPacket.HEADER_LENGTH) {
			return null;
		}

		//消息类型
		byte type = buffer.get();

		int bodyLength = buffer.getInt();

		if (bodyLength < 0) {
			throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext
					.getClientNode());
		}

		int neededLength = BlockPacket.HEADER_LENGTH + bodyLength;
		int test = readableLength - neededLength;
		// 不够消息体长度(剩下的buffer组不了消息体)
		if (test < 0) {
			return null;
		}
		BlockPacket imPacket = new BlockPacket();
		imPacket.setType(type);
		if (bodyLength > 0) {
			byte[] dst = new byte[bodyLength];
			buffer.get(dst);
			imPacket.setBody(dst);
		}
		return imPacket;
	}

	/**
	 * 编码：把业务消息包编码为可以发送的ByteBuffer
	 * 消息头：type + bodyLength
	 * 消息体：byte[]
	 */
	@Override
	public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
		BlockPacket showcasePacket = (BlockPacket) packet;
		byte[] body = showcasePacket.getBody();
		int bodyLen = 0;
		if (body != null) {
			bodyLen = body.length;
		}

		//总长度是消息头的长度+消息体的长度
		int allLen = BlockPacket.HEADER_LENGTH + bodyLen;

		ByteBuffer buffer = ByteBuffer.allocate(allLen);
		buffer.order(groupContext.getByteOrder());

		//写入消息类型
		buffer.put(showcasePacket.getType());
		//写入消息体长度
		buffer.putInt(bodyLen);

		//写入消息体
		if (body != null) {
			buffer.put(body);
		}
		return buffer;
	}
}
