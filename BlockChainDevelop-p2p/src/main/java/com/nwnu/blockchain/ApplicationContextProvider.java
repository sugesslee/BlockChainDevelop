package com.nwnu.blockchain;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextProvider
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 11:03 AM
 * @since 1.0.0
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		context = ac;
	}

	public static <T> T getBean(Class<T> tClass) {
		return context.getBean(tClass);
	}

	public static <T> T getBean(String name, Class<T> tClass) {
		return context.getBean(name, tClass);
	}

	public static void publishEvent(ApplicationEvent event) {
		context.publishEvent(event);
	}
}
