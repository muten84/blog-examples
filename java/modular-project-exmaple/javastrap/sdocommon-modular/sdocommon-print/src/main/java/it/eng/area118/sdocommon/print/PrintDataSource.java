/**
 * 
 */
package it.eng.area118.sdocommon.print;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bifulco Luigi
 *
 */
public abstract class PrintDataSource<E> {

	protected Map<String, E> dataSourceCollection;

	public PrintDataSource() {
		dataSourceCollection = new HashMap<String, E>();
	}

	public Map<String, E> getDataSourceCollection() {
		return this.dataSourceCollection;
	}

	public void addDataSource(String key, E dataSource) {
		getDataSourceCollection().put(key, dataSource);
	}

	public E getDataSource(String key) {
		return getDataSourceCollection().get(key);

	}

}
