#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.rest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bifulco Luigi
 *
 */
@Configuration
@ComponentScan(basePackages = "${package}.rest")
public class RestLayerConfiguration {

}
