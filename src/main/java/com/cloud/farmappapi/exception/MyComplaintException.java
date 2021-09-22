/**
 * 
 */
package com.cloud.farmappapi.exception;

/**
 * @author Ragini
 *
 */
public class MyComplaintException extends Exception {
		
		private static final long serialVersionUID = 1L;
		
		public MyComplaintException() {
			super();
		}
		
		public MyComplaintException(String message) {
			super(message);
		}
	}