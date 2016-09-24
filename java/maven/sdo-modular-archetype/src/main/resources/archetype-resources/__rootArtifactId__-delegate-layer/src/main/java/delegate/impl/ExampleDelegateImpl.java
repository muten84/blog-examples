#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.delegate.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ${package}.delegate.*;
import it.eng.areas.ems.common.sdo.dto.DTOService;
import ${package}.service.ExampleTransactionalService;
import ${package}.entity.*;
import ${package}.delegate.model.*;


public class ExampleDelegateImpl implements ExampleDelegate {
	
	@Autowired
	private DTOService dtoService;
	
	@Autowired
	private ExampleTransactionalService service;		
	
	@Override
	public ExampleDTO getExampleById(String id){
		ExampleDO exampleDO = service.getExampleById(id);
		return dtoService.convertObject(exampleDO, ExampleDTO.class);
	}
	
}