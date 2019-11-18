package com.nwnu.blockchain.core.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * permission data
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:32 AM
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionData extends BaseData {
	private List<Permission> permissions;
}
