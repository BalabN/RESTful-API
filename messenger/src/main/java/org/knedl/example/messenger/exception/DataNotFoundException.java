package org.knedl.example.messenger.exception;

public class DataNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = -51351255135134515L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
