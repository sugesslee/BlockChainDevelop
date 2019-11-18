package com.nwnu.blockchain.common.enums;

/**
 * ${desc}
 * <pre>
 *  Version         Date            Author          Description
 * ---------------------------------------------------------------------------------------
 *  1.0.0           2018/10/13     redli        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2018/10/13 13:00
 * @date 2018/10/13 13:00
 * @since 1.0.0
 */
public enum ResponseCode {
	/**
	 * 0代表SUCCESS
	 * 1代表ERROR
	 * 10代表需要登录
	 */
	SUCCESS(0, "SUCCESS"),
	ERROR(1, "ERROR"),
	NEED_LOGIN(10, "NEED_LOGIN"),
	ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
	INVALID_PARAM_ERROR(3, "参数错误"),
	DES3_ENCRYPT_ERROR(4, "DES3加解密错误"),
	AES_ENCRYPT_ERROR(5, "AES加解密错误"),
	ECDSA_ENCRYPT_ERROR(6, "ECDSA加解密错误"),
	SIGN_ERROR(7, "签名错误"),
	GENERATE_SIGN_ERROR(8, "生成签名错误"),
	GENERATE_SQL_ERROR(8, "生成SQL错误"),
	VERIFY_SIGN_ERROR(11, "验证签名错误");

	private final int code;
	private final String desc;

	/**
	 * 构造器
	 */
	ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 将构造器开放
	 *
	 * @return
	 */
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
