/**
 * 
 */
package it.eng.area118.sdocommon.print.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import it.eng.area118.sdocommon.print.IPrint;
import it.eng.area118.sdocommon.print.ReportData;
import it.eng.area118.sdocommon.print.excpetions.PrintReportException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Bifulco Luigi
 *
 */
public class PrintImpl implements IPrint {

	@Override
	public <E> OutputStream printToStream(ReportData<E> reportData, OutputStream os) throws PrintReportException {
		// if (!isJasperFile(reportData.getReportPath(), "jasper")) {
		// throw new PrintReportException("Input File is not a .jasper file");
		// }

		ByteArrayInputStream bais = null;

		// if outputStream is not null then it will be processed like a jasper
		// file output stream else
		// the jasper file stream will be retrieved from filesystem
		// finally it will be returned the exported stream.
		if (os != null) {

			bais = new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray());
		} else {
			try {
				bais = new ByteArrayInputStream(getBytesFromFile(new File(reportData.getReportSourcePath())));
			} catch (IOException e1) {
				throw new PrintReportException("Exception while getting bytes from File. Message cause: " + e1.getMessage());
			}
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			JasperCompileManager.compileReportToStream(bais, baos);
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		bais = new ByteArrayInputStream(baos.toByteArray());
		baos = new ByteArrayOutputStream();

		try {
			JasperFillManager.fillReportToStream(bais, baos, reportData.getReportParameters(), new JRBeanCollectionDataSource(reportData.getDatasource()));
		} catch (JRException e) {
			throw new PrintReportException("Exception while filling the report from InputStream. Message cause: " + e.getMessage());
		}

		bais = new ByteArrayInputStream(baos.toByteArray());
		baos = new ByteArrayOutputStream();

		try {
			JasperExportManager.exportReportToPdfStream(bais, baos);
		} catch (JRException e) {
			throw new PrintReportException("Exception while filling the report from ByteArrayInputStream. Message cause: " + e.getMessage());
		}

		//osHolder.setOs(baos);
		return baos;

	}

	protected byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// check the size of the file
		if (length > Integer.MAX_VALUE) {
			throw new IOException("File too large");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

}
