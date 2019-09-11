package com.nwnu.blockchain.grpc.client;

import com.nwnu.blockchain.protobuf.MistralRequest;
import com.nwnu.blockchain.protobuf.MistralResponse;
import com.nwnu.blockchain.protobuf.MistralServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * MistralClient
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/11     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/11 5:32 PM
 * @since 1.0.0
 */
public class MistralClient {
	private final ManagedChannel channel;
	private final MistralServiceGrpc.MistralServiceBlockingStub blockingStub;

	public MistralClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port)
				.usePlaintext(true)
				.build();

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

	public static void main(String[] args) throws InterruptedException {
		MistralClient client = new MistralClient("127.0.0.1", 8010);
		System.out.println("-------------------客户端开始访问请求-------------------");
		for (int i = 0; i < 10; i++) {
			client.greet("hello: " + i);
		}
	}
}
