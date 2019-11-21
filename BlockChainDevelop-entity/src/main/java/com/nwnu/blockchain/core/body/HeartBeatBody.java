package com.nwnu.blockchain.core.body;

/**
 * HeartBeatBody
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:38 PM
 * @since 1.0.0
 */
@Deprecated
public class HeartBeatBody extends BaseBody {
	/**
	 * text
	 */
	private String text;

	public HeartBeatBody() {
		super();
	}

	public HeartBeatBody(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "HeartBeatBody{" +
				"text='" + text + '\'' +
				'}';
	}
}
