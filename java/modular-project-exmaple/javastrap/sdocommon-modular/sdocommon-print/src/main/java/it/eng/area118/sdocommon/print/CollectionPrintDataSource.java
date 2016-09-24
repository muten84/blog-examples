/**
 * 
 */
package it.eng.area118.sdocommon.print;

import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Bifulco Luigi
 *
 */
public class CollectionPrintDataSource extends PrintDataSource<JRBeanCollectionDataSource> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.eng.area118.sdocommon.print.PrintDataSource#addDataSource(java.lang.String, java.lang.Object)
	 */

	public <E> void wrap(String key, Collection<E> dataSource) {
		addDataSource(key, new JRBeanCollectionDataSource(dataSource));
	}

	public <E> Collection<E> unwrap(String key) {
		JRBeanCollectionDataSource jrbean = getDataSource(key);
		return (Collection<E>) jrbean.getData();
	}
}
