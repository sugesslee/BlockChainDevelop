package com.nwnu.blockchain.core.bean;

/**
 * ResultGenerator
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:35 AM
 * @since 1.0.0
 */
public class ResultGenerator {
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	public static BaseData genSuccessResult() {
		return new BaseData()
				.setCode(ResultCode.SUCCESS)
				.setMessage(DEFAULT_SUCCESS_MESSAGE);
	}

	public static BaseData genSuccessResult(Object data) {
		return new BaseData()
				.setCode(ResultCode.SUCCESS)
				.setMessage(DEFAULT_SUCCESS_MESSAGE)
				.setData(data);
	}

	public static BaseData genFailResult(String message) {
		return new BaseData()
				.setCode(ResultCode.FAIL)
				.setMessage(message);
	}

	public static BaseData genFailResult(ResultCode resultCode, String message) {
		return new BaseData()
				.setCode(resultCode)
				.setMessage(message);
	}
}
