/**
 * 
 */
package it.eng.area118.sdocommon.persistence.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Bifulco Luigi
 *
 */

@Configuration
public class PersistenceConfig {

	@Value("${dataSource.driverClassName}")
	protected String dataSourceDriverClassName;

	@Value("${defaultDataSource.url}")
	protected String defaultDataSourceUrl;

	@Value("${defaultDataSource.username}")
	protected String defaultDataSourcUsername;

	@Value("${defaultDataSource.password}")
	protected String defaultDataSourcePassword;

	@Value("${jpa.databasePlatform}")
	protected String jpaDatabasePlatform;

	@Value("${jpa.persistenceXmlLocation}")
	protected String persistenceXmlLocation;

	@Value("${hibernate.bulkIdStrategy}")
	protected String bulkIdStrategy;

	@Bean
	public PersistenceConfigBean getBean() {
		return createPersistenceConfiguration();
	}

	protected PersistenceConfigBean createPersistenceConfiguration() {
		PersistenceConfigBean bean = new PersistenceConfigBean();
		bean.setDataSourceDriverClassName(dataSourceDriverClassName);
		bean.setDefaultDataSourcePassword(defaultDataSourcePassword);
		bean.setDefaultDataSourceUrl(defaultDataSourceUrl);
		bean.setDefaultDataSourcUsername(defaultDataSourcUsername);
		bean.setJpaDatabasePlatform(jpaDatabasePlatform);
		bean.setPersistenceXmlLocation(persistenceXmlLocation);
		bean.setBulkIdStrategy(bulkIdStrategy);
		return bean;
	}

}
