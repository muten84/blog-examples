#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import it.eng.area118.sdocommon.persistence.configuration.TransactionManagementConfiguration;
import it.eng.area118.sdocommon.persistence.configuration.TwoDbTransactionManagerConfiguration;
import ${package}.dao.ExampleDAO;
import ${package}.dao.impl.ExampleDAOImpl;
import ${package}.service.ExampleTransactionalService;
import ${package}.service.impl.ExampleTransactionalServiceImpl;

@PropertySources(//
		value = { //
				@PropertySource(ignoreResourceNotFound = false, //
						value = "classpath:${packageInPathFormat}/test-context/cfg-hsqldb-jpa.properties") //
		})
@Import(TransactionManagementConfiguration.class)
@Configuration
public class ExamplePersistenceTestCtx {

	@Bean
	ExampleTransactionalService getService() {
		return new ExampleTransactionalServiceImpl();
	}

	@Bean
	ExampleDAO getExampleDAO() {
		return new ExampleDAOImpl();
	}

}