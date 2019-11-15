package com.nwnu.blockchain.block;


import lombok.Data;

/**
 * block
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/14     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/14 3:54 PM
 * @since 1.0.0
 */
@Data
public class ContentInfo {
	/**
	 * 新的json内容
	 */
	private String jsonContent;
	/**
	 * 时间戳
	 */
	private Long timeStamp;
	/**
	 * 公钥
	 */
	private String publicKey;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 该操作的hash
	 */
	private String hash;
}

