package com.nwnu.blockchain.gossip.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.nwnu.blockchain.gossip.model.GossipMember;

import java.io.IOException;

/**
 * CustomSerializer
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
public class CustomSerializer extends JsonSerializer<GossipMember> {
	@Override
	public void serialize(GossipMember value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		gen.writeFieldName(mapper.writeValueAsString(value));
	}
}
