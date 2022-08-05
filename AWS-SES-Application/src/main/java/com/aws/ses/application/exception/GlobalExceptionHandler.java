package com.aws.ses.application.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.services.simpleemail.model.MessageRejectedException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//Handle specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), "E00500" );
		logger.error("Exception occured::"+exception);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	//Handle global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), "E00500" );
		logger.error("Exception occured::"+exception);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Handle global exception
		@ExceptionHandler(MessageRejectedException.class)
		public ResponseEntity<?> handleAWSException(MessageRejectedException exception){
			System.out.println("handleAWSException");
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), "E00500" );
			return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
}
