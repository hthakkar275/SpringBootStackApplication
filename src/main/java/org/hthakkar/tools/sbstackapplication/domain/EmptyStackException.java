package org.hthakkar.tools.sbstackapplication.domain;

public class EmptyStackException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8946774365695595199L;

	public EmptyStackException(String message) {
		super(message);
	}
	
	public EmptyStackException() {
		super();
	}

}
