#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * @author Bifulco Luigi
 *
 */
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        register(RequestContextFilter.class);
        packages("${package}.rest");
        register(LoggingFeature.class);
        register(JacksonFeature.class);
    }
}
