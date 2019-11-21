package com.nwnu.blockchain.repository.sqlparser;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.nwnu.blockchain.block.Operation;
import com.nwnu.blockchain.core.model.MessageEntity;
import com.nwnu.blockchain.repository.sqlite.repository.MessageRepository;
import com.nwnu.blockchain.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 解析语句入库的具体实现，Message表的
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:39 PM
 * @since 1.0.0
 */
@Service
public class MessageSqlParser extends AbstractSqlParser<MessageEntity> {
	@Resource
	private MessageRepository messageRepository;

	@Override
	public void parse(byte operation, String messageId, MessageEntity entity) {
		if (Operation.ADD == operation) {
			entity.setCreateTime(CommonUtil.getNow());
			entity.setMessageId(messageId);
			messageRepository.save(entity);
		} else if (Operation.DELETE == operation) {
			messageRepository.deleteByMessageId(messageId);
		} else if (Operation.UPDATE == operation) {
			MessageEntity messageEntity = messageRepository.findByMessageId(messageId);
			BeanUtil.copyProperties(entity, messageEntity,
					CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties("id", "createTime"));
			messageRepository.save(messageEntity);
		}
	}

	@Override
	public Class getEntityClass() {
		return MessageEntity.class;
	}

}
