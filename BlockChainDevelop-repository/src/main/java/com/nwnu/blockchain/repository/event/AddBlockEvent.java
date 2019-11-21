package com.nwnu.blockchain.repository.event;

import com.nwnu.blockchain.block.Block;
import org.springframework.context.ApplicationEvent;

/**
 * AddBlockEvent
 * 确定生成block的Event（添加到rocksDB，执行sqlite语句，发布给其他节点）
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:48 PM
 * @since 1.0.0
 */
public class AddBlockEvent extends ApplicationEvent {
	public AddBlockEvent(Block block) {
		super(block);
	}
}
