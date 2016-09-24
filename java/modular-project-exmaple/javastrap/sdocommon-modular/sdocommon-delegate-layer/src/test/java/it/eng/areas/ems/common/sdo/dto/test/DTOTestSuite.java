/**
 * 
 */
package it.eng.areas.ems.common.sdo.dto.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.areas.ems.common.sdo.dto.DTOService;

/**
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DTOTestCtx.class)
public class DTOTestSuite {

	@Autowired
	private DTOService dtoService;

	@Test
	public final void testDTOObjectConversion() {
		ExampleDTO source = new ExampleDTO();
		source.setField1("field1");
		source.setField2(2);
		TargetDTO target = dtoService.convertObject(source, TargetDTO.class);
		Assert.assertTrue(target.getField1().equals(source.getField1()));

	}

}
