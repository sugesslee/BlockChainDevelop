package com.nwnu.blockchain.core.packet;

import com.nwnu.blockchain.core.body.BaseBody;
import org.tio.utils.json.Json;

/**
 * PacketBuilder
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 12:42 PM
 * @since 1.0.0
 */
public class PacketBuilder<T extends BaseBody> {
	/**
	 * 消息类型，其值在Type中定义
	 */
	private byte type;

	private T body;

	public PacketBuilder<T> setType(byte type) {
		this.type = type;
		return this;
	}

	public PacketBuilder<T> setBody(T body) {
		this.body = body;
		return this;
	}

	public BlockPacket build() {
		return new BlockPacket(type, Json.toJson(body));
	}
}
