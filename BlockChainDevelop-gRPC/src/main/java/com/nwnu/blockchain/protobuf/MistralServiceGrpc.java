package com.nwnu.blockchain.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *声明一个服务名称
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.11.0)",
    comments = "Source: hello_world.proto")
public final class MistralServiceGrpc {

  private MistralServiceGrpc() {}

  public static final String SERVICE_NAME = "com.nwnu.blockchain.protobuf.MistralService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getSayHelloMethod()} instead.
  public static final io.grpc.MethodDescriptor<MistralRequest,
      MistralResponse> METHOD_SAY_HELLO = getSayHelloMethodHelper();

  private static volatile io.grpc.MethodDescriptor<MistralRequest,
      MistralResponse> getSayHelloMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<MistralRequest,
      MistralResponse> getSayHelloMethod() {
    return getSayHelloMethodHelper();
  }

  private static io.grpc.MethodDescriptor<MistralRequest,
      MistralResponse> getSayHelloMethodHelper() {
    io.grpc.MethodDescriptor<MistralRequest, MistralResponse> getSayHelloMethod;
    if ((getSayHelloMethod = MistralServiceGrpc.getSayHelloMethod) == null) {
      synchronized (MistralServiceGrpc.class) {
        if ((getSayHelloMethod = MistralServiceGrpc.getSayHelloMethod) == null) {
          MistralServiceGrpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<MistralRequest, MistralResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.nwnu.blockchain.protobuf.MistralService", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MistralRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MistralResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MistralServiceMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MistralServiceStub newStub(io.grpc.Channel channel) {
    return new MistralServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MistralServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MistralServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MistralServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MistralServiceFutureStub(channel);
  }

  /**
   * <pre>
   *声明一个服务名称
   * </pre>
   */
  public static abstract class MistralServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *请求参数MistralRequest   响应参数MistralResponse
     * </pre>
     */
    public void sayHello(MistralRequest request,
						 io.grpc.stub.StreamObserver<MistralResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethodHelper(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                MistralRequest,
                MistralResponse>(
                  this, METHODID_SAY_HELLO)))
          .build();
    }
  }

  /**
   * <pre>
   *声明一个服务名称
   * </pre>
   */
  public static final class MistralServiceStub extends io.grpc.stub.AbstractStub<MistralServiceStub> {
    private MistralServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MistralServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MistralServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MistralServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *请求参数MistralRequest   响应参数MistralResponse
     * </pre>
     */
    public void sayHello(MistralRequest request,
						 io.grpc.stub.StreamObserver<MistralResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *声明一个服务名称
   * </pre>
   */
  public static final class MistralServiceBlockingStub extends io.grpc.stub.AbstractStub<MistralServiceBlockingStub> {
    private MistralServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MistralServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MistralServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MistralServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *请求参数MistralRequest   响应参数MistralResponse
     * </pre>
     */
    public MistralResponse sayHello(MistralRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *声明一个服务名称
   * </pre>
   */
  public static final class MistralServiceFutureStub extends io.grpc.stub.AbstractStub<MistralServiceFutureStub> {
    private MistralServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MistralServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MistralServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MistralServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *请求参数MistralRequest   响应参数MistralResponse
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MistralResponse> sayHello(
        MistralRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MistralServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MistralServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((MistralRequest) request,
              (io.grpc.stub.StreamObserver<MistralResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MistralServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MistralServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return MistralProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MistralService");
    }
  }

  private static final class MistralServiceFileDescriptorSupplier
      extends MistralServiceBaseDescriptorSupplier {
    MistralServiceFileDescriptorSupplier() {}
  }

  private static final class MistralServiceMethodDescriptorSupplier
      extends MistralServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MistralServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MistralServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MistralServiceFileDescriptorSupplier())
              .addMethod(getSayHelloMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
