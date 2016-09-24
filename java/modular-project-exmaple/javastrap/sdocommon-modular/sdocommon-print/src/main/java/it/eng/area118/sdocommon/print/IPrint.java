/**
 * 
 */
package it.eng.area118.sdocommon.print;

import java.io.OutputStream;

import it.eng.area118.sdocommon.print.excpetions.PrintReportException;

/**
 * @author Bifulco Luigi
 *
 */
public interface IPrint {

	/**
	 * @param reportData
	 * @param os
	 * @throws PrintReportException
	 */
	<E> OutputStream printToStream(ReportData<E> reportData, OutputStream os) throws PrintReportException;

}
