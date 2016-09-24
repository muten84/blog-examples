#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.rest;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import it.eng.areas.ems.common.sdo.dto.configuration.DTOConfiguration;
import ${package}.service.*;
import ${package}.delegate.*;
import ${package}.delegate.impl.*;
import ${package}.rest.service.*;


@Configuration
@Import(DTOConfiguration.class)
public class RestLayerTestCtx {

	@Bean
	public ExampleDelegate getExampleDelegate(){
		return new ExampleDelegateImpl();
	}
	
	@Bean
	public ExampleTransactionalService getExampleService(){
		return EasyMock.createMock(ExampleTransactionalService.class);
	}

	@Bean
	public ExampleServiceResource getExampleServiceResource(){
		return new ExampleServiceResource();
	}

}
