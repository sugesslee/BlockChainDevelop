package com.nwnu.blockchain.core.model;


import com.nwnu.blockchain.core.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:35 AM
 * @since 1.0.0
 */
@Entity
@Table(name = "message")
public class MessageEntity extends BaseEntity {
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 目标用户
	 */
	private String target;
	/**
	 * 来源
	 */
	private String origin;
	/**
	 * 业务id
	 */
	private String messageId;

	@Override
	public String toString() {
		return "MessageEntity{" +
				"content='" + content + '\'' +
				", target='" + target + '\'' +
				", origin='" + origin + '\'' +
				", messageId='" + messageId + '\'' +
				'}';
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
