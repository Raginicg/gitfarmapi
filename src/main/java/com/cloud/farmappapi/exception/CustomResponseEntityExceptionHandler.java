/**
 * 
 */
package com.cloud.farmappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author kmahendr
 *
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler // used to handle the specific exceptions and sending the custom responses to the client.
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
			WebRequest request) {

		UserNotFoundExceptionResponse exceptionResponse = new UserNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleClientAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
		
		UserAlreadyExistExceptionResponse exceptionResponse =  new UserAlreadyExistExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleAdvertisementAlreadyExistException(AdvertisementAlreadyExistException ex, WebRequest request) {
		
		AdvertisementAlreadyExistExceptionResponse exceptionResponse =  new AdvertisementAlreadyExistExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleAdvertisementNotFoundException(AdvertisementNotFoundException ex,
			WebRequest request) {

		AdvertisementNotFoundExceptionResponse exceptionResponse = new AdvertisementNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleInvalidIdException(InvalidIdException ex,
			WebRequest request) {

		InvalidIdExceptionResponse exceptionResponse = new InvalidIdExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}	
	@ExceptionHandler
	public final ResponseEntity<Object> handleComplaintNotFoundException(ComplaintNotFoundException ex,
			WebRequest request){
		
		ComplaintNotFoundExceptionResponse exceptionResponse=new ComplaintNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleComplaintAlreadyExistException(ComplaintAlreadyExistException ex, WebRequest request){
		ComplaintAlreadyExistExceptionResponse exceptionResponse=new ComplaintAlreadyExistExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
