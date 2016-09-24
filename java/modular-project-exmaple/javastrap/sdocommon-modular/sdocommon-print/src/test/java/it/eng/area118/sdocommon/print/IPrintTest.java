package it.eng.area118.sdocommon.print;
/**
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.area118.sdocommon.print.excpetions.PrintReportException;

/**
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PrintServiceConfiguration.class)
public class IPrintTest {

	@Autowired
	private IPrint printService;

	@Test
	public final void testPrintToOutputStream() throws PrintReportException, IOException {
		ReportData<PrintTestDataSource> reportData = new ReportData<>();
		reportData.setDatasource(prepareData());
		reportData.setReportSourcePath("src/test/resources/it.eng.area118.sdocommon.print/report.jrxml");
		ByteArrayOutputStream stream = (ByteArrayOutputStream) printService.printToStream(reportData, null);
		FileUtils.writeByteArrayToFile(new File("src/test/resources/it.eng.area118.sdocommon.print/out.pdf"), stream.toByteArray());
		File f = new File("src/test/resources/it.eng.area118.sdocommon.print/out.pdf");
		Assert.assertTrue(FileUtils.waitFor(f, 5));
		FileUtils.forceDelete(f);
	}

	protected List<PrintTestDataSource> prepareData() {
		List<PrintTestDataSource> coll = new ArrayList<>();
		PrintTestDataSource dataSource = new PrintTestDataSource();
		dataSource.setField1("FIELD1");
		dataSource.setField2("FIELD2");
		coll.add(dataSource);
		return coll;
	}
}
