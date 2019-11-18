package com.nwnu.blockchain.p2p.core.sqlparser;

import com.nwnu.blockchain.block.Instruction;
import com.nwnu.blockchain.block.InstructionBase;
import com.nwnu.blockchain.p2p.core.model.base.BaseEntity;
import com.nwnu.blockchain.p2p.core.model.convert.ConvertTableName;
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
public class InstructionParserImpl<T extends BaseEntity> implements InstructionParser {
	@Resource
	private ConvertTableName<T> convertTableName;
	@Resource
	private AbstractSqlParser<T>[] sqlParsers;

	@Override
	public boolean parse(InstructionBase instructionBase) {
		byte operation = instructionBase.getOperation();
		String table = instructionBase.getTable();
		String json = instructionBase.getOldJson();
		//表对应的类名，如MessageEntity.class
		Class<T> clazz = convertTableName.convertOf(table);
		T object = FastJsonUtil.toBean(json, clazz);
		for (AbstractSqlParser<T> sqlParser : sqlParsers) {
			if (clazz.equals(sqlParser.getEntityClass())) {
				if (instructionBase instanceof Instruction) {
					object.setPublicKey(((Instruction) instructionBase).getPublicKey());
				}
				sqlParser.parse(operation, instructionBase.getInstructionId(), object);
				break;
			}
		}

		return true;
	}
}
