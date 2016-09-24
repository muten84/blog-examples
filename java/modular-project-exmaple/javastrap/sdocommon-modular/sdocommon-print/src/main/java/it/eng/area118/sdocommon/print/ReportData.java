/**
 * 
 */
package it.eng.area118.sdocommon.print;

import java.util.List;
import java.util.Map;

/**
 * @author Bifulco Luigi
 *
 */
public class ReportData<E> {

	protected String reportSourcePath; // il path del report da compilare utilizzato dalla printToStream

	protected List<E> datasource; // il dataSource

	protected Map<String, Object> reportParameters; // parametri utilizzabili dal report e dal modulo che cmpila il
													// report

	private String reportDestPath; // utilizzato dalla printToStream

	private String fileName;// il nome del file che verra' utilizzato nel caso in cui si vuole utilizzare la printToFile

	private String basePath;// il path base dove sono contenuti i report utilizzato dalla printToFile e dalla
							// printToStream

	public ReportData() {

	}

	public String getBasePath() {
		return basePath;
	}

	public List<E> getDatasource() {
		return datasource;
	}

	public String getFileName() {
		return fileName;
	}

	public String getReportDestPath() {
		return reportDestPath;
	}

	public Map<String, Object> getReportParameters() {
		return reportParameters;
	}

	public String getReportSourcePath() {
		return reportSourcePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setDatasource(List<E> datasource) {
		this.datasource = datasource;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setReportDestPath(String reportDestPath) {
		this.reportDestPath = reportDestPath;
	}

	public void setReportParameters(Map<String, Object> reportParameters) {
		this.reportParameters = reportParameters;
	}

	public void setReportSourcePath(String reportPath) {
		this.reportSourcePath = reportPath;
	}

}
