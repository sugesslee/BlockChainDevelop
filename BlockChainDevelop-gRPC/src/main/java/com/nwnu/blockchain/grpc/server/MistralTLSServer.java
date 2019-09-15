package com.nwnu.blockchain.grpc.server;

import com.nwnu.blockchain.protobuf.MistralRequest;
import com.nwnu.blockchain.protobuf.MistralResponse;
import com.nwnu.blockchain.protobuf.MistralServiceGrpc;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * MistralTLSServer
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/13     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/13 11:03 AM
 * @since 1.0.0
 */

/**
 * openssl genrsa -passout pass:111111 -des3 -out ca.key 4096
 * openssl req -passin pass:111111 -new -x509 -days 365 -key ca.key -out ca.crt -subj "/CN=localhost"
 * openssl genrsa -passout pass:111111 -des3 -out server.key 4096
 * openssl req -passin pass:111111 -new -key server.key -out server.csr -subj "/CN=localhost"
 * openssl x509 -req -passin pass:111111 -days 365 -in server.csr -CA ca.crt -CAkey ca.key -set_serial 01 -out server.crt
 * openssl rsa -passin pass:111111 -in server.key -out server.key
 * openssl genrsa -passout pass:111111 -des3 -out client.key 4096
 * openssl req -passin pass:111111 -new -key client.key -out client.csr -subj "/CN=localhost"
 * openssl x509 -passin pass:111111 -req -days 365 -in client.csr -CA ca.crt -CAkey ca.key -set_serial 01 -out client.crt
 * openssl rsa -passin pass:111111 -in client.key -out client.key
 * openssl pkcs8 -topk8 -nocrypt -in client.key -out client.pem
 * openssl pkcs8 -topk8 -nocrypt -in server.key -out server.pem
 */
@Slf4j
public class MistralTLSServer {
	private Server server;

	private final String host;
	private final int port;
	private final String certChainFilePath;
	private final String privateKeyFilePath;
	private final String trustCertCollectionFilePath;

	MistralTLSServer(String host,
					 int port,
					 String certChainFilePath,
					 String privateKeyFilePath,
					 String trustCertCollectionFilePath) {
		this.host = host;
		this.port = port;
		this.certChainFilePath = certChainFilePath;
		this.privateKeyFilePath = privateKeyFilePath;
		this.trustCertCollectionFilePath = trustCertCollectionFilePath;
	}

	private SslContextBuilder getSslContextBuilder() {
		SslContextBuilder sslClientContextBuilder = SslContextBuilder.forServer(new File(certChainFilePath),
				new File(privateKeyFilePath));
		if (trustCertCollectionFilePath != null) {
			sslClientContextBuilder.trustManager(new File(trustCertCollectionFilePath));
			sslClientContextBuilder.clientAuth(ClientAuth.REQUIRE);
		}
		return GrpcSslContexts.configure(sslClientContextBuilder,
				SslProvider.OPENSSL);
	}

	public void start() throws IOException {
		server = NettyServerBuilder.forAddress(new InetSocketAddress(host, port))
				.addService((BindableService) new MistralHelloWorldImpl())
				.sslContext(getSslContextBuilder().build())
				.build()
				.start();

		log.info("server on address: {}, port: {}.", host, port);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				MistralTLSServer.this.stop();
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
		final MistralTLSServer server = new MistralTLSServer("localhost", 8081,
				"/Users/red/Desktop/workspace/project/java/BlockChainDevelop/BlockChainDevelop-gRPC/openssl-keys/server.crt",
				"/Users/red/Desktop/workspace/project/java/BlockChainDevelop/BlockChainDevelop-gRPC/openssl-keys/server.pem",
				null);
		//启动服务
		server.start();
		//服务一直在线，不关闭
		server.blockUntilShutdown();
	}

	// 定义一个实现服务接口的类
	private static class MistralHelloWorldImpl extends MistralServiceGrpc.MistralServiceImplBase {

		@Override
		public void sayHello(MistralRequest mistralRequest, StreamObserver<MistralResponse> responseObserver) {
			// 具体其他丰富的业务实现代码
			System.err.println("server:" + mistralRequest.getName());
			MistralResponse reply = MistralResponse.newBuilder().setMessage(("响应信息: " + mistralRequest.getName()))
					.build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
	}
}
