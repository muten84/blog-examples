/**
 * 
 */
package it.eng.area118.sdocommon.persistence.configuration;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import it.eng.area118.sdocommon.datasource.RoutingDataSource;
import it.eng.area118.sdocommon.datasource.constant.DataSourceType;

/**
 * @author Bifulco Luigi
 *
 */
@Configuration
@EnableTransactionManagement
@Import(TwoDbConfiguration.class)
// @ComponentScan(basePackages = { "it.eng.area118.*", "it.eng.areas.ems.*" })
public class TwoDbTransactionManagerConfiguration extends TransactionManagementConfiguration {

	ComboPooledDataSource historicalDs() {
		// com.mchange.v2.c3p0.ComboPooledDataSource
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(config.getDataSourceDriverClassName());
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		dataSource.setJdbcUrl(config.getStoricoDataSourceUrl());
		dataSource.setUser(config.getStoricoDataSourcUsername());
		dataSource.setPassword(config.getStoricoDataSourcePassword());
		return dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.area118.sdocommon.persistence.configuration.TransactionManagementConfiguration#router()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public RoutingDataSource router() {
		RoutingDataSource ds = new RoutingDataSource();
		Map map = new HashMap();
		map.put(DataSourceType.DINAMICO, defaultDs());
		map.put(DataSourceType.STORICO, historicalDs());
		ds.setTargetDataSources(map);
		ds.setDefaultTargetDataSource(defaultDs());
		return ds;
	}

	// @Bean
	// public DataSource historicalDs() {
	// // com.mchange.v2.c3p0.ComboPooledDataSource
	// ComboPooledDataSource dataSource = new ComboPooledDataSource();
	// try {
	// dataSource.setDriverClass(dataSourceDriverClassName);
	// } catch (PropertyVetoException e) {
	// throw new RuntimeException(e);
	// }
	// dataSource.setJdbcUrl(storicoDataSourceUrl);
	// dataSource.setUser(storicoDataSourcUsername);
	// dataSource.setPassword(storicoDataSourcePassword);
	// return dataSource;
	// }

}
