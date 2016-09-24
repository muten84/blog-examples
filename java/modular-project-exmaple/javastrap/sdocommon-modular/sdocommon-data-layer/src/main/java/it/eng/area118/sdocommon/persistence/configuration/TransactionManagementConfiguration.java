/**
 * 
 */
package it.eng.area118.sdocommon.persistence.configuration;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import it.eng.area118.sdocommon.datasource.RoutingDataSource;
import it.eng.area118.sdocommon.datasource.constant.DataSourceType;

/**
 * TransactionManagementConfiguration enables developers to configure the application context with a data layer to build
 * transactional services within transactional context. The main bean configured for the container are the transaction
 * manager, the data source the connection pool. It relies on jpa 2.1, hibernate and c3p0 and the RoutingDataSource
 * system to manage more than one data source the {@link TwoDbTransactionManagerConfiguration} is a specializazion of
 * this configuration to manage two specific data sources.
 * 
 * @author Bifulco Luigi
 * 
 */
@Configuration
@EnableTransactionManagement
@Import(PersistenceConfig.class)
// @ComponentScan(basePackages = { "it.eng.area118.*", "it.eng.areas.ems.*" })
public class TransactionManagementConfiguration {

	@Autowired
	protected PersistenceConfigBean config;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public ComboPooledDataSource defaultDs() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(config.getDataSourceDriverClassName());
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		dataSource.setJdbcUrl(config.getDefaultDataSourceUrl());
		dataSource.setUser(config.getDefaultDataSourcUsername());
		dataSource.setPassword(config.getDefaultDataSourcePassword());
		return dataSource;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	@Scope("singleton")
	public DataSource router() {
		RoutingDataSource ds = new RoutingDataSource();
		Map map = new HashMap();
		map.put(DataSourceType.DEFAULT, defaultDs());
		// map.put(DataSourceType.STORICO, historicalDs());
		ds.setTargetDataSources(map);
		ds.setDefaultTargetDataSource(defaultDs());
		return ds;
	}

	@Bean
	public HibernateJpaVendorAdapter jpaAdapter() {
		HibernateJpaVendorAdapter ad = new HibernateJpaVendorAdapter();
		ad.setDatabasePlatform(config.getJpaDatabasePlatform());
		ad.setShowSql(true);
		return ad;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	@Autowired
	public EntityManagerFactory emf() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(router());
		factoryBean.setJpaVendorAdapter(jpaAdapter());
		factoryBean.setPersistenceUnitName("default");
		factoryBean.setPersistenceXmlLocation(config.getPersistenceXmlLocation());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager(emf());
		txManager.setDataSource(router());
		txManager.setNestedTransactionAllowed(true);
		return txManager;
	}

	@Bean
	Properties hibernateProperties() {
		return new Properties() {
			{
				// setProperty("hibernate.hbm2ddl.auto", "none");
				setProperty("hibernate.dialect", config.getJpaDatabasePlatform());
				setProperty("hibernate.globally_quoted_identifiers", "false");
				setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
				setProperty("hibernate.cache.use_second_level_cache", "false");
				setProperty("hibernate.cache.use_query_cache", "false");
				// setProperty("hibernate.cache.provider_class","");
				setProperty("hibernate.generate_statistics", "false");
				setProperty("hibernate.use_sql_comments", "false");
				setProperty("hibernate.hql.bulk_id_strategy.global_temporary.drop_tables", "true");
				setProperty("hibernate.hql.bulk_id_strategy", config.getBulkIdStrategy());

			}
		};
	}
}
