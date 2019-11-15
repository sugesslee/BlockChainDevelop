package com.nwnu.blockchain.p2p.protobuf;

/**
 * EchoRequest
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/06     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/6 11:11 AM
 * @since 1.0.0
 */
public final class EchoRequest extends com.google.protobuf.GeneratedMessageV3 implements EchoRequestOrBuilder {
	// Use EchoRequest.newBuilder() to construct.
	private EchoRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
		super(builder);
	}

	private EchoRequest() {
		name_ = "";
		group_ = 0;
	}

	@java.lang.Override
	public final com.google.protobuf.UnknownFieldSet
	getUnknownFields() {
		return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
	}

	private EchoRequest(
			com.google.protobuf.CodedInputStream input,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		this();
		int mutable_bitField0_ = 0;
		try {
			boolean done = false;
			while (!done) {
				int tag = input.readTag();
				switch (tag) {
					case 0:
						done = true;
						break;
					default: {
						if (!input.skipField(tag)) {
							done = true;
						}
						break;
					}
					case 10: {
						java.lang.String s = input.readStringRequireUtf8();

						name_ = s;
						break;
					}
					case 16: {
						int rawValue = input.readEnum();

						group_ = rawValue;
						break;
					}
				}
			}
		} catch (com.google.protobuf.InvalidProtocolBufferException e) {
			throw e.setUnfinishedMessage(this);
		} catch (java.io.IOException e) {
			throw new com.google.protobuf.InvalidProtocolBufferException(
					e).setUnfinishedMessage(this);
		} finally {
			makeExtensionsImmutable();
		}
	}

	public static final com.google.protobuf.Descriptors.Descriptor
	getDescriptor() {
		return ProtoServiceOuterClass.internal_static_com_alipay_sofa_rpc_protobuf_EchoRequest_descriptor;
	}

	protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
	internalGetFieldAccessorTable() {
		return ProtoServiceOuterClass.internal_static_com_alipay_sofa_rpc_protobuf_EchoRequest_fieldAccessorTable
				.ensureFieldAccessorsInitialized(
						EchoRequest.class,
						EchoRequest.Builder.class);
	}

	public static final int NAME_FIELD_NUMBER = 1;
	private volatile java.lang.Object name_;

	/**
	 * <code>string name = 1;</code>
	 */
	public java.lang.String getName() {
		java.lang.Object ref = name_;
		if (ref instanceof java.lang.String) {
			return (java.lang.String) ref;
		} else {
			com.google.protobuf.ByteString bs =
					(com.google.protobuf.ByteString) ref;
			java.lang.String s = bs.toStringUtf8();
			name_ = s;
			return s;
		}
	}

	/**
	 * <code>string name = 1;</code>
	 */
	public com.google.protobuf.ByteString
	getNameBytes() {
		java.lang.Object ref = name_;
		if (ref instanceof java.lang.String) {
			com.google.protobuf.ByteString b =
					com.google.protobuf.ByteString.copyFromUtf8(
							(java.lang.String) ref);
			name_ = b;
			return b;
		} else {
			return (com.google.protobuf.ByteString) ref;
		}
	}

	public static final int GROUP_FIELD_NUMBER = 2;
	private int group_;

	/**
	 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
	 */
	public int getGroupValue() {
		return group_;
	}

	/**
	 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
	 */
	public Group getGroup() {
		Group result = Group.valueOf(group_);
		return result == null ? Group.UNRECOGNIZED : result;
	}

	private byte memoizedIsInitialized = -1;

	public final boolean isInitialized() {
		byte isInitialized = memoizedIsInitialized;
		if (isInitialized == 1)
			return true;
		if (isInitialized == 0)
			return false;

		memoizedIsInitialized = 1;
		return true;
	}

	public void writeTo(com.google.protobuf.CodedOutputStream output)
			throws java.io.IOException {
		if (!getNameBytes().isEmpty()) {
			com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
		}
		if (group_ != Group.A.getNumber()) {
			output.writeEnum(2, group_);
		}
	}

	public int getSerializedSize() {
		int size = memoizedSize;
		if (size != -1)
			return size;

		size = 0;
		if (!getNameBytes().isEmpty()) {
			size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
		}
		if (group_ != Group.A.getNumber()) {
			size += com.google.protobuf.CodedOutputStream
					.computeEnumSize(2, group_);
		}
		memoizedSize = size;
		return size;
	}

	private static final long serialVersionUID = 0L;

	@java.lang.Override
	public boolean equals(final java.lang.Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof EchoRequest)) {
			return super.equals(obj);
		}
		EchoRequest other = (EchoRequest) obj;

		boolean result = true;
		result = result && getName()
				.equals(other.getName());
		result = result && group_ == other.group_;
		return result;
	}

	@java.lang.Override
	public int hashCode() {
		if (memoizedHashCode != 0) {
			return memoizedHashCode;
		}
		int hash = 41;
		hash = (19 * hash) + getDescriptor().hashCode();
		hash = (37 * hash) + NAME_FIELD_NUMBER;
		hash = (53 * hash) + getName().hashCode();
		hash = (37 * hash) + GROUP_FIELD_NUMBER;
		hash = (53 * hash) + group_;
		hash = (29 * hash) + unknownFields.hashCode();
		memoizedHashCode = hash;
		return hash;
	}

	public static EchoRequest parseFrom(
			com.google.protobuf.ByteString data)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}

	public static EchoRequest parseFrom(
			com.google.protobuf.ByteString data,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}

	public static EchoRequest parseFrom(byte[] data)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data);
	}

	public static EchoRequest parseFrom(
			byte[] data,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws com.google.protobuf.InvalidProtocolBufferException {
		return PARSER.parseFrom(data, extensionRegistry);
	}

	public static EchoRequest parseFrom(java.io.InputStream input)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3
				.parseWithIOException(PARSER, input);
	}

	public static EchoRequest parseFrom(
			java.io.InputStream input,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3
				.parseWithIOException(PARSER, input, extensionRegistry);
	}

	public static EchoRequest parseDelimitedFrom(java.io.InputStream input)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3
				.parseDelimitedWithIOException(PARSER, input);
	}

	public static EchoRequest parseDelimitedFrom(
			java.io.InputStream input,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3
				.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
	}

	public static EchoRequest parseFrom(
			com.google.protobuf.CodedInputStream input)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3
				.parseWithIOException(PARSER, input);
	}

	public static EchoRequest parseFrom(
			com.google.protobuf.CodedInputStream input,
			com.google.protobuf.ExtensionRegistryLite extensionRegistry)
			throws java.io.IOException {
		return com.google.protobuf.GeneratedMessageV3
				.parseWithIOException(PARSER, input, extensionRegistry);
	}

	public Builder newBuilderForType() {
		return newBuilder();
	}

	public static Builder newBuilder() {
		return DEFAULT_INSTANCE.toBuilder();
	}

	public static Builder newBuilder(EchoRequest prototype) {
		return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
	}

	public Builder toBuilder() {
		return this == DEFAULT_INSTANCE
				? new Builder() : new Builder().mergeFrom(this);
	}

	@java.lang.Override
	protected Builder newBuilderForType(
			com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
		Builder builder = new Builder(parent);
		return builder;
	}

	/**
	 * Protobuf type {@code com.alipay.sofa.rpc.protobuf.EchoRequest}
	 */
	public static final class Builder extends
			com.google.protobuf.GeneratedMessageV3.Builder<Builder>
			implements
			// @@protoc_insertion_point(builder_implements:com.alipay.sofa.rpc.protobuf.EchoRequest)
			EchoRequestOrBuilder {
		public static final com.google.protobuf.Descriptors.Descriptor
		getDescriptor() {
			return ProtoServiceOuterClass.internal_static_com_alipay_sofa_rpc_protobuf_EchoRequest_descriptor;
		}

		protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
		internalGetFieldAccessorTable() {
			return ProtoServiceOuterClass.internal_static_com_alipay_sofa_rpc_protobuf_EchoRequest_fieldAccessorTable
					.ensureFieldAccessorsInitialized(
							EchoRequest.class,
							EchoRequest.Builder.class);
		}

		// Construct using com.alipay.sofa.rpc.protobuf.EchoRequest.newBuilder()
		private Builder() {
			maybeForceBuilderInitialization();
		}

		private Builder(
				com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
			super(parent);
			maybeForceBuilderInitialization();
		}

		private void maybeForceBuilderInitialization() {
			if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
			}
		}

		public Builder clear() {
			super.clear();
			name_ = "";

			group_ = 0;

			return this;
		}

		public com.google.protobuf.Descriptors.Descriptor
		getDescriptorForType() {
			return ProtoServiceOuterClass.internal_static_com_alipay_sofa_rpc_protobuf_EchoRequest_descriptor;
		}

		public EchoRequest getDefaultInstanceForType() {
			return EchoRequest.getDefaultInstance();
		}

		public EchoRequest build() {
			EchoRequest result = buildPartial();
			if (!result.isInitialized()) {
				throw newUninitializedMessageException(result);
			}
			return result;
		}

		public EchoRequest buildPartial() {
			EchoRequest result = new EchoRequest(this);
			result.name_ = name_;
			result.group_ = group_;
			onBuilt();
			return result;
		}

		public Builder clone() {
			return (Builder) super.clone();
		}

		public Builder setField(
				com.google.protobuf.Descriptors.FieldDescriptor field,
				Object value) {
			return (Builder) super.setField(field, value);
		}

		public Builder clearField(
				com.google.protobuf.Descriptors.FieldDescriptor field) {
			return (Builder) super.clearField(field);
		}

		public Builder clearOneof(
				com.google.protobuf.Descriptors.OneofDescriptor oneof) {
			return (Builder) super.clearOneof(oneof);
		}

		public Builder setRepeatedField(
				com.google.protobuf.Descriptors.FieldDescriptor field,
				int index, Object value) {
			return (Builder) super.setRepeatedField(field, index, value);
		}

		public Builder addRepeatedField(
				com.google.protobuf.Descriptors.FieldDescriptor field,
				Object value) {
			return (Builder) super.addRepeatedField(field, value);
		}

		public Builder mergeFrom(com.google.protobuf.Message other) {
			if (other instanceof EchoRequest) {
				return mergeFrom((EchoRequest) other);
			} else {
				super.mergeFrom(other);
				return this;
			}
		}

		public Builder mergeFrom(EchoRequest other) {
			if (other == EchoRequest.getDefaultInstance())
				return this;
			if (!other.getName().isEmpty()) {
				name_ = other.name_;
				onChanged();
			}
			if (other.group_ != 0) {
				setGroupValue(other.getGroupValue());
			}
			onChanged();
			return this;
		}

		public final boolean isInitialized() {
			return true;
		}

		public Builder mergeFrom(
				com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			EchoRequest parsedMessage = null;
			try {
				parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
			} catch (com.google.protobuf.InvalidProtocolBufferException e) {
				parsedMessage = (EchoRequest) e.getUnfinishedMessage();
				throw e.unwrapIOException();
			} finally {
				if (parsedMessage != null) {
					mergeFrom(parsedMessage);
				}
			}
			return this;
		}

		private java.lang.Object name_ = "";

		/**
		 * <code>string name = 1;</code>
		 */
		public java.lang.String getName() {
			java.lang.Object ref = name_;
			if (!(ref instanceof java.lang.String)) {
				com.google.protobuf.ByteString bs =
						(com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				name_ = s;
				return s;
			} else {
				return (java.lang.String) ref;
			}
		}

		/**
		 * <code>string name = 1;</code>
		 */
		public com.google.protobuf.ByteString
		getNameBytes() {
			java.lang.Object ref = name_;
			if (ref instanceof String) {
				com.google.protobuf.ByteString b =
						com.google.protobuf.ByteString.copyFromUtf8(
								(java.lang.String) ref);
				name_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		/**
		 * <code>string name = 1;</code>
		 */
		public Builder setName(
				java.lang.String value) {
			if (value == null) {
				throw new NullPointerException();
			}

			name_ = value;
			onChanged();
			return this;
		}

		/**
		 * <code>string name = 1;</code>
		 */
		public Builder clearName() {

			name_ = getDefaultInstance().getName();
			onChanged();
			return this;
		}

		/**
		 * <code>string name = 1;</code>
		 */
		public Builder setNameBytes(
				com.google.protobuf.ByteString value) {
			if (value == null) {
				throw new NullPointerException();
			}
			checkByteStringIsUtf8(value);

			name_ = value;
			onChanged();
			return this;
		}

		private int group_ = 0;

		/**
		 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
		 */
		public int getGroupValue() {
			return group_;
		}

		/**
		 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
		 */
		public Builder setGroupValue(int value) {
			group_ = value;
			onChanged();
			return this;
		}

		/**
		 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
		 */
		public Group getGroup() {
			Group result = Group.valueOf(group_);
			return result == null ? Group.UNRECOGNIZED : result;
		}

		/**
		 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
		 */
		public Builder setGroup(Group value) {
			if (value == null) {
				throw new NullPointerException();
			}

			group_ = value.getNumber();
			onChanged();
			return this;
		}

		/**
		 * <code>.com.alipay.sofa.rpc.protobuf.Group group = 2;</code>
		 */
		public Builder clearGroup() {

			group_ = 0;
			onChanged();
			return this;
		}

		public final Builder setUnknownFields(
				final com.google.protobuf.UnknownFieldSet unknownFields) {
			return this;
		}

		public final Builder mergeUnknownFields(
				final com.google.protobuf.UnknownFieldSet unknownFields) {
			return this;
		}

		// @@protoc_insertion_point(builder_scope:com.alipay.sofa.rpc.protobuf.EchoRequest)
	}

	// @@protoc_insertion_point(class_scope:com.alipay.sofa.rpc.protobuf.EchoRequest)
	private static final EchoRequest DEFAULT_INSTANCE;

	static {
		DEFAULT_INSTANCE = new EchoRequest();
	}

	public static EchoRequest getDefaultInstance() {
		return DEFAULT_INSTANCE;
	}

	private static final com.google.protobuf.Parser<EchoRequest> PARSER = new com.google.protobuf.AbstractParser<EchoRequest>() {
		public EchoRequest parsePartialFrom(
				com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return new EchoRequest(input,
					extensionRegistry);
		}
	};

	public static com.google.protobuf.Parser<EchoRequest> parser() {
		return PARSER;
	}

	@java.lang.Override
	public com.google.protobuf.Parser<EchoRequest> getParserForType() {
		return PARSER;
	}

	public EchoRequest getDefaultInstanceForType() {
		return DEFAULT_INSTANCE;
	}
}
