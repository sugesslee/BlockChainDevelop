package com.nwnu.blockchain.p2p.core.sqlite.config;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Metamodel;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * 构建一个存放表名和model实体class的对应关系，如account_entity:AccountEntity.class
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
@AutoConfigureAfter(JpaConfiguration.class)
public class ModelMetaData {

	@Bean(name = "metaMap")
	public Map<String, Class> metaMap(EntityManagerFactory factory) throws ClassNotFoundException {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}

		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) factory.unwrap(SessionFactory.class);
		MetamodelImplementor metamodelImplementor = sessionFactory.getMetamodel();
		Map<String, EntityPersister> persisterMap = metamodelImplementor.entityPersisters();
		Map<String, Class> map = new HashMap<>(persisterMap.size());

		for (Map.Entry<String, EntityPersister> entity : persisterMap.entrySet()) {
			Class targetClass = entity.getValue().getMappedClass();
			SingleTableEntityPersister persister = (SingleTableEntityPersister) entity.getValue();
			String tableName = persister.getTableName();//Entity对应的表的英文名

			map.put(tableName, targetClass);
		}
		return map;
	}
}
