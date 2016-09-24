#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.war;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@Import({ApplicationConfiguration.class})
@PropertySources(//
		value = { //
				@PropertySource(ignoreResourceNotFound = false, //
						value = "${confPath}/cfg-jpa.properties"), //
				@PropertySource(ignoreResourceNotFound = false, //
						value = "${confPath}/cfg-docservice.properties"), //
				@PropertySource(ignoreResourceNotFound = false, //
						value = "${confPath}/cfg-common.properties") })
public class WebAppConfiguration {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
		// conf.
		conf.setIgnoreUnresolvablePlaceholders(true);
		return conf;
	}
}
