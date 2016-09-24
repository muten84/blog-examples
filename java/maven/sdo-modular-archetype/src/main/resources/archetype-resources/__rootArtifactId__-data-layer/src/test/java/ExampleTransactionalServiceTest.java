#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import ${package}.entity.ExampleDO;
import ${package}.service.ExampleTransactionalService;

/**
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath:${packageInPathFormat}/test-context/test-ctx.xml")
public class ExampleTransactionalServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static EmbeddedDatabase db;

	@Autowired
	private ExampleTransactionalService service;

	@BeforeClass
	public static void createCommonDb() {
		String staticBase = "${packageInPathFormat}/test-context/common/sql/";
		String dynBase = "${packageInPathFormat}/test-context/common/sql/";
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setName("testdb");
		db = builder.setType(EmbeddedDatabaseType.HSQL) // .H2
																			// or
																			// .DERBY
				.setName("testdb")//
				.addScript(staticBase + "schema.sql")//
			//	.addScript(staticBase + "insert.sql")//
				.build();
	}

	@Test
	public final void testGetExample() {
		ExampleDO example = service.getExampleById("TEST");
		//Assert.assertNotNull(example);
	}
	
	@AfterClass
	public static void shutdownDb(){
		db.shutdown();
	}

}
