package com.aws.ses.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ses.application.serivces.MailService;

@RestController
public class MailController 
{
	@Autowired
	private MailService mailService;

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@GetMapping("/sendEmail")
	public ResponseEntity<Object> sendEmail(@RequestParam String toEmail,
							@RequestParam String fromEmail,
							@RequestParam String subject,
							@RequestParam String body) 
	{
		logger.info(" Request received to /sendEmail path process starts");
		mailService.sendMessage(toEmail, fromEmail, subject, body);
		logger.info(" Request received to /sendEmail path process end");
		return new ResponseEntity<Object>("Mail sent successfully", HttpStatus.OK);
	}
	
		
	@GetMapping("/sendHTMLEmail")
	public ResponseEntity<Object> sendHTMLEmail(@RequestParam String toEmail,
							@RequestParam String fromEmail,
							@RequestParam String subject,
							@RequestParam String body) 
	{
		logger.info(" Request received to /sendHTMLEmail path process start");
		String res = mailService.sendMail(toEmail, fromEmail, subject, body);
		logger.info(" Request received to /sendHTMLEmail path process end");
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	
}
