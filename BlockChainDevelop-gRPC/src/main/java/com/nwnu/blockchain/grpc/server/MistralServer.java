package com.nwnu.blockchain.grpc.server;

import com.nwnu.blockchain.protobuf.MistralRequest;
import com.nwnu.blockchain.protobuf.MistralResponse;
import com.nwnu.blockchain.protobuf.MistralServiceGrpc;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;


/**
 * MistralServer
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/11     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/11 7:55 PM
 * @since 1.0.0
 */
public class MistralServer {
	private int port = 8010;
	private Server server;

	private void start() throws IOException {
		server = ServerBuilder.forPort(port)
				.addService((BindableService) new MistralHelloWorldImpl())
				.build()
				.start();

		System.out.println("------------------- 服务端服务已开启，等待客户端访问 -------------------");

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {

				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				MistralServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		final MistralServer server = new MistralServer();
		//启动服务
		server.start();
		//服务一直在线，不关闭
		server.blockUntilShutdown();
	}

	// 定义一个实现服务接口的类
	private class MistralHelloWorldImpl extends MistralServiceGrpc.MistralServiceImplBase {

		@Override
		public void sayHello(MistralRequest mistraRequest, StreamObserver<MistralResponse> responseObserver) {
			// 具体其他丰富的业务实现代码
			System.err.println("server:" + mistraRequest.getName());
			MistralResponse reply = MistralResponse.newBuilder().setMessage(("响应信息: " + mistraRequest.getName())).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
	}
}
