package com.nwnu.blockchain.gossip.core;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwnu.blockchain.gossip.model.GossipMember;

import java.io.IOException;

/**
 * CustomDeserializer
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/12     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/12 10:03 AM
 * @since 1.0.0
 */
public class CustomDeserializer extends KeyDeserializer {
	private ObjectMapper mapper = new ObjectMapper();

	public CustomDeserializer() {
	}

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
		return mapper.readValue(key, GossipMember.class);
	}
}
