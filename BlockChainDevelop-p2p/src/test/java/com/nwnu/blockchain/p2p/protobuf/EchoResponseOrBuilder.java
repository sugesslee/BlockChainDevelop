package com.nwnu.blockchain.p2p.protobuf;

/**
 * EchoResponseOrBuilder
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/06     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/6 4:25 PM
 * @since 1.0.0
 */
public interface EchoResponseOrBuilder extends
		// @@protoc_insertion_point(interface_extends:com.alipay.sofa.rpc.protobuf.EchoResponse)
		com.google.protobuf.MessageOrBuilder {
	/**
	 * <code>int32 code = 1;</code>
	 */
	int getCode();

	/**
	 * <code>string message = 2;</code>
	 */
	java.lang.String getMessage();

	/**
	 * <code>string message = 2;</code>
	 */
	com.google.protobuf.ByteString
	getMessageBytes();
}
