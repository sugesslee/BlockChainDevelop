package com.nwnu.blockchain.p2p.message;

import com.nwnu.blockchain.p2p.vote.BlockPacket;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * msg encode
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/17     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/17 4:22 PM
 * @since 1.0.0
 */
public class MsgEncode {
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
}
