#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import it.eng.areas.sdocommon.test.rest.AbstractRestTest;

import ${package}.rest.configuration.RestLayerConfiguration;
import ${package}.rest.service.ExampleServiceResource;
import ${package}.rest.model.*;




/**
 * Questa classe rappresenta una suite di test per il rest layer che utilizza il JerseyTest la calsse {@link JerseyTest}
 * offre molte cose già  fatte per creare le Junit per i rest service
 * 
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestLayerTestCtx.class)
public class ExampleServiceJerseyTest extends AbstractRestTest {

	

	
	protected Application configure() {		
		return new RestApplication();

	}

	@Test
	public final void testTestMethod() {		
		final WebTarget target = target("exampleService/test");
		Response response = target.request().get();		
		String res = (String) response.readEntity(String.class);
		Assert.assertTrue(response.getStatus() == 200);
		Assert.assertTrue(res.equals("Got it!"));
	}
	
	@Test
	public final void testRequestResponnse(){
		final WebTarget target = target("exampleService/requestOutput");
		ExampleInput request =  new ExampleInput();
		request.setField1("1");
		request.setField2("2");
		Entity<ExampleInput> reqEntity = Entity.entity(request, MediaType.APPLICATION_JSON);
		Response response = target.request().post(reqEntity);
		ExampleOutput dto = response.readEntity(ExampleOutput.class);
		Assert.assertTrue(dto.getField1().equals("1"));
		Assert.assertTrue(dto.getField2().equals("2"));		
	}

	

}
