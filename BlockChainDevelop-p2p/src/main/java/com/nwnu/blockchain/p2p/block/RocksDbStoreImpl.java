package com.nwnu.blockchain.p2p.block;

import com.nwnu.blockchain.p2p.socket.vote.Const;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;


/**
 * rocksDB对于存储接口的实现
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:30 PM
 * @since 1.0.0
 */
@Component
@ConditionalOnProperty("db.rocksDB")
public class RocksDbStoreImpl implements DbStore {
	@Resource
	private RocksDB rocksDB;

	@Override
	public void put(String key, String value) {
		try {
			rocksDB.put(key.getBytes(Const.CHARSET), value.getBytes(Const.CHARSET));
		} catch (RocksDBException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String get(String key) {
		try {
			byte[] bytes = rocksDB.get(key.getBytes(Const.CHARSET));
			if (bytes != null) {
				return new String(bytes);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void remove(String key) {
		try {
			rocksDB.delete(rocksDB.get(key.getBytes(Const.CHARSET)));
		} catch (RocksDBException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
