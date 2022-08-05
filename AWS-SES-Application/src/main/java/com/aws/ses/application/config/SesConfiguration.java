package com.aws.ses.application.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class SesConfiguration {

	@Value("${aws.access_key_id}")
	private String awsId;

	@Value("${aws.secret_access_key}")
	private String awsKey;

	@Value("${aws.region}")
	private String region;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SesConfiguration.class);
	
	@Bean
	public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) 
	{
		logger.info("SesConfiguration :: mailSender() loaded");
		return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
	}
	
	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() 
	{
		logger.info("SesConfiguration :: SES Configure loaded");
		BasicAWSCredentials credentials = new BasicAWSCredentials(awsId, awsKey);
		return AmazonSimpleEmailServiceClientBuilder
				.standard()
				.withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}
	
	/*
	 * @Bean public AmazonSimpleEmailService amazonSimpleEmailService1() {
	 * logger.info("SesConfiguration :: SES Configure loaded"); return
	 * AmazonSimpleEmailServiceClientBuilder .standard()
	 * .withRegion(Regions.fromName(region)) .build(); }
	 */

}
