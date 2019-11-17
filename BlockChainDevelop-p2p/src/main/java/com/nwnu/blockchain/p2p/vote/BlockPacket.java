package com.nwnu.blockchain.p2p.vote;

import lombok.Data;
import org.tio.core.intf.Packet;

/**
 * BlockPacket
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/15     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/15 10:46 AM
 * @since 1.0.0
 */
@Data
public class BlockPacket extends Packet {
	// 网络传输需序列化，这里采用Java自带序列化方式
	private static final long serialVersionUID = -172060606924066412L;
	// 消息头的长度
	public static final int HEADER_LENGTH = 4;
	// 字符编码类型
	public static final String CHARSET = "utf-8";
	// 传输内容的字节
	private byte[] body;
}
