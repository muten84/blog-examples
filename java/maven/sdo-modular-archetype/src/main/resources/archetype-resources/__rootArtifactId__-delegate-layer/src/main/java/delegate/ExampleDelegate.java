#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.delegate;

import ${package}.delegate.model.*;

public interface ExampleDelegate{
	
	public ExampleDTO getExampleById(String id);
}