package com.enviro.assessment.gard001.seshagirisainni.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(value=NoEnvironmentalDataFoundException.class)
	public ResponseEntity<ApiError> handleNoDataFoundException(HttpServletRequest req) {
		ApiError error=new ApiError(400, "No Environmental Data found on: "+req.getRequestURI(), new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
}
