package com.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e)
	{
		log.error("GlobalExceptionHandler :: handleException ::",e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e)
	{
		log.error("GlobalExceptionHandler :: handleResourceNotFoundException ::",e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e)
	{
		log.error("GlobalExceptionHandler :: handleNullPointerException ::",e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e)
	{
		return new ResponseEntity<>(e.getErrors(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExitDataException.class)
	public ResponseEntity<?> handleExitDataException(ExitDataException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
