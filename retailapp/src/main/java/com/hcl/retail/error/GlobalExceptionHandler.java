package com.hcl.retail.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.retail.controller.BuyController;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value=Exception.class)
	protected ResponseEntity<Object> exceptionHandler(Exception exception) {
		Errors errors = new Errors();			
		errors.setErrorMsg("Exe Error");
		logger.error("Exception Occured");
        return new ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
}
