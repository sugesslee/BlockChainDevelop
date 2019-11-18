package com.nwnu.blockchain.core.bean;

import lombok.ToString;

/**
 * base data
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:27 AM
 * @since 1.0.0
 */
@ToString
public class BaseData {
	private int code;
	private String message;
	private Object data;

	public BaseData setCode(ResultCode resultCode) {
		this.code = resultCode.code;
		return this;
	}

	public int getCode() {
		return code;
	}

	public BaseData setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public BaseData setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public BaseData setData(Object data) {
		this.data = data;
		return this;
	}
}
