package com.core.exception;


public class StaffNotFoundException extends RuntimeException{

    public StaffNotFoundException(String message) {
        super(message);
    }

    public StaffNotFoundException(Throwable cause) {
        super(cause);
    }

    public StaffNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }
}
