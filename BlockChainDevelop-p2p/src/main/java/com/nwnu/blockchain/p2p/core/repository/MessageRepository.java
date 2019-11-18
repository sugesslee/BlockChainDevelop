package com.nwnu.blockchain.p2p.core.repository;


import com.nwnu.blockchain.p2p.core.model.MessageEntity;

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
public interface MessageRepository extends BaseRepository<MessageEntity> {
	/**
	 * 删除一条记录
	 *
	 * @param messageId messageId
	 */
	void deleteByMessageId(String messageId);

	/**
	 * 查询一个
	 *
	 * @param messageId messageId
	 * @return MessageEntity
	 */
	MessageEntity findByMessageId(String messageId);
}
