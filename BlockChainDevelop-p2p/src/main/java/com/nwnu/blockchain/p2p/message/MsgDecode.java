package com.nwnu.blockchain.p2p.message;

import com.nwnu.blockchain.p2p.vote.BlockPacket;
import org.tio.core.ChannelContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * msg decode
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/17     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/17 4:26 PM
 * @since 1.0.0
 */
public class MsgDecode {
	/**
	 * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包 总的消息结构：消息头 + 消息体 消息头结构： 4个字节，存储消息体的长度 消息体结构： 对象的json串的byte[]
	 */
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
}
