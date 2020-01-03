package hr.fer.zemris.opp.giger.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailSenderTest {

	@Mock
	private JavaMailSender javaMailSender;
	private EmailSender emailSender;

	@Before
	public void setUp() {
		emailSender = new EmailSender(javaMailSender);
		ReflectionTestUtils.setField(emailSender, "baseUrl", "http://baseurl");
		ReflectionTestUtils.setField(emailSender, "contextPath", "/api");
	}

	@Test
	public void testEmailSent() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("email");
		message.setSubject("Please verify this account");
		message.setText("To verify your registration click the following link " + emailSender.baseUrl + emailSender.contextPath + "/register/verification?token=1&username=token");
		emailSender.sendRegistrationConfirmationMessage("email", 1, "token");
		verify(javaMailSender).send(message);
	}
}
