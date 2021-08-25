package com.bkc.pathfinder.exception;

public class PFException extends RuntimeException {
	
	public PFException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public PFException(String exMessage) {
		super(exMessage);
	}

}
