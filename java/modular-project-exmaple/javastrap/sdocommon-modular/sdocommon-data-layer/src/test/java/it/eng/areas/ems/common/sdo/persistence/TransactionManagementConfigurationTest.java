/**
 * 
 */
package it.eng.areas.ems.common.sdo.persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.areas.ems.common.sdo.entity.ExampleDO;
import it.eng.areas.ems.common.sdo.service.AnotherTransactionalService;
import it.eng.areas.ems.common.sdo.service.ExampleTransactionalService;

/**
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestCtx.class)
public class TransactionManagementConfigurationTest extends AbstractTransactionalJUnit4SpringContextTests {

	private final static String BASE_SQL_PATH = "classpath:/it/eng/areas/ems/common/sdo/test-context/common/sql/";

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	private ExampleTransactionalService service;

	@Autowired
	private AnotherTransactionalService anotherService;

	private static EmbeddedDatabase db;

	@BeforeClass
	public static void createCommonDb() {
		String staticBase = "it/eng/areas/ems/common/sdo/test-context/common/sql/";
		String dynBase = "it/eng/areas/ems/common/sdo/test-context/common/sql/";
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setName("testdb");
		db = builder.setType(EmbeddedDatabaseType.HSQL) // .H2
														// or
														// .DERBY
				.setName("testdb")//
				.addScript(staticBase + "schema.sql")//
				// .addScript(staticBase + "insert.sql")//
				.build();
	}

	@Before

	public final void prepareTestData() {
		// executeSqlScript(BASE_SQL_PATH + "insert.sql", false);
	}

	@After
	public final void cleanTestData() {
		// executeSqlScript(BASE_SQL_PATH + "delete.sql", false);
	}

	@Test
	@Commit
	public final void testGetExampleById() {
		executeSqlScript(BASE_SQL_PATH + "insert.sql", true);
		ExampleDO example = service.getExampleById("TEST");
		Assert.assertTrue(example != null);
		//Assert.assertTrue(example.getCol1().equals("TEST"));
		executeSqlScript(BASE_SQL_PATH + "delete.sql", false);
	}

	@Test
	@Commit
	public final void testUpdateExample() throws InterruptedException {
		executeSqlScript(BASE_SQL_PATH + "insert.sql", true);
		int count = countRowsInTable("EXAMPLE");
		Assert.assertTrue(count > 0);
		ExampleDO example = service.getExampleById("TEST");
		Assert.assertTrue(example != null);
		example.setCol1("UPDATE");
		example = anotherService.update(example);
		Assert.assertTrue(example != null);
		Assert.assertTrue(example.getCol1().equals("UPDATE"));
	}

	@AfterClass
	public static void shutdownDb() {
		db.shutdown();
	}

}
