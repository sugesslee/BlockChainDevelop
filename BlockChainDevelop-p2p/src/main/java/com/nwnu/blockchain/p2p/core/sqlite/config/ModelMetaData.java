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

//通过EntityManager获取factory
//		EntityManagerFactory entityManagerFactory = factory.getEntityManagerFactory();
		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) factory.unwrap(SessionFactory.class);
		Map<String, EntityPersister> persisterMap = sessionFactory.getEntityPersisters();
//		Map<String, EntityPersister> persisterMap = factory.getMetamodel().getManagedTypes();
		Map<String, Class> map = new HashMap<>(persisterMap.size());

		for (Map.Entry<String, EntityPersister> entity : persisterMap.entrySet()) {
			Class targetClass = entity.getValue().getMappedClass();
			SingleTableEntityPersister persister = (SingleTableEntityPersister) entity.getValue();
			Iterable<AttributeDefinition> attributes = persister.getAttributes();
//			String entityName = targetClass.getSimpleName();//Entity的名称
			String tableName = persister.getTableName();//Entity对应的表的英文名

			map.put(tableName, targetClass);
//			System.out.println("类名：" + entityName + " => 表名：" + tableName);
//
//			//属性
//			for(AttributeDefinition attr : attributes){
//				String propertyName = attr.getName(); //在entity中的属性名称
//				String[] columnName = persister.getPropertyColumnNames(propertyName); //对应数据库表中的字段名
//				String type = "";
//				PropertyDescriptor targetPd = BeanUtils.getPropertyDescriptor(targetClass, propertyName);
//				if(targetPd != null){
//					type = targetPd.getPropertyType().getSimpleName();
//				}
//				System.out.println("属性名：" + propertyName + " => 类型：" + type + " => 数据库字段名：" + columnName[0]);
//			}

		}
//		Map<String, Class> map = new HashMap<>();
//		return map.put(factory.getMetamodel().toString(), factory.getMetamodel().getClass());

//		SessionFactory sessionFactory = factory.unwrap(SessionFactory.class);
//
//		Map<String, ClassMetadata> metaMap = sessionFactory.getAllClassMetadata();
////		factory.getMetamodel();
////		MetamodelImplementor metamodel = (MetamodelImplementor) sessionFactory.getMetamodel();
////		ClassMetadata classMetadata = (ClassMetadata) metamodel.entityPersister(entityName);
//		Map<String, Class> map = new HashMap<>(metaMap.size());
//		for (String key : metaMap.keySet()) {
//			AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap
//					.get(key);
//			String tableName = classMetadata.getTableName().toLowerCase();
//			int index = tableName.indexOf(".");
//			if (index >= 0) {
//				tableName = tableName.substring(index + 1);
//			}
//			map.put(tableName, Class.forName(key));
//		}
		return map;
	}

}
