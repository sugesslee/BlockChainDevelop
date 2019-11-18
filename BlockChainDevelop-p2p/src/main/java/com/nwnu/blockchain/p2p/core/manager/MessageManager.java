package com.nwnu.blockchain.p2p.core.manager;

import com.nwnu.blockchain.p2p.core.model.MessageEntity;
import com.nwnu.blockchain.p2p.core.repository.MessageRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
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
@Component
public class MessageManager {
	@Resource
	private MessageRepository messageRepository;

	public List<MessageEntity> findAll() {
		return messageRepository.findAll();
	}

	public List<String> findAllContent() {
		return findAll().stream().map(MessageEntity::getContent).collect(Collectors.toList());
	}
}
