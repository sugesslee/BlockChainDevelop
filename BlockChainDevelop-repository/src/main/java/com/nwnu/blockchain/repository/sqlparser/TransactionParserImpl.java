package com.nwnu.blockchain.repository.sqlparser;

import com.nwnu.blockchain.block.Transaction;
import com.nwnu.blockchain.block.TransactionBase;
import com.nwnu.blockchain.core.model.base.BaseEntity;
import com.nwnu.blockchain.core.model.convert.ConvertTableName;
import com.nwnu.blockchain.utils.FastJsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 将区块内指令解析并入库
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
public class TransactionParserImpl<T extends BaseEntity> implements TransactionParser {
	@Resource
	private ConvertTableName<T> convertTableName;
	@Resource
	private AbstractSqlParser<T>[] sqlParsers;

	@Override
	public boolean parse(TransactionBase transactionBase) {
		String table = transactionBase.getTable();
		String json = transactionBase.getOldJson();
		//表对应的类名，如MessageEntity.class
		Class<T> clazz = convertTableName.convertOf(table);
		T object = FastJsonUtil.toBean(json, clazz);
		for (AbstractSqlParser<T> sqlParser : sqlParsers) {
			if (clazz.equals(sqlParser.getEntityClass())) {
				if (transactionBase instanceof Transaction) {
					object.setPublicKey(((Transaction) transactionBase).getPublicKey());
				}
				sqlParser.parse(transactionBase.getTransactionId(), object);
				break;
			}
		}
		return true;
	}
}
