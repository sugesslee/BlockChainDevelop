package com.nwnu.blockchain.p2p.protobuf;

/**
 * EchoRequestOrBuilder
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/06     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/6 11:15 AM
 * @since 1.0.0
 */
public interface EchoRequestOrBuilder extends
		// @@protoc_insertion_point(interface_extends:com.alipay.sofa.rpc.protobuf.EchoRequest)
		com.google.protobuf.MessageOrBuilder {
	/**
	 * <code>string name = 1;</code>
	 */
	java.lang.String getName();

	/**
	 * <code>string name = 1;</code>
	 */
	com.google.protobuf.ByteString
	getNameBytes();

	/**
	 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
	 */
	int getGroupValue();

	/**
	 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
	 */
	Group getGroup();
}
