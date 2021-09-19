package com.cloud.farmappapi.exception;

/**
 * 
 * @author Ragini
 *
 *ComplaintNotFoundExceptionResponse is response with a login name
 */
public class ComplaintNotFoundExceptionResponse extends RuntimeException {

	private String loginName;

	public ComplaintNotFoundExceptionResponse(String loginName) {
		super();
		this.loginName = loginName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
