package it.eng.areas.ems.common.sdo.mail.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import it.eng.areas.ems.common.sdo.mail.configuration.SendMailConfiguration;

/**
 * @author Bifulco Luigi
 *
 */

@Configuration
@PropertySources(//
		value = { //
				@PropertySource(ignoreResourceNotFound = false, //
						value = "classpath:it.eng.areas.ems.common.sdo.mail/cfg-mail.properties") //
		})
@Import(SendMailConfiguration.class)
public class SendMailTextCtx {

}
