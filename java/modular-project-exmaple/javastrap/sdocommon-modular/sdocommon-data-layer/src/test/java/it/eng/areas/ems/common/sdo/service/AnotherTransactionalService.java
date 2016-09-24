/**
 * 
 */
package it.eng.areas.ems.common.sdo.service;

import it.eng.areas.ems.common.sdo.entity.ExampleDO;

/**
 * @author Bifulco Luigi
 *
 */
public interface AnotherTransactionalService {

	ExampleDO saveOrUpdateExample(ExampleDO example);
	
	ExampleDO update(ExampleDO example);

}
