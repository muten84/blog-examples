#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.test;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.areas.ems.common.sdo.dto.DTOService;
import ${package}.delegate.*;
import ${package}.delegate.model.*;
import ${package}.service.*;
import ${package}.service.impl.*;

/**
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExampleDelegateTestCtx.class)
public class ExampleDelegateTestSuite {

	@Autowired
	private ExampleDelegate delegate;
	
	@Autowired
	private ExampleTransactionalService service;

	@Test
	public final void testDTOObjectConversion() {		
		EasyMock.expect(service.getExampleById(EasyMock.anyString())).andReturn(ExampleDelegateTestHelper.getData("TEST"));
		EasyMock.replay(service);
		ExampleDTO dto = delegate.getExampleById("TEST");
		Assert.assertTrue(dto.getId().equals("TEST"));
		EasyMock.reset(service);
	}
	
	
}
