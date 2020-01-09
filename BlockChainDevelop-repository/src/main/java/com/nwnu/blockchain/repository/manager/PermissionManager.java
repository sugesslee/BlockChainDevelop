package com.nwnu.blockchain.repository.manager;

import com.nwnu.blockchain.block.Block;
import com.nwnu.blockchain.block.Transaction;
import com.nwnu.blockchain.common.type.PermissionType;
import com.nwnu.blockchain.core.bean.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * PermissionManager
 * 对Permission信息的存储和使用
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 1:19 PM
 * @since 1.0.0
 */
@Service
@Slf4j
public class PermissionManager {
	/**
	 * 将权限信息常驻内存
	 */
	public static final Map<String, List<Permission>> PERMISSION_MAP = new HashMap<>();

	/**
	 * 校验block内的所有指令的权限是否合法
	 *
	 * @param block 区块
	 * @return 合法
	 */
	public boolean checkPermission(Block block) {
		List<Transaction> transactions = block.getBlockBody().getTransactions();
		return checkPermission(transactions);
	}

	public boolean checkPermission(List<Transaction> transactions) {
		for (Transaction transaction : transactions) {
			String publicKey = transaction.getPublicKey();
			String tableName = transaction.getTable();
			//TODO 这块要优化，循环次数太多，需要精简
			if (!checkOperation(publicKey, tableName)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * 校验某用户对某表的某个操作是否有权限
	 *
	 * @param publicKey 公钥
	 * @param tableName 表名
	 * @return 有权限true
	 */
	private boolean checkOperation(String publicKey, String tableName) {
		List<Permission> permissionList = PERMISSION_MAP.get(tableName);

		Set<Byte> userPermissionSet = new HashSet<>();
		for (Permission permission : permissionList) {
			//如果是不限用户的情况，取到该表的所有公开的权限
			if ("*".equals(permission.getPublicKey())) {
				userPermissionSet.add(permission.getPermissionType());
			} else {
				//找到该publicKey的所有权限
				if (publicKey.equals(permission.getPublicKey())) {
					userPermissionSet.add(permission.getPermissionType());
				}
			}
		}

		//判断该用户的权限是否包含operation
		return userPermissionSet.contains(PermissionType.OWNER)
				|| userPermissionSet.contains(PermissionType.ALL);
	}


	/**
	 * 保存权限信息，static常驻内存，按table划分到map里
	 *
	 * @param permissions permissions
	 */
	public void savePermissionList(List<Permission> permissions) {
		PERMISSION_MAP.clear();
		for (Permission permission : permissions) {
			String key = permission.getTableName();
			if (!PERMISSION_MAP.containsKey(key)) {
				PERMISSION_MAP.put(key, new ArrayList<>());
			}
			PERMISSION_MAP.get(key).add(permission);
		}
		log.info("所有的权限信息：" + PERMISSION_MAP);
	}
}
