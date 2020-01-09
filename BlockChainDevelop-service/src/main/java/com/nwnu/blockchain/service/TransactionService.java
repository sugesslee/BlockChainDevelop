package com.nwnu.blockchain.service;

import cn.hutool.core.bean.BeanUtil;
import com.nwnu.blockchain.block.Transaction;
import com.nwnu.blockchain.block.TransactionReverse;
import com.nwnu.blockchain.common.exception.TrustSDKException;
import com.nwnu.blockchain.core.requestbody.TransactionBody;
import com.nwnu.blockchain.repository.utils.TrustSDKUtil;
import com.nwnu.blockchain.utils.CommonUtil;
import com.nwnu.blockchain.utils.CryptoUtil;
import org.springframework.stereotype.Service;

/**
 * 一条指令的service
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
public class TransactionService {
	/**
	 * 校验公私钥是不是一对
	 *
	 * @param transactionBody transactionBody
	 * @return boolean
	 * @throws TrustSDKException TrustSDKException
	 */
	public boolean checkKeyPair(TransactionBody transactionBody) throws TrustSDKException {
		return TrustSDKUtil.checkPairKey(transactionBody.getPrivateKey(), transactionBody.getPublicKey());
	}

	/**
	 * 校验内容的合法性
	 *
	 * @param transactionBody transactionBody
	 * @return true false
	 */
	public boolean checkContent(TransactionBody transactionBody) {
		return transactionBody.getJson() != null && transactionBody.getOldJson() != null;
	}

	/**
	 * 根据传来的body构建一条指令
	 *
	 * @param transactionBody body
	 * @return Transaction
	 */
	public Transaction build(TransactionBody transactionBody) throws Exception {
		Transaction transaction = new Transaction();
		BeanUtil.copyProperties(transactionBody, transaction);
		transaction.setTransactionId(CommonUtil.generateUuid());
		transaction.setTimeStamp(CommonUtil.getNow());
		String buildStr = getSignString(transaction);
		//设置签名，供其他人验证
		transaction.setSign(TrustSDKUtil.signString(transactionBody.getPrivateKey(), buildStr));
		//设置hash，防止篡改
		transaction.setHash(CryptoUtil.getSHA256(buildStr));

		return transaction;
	}

	private String getSignString(Transaction transaction) {
		return transaction.getTable() + transaction
				.getTransactionId() + (transaction.getJson() == null ? "" : transaction.getJson());
	}

	/**
	 * 根据一个指令，计算它的回滚时的指令。<p>
	 * 如add table1 {id:xxx, name:"123"}，那么回滚时就是delete table1 {id:xxx}
	 * 如delete table2 id2 oldJson:{id:xxx, name:"123"}，那么回滚时就是add table2 {id:xxx, name:"123"}。
	 * 如update table3 id3 json:{id:xxx, name:"123"} oldJson:{id:xxx, name:"456"}
	 * 注意，更新和删除时，原来的json都得有，不然没法回滚
	 *
	 * @param transaction transaction
	 * @return 回滚指令
	 */
	public TransactionReverse buildReverse(Transaction transaction) {
		TransactionReverse transactionReverse = new TransactionReverse();
		BeanUtil.copyProperties(transaction, transactionReverse);


		return transactionReverse;
	}

	public boolean checkSign(Transaction transaction) throws TrustSDKException {
		String buildStr = getSignString(transaction);
		return TrustSDKUtil.verifyString(transaction.getPublicKey(), buildStr, transaction.getSign());
	}

	public boolean checkHash(Transaction transaction) {
		String buildStr = getSignString(transaction);
		return CryptoUtil.getSHA256(buildStr).equals(transaction.getHash());
	}
}
