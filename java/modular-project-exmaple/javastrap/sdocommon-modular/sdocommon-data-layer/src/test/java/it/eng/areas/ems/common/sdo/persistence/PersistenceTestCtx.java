/**
 * 
 */
package it.eng.areas.ems.common.sdo.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import it.eng.area118.sdocommon.persistence.configuration.TwoDbTransactionManagerConfiguration;
import it.eng.areas.ems.common.sdo.dao.ExampleDAO;
import it.eng.areas.ems.common.sdo.dao.impl.ExampleDAOImpl;
import it.eng.areas.ems.common.sdo.service.AnotherTransactionalService;
import it.eng.areas.ems.common.sdo.service.ExampleTransactionalService;
import it.eng.areas.ems.common.sdo.service.impl.AnotherTransactionalServiceImpl;
import it.eng.areas.ems.common.sdo.service.impl.ExampleTransactionalServiceImpl;

/**
 * @author Bifulco Luigi
 *
 */
@PropertySources(//
		value = { //
				@PropertySource(ignoreResourceNotFound = false, //
						value = "classpath:META-INF/test/cfg-hsqldb-jpa.properties") //
		})
@Import(TwoDbTransactionManagerConfiguration.class)
@Configuration
public class PersistenceTestCtx {

	@Bean
	ExampleTransactionalService getService() {
		return new ExampleTransactionalServiceImpl();
	}

	@Bean
	ExampleDAO getExampleDAO() {
		return new ExampleDAOImpl();
	}

	@Bean
	AnotherTransactionalService getAnotherService() {
		return new AnotherTransactionalServiceImpl();
	}

}
