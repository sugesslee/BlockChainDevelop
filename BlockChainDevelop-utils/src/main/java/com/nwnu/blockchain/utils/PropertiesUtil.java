package com.nwnu.blockchain.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * ${desc}
 * <pre>
 *  Version         Date            Author          Description
 * ---------------------------------------------------------------------------------------
 *  1.0.0           2018/10/13     redli        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2018/10/13 13:16
 * @date 2018/10/13 13:16
 * @since 1.0.0
 */
@Slf4j
public class PropertiesUtil {
	private static Properties props;

	static {
		String fileName = "block.properties";
		props = new Properties();
		try {
			props.load(new InputStreamReader(
					Objects.requireNonNull(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)),
					StandardCharsets.UTF_8));
		} catch (IOException e) {
			log.error("配置文件读取异常", e);
		}
	}

	public static String getProperty(String key) {
		String value = props.getProperty(key.trim());
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return value.trim();
	}

	public static String getProperty(String key, String defaultValue) {

		String value = props.getProperty(key.trim());
		if (StringUtils.isBlank(value)) {
			value = defaultValue;
		}
		return value.trim();
	}
}
