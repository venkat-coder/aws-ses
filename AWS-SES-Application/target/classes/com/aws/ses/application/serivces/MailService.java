package com.aws.ses.application.serivces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class MailService 
{
	@Autowired
	private MailSender mailSender;
	
	@Value("${CONFIGSET}")
	private String CONFIGSET;
	
	@Autowired
	private AmazonSimpleEmailService amazonSimpleEmailService;
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	public void sendMessage(String toEmail, String fromEmail, String subject, String body) 
	{
		logger.info("MailService :: sendMessage - start");
		
		logger.info("MailService :: toEmail::"+toEmail);
		logger.info("MailService :: fromEmail::"+fromEmail);
		logger.info("MailService :: subject::"+subject);
		logger.info("MailService :: body::"+body);
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromEmail);
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setCc(toEmail);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		logger.info("MailController :: simpleMailMessage is ready::"+simpleMailMessage);
		
		mailSender.send(simpleMailMessage);
		logger.info("MailService :: sendMessage - end");
	}
	
	public String sendMail(String toEmail, String fromEmail, String subject, String body) 
	{
		logger.info("MailService :: sendMail - starts");
			SendEmailRequest request = new SendEmailRequest()
		
		          .withDestination(
		              new Destination().withToAddresses(toEmail))
		          .withMessage(new Message()
		              .withBody(new Body()
		                  .withHtml(new Content()
		                      .withCharset("UTF-8").withData(body))
		                  .withText(new Content()
		                      .withCharset("UTF-8").withData(body)))
		              .withSubject(new Content()
		                  .withCharset("UTF-8").withData(subject)))
		          .withSource(fromEmail)
		          .withConfigurationSetName(CONFIGSET);
			
		logger.debug("MailService :: sendMail request object::"+request);
		amazonSimpleEmailService.sendEmail(request);
		logger.info("MailService :: sendMail - end");
		
		return "Mail sent successfully";
	}
}