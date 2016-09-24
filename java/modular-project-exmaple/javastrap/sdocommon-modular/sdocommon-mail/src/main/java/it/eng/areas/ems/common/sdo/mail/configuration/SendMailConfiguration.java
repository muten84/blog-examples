/**
 * 
 */
package it.eng.areas.ems.common.sdo.mail.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import it.eng.areas.ems.common.sdo.mail.MailService;
import it.eng.areas.ems.common.sdo.mail.impl.MailServiceImpl;

/**
 * @author Bifulco Luigi
 *
 */
@Configuration
public class SendMailConfiguration {

	@Value("${notifier.mail.sender.host}")
	private String mailSenderHost;

	@Value("${notifier.mail.sender.port}")
	private int mailSenderPort;

	@Value("${notifier.mail.sender.username}")
	private String username;

	@Value("${notifier.mail.sender.password}")
	private String password;

	@Value("${notifier.mail.sender.protocol}")
	private String protocol;

	@Value("${notifier.mail.sender.from}")
	private String from;

	@Value("${notifier.mail.sender.subject}")
	private String subject;

	@Value("${notifier.mail.java.enableMailAuth}")
	private boolean enableMailAuth;

	@Value("${notifier.mail.java.enableStarttls}")
	private boolean enableStarttls;

	@Value("${notifier.mail.java.enableSSL}")
	private boolean enableSSL;

	@Value("${notifier.mail.java.trustDomain}")
	private String trustDomain;

	@Value("${notifier.mail.java.connectiontimeout}")
	private int connectiontimeout;

	@Bean
	public MailService mailService() {
		return new MailServiceImpl();
	}

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(mailSenderHost);
		sender.setPort(mailSenderPort);
		sender.setUsername(username);
		sender.setPassword(password);
		// sender.setProtocol(protocol);
		sender.setDefaultEncoding("UTF-8");
		sender.setJavaMailProperties(javaMailProperties());
		return sender;
	}

	@Bean
	public SimpleMailMessage templateMessage() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setSubject(subject);
		return msg;
	}

	/**
	 * @return
	 */
	private Properties javaMailProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", enableMailAuth); // true
		props.put("mail.smtp.starttls.enable", enableStarttls);// true
		props.put("mail.smtp.quitwait", false);
		// props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", false);
		props.put("mail.debug", true);
		props.put("mail.smtp.ssl.enable", enableSSL);// true
		props.put("mail.smtp.ssl.trust", trustDomain);// *
		props.put("mail.smtp.connectiontimeout", connectiontimeout);

		return props;
	}

}
