package com.nwnu.blockchain.grpc.client;

import com.nwnu.blockchain.protobuf.MistralRequest;
import com.nwnu.blockchain.protobuf.MistralResponse;
import com.nwnu.blockchain.protobuf.MistralServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * MistralTLSClient
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/13     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/13 11:27 AM
 * @since 1.0.0
 */
public class MistralTLSClient {
	private final ManagedChannel channel;
	private final MistralServiceGrpc.MistralServiceBlockingStub blockingStub;

	public MistralTLSClient(String host,
							int port,
							SslContext sslContext) throws SSLException {
		this(NettyChannelBuilder.forAddress(host, port)
				.negotiationType(NegotiationType.TLS)
				.sslContext(sslContext)
				.build());
	}

	private MistralTLSClient(ManagedChannel channel) {
		this.channel = channel;
		blockingStub = MistralServiceGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public void greet(String name) {
		MistralRequest request = MistralRequest.newBuilder().setName(name).build();
		MistralResponse response = blockingStub.sayHello(request);
		System.out.println(response.getMessage());
	}

	private static SslContext buildSslContext(String trustCertCollectionFilePath,
											  String clientCertChainFilePath,
											  String clientPrivateKeyFilePath) throws SSLException {
		SslContextBuilder builder = GrpcSslContexts.forClient();
		if (trustCertCollectionFilePath != null) {
			builder.trustManager(new File(trustCertCollectionFilePath));
		}
		if (clientCertChainFilePath != null && clientPrivateKeyFilePath != null) {
			builder.keyManager(new File(clientCertChainFilePath), new File(clientPrivateKeyFilePath));
		}
		return builder.build();
	}

	public static void main(String[] args) throws InterruptedException, SSLException {
		MistralTLSClient client = new MistralTLSClient("localhost", 8081,
				buildSslContext(
						"/Users/red/Desktop/workspace/project/java/BlockChainDevelop/BlockChainDevelop-gRPC/openssl-keys/ca.crt",
						"/Users/red/Desktop/workspace/project/java/BlockChainDevelop/BlockChainDevelop-gRPC/openssl-keys/client.crt",
						"/Users/red/Desktop/workspace/project/java/BlockChainDevelop/BlockChainDevelop-gRPC/openssl-keys/client.pem"));
		System.out.println("-------------------客户端开始访问请求-------------------");
		for (int i = 0; i < 10; i++) {
			client.greet("hello: " + i);
		}
	}
}
