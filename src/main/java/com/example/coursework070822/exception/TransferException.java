package com.example.coursework070822.exception;

public class TransferException extends RuntimeException{
    public TransferException() {
        super();
    }

    public TransferException(String message) {
        super(message);
    }

    public TransferException(String message, Throwable cause) {
        super(message, cause);
    }
}
