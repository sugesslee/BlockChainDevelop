package com.nwnu.blockchain.transaction;

import com.nwnu.blockchain.utils.SerializeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Transaction
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/10/09     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/10/9 8:46 AM
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Transaction {
	private static final int SUBSIDY = 10;

	/**
	 * 交易的Hash
	 */
	private byte[] txId;
	/**
	 * 交易输入
	 */
	private TXInput[] inputs;
	/**
	 * 交易输出
	 */
	private TXOutput[] outputs;
	/**
	 * 创建日期
	 */
	private long createTime;
	/**
	 * 计算交易信息的Hash值
	 *
	 * @return byte[]
	 */
	public byte[] hash() {
		// 使用序列化的方式对Transaction对象进行深度复制
		byte[] serializeBytes = SerializeUtil.serialize(this);
		Transaction copyTx = (Transaction) SerializeUtil.deserialize(serializeBytes);
		copyTx.setTxId(new byte[]{});
		return DigestUtils.sha256(SerializeUtil.serialize(copyTx));
	}
}
