#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.test;

import ${package}.entity.*;

public class ExampleDelegateTestHelper{
	
	public static ExampleDO getData(String id){
		ExampleDO example =  new ExampleDO();
		example.setId(id);
		return example;
	}
}