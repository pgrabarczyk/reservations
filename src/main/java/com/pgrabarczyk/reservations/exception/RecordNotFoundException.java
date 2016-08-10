package com.pgrabarczyk.reservations.exception;

public class RecordNotFoundException extends Exception {
    private static final long serialVersionUID = -3296205038483779648L;

    public RecordNotFoundException(String message) {
	super(message);
    }
}
