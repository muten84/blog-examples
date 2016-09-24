/**
 * 
 */
package it.eng.areas.ems.common.sdo.mail.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.eng.areas.ems.common.sdo.mail.MailService;

/**
 * @author Bifulco Luigi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SendMailTextCtx.class)
public class MailServiceTest {

	@Autowired
	private MailService mailService;

	@Test
	public final void testSendMail() {
		boolean res = mailService.sendMail("michele.molino@eng.it", "do not reply this is a test message");
		Assert.assertTrue(res);
	}
}
