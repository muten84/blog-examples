/**
 * 
 */
package it.eng.area118.sdocommon.print;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.eng.area118.sdocommon.print.impl.PrintImpl;

/**
 * @author Bifulco Luigi
 *
 */
@Configuration
public class PrintServiceConfiguration {

	@Bean
	public IPrint getPrintService() {
		return new PrintImpl();
	}
}
