package com.nwnu.blockchain.rpc.protobuf;

import com.nwnu.blockchain.rpc.test.EchoService;

/**
 * protoservice
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
public interface ProtoService {
	EchoResponse echoObj(EchoRequest req);
}
