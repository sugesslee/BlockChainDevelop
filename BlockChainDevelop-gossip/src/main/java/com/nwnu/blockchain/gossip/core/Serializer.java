package com.nwnu.blockchain.gossip.core;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

/**
 * Serializer
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:08 AM
 * @since 1.0.0
 */
public class Serializer {
	private static Serializer ourInstance = new Serializer();

	public static Serializer getInstance() {
		return ourInstance;
	}

	private Serializer() {
	}

	public Buffer encode(Serializable obj) {
		Buffer buffer = Buffer.buffer();
		try {
			buffer.appendString(JsonObject.mapFrom(obj).encode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public <T> T decode(Buffer buffer, Class<T> typeReference) {
		T gdsm = null;
		if (buffer != null) {
			try {
				gdsm = buffer.toJsonObject().mapTo(typeReference);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gdsm;
	}
}
