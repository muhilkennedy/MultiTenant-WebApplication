package com.platform.base.exception;

/**
 * @author Muhil
 *
 */
public class GenericException extends Exception {

	public GenericException() {
		super();
	}
	
	public GenericException(Exception ex) {
		super(ex);
	}
	
}
