/**
 * 
 */
package it.eng.areas.ems.common.sdo.service;

import it.eng.areas.ems.common.sdo.entity.*;

/**
 * @author Bifulco Luigi
 *
 */

public interface ExampleTransactionalService {

	/**
	 * 
	 * Get an entity of type ExampleDO by its Id
	 * 
	 * @param id
	 * @return
	 */
	ExampleDO getExampleById(String id);

}
