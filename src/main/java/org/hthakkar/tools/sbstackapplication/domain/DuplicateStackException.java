package org.hthakkar.tools.sbstackapplication.domain;

public class DuplicateStackException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8946774365695595199L;

	public DuplicateStackException(String message) {
		super(message);
	}
	
	public DuplicateStackException() {
		super();
	}

}