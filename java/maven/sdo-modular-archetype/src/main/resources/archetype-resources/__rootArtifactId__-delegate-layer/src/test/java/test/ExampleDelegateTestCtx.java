#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.test;

import org.springframework.context.annotation.Import;
import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;

import it.eng.areas.ems.common.sdo.dto.configuration.DTOConfiguration;
import ${package}.delegate.*;
import ${package}.delegate.impl.*;
import org.easymock.EasyMock;
import ${package}.service.*;
import ${package}.service.impl.*;

/**
 * @author Bifulco Luigi
 *
 */
@Import(DTOConfiguration.class)
public class ExampleDelegateTestCtx {

	@Bean
	public ExampleDelegate getExampleDelegate(){
		return new ExampleDelegateImpl();
	}
	
	@Bean
	public ExampleTransactionalService getExampleService(){
		return EasyMock.createMock(ExampleTransactionalService.class);
	}
	
	
}
