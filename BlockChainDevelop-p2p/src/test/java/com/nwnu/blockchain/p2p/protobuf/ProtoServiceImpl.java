package com.nwnu.blockchain.p2p.protobuf;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/06     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/6 11:13 AM
 * @since 1.0.0
 */
public class ProtoServiceImpl implements ProtoService {

	private int sleep;

	private String result;

	private AtomicInteger counter = new AtomicInteger();

	public ProtoServiceImpl() {

	}

	public ProtoServiceImpl(String result) {
		this.result = result;
	}

	public ProtoServiceImpl(int sleep) {
		this.sleep = sleep;
	}

	@Override
	public EchoResponse echoObj(EchoRequest req) {
		if (sleep > 0) {
			try {
				Thread.sleep(sleep);
			} catch (Exception ignore) { // NOPMD
			}
		}
		counter.incrementAndGet();
		EchoResponse response = EchoResponse.newBuilder()
				.setCode(200)
				.setMessage(result != null ? result : "protobuf works! " + req.getName())
				.build();
		return response;
	}

	public AtomicInteger getCounter() {
		return counter;
	}
}