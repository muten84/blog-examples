#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ${package}.entity.ExampleDO;
import ${package}.service.ExampleTransactionalService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExamplePersistenceTestCtx.class)
public class ExamplePersistenceServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static EmbeddedDatabase db;

	private final static String BASE_SQL_PATH = "classpath:${packageInPathFormat}/test-context/common/sql/";

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

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
				// .addScript(staticBase + "insert.sql")//
				.build();
	}

	@Test
	@Rollback
	public final void testGetExampleById() {
		executeSqlScript(BASE_SQL_PATH + "insert.sql", false);
		ExampleDO example = service.getExampleById("TEST");
		Assert.assertTrue(example != null);
		Assert.assertTrue(example.getCol1().equals("TEST"));
	}
	
	@AfterClass
	public static void shutdownDb(){
		db.shutdown();
	}

}
