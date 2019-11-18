package com.nwnu.blockchain.common.exception;

import com.alibaba.fastjson.JSONObject;

/**
 * TrustSDKException
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 10:21 AM
 * @since 1.0.0
 */
public class TrustSDKException extends Exception {
	private static final long serialVersionUID = -4214831807802264420L;

	protected Integer rtnCd;
	protected String rtnMsg;

	public TrustSDKException(Integer rtnCd, String rtnMsg) {
		super(rtnMsg);
		this.rtnCd = rtnCd;
		this.rtnMsg = rtnMsg;
	}

	public TrustSDKException(Integer rtnCd, String rtnMsg, Throwable t) {
		super(rtnMsg, t);
		this.rtnCd = rtnCd;
		this.rtnMsg = rtnMsg;
	}

	public Integer getRtnCd() {
		return rtnCd;
	}

	public void setRtnCd(Integer rtnCd) {
		this.rtnCd = rtnCd;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
