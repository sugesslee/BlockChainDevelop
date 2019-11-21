package com.nwnu.blockchain.core.body;

import com.nwnu.blockchain.common.AppId;
import com.nwnu.blockchain.utils.CommonUtil;
import lombok.Data;

/**
 * BaseBody
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:46 AM
 * @since 1.0.0
 */
@Data
public class BaseBody {
	/**
	 * 消息发送时间
	 */
	private Long time = System.currentTimeMillis();
	/**
	 * 每条消息的唯一id
	 */
	private String messageId = CommonUtil.generateUuid();
	/**
	 * 回复的哪条消息
	 */
	private String responseMsgId;
	/**
	 * 自己是谁
	 */
	private String appId = AppId.value;
}
