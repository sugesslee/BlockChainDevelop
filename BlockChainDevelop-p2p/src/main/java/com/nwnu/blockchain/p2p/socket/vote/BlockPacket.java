package com.nwnu.blockchain.p2p.socket.vote;

import org.tio.core.intf.Packet;

import java.io.UnsupportedEncodingException;

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
public class BlockPacket extends Packet {
	// 网络传输需序列化，这里采用Java自带序列化方式
	private static final long serialVersionUID = -172060606924066412L;
	/**
	 *  消息头的长度 1+4
	 */
	public static final int HEADER_LENGTH = 5;
	/**
	 * 消息类型，其值在Type中定义
	 */
	private byte type;

	private byte[] body;

	public BlockPacket() {
		super();
	}

	/**
	 * @param type type
	 * @param body body
	 * @author tanyaowu
	 */
	public BlockPacket(byte type, byte[] body) {
		super();
		this.type = type;
		this.body = body;
	}

	public BlockPacket(byte type, String body) {
		super();
		this.type = type;
		setBody(body);
	}

	/**
	 * @return the body
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * @return the type
	 */
	public byte getType() {
		return type;
	}

	@Override
	public String logstr() {
		return "" + type;
	}

	/**
	 * @param body
	 *         the body to set
	 */
	public void setBody(byte[] body) {
		this.body = body;
	}

	public void setBody(String body) {
		try {
			this.body = body.getBytes(Const.CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param type
	 *         the type to set
	 */
	public void setType(byte type) {
		this.type = type;
	}
}
