package com.nwnu.blockchain.p2p.socket.handler.server;

import com.nwnu.blockchain.ApplicationContextProvider;
import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.p2p.socket.base.AbstractBlockHandler;
import com.nwnu.blockchain.p2p.socket.body.RpcBlockBody;
import com.nwnu.blockchain.p2p.socket.body.RpcCheckBlockBody;
import com.nwnu.blockchain.p2p.check.CheckerManager;
import com.nwnu.blockchain.p2p.socket.pbft.queue.MsgQueueManager;
import com.nwnu.blockchain.p2p.socket.vote.BlockPacket;
import com.nwnu.blockchain.p2p.socket.vote.VotePreMsg;
import com.nwnu.blockchain.p2p.socket.vote.VoteType;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;

/**
 * GenerateBlockRequestHandler
 * 收到请求生成区块消息，进入PrePre队列
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:05 PM
 * @since 1.0.0
 */
@Slf4j
public class GenerateBlockRequestHandler extends AbstractBlockHandler<RpcBlockBody> {
	@Override
	public Class<RpcBlockBody> bodyClass() {
		return RpcBlockBody.class;
	}

	@Override
	public Object handler(BlockPacket packet, RpcBlockBody rpcBlockBody, ChannelContext channelContext) {
		Block block = rpcBlockBody.getBlock();
		log.info("收到来自于<" + rpcBlockBody.getAppId() + "><请求生成Block>消息，block信息为[" + block + "]");

		CheckerManager checkerManager = ApplicationContextProvider.getBean(CheckerManager.class);
		//对区块的基本信息进行校验，校验通过后进入pbft的Pre队列
		RpcCheckBlockBody rpcCheckBlockBody = checkerManager.check(block);
		log.info("校验结果:" + rpcCheckBlockBody.toString());
		if (rpcCheckBlockBody.getCode() == 0) {
			VotePreMsg votePreMsg = new VotePreMsg();
			votePreMsg.setBlock(block);
			votePreMsg.setVoteType(VoteType.PRE_PREPARE);
			votePreMsg.setNumber(block.getBlockHeader().getNumber());
			votePreMsg.setAppId(rpcBlockBody.getAppId());
			votePreMsg.setHash(block.getBlockHash());
			votePreMsg.setAgree(true);
			//将消息推入PrePrepare队列
			ApplicationContextProvider.getBean(MsgQueueManager.class).pushMsg(votePreMsg);
		}

		return null;
	}
}
