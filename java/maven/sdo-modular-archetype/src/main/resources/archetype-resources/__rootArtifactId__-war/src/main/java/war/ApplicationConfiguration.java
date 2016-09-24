#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.war;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import it.eng.areas.ems.common.sdo.dto.configuration.DTOConfiguration;
import ${package}.rest.configuration.RestLayerConfiguration;
import it.eng.area118.sdocommon.persistence.configuration.TransactionManagementConfiguration;
import ${package}.dao.*;
import ${package}.dao.impl.*;
import ${package}.service.*;
import ${package}.service.impl.*;
import ${package}.delegate.*;
import ${package}.delegate.impl.*;
import ${package}.rest.service.*;


@Configuration
@Import({RestLayerConfiguration.class, DTOConfiguration.class,TransactionManagementConfiguration.class})
public class ApplicationConfiguration{
	
	@Bean
	public ExampleDelegate getExampleDelegate(){
		return new ExampleDelegateImpl();
	}
	
	@Bean
	public ExampleTransactionalService getService() {
		return new ExampleTransactionalServiceImpl();
	}

	@Bean
	public ExampleDAO getExampleDAO() {
		return new ExampleDAOImpl();
	}		

	@Bean
	public ExampleServiceResource getExampleServiceResource(){
		return new ExampleServiceResource();
	}
}