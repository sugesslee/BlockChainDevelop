package com.nwnu.blockchain.repository.sqlite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;


/**
 * JpaConfiguration
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:39 PM
 * @since 1.0.0
 */
@Configuration
@EnableJpaRepositories(
		basePackages = "com.nwnu.blockchain.repository.sqlite.repository",
		transactionManagerRef = "jpaTransactionManager",
		entityManagerFactoryRef = "localContainerEntityManagerFactoryBean"
)
@EnableTransactionManagement
public class JpaConfiguration {
	@Resource
	private JpaProperties jpaProperties;

	@Autowired
	@Bean
	public JpaTransactionManager jpaTransactionManager(@Qualifier(value = "EmbeddeddataSource") DataSource
															   dataSource, EntityManagerFactory
															   entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager
				= new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		jpaTransactionManager.setDataSource(dataSource);

		return jpaTransactionManager;
	}

	@Autowired
	@Bean
	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(@Qualifier(value =
			"EmbeddeddataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource)
				.packages("com.nwnu.blockchain.core.model")
				.properties(getVendorProperties(dataSource))
				.build();
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getProperties();
	}
}