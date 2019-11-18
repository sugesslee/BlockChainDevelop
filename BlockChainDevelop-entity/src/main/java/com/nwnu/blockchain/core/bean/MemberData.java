package com.nwnu.blockchain.core.bean;

import lombok.Data;

import java.util.List;

/**
 * member data
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:30 AM
 * @since 1.0.0
 */
@Data
public class MemberData {
	private int code;
	private String message;
	private List<Member> members;
}
