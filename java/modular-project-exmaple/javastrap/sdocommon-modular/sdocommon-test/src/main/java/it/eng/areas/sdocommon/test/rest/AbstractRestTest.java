/**
 * 
 */
package it.eng.areas.sdocommon.test.rest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractRestTest {
	/**
	 * We cannot inherit from JerseyTest because it initializes the Application in the constructor before the test
	 * application context is initialized. So we use it!
	 */
	private JerseyTest _jerseyTest;

	public final WebTarget target(final String path) {
		return _jerseyTest.target(path);
	}

	protected abstract Application configure();

	@Before
	public void setup() throws Exception {

		_jerseyTest.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_jerseyTest.tearDown();
	}

	@Autowired
	public void setApplicationContext(final ApplicationContext context) {
//		System.setProperty(TestProperties.LOG_TRAFFIC, "true");
//		System.setProperty(TestProperties.DUMP_ENTITY, "true");		
		_jerseyTest = new JerseyTest() {
			@Override
			protected Application configure() {
				ResourceConfig application = (ResourceConfig) AbstractRestTest.this.configure();

				application.property("contextConfig", context);

				return application;
			}
		};
				
	}

	protected <T> GenericType<List<T>> getListGenericType(Class<T> clazz) {
		ParameterizedType parameterizedGenericType = new ParameterizedType() {
			public Type[] getActualTypeArguments() {
				return new Type[] { clazz };
			}

			public Type getRawType() {
				return List.class;
			}

			public Type getOwnerType() {
				return List.class;
			}
		};
		GenericType<List<T>> genericType = new GenericType<List<T>>(parameterizedGenericType) {
		};
		return genericType;
	}

}
