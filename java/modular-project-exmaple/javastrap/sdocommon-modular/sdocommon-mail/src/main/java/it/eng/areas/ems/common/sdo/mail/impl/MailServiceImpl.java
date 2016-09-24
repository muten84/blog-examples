/**
 * 
 */
package it.eng.areas.ems.common.sdo.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import it.eng.areas.ems.common.sdo.mail.MailService;
import it.esel.parsley.lang.StringUtils;

/**
 * @author Bifulco Luigi
 *
 */
@Component
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SimpleMailMessage templateMessage;

	@Override
	public boolean sendMail(String dest, String text) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(dest);
		if (!StringUtils.isEmpty(text)) {
			msg.setText(text);
		}
		this.mailSender.send(msg);
		return true;
	}

	@Override
	public boolean sendMail(String[] dest, String text) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(dest);
		if (!StringUtils.isEmpty(text)) {
			msg.setText(text);
		}
		this.mailSender.send(msg);

		return true;
	}

	@Override
	public boolean sendMail(String dest, String text, String attachName, byte[] attachment) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(templateMessage.getFrom());
			helper.setTo(dest);
			helper.setSubject(templateMessage.getSubject());
			if (!StringUtils.isEmpty(text)) {
				helper.setText(text);
			}
			ByteArrayResource resource = new ByteArrayResource(attachment);
			helper.addAttachment(attachName, resource);

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
		return true;
	}
}
