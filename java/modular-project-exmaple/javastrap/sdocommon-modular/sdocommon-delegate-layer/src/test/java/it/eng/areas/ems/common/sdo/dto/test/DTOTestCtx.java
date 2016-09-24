/**
 * 
 */
package it.eng.areas.ems.common.sdo.dto.test;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import it.eng.areas.ems.common.sdo.dto.configuration.DTOConfiguration;

/**
 * @author Bifulco Luigi
 *
 */
@Import(DTOConfiguration.class)
@PropertySources(//
		value = { //
				@PropertySource(ignoreResourceNotFound = false, //
						value = "classpath:/it/eng/areas/ems/common/sdo/cfg-test.properties") //
		})
public class DTOTestCtx {

}
