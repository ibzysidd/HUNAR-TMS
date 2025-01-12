package com.hunar.api.email;

import java.io.IOException;
import java.util.Map;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private Configuration configuration;
	
	@Value("${spring.mail.username}")
	private String senderEmail;
	

	public EmailResponse sendEmail(EmailRequest request, Map<String, Object> model) {
		EmailResponse response = new EmailResponse();
		MimeMessage message = sender.createMimeMessage();
		
 		try {
			MimeMessageHelper helper = new MimeMessageHelper(message);
			Template template = configuration.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject("Booking Confirmation.");
			helper.setFrom(senderEmail);
			sender.send(message);
			response.setMessage("mail send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	}

}
