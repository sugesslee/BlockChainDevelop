package com.nwnu.blockchain.p2p.rest;

import com.alipay.sofa.rpc.api.context.RpcContextManager;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RestServiceImpl
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 10:32 AM
 * @since 1.0.0
 */
@Slf4j
public class RestServiceImpl implements RestService {
	private final static Map<Integer, String> db = new ConcurrentHashMap<Integer, String>();

	@Override
	public String add(int code, String name) {
		log.info("post code is " + code + ", name is " + name);
		db.put(code, name);
		return "create ok !" + code;
	}

	@Override
	public String query(int code) {
		//String remote = RpcContextManager.currentServiceContext(false).getCallerUrl();
		//LOGGER.info("remote:" + remote + " get code:" + code);
		return "hello world !" + db.get(code);
	}

	@Override
	public String query(int code, String name) {
		String remote = RpcContextManager.currentServiceContext(false).getCallerUrl();
		log.info("remote:" + remote + " get code:" + code + ", name is: " + name);
		return "hello world !" + db.get(code);
	}

	public Response update(int code, String name) {
		log.info("put code is " + code + ", name is " + name);
		db.put(code, name);
		String result = "update ok !" + code;
		return Response.status(200).entity(result).build();
	}

	@Override
	public String delete(int code) {
		log.info("delete code:" + code);
		return db.remove(code);
	}

	@Override
	public ExampleObj object(ExampleObj code) {
		code.setName(code.getName() + " server");
		return code;
	}

	@Override
	public List<ExampleObj> objects(List<ExampleObj> codes) {
		for (ExampleObj code : codes) {
			code.setName(code.getName() + " server");
		}
		return codes;
	}

	@Override
	public String get(String code) {
		return "server" + code;
	}

	@Override
	public String post(String code, String body) {
		return "server " + code + body;
	}
}
