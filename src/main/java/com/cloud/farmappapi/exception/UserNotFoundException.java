/**
 * 
 */
package com.cloud.farmappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kmahendr
 * 
 * The UserNotFoundException handle UserNotFound 
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * This class Displays message of exception
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
		
}
