package com.nwnu.blockchain.p2p.handler.base;

import com.nwnu.blockchain.common.constant.ServerConst;
import com.nwnu.blockchain.core.body.BaseBody;
import com.nwnu.blockchain.core.packet.BlockPacket;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * AbstractBlockHandler
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:45 AM
 * @since 1.0.0
 */
public abstract class AbstractBlockHandler<T extends BaseBody> implements HandlerInterface {
	public AbstractBlockHandler() {
	}

	public abstract Class<T> bodyClass();

	@Override
	public Object handler(BlockPacket packet, ChannelContext channelContext) throws Exception {
		String jsonStr;
		T bsBody = null;
		if (packet.getBody() != null) {
			jsonStr = new String(packet.getBody(), ServerConst.CHARSET);
			bsBody = Json.toBean(jsonStr, bodyClass());
		}

		return handler(packet, bsBody, channelContext);
	}

	/**
	 * 实际的handler处理
	 *
	 * @param packet         packet
	 * @param bsBody         解析后的对象
	 * @param channelContext channelContext
	 * @return 用不上
	 * @throws Exception Exception
	 */
	public abstract Object handler(BlockPacket packet, T bsBody, ChannelContext channelContext) throws Exception;
}
