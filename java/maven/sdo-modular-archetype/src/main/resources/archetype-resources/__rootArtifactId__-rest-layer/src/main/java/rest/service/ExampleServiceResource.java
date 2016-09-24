#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.rest.service;

import java.io.FileNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import ${package}.delegate.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ${package}.rest.model.*;

/**
 * @author Bifulco Luigi
 *
 */
@Component
@Path("/exampleService")
@Api(value = "/exampleService", protocols = "http")
public class ExampleServiceResource {

	@Autowired
	private ExampleDelegate delegate;
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("test")
	@ApiOperation(value = "Test service operation", notes = "Test service operation")
	public String test() {
		if(delegate!=null)
			return "Got it!";
		return "Something goes wrong check your dependencies";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("requestOutput")
	@ApiOperation(value = "Echo service operation", notes = "Echo service operation")
	public ExampleOutput requestOutput(@ApiParam(value = "Input request example parameters") ExampleInput input) {
		ExampleOutput output = new ExampleOutput();
		output.setField1(input.getField1());
		output.setField2(input.getField2());
		return output;
	}

}
