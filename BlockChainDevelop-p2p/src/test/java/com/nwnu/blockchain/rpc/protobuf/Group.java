package com.nwnu.blockchain.rpc.protobuf;

/**
 * Group
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/06     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/6 11:16 AM
 * @since 1.0.0
 */
public enum Group implements com.google.protobuf.ProtocolMessageEnum {
	/**
	 * <code>A = 0;</code>
	 */
	A(0),
	/**
	 * <code>B = 1;</code>
	 */
	B(1),
	UNRECOGNIZED(-1),
	;

	/**
	 * <code>A = 0;</code>
	 */
	public static final int A_VALUE = 0;
	/**
	 * <code>B = 1;</code>
	 */
	public static final int B_VALUE = 1;

	public final int getNumber() {
		if (this == UNRECOGNIZED) {
			throw new java.lang.IllegalArgumentException(
					"Can't get the number of an unknown enum value.");
		}
		return value;
	}

	/**
	 * @deprecated Use {@link #forNumber(int)} instead.
	 */
	@java.lang.Deprecated
	public static Group valueOf(int value) {
		return forNumber(value);
	}

	public static Group forNumber(int value) {
		switch (value) {
			case 0:
				return A;
			case 1:
				return B;
			default:
				return null;
		}
	}

	public static com.google.protobuf.Internal.EnumLiteMap<Group>
	internalGetValueMap() {
		return internalValueMap;
	}

	private static final com.google.protobuf.Internal.EnumLiteMap<Group> internalValueMap =
			new com.google.protobuf.Internal.EnumLiteMap<Group>() {
				public Group findValueByNumber(int number) {
					return Group
							.forNumber(number);
				}
			};

	public final com.google.protobuf.Descriptors.EnumValueDescriptor
	getValueDescriptor() {
		return getDescriptor().getValues().get(ordinal());
	}

	public final com.google.protobuf.Descriptors.EnumDescriptor
	getDescriptorForType() {
		return getDescriptor();
	}

	public static final com.google.protobuf.Descriptors.EnumDescriptor
	getDescriptor() {
		return ProtoServiceOuterClass.getDescriptor().getEnumTypes().get(0);
	}

	private static final Group[] VALUES = values();

	public static Group valueOf(
			com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
		if (desc.getType() != getDescriptor()) {
			throw new java.lang.IllegalArgumentException(
					"EnumValueDescriptor is not for this type.");
		}
		if (desc.getIndex() == -1) {
			return UNRECOGNIZED;
		}
		return VALUES[desc.getIndex()];
	}

	private final int value;

	private Group(int value) {
		this.value = value;
	}

	// @@protoc_insertion_point(enum_scope:com.alipay.sofa.rpc.protobuf.Group)

}
