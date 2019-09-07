package com.nwnu.blockchain.rpc.rest;

import com.alipay.sofa.rpc.common.json.JSON;
import com.nwnu.blockchain.rpc.rest.ExampleObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;

/**
 * HttpApacheClientMain
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 10:57 AM
 * @since 1.0.0
 */
@Slf4j
public class HttpApacheClientMainTest {

	/**
	 * 注意：windows下服务端若未指定绑定到所有网卡0.0.0.0，则本机客户端是不能直接使用127.0.0.1访问的。
	 * 请查看服务端启动日志，看具体绑定的网卡和端口是哪个：Server have success bind to 10.23.11.22:11090
	 * 例如 http://10.23.11.22:11090
	 */
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8888/rest/post/1234567890";
		Object[] params = new Object[]{"xxhttpxxx"};
		String result = sendByPost(url, params);
		log.info("result : {}", result);

		url = "http://127.0.0.1:8888/rest/object";
		ExampleObj example = new ExampleObj();
		example.setId(100);
		example.setName("name_name");
		params = new Object[]{example};
		result = sendByPost(url, params);
		ExampleObj objResult = JSON.parseObject(result, ExampleObj.class);
		log.info("obj result : {}", objResult);

	}

	private static String sendByPost(String url, Object[] params) {
		String response = "";
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httPost = new HttpPost(url);

			httPost.setHeader("token", "1qaz2wsx"); // 服务端需要token
			String json = JSON.toJSONString(params[0]);
			StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
			entity.setContentType("application/json");
			httPost.setEntity(entity);
			HttpResponse httpResponse = null;

			httpResponse = httpclient.execute(httPost);
			HttpEntity responseEntity = httpResponse.getEntity();
			log.info("response status: " + httpResponse.getStatusLine());
			response = EntityUtils.toString(responseEntity);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
