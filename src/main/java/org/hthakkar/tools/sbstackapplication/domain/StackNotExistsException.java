package org.hthakkar.tools.sbstackapplication.domain;

public class StackNotExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8946774365695595199L;

	public StackNotExistsException(String message) {
		super(message);
	}
	
	public StackNotExistsException() {
		super();
	}

}