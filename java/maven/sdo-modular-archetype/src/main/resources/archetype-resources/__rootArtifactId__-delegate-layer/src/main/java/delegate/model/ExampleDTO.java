#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.delegate.model;

import java.io.Serializable;

public class ExampleDTO implements Serializable { 

	private String id;
	
	private String col1;
	
	public ExampleDTO() {
	
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getCol1() {
		return this.col1;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setCol1(String col1) {
		this.col1 = col1;
	}
}