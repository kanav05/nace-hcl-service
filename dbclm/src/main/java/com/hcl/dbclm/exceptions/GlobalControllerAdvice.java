package com.hcl.dbclm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
	
	@ExceptionHandler(value = NaceResourceNotFoundException.class)
	   public ResponseEntity<Object> exception(NaceResourceNotFoundException exception) {
	      return new ResponseEntity<>("Nace code not found", HttpStatus.NOT_FOUND);
	   }

}
