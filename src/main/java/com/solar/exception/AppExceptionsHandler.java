package com.solar.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{

	public ResponseEntity<Object> handleAnyExeption(Exception ex, WebRequest request){
		if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		return new ResponseEntity<>(
				ex,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
