package com.example.coursework070822.exception;

public class ConfirmOperationException extends RuntimeException{
    public ConfirmOperationException() {
        super();
    }

    public ConfirmOperationException(String message) {
        super(message);
    }

    public ConfirmOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
