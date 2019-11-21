package com.nwnu.blockchain.repository.sqlite.config;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuilderInitializer;
import org.hibernate.engine.jdbc.dialect.internal.DialectResolverSet;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.jboss.logging.Logger;

/**
 * SQLite工具
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
public class SQLiteMetadataBuilderInitializer implements MetadataBuilderInitializer {

	private final static Logger logger = Logger.getLogger(SQLiteMetadataBuilderInitializer.class);

	@Override
	public void contribute(MetadataBuilder metadataBuilder, StandardServiceRegistry serviceRegistry) {
		DialectResolver dialectResolver = serviceRegistry.getService(DialectResolver.class);

		if (!(dialectResolver instanceof DialectResolverSet)) {
			logger.warnf("DialectResolver '%s' is not an instance of DialectResolverSet, not registering SQLiteDialect",
					dialectResolver);
			return;
		}

		((DialectResolverSet) dialectResolver).addResolver(resolver);
	}

	static private final SQLiteDialect dialect = new SQLiteDialect();

	static private final DialectResolver resolver = (DialectResolver) info -> {
		if (info.getDatabaseName().equals("SQLite")) {
			return dialect;
		}

		return null;
	};
}